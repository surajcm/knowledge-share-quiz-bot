package com.knowledge.share.quiz.mapper;

import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizMapper {

    private static final String WHITE_CHECK_MARK = ":white_check_mark: ";
    private static final String RED_CIRCLE = ":red_circle: ";
    private static final String WHITE_CIRCLE = ":white_circle: ";

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
        StringBuilder builder = new StringBuilder();
        if (answer.equals("A")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option1).append('*');
        } else if (selectedAnswer.equals("1")) {
            builder.append(RED_CIRCLE).append('~').append(option1).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option1);
        }
        builder.append("\n");


        if (answer.equals("B")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option2).append('*');
        } else if (selectedAnswer.equals("2")) {
            builder.append(RED_CIRCLE).append('~').append(option2).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option2);
        }
        builder.append("\n");
        if (answer.equals("C")) {
            builder.append(WHITE_CHECK_MARK).append('*').append(option3).append('*');
        } else if (selectedAnswer.equals("3")) {
            builder.append(RED_CIRCLE).append('~').append(option3).append('~');
        } else {
            builder.append(WHITE_CIRCLE).append(option3);
        }
        builder.append("\n");

        if (answer.equals("D")) {
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

    public LayoutBlock answers(final Long id) {
        ActionsBlock.ActionsBlockBuilder builder = ActionsBlock.builder();
        return builder.elements(buttons(id)).build();
    }

    private List<BlockElement> buttons(final Long id) {
        List<BlockElement> blockElements = new ArrayList<>();
        blockElements.add(firstOption(id));
        blockElements.add(secondOption(id));
        blockElements.add(thirdOption(id));
        blockElements.add(forthOption(id));
        return blockElements;
    }

    private BlockElement firstOption(final Long id) {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value(String.valueOf(id)).text(firstButton()).actionId("Answer-A");
        return builder.build();
    }

    private PlainTextObject firstButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("1").emoji(true);
        return builder.build();
    }

    private BlockElement secondOption(final Long id) {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value(String.valueOf(id)).text(secondButton()).actionId("Answer-B");
        return builder.build();
    }

    private PlainTextObject secondButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("2").emoji(true);
        return builder.build();
    }

    private BlockElement thirdOption(final Long id) {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value(String.valueOf(id)).text(thirdButton()).actionId("Answer-C");
        return builder.build();
    }

    private PlainTextObject thirdButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("3").emoji(true);
        return builder.build();
    }

    private BlockElement forthOption(final Long id) {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value(String.valueOf(id)).text(forthButton()).actionId("Answer-D");
        return builder.build();
    }

    private PlainTextObject forthButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("4").emoji(true);
        return builder.build();
    }

    public LayoutBlock messageBlock(final String message) {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(message(message)).build();
    }

    private TextObject message(final String message) {
        MarkdownTextObject.MarkdownTextObjectBuilder builder = MarkdownTextObject.builder();
        builder.text(message);
        return builder.build();
    }

}
