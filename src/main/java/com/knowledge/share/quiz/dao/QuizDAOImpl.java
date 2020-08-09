package com.knowledge.share.quiz.dao;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.knowledge.share.quiz.dao.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizDAOImpl implements QuizDAO {

    @Autowired
    private QuizRepository repository;

    @Override
    public List<Quiz> fetchAllQuizFromDB() {
        return repository.findAll();
    }


}
