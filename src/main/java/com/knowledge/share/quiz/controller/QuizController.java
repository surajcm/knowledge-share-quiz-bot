package com.knowledge.share.quiz.controller;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.knowledge.share.quiz.service.QuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Quiz APIs",
        description = "APIs for trial quizzes")
public class QuizController {
    private static final Logger logger = LogManager.getLogger(QuizController.class);
    @Autowired
    private QuizService quizService;

    @GetMapping(value = "/do_quiz", produces = "application/json")
    @ApiOperation(value = "Do a quiz", response = ResponseEntity.class)
    public ResponseEntity<Object> quiz() throws Exception {
        Quiz quiz = quizService.generateRandomQuiz();
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    /*@PostMapping(value = "/submit", produces = "application/json")
    @ApiOperation(value = "Receive payload", response = ResponseEntity.class)
    public ResponseEntity<Object> receiveAnswer(final String payload) throws Exception {
        logger.info("payload");
        logger.info(payload);
        String responseMap = "{\n" +
                "    \"delete_original\": \"true\"\n" +
                "}";
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }*/
}
