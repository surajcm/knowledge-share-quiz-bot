package com.knowledge.share.quiz.service;

import com.knowledge.share.quiz.dao.entity.Quiz;

public interface QuizService {
    Quiz generateRandomQuiz();

    Quiz generateRandomQuizNewImplementation();

    Quiz getQuizFromId(String id);
}
