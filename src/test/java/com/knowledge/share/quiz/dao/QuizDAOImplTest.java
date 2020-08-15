package com.knowledge.share.quiz.dao;

import com.knowledge.share.quiz.dao.entity.Quiz;
import com.knowledge.share.quiz.dao.repository.QuizRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class QuizDAOImplTest {
    private final QuizDAO quizDAO = new QuizDAOImpl();
    private final QuizRepository repository = Mockito.mock(QuizRepository.class);

    @Test
    void verifyCountOfQuizzes() {
        long count = 2L;
        Whitebox.setInternalState(quizDAO, "repository", repository);
        when(repository.count()).thenReturn(count);
        Assertions.assertEquals(count, quizDAO.countOfQuizzes());
    }

    @Test
    void verifyFetchQuizFromId() {
        Optional<Quiz> mockQuiz = Optional.of(Mockito.mock(Quiz.class));
        Whitebox.setInternalState(quizDAO, "repository", repository);
        when(repository.findById(anyLong())).thenReturn(mockQuiz);
        Assertions.assertEquals(mockQuiz, quizDAO.fetchQuizFromId(2L));
    }


}