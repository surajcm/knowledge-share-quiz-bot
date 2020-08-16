package com.knowledge.share.challenge.mapper;

import com.slack.api.model.Field;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.slack.api.model.Attachments.asFields;
import static com.slack.api.model.Attachments.field;
import static com.slack.api.model.block.Blocks.actions;
import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;

@Component
public class ChallengeMapper {

    public List<LayoutBlock> welcomeBlocks(final String user) {
        return asBlocks(
                section(f -> f.text(
                        markdownText("Challenge begin in 1 minute !! \n Created by <@"
                                + user + "> "))));
    }

    public List<Field> createChallengeFields() {
        return asFields(
                field(f -> f.title("About").value("AWS").valueShortEnough(true)),
                field(f -> f.title("15 Questions").value("30 Seconds Each")
                        .valueShortEnough(true))
        );
    }

    public List<LayoutBlock> joinButtons() {
        return asBlocks(actions(join()));
    }

    private List<BlockElement> join() {
        return asElements(
                button(b -> b.actionId("Join")
                        .text(plainText(pt -> pt.text("Join Challenge"))).value("join")
                        .style("primary"))
        );
    }
}
