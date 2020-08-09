package com.knowledge.share.quiz.dao;

import com.knowledge.share.quiz.dao.entity.Quiz;

import java.util.List;

public interface QuizDAO {
    List<Quiz> fetchAllQuizFromDB();
}
