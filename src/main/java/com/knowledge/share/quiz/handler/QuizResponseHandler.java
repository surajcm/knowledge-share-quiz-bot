package com.knowledge.share.quiz.handler;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.knowledge.share.quiz.mapper.QuizMapper;
import com.knowledge.share.quiz.service.QuizService;
import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.handler.builtin.BlockActionHandler;
import com.slack.api.bolt.request.builtin.BlockActionRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.Attachment;
import com.slack.api.model.block.LayoutBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class QuizResponseHandler implements BlockActionHandler {
    private static final Logger logger = LogManager.getLogger(QuizResponseHandler.class);
    private static final String WHITE_CHECK_MARK = ":white_check_mark: ";
    private static final String RED_CIRCLE = ":red_circle: ";
    private static final String WHITE_CIRCLE = ":white_circle: ";

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizMapper quizMapper;

    @Override
    public Response apply(final BlockActionRequest req,
                          final ActionContext ctx)
            throws IOException, SlackApiException {
        String actionVal = req.getPayload().getActions().get(0).getValue();
        logger.info("actionVal is {}", actionVal);
        String selectedAnswer = req.getPayload().getActions().get(0).getText().getText();
        logger.info("selectedAnswer is {}", selectedAnswer);
        String user = ctx.getRequestUserId();
        logger.info("user is {}", user);
        Quiz quiz = quizService.getQuizFromId(actionVal);
        boolean status = selectedAnswer.equalsIgnoreCase(quiz.getAnswer());
        String updatedOptions = updateOptionsWithCorrectSelection(quiz, selectedAnswer);
        try {
            ctx.respond(res -> res
                    .deleteOriginal(true)
                    .responseType("in_channel")
                    .blocks(blocks(quiz, user, status, updatedOptions))
                    .attachments(attachments()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ctx.ack();
    }

    private List<Attachment> attachments() {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(askAgainAttachment());
        return attachments;
    }

    private Attachment askAgainAttachment() {
        Attachment attachment = new Attachment();
        attachment.setBlocks(attachmentBlock());
        return attachment;
    }

    private List<LayoutBlock> blocks(final Quiz quiz,
                                     final String user,
                                     final boolean status,
                                     final String updatedOptions) {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(quizMapper.questionBlock(quiz.getQuestion()));
        blocks.add(quizMapper.optionResultsBlock(updatedOptions));
        blocks.add(quizMapper.messageBlock(quiz.getMessage()));
        blocks.add(quizMapper.divider());
        blocks.add(quizMapper.statusMessage(user, status));
        return blocks;
    }

    private String updateOptionsWithCorrectSelection(final Quiz quiz,
                                                     final String selectedAnswer) {
        List<String> optionsAsList = new LinkedList<>();
        optionsAsList.add(quiz.getOption1());
        optionsAsList.add(quiz.getOption2());
        optionsAsList.add(quiz.getOption3());
        optionsAsList.add(quiz.getOption4());
        return generateFormattedMessage(optionsAsList,
                        quiz.getAnswer(), selectedAnswer);
    }

    private String generateFormattedMessage(final List<String> optionsAsList,
                                            final String answer,
                                            final String selectedAnswer) {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (String currentOption : optionsAsList) {
            if (answer.equals(String.valueOf(count))) {
                builder.append(WHITE_CHECK_MARK)
                        .append('*').append(currentOption).append('*');
            } else if (selectedAnswer.equals(String.valueOf(count))) {
                builder.append(RED_CIRCLE)
                        .append('~').append(currentOption).append('~');
            } else {
                builder.append(WHITE_CIRCLE).append(currentOption);
            }
            builder.append("\n");
            count++;
        }
        return builder.toString();
    }

    private List<LayoutBlock> attachmentBlock() {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(quizMapper.askAgain());
        return blocks;
    }

}
