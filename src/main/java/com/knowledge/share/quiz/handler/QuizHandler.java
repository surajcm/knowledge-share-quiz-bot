package com.knowledge.share.quiz.handler;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.knowledge.share.quiz.mapper.QuizMapper;
import com.knowledge.share.quiz.service.QuizService;
import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.Attachment;
import com.slack.api.model.block.LayoutBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuizHandler implements SlashCommandHandler {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizMapper quizMapper;

    @Override
    public Response apply(final SlashCommandRequest req, final SlashCommandContext ctx)
            throws IOException, SlackApiException {
        Quiz quiz = quizService.generateRandomQuiz();
        SlashCommandResponse response = SlashCommandResponse.builder()
                .blocks(blocks(quiz))
                .attachments(attachments(quiz))
                .responseType("in_channel")
                .build();
        return ctx.ack(response);
    }

    private List<LayoutBlock> blocks(final Quiz quiz) {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(quizMapper.okLetsDoThis());
        blocks.add(quizMapper.questionBlock(quiz.getQuestion()));
        return blocks;
    }

    private List<LayoutBlock> attachmentBlock(final Quiz quiz) {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(quizMapper.optionBlock(quiz.getOption1(),
                quiz.getOption2(),
                quiz.getOption3(),
                quiz.getOption4()));
        blocks.add(quizMapper.answers(quiz.getId()));
        return blocks;
    }

    private Attachment optionsAsAttachments(final Quiz quiz) {
        Attachment attachment = new Attachment();
        attachment.setBlocks(attachmentBlock(quiz));
        return attachment;
    }

    private List<Attachment> attachments(final Quiz quiz) {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(optionsAsAttachments(quiz));
        return attachments;
    }
}