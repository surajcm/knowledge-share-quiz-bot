package com.knowledge.share;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.App;
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.composition.TextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SlackApp {

    @Bean
    public App initSlackApp() {
        App app = new App();
        app.command("/hello", (req, ctx) -> {
            String message = req.getPayload().getText();
            SlashCommandResponse response = SlashCommandResponse.builder()
                    //.text("In upper case : " + message.toUpperCase())
                    .blocks(blocks())
                    .responseType("in_channel")
                    .build();
            return ctx.ack(response);
        });

        app.command("/aws_quiz", (req, ctx) -> {
            String message = req.getPayload().getText();
            SlashCommandResponse response = SlashCommandResponse.builder()
                    .blocks(blocks())
                    .responseType("in_channel")
                    .build();
            return ctx.ack(response);
        });
        return app;
    }

    private List<LayoutBlock> blocks() {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(questionBlock());
        blocks.add(optionBlock());
        blocks.add(answers());
        return blocks;
    }

    private LayoutBlock questionBlock() {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(question()).build();
    }

    private TextObject question() {
        MarkdownTextObject.MarkdownTextObjectBuilder builder = MarkdownTextObject.builder();
        builder.text("Which AWS service would be the best choice for long term data archival?");
        return builder.build();
    }

    private LayoutBlock optionBlock() {
        SectionBlock.SectionBlockBuilder builder = SectionBlock.builder();
        return builder.text(options()).build();
    }

    private TextObject options() {
        MarkdownTextObject.MarkdownTextObjectBuilder builder = MarkdownTextObject.builder();
        builder.text(":white_circle: S3" +
                "\n :white_circle: CloudFront" +
                "\n:white_circle: EFS" +
                "\n:white_check_mark: Glacier");
        return builder.build();
    }

    private LayoutBlock answers() {
        ActionsBlock.ActionsBlockBuilder builder = ActionsBlock.builder();
        return builder.elements(buttons()).build();
    }

    private List<BlockElement> buttons() {
        List<BlockElement> blockElements = new ArrayList<>();
        blockElements.add(firstOption());
        blockElements.add(secondOption());
        blockElements.add(thirdOption());
        blockElements.add(forthOption());
        return blockElements;
    }

    private BlockElement firstOption() {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value("A").text(firstButton());
        return builder.build();
    }

    private PlainTextObject firstButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("1").emoji(true);
        return builder.build();
    }

    private BlockElement secondOption() {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value("B").text(secondButton());
        return builder.build();
    }

    private PlainTextObject secondButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("2").emoji(true);
        return builder.build();
    }

    private BlockElement thirdOption() {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value("C").text(thirdButton());
        return builder.build();
    }

    private PlainTextObject thirdButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("3").emoji(true);
        return builder.build();
    }

    private BlockElement forthOption() {
        ButtonElement.ButtonElementBuilder builder = ButtonElement.builder();
        builder.value("D").text(forthButton());
        return builder.build();
    }

    private PlainTextObject forthButton() {
        PlainTextObject.PlainTextObjectBuilder builder = PlainTextObject.builder();
        builder.text("4").emoji(true);
        return builder.build();
    }
}
