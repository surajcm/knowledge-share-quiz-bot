package com.knowledge.share.quiz.handler;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UpperCaseHandler implements SlashCommandHandler {

    @Override
    public Response apply(final SlashCommandRequest req, final SlashCommandContext ctx)
            throws IOException, SlackApiException {
        String message = req.getPayload().getText();
        SlashCommandResponse response = SlashCommandResponse.builder()
                .text("In upper case : " + message.toUpperCase())
                .responseType("in_channel")
                .build();
        return ctx.ack(response);
    }
}
