package ua.donetc.HistoryMicrApplication.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.donetc.HistoryMicrApplication.entity.Question;
import ua.donetc.HistoryMicrApplication.repo.QuestionRepo;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionServiceTest {


    @Mock
    private QuestionRepo questionRepo;


    @InjectMocks
    private QuestionService questionService;



    @Test
    public void testGetAllQuestions() {
        // Arrange
        Question question1 = new Question(1, "Question 1", "Answer 1");
        Question question2 = new Question(2, "Question 2", "Answer 2");
        List<Question> expectedQuestions = Arrays.asList(question1, question2);

        when(questionRepo.findAll()).thenReturn(expectedQuestions);

        // Act
        List<Question> actualQuestions = questionService.getAllQuestions();

        // Assert
        assertEquals(expectedQuestions, actualQuestions);

    }

    @Test
    void saveQuestion() {
        Question question = new Question(1, "Question 1", "Answer 1");
        when(questionRepo.save(question)).thenReturn(question);
        Question savedQuestion = questionService.saveQuestion(question);
        assertEquals(question, savedQuestion);
        verify(questionRepo).save(question);
    }

    @Test
    void deleteQuestion() {
        int questionId = 1;
        questionService.deleteQuestion(questionId);
        verify(questionRepo).deleteById(questionId);
    }
}