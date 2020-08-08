package com.knowledge.share;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackApp {
    @Bean
    public App initSlackApp() {
        App app = new App();
        app.command("/hello", (req, ctx) -> {
            String message = req.getPayload().getText();
            SlashCommandResponse response = SlashCommandResponse.builder()
                    .text("In upper case : " + message.toUpperCase())
                    .responseType("in_channel")
                    .build();
            return ctx.ack(response);
        });
        return app;
    }
}
