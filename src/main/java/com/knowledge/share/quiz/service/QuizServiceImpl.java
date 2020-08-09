package com.knowledge.share.quiz.service;

import com.knowledge.share.quiz.dao.QuizDAO;
import com.knowledge.share.quiz.dao.entity.Quiz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger logger = LogManager.getLogger(QuizServiceImpl.class);

    @Autowired
    private QuizDAO quizDAO;

    @Override
    public Quiz generateRandomQuiz() {
        logger.info("Inside random quiz !!!");
        List<Quiz> quizzes = quizDAO.fetchAllQuizFromDB();
        int random = new Random().nextInt(quizzes.size());
        logger.info("Random number is :{}", random);
        Quiz[] quizArray = quizzes.toArray(new Quiz[quizzes.size() - 1]);
        return quizArray[random];
    }
}
