package com.knowledge.share.quiz.handler;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.knowledge.share.quiz.mapper.QuizMapper;
import com.knowledge.share.quiz.service.QuizService;
import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.AppMentionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QuizEventHandler implements BoltEventHandler<AppMentionEvent> {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizMapper quizMapper;

    @Override
    public Response apply(final EventsApiPayload<AppMentionEvent> payload,
                          final EventContext ctx)
            throws IOException, SlackApiException {
        AppMentionEvent event = payload.getEvent();
        if (event.getText().contains("quiz")) {
            Quiz quiz = quizService.generateRandomQuiz();

            ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
                    .channel(event.getChannel())
                    .threadTs(event.getThreadTs())
                    .blocks(quizMapper.quizBlocks(quiz))
                    .attachments(quizMapper.attachments(quiz)));
            if (!message.isOk()) {
                ctx.logger.error("chat.postMessage failed: {}", message.getError());
            }
        }
        return ctx.ack();
    }
}
