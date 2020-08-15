package com.knowledge.share.quiz.dao;

import com.knowledge.share.quiz.dao.entity.Quiz;

import java.util.Optional;

public interface QuizDAO {

    long countOfQuizzes();

    Optional<Quiz> fetchQuizFromId(Long id);
}
