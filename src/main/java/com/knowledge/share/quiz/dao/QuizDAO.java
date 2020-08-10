package com.knowledge.share.quiz.dao;

import com.knowledge.share.quiz.dao.entity.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizDAO {
    List<Quiz> fetchAllQuizFromDB();

    long countOfQuizzes();

    Optional<Quiz> fetchQuizFromId(Long id);
}
