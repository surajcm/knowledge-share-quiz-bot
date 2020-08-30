package com.knowledge.share.quiz.mapper;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.slack.api.model.Attachment;
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

@Component
public class QuizMapper {
    private static final String LET_S_DO_THIS = "Ok. Let's do this :thinking_face:";
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

    public List<LayoutBlock> quizBlocks(final Quiz quiz) {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(section(s -> s.text(markdownText(LET_S_DO_THIS))));
        blocks.add(section(s -> s.text(markdownText(quiz.getQuestion()))));
        return blocks;
    }

    public LayoutBlock answers(final Long id) {
        return ActionsBlock.builder().elements(buttons(id)).build();
    }

    public LayoutBlock askAgain() {
        return ActionsBlock.builder().elements(askButtons()).build();
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

    private List<LayoutBlock> attachmentBlock(final Quiz quiz) {
        List<LayoutBlock> blocks = new ArrayList<>();
        String message = WHITE_CIRCLE + quiz.getOption1() +
                "\n " + WHITE_CIRCLE + quiz.getOption2() +
                "\n " + WHITE_CIRCLE + quiz.getOption3() +
                "\n " + WHITE_CIRCLE + quiz.getOption4();
        blocks.add(section(s -> s.text(markdownText(message))));
        blocks.add(answers(quiz.getId()));
        return blocks;
    }

    public List<Attachment> attachments(final Quiz quiz) {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(Attachment.builder()
                .blocks(attachmentBlock(quiz))
                .build());
        return attachments;
    }
}
