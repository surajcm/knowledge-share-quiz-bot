package com.knowledge.share.quiz.mapper;

import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.model.block.element.BlockElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

@Component
public class QuizMapper {
    private static final Logger logger = LogManager.getLogger(QuizMapper.class);
    private static final String WHITE_CHECK_MARK = ":white_check_mark: ";
    private static final String RED_CIRCLE = ":red_circle: ";
    private static final String WHITE_CIRCLE = ":white_circle: ";

    private List<BlockElement> buttons(final Long id) {
        return asElements(
                button(b -> b.actionId("Answer-A")
                        .text(plainText(pt -> pt.text("1"))).value(String.valueOf(id))),
                button(b -> b.actionId("Answer-B")
                        .text(plainText(pt -> pt.text("2"))).value(String.valueOf(id))),
                button(b -> b.actionId("Answer-C")
                        .text(plainText(pt -> pt.text("3"))).value(String.valueOf(id))),
                button(b -> b.actionId("Answer-D")
                        .text(plainText(pt -> pt.text("4"))).value(String.valueOf(id)))
        );
    }

    public LayoutBlock questionBlock(final String question) {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(question(question)).build();
    }

    private TextObject question(final String question) {
        MarkdownTextObject.MarkdownTextObjectBuilder builder = MarkdownTextObject.builder();
        builder.text(question);
        return builder.build();
    }

    public LayoutBlock optionBlock(final String op1, final String op2,
                                   final String op3, final String op4) {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(options(op1, op2, op3, op4)).build();
    }

    private TextObject options(final String op1, final String op2,
                               final String op3, final String op4) {
        MarkdownTextObject.MarkdownTextObjectBuilder builder = MarkdownTextObject.builder();
        builder.text(WHITE_CIRCLE + op1 +
                "\n :white_circle: " + op2 +
                "\n:white_circle: " + op3 +
                "\n:white_circle: " + op4);
        return builder.build();
    }

    public LayoutBlock optionResultsBlock(final String option1,
                                          final String option2,
                                          final String option3,
                                          final String option4,
                                          final String answer,
                                          final String selectedAnswer) {
        List<String> optionsAsList = new LinkedList<>();
        optionsAsList.add(option1);
        optionsAsList.add(option2);
        optionsAsList.add(option3);
        optionsAsList.add(option4);
        String message = generateFormattedMessage(optionsAsList, answer, selectedAnswer);
        logger.info(message);
        StringBuilder builder = new StringBuilder();
        if (answer.equals("1")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option1).append('*');
        } else if (selectedAnswer.equals("1")) {
            builder.append(RED_CIRCLE).append('~').append(option1).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option1);
        }
        builder.append("\n");

        if (answer.equals("2")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option2).append('*');
        } else if (selectedAnswer.equals("2")) {
            builder.append(RED_CIRCLE).append('~').append(option2).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option2);
        }
        builder.append("\n");
        if (answer.equals("3")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option3).append('*');
        } else if (selectedAnswer.equals("3")) {
            builder.append(RED_CIRCLE).append('~').append(option3).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option3);
        }
        builder.append("\n");

        if (answer.equals("4")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option4).append('*');
        } else if (selectedAnswer.equals("4")) {
            builder.append(RED_CIRCLE).append('~').append(option4).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option4);
        }
        builder.append("\n");

        MarkdownTextObject.MarkdownTextObjectBuilder
                textObjectBuilder = MarkdownTextObject.builder();
        textObjectBuilder.text(builder.toString());
        SectionBlock.SectionBlockBuilder sectionBlockBuilder = SectionBlock.builder();
        return sectionBlockBuilder.text(textObjectBuilder.build()).build();
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

    public LayoutBlock answers(final Long id) {
        ActionsBlock.ActionsBlockBuilder builder = ActionsBlock.builder();
        return builder.elements(buttons(id)).build();
    }

    public LayoutBlock messageBlock(final String message) {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(message(message)).build();
    }

    public LayoutBlock statusMessage(final String user,
                                    final boolean status) {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(statusMessageDetail(user, status)).build();
    }

    private TextObject message(final String message) {
        MarkdownTextObject.MarkdownTextObjectBuilder
                objectBuilder = MarkdownTextObject.builder();
        StringBuilder builder = new StringBuilder();
        builder.append(message);
        objectBuilder.text(builder.toString());
        return objectBuilder.build();
    }

    private TextObject statusMessageDetail(final String user,
                               final boolean status) {
        MarkdownTextObject.MarkdownTextObjectBuilder
                objectBuilder = MarkdownTextObject.builder();
        StringBuilder builder = new StringBuilder();
        builder.append(buildStatusMessage(user, status));
        objectBuilder.text(builder.toString());
        return objectBuilder.build();
    }

    private String buildStatusMessage(final String user, final boolean status) {
        if (status) {
            return "\n\nNice work <@" + user + "> That's correct. :thumbsup:";
        } else {
            return "\n\nSorry, <@" + user + "> that's not right." +
                    " Try again! :slightly_smiling_face:";
        }
    }

    public LayoutBlock askAgain() {
        ActionsBlock.ActionsBlockBuilder builder = ActionsBlock.builder();
        return builder.elements(askButtons()).build();
    }

    private List<BlockElement> askButtons() {
        return asElements(
                button(b -> b.actionId("Next-A")
                        .text(plainText(pt -> pt.text("YES"))).value("YES")
                        .style("primary")),
                button(b -> b.actionId("Next-B")
                        .text(plainText(pt -> pt.text("NO"))).value("NO"))
        );
    }

    public LayoutBlock divider() {
        return DividerBlock.builder().build();
    }

}
