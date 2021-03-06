package com.knowledge.share.quiz.handler;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.ActionContext;
import com.slack.api.bolt.handler.builtin.BlockActionHandler;
import com.slack.api.bolt.request.builtin.BlockActionRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NextQuestionHandler implements BlockActionHandler {
    private static final Logger logger = LogManager.getLogger(NextQuestionHandler.class);

    @Autowired
    private QuizHandler quizHandler;

    @Override
    public Response apply(final BlockActionRequest req, final ActionContext ctx)
            throws IOException, SlackApiException {
        String actionVal = req.getPayload().getActions().get(0).getValue();
        logger.info("actionVal is {}", actionVal);
        if (actionVal.equalsIgnoreCase("YES")) {
            SlashCommandResponse response = quizHandler.response();
            return ctx.ackWithJson(response.getText());
        }
        return ctx.ack();
    }
}
