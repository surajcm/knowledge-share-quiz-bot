package com.knowledge.share.quiz.dao.repository;

import com.knowledge.share.quiz.dao.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
