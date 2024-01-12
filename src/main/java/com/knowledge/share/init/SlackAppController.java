package com.knowledge.share.init;

import com.slack.api.bolt.App;
import com.slack.api.bolt.jakarta_servlet.SlackAppServlet;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/slack/events")
public class SlackAppController extends SlackAppServlet {
    public SlackAppController(final App app) {
        super(app);
    }
}
