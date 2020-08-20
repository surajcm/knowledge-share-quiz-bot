package com.knowledge.share.quiz.mapper;

import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.element.BlockElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
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
        return SectionBlock
                .builder()
                .text(markdownText(question))
                .build();
    }

    public LayoutBlock optionBlock(final String op1, final String op2,
                                   final String op3, final String op4) {
        String message = WHITE_CIRCLE + op1 +
                "\n " + WHITE_CIRCLE + op2 +
                "\n " + WHITE_CIRCLE + op3 +
                "\n " + WHITE_CIRCLE + op4;
        return SectionBlock
                .builder()
                .text(markdownText(message))
                .build();
    }

    public LayoutBlock optionResultsBlock(final String message) {
        return SectionBlock
                .builder()
                .text(markdownText(message))
                .build();
    }

    public LayoutBlock answers(final Long id) {
        ActionsBlock.ActionsBlockBuilder builder = ActionsBlock.builder();
        return builder.elements(buttons(id)).build();
    }

    public LayoutBlock messageBlock(final String message) {
        return SectionBlock
                .builder()
                .text(markdownText(message))
                .build();
    }

    public LayoutBlock statusMessage(final String user,
                                     final boolean status) {
        return SectionBlock
                .builder()
                .text(markdownText(buildStatusMessage(user, status)))
                .build();
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

    public LayoutBlock okLetsDoThis() {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(markdownText("Ok. Let's do this :thinking_face:"))
                .build();
    }
}
