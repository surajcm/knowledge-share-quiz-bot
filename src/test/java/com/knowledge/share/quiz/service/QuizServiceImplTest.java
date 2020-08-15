package com.knowledge.share.quiz.service;

import com.knowledge.share.quiz.dao.QuizDAO;
import com.knowledge.share.quiz.dao.QuizDAOImpl;
import com.knowledge.share.quiz.dao.entity.Quiz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {QuizDAO.class})
class QuizServiceImplTest {
    private final QuizService quizService = new QuizServiceImpl();
    private final QuizDAO quizDAO = Mockito.mock(QuizDAOImpl.class);

    @Test
    void verifyGenerateRandomQuiz() {
        Whitebox.setInternalState(quizService, "quizDAO", quizDAO);
        when(quizDAO.countOfQuizzes()).thenReturn(2L);
        when(quizDAO.fetchQuizFromId(anyLong()))
                .thenReturn(Optional.of(Mockito.mock(Quiz.class)));
        Quiz quiz = quizService.generateRandomQuiz();
        Assertions.assertNotNull(quiz);
    }

    @Test
    void verifyGetQuizFromId() {
        Whitebox.setInternalState(quizService, "quizDAO", quizDAO);
        when(quizDAO.fetchQuizFromId(anyLong()))
                .thenReturn(Optional.of(Mockito.mock(Quiz.class)));
        Quiz quiz = quizService.getQuizFromId("2");
        Assertions.assertNotNull(quiz);
    }
}