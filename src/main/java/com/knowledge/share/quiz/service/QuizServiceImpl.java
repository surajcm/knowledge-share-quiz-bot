package com.knowledge.share.quiz.service;

import com.knowledge.share.quiz.dao.QuizDAO;
import com.knowledge.share.quiz.dao.entity.Quiz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger logger = LogManager.getLogger(QuizServiceImpl.class);

    @Autowired
    private QuizDAO quizDAO;

    @Override
    public Quiz generateRandomQuiz() {
        Quiz quiz = null;
        logger.info("Inside generateRandomQuizNewImplementation quiz !!!");
        long count = quizDAO.countOfQuizzes();
        int random = new Random().nextInt((int) count - 1);
        random++;
        logger.info("Random number is :{}", random);
        Optional<Quiz> quizOptional = quizDAO.fetchQuizFromId((long) random);
        if (quizOptional.isPresent()) {
            quiz = quizOptional.get();
        }
        return quiz;
    }

    @Override
    public Quiz getQuizFromId(final String id) {
        Quiz quiz = null;
        logger.info("Inside getQuizFromId  !!!");
        Long idValue = Long.valueOf(id);
        Optional<Quiz> quizOptional = quizDAO.fetchQuizFromId(idValue);
        if (quizOptional.isPresent()) {
            quiz = quizOptional.get();
        }
        return quiz;
    }
}
