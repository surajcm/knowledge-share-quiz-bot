package com.knowledge.share.quiz.dao.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String question;
    @Column
    private String option1;
    @Column
    private String option2;
    @Column
    private String option3;
    @Column
    private String option4;
    @Column
    private String answer;
    @Column
    private String message;

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getMessage() {
        return message;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public void setOption1(final String option1) {
        this.option1 = option1;
    }

    public void setOption2(final String option2) {
        this.option2 = option2;
    }

    public void setOption3(final String option3) {
        this.option3 = option3;
    }

    public void setOption4(final String option4) {
        this.option4 = option4;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
