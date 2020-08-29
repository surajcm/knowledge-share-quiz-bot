package com.knowledge.share.challenge.handler;

import com.knowledge.share.challenge.mapper.ChallengeMapper;
import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
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
import java.util.List;

@Component
public class ChallengeCreateHandler implements SlashCommandHandler {
    private static final Logger logger = LogManager.getLogger(ChallengeCreateHandler.class);

    @Autowired
    private ChallengeMapper challengeMapper;

    @Override
    public Response apply(final SlashCommandRequest req, final SlashCommandContext ctx)
            throws IOException, SlackApiException {
        String user = ctx.getRequestUserId();
        logger.info("user is {}", user);
        SlashCommandResponse response = SlashCommandResponse.builder()
                .blocks(challengeMapper.welcomeBlocks())
                .attachments(attachments(user))
                .responseType("in_channel")
                .build();
        return ctx.ack(response);
    }

    private List<Attachment> attachments(final String user) {
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(Attachment.builder()
                .blocks(blocks(user))
                .build());
        return attachments;
    }

    private List<LayoutBlock> blocks(final String user) {
        List<LayoutBlock> blocks = new ArrayList<>();
        blocks.add(challengeMapper.createdBy(user));
        blocks.add(challengeMapper.challengeContent());
        blocks.add(challengeMapper.buttons());
        return blocks;
    }
}
