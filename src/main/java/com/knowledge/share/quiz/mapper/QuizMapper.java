package com.knowledge.share.quiz.mapper;

import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

@Component
public class QuizMapper {

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
}
