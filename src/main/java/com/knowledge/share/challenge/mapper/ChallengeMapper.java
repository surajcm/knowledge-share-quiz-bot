package com.knowledge.share.challenge.mapper;

import com.slack.api.model.Field;
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ImageElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public List<LayoutBlock> welcomeBlocks() {
        return asBlocks(
                section(f -> f.text(
                        markdownText("Challenge begin in 1 minute !! "))));
    }

    public List<Field> createChallengeFields() {
        return asFields(
                field(f -> f.title("About").value("AWS").valueShortEnough(true)),
                field(f -> f.title("15 Questions").value("30 Seconds Each")
                        .valueShortEnough(true))
        );
    }

    public SectionBlock createdBy(final String user) {
        return section(f -> f.text(
                        markdownText("\n Created by <@" + user + "> ")));
    }

    public ActionsBlock buttons() {
        return actions(join());
    }

    private List<BlockElement> join() {
        return asElements(
                button(b -> b.actionId("Join")
                        .text(plainText(pt -> pt.text("Join Challenge"))).value("join")
                        .style("primary"))
        );
    }

    public SectionBlock challengeContent() {
        return SectionBlock.builder()
                .fields(fields())
                .accessory(image())
                .build();
    }

    private BlockElement image() {
        ImageElement.ImageElementBuilder builder = ImageElement.builder();
        builder.imageUrl("https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg");
        builder.altText("cat");
        return builder.build();
    }

    private List<TextObject> fields() {
        List<TextObject> textObjects = new ArrayList<>();
        textObjects.add(markdownText("*About:*\nAmazon Web Services"));
        textObjects.add(markdownText("15 Questions\n30 seconds each"));
        return textObjects;
    }
}
