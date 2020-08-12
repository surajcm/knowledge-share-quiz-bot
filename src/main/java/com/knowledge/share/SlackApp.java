package com.knowledge.share;

import com.knowledge.share.quiz.handler.NextQuestionHandler;
import com.knowledge.share.quiz.handler.QuizHandler;
import com.knowledge.share.quiz.handler.QuizResponseHandler;
import com.knowledge.share.quiz.handler.UpperCaseHandler;
import com.slack.api.bolt.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class SlackApp {

    @Autowired
    private UpperCaseHandler upperCaseHandler;

    @Autowired
    private QuizHandler quizHandler;

    @Autowired
    private QuizResponseHandler quizResponseHandler;

    @Autowired
    private NextQuestionHandler nextQuestionHandler;

    @Bean
    public App initSlackApp() {
        App app = new App();
        app.command("/hello", upperCaseHandler);
        app.command("/aws_quiz", quizHandler);
        app.blockAction(Pattern.compile("Answer-.*"), quizResponseHandler);
        app.blockAction(Pattern.compile("Next-.*"), nextQuestionHandler);
        return app;
    }
}
