package ua.donetc.HistoryMicrApplication.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.donetc.HistoryMicrApplication.entity.Question;
import ua.donetc.HistoryMicrApplication.repo.QuestionRepo;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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


    @Test
    public void testUpdateQuestion() {
        Question question = new Question();
        question.setId(1);
        question.setQuestion("New Question");
        question.setAnswer("New answer");
        when(questionRepo.save(any(Question.class))).thenReturn(question);
        Question updatedQuestion = questionService.updateQuestion(question);
        assertNotNull(updatedQuestion);
        assertEquals(question.getId(), updatedQuestion.getId());
        assertEquals(question.getAnswer(), updatedQuestion.getAnswer());
        verify(questionRepo, times(1)).save(any(Question.class));
    }

    @Test
    public void getQuestionById(){
        Question question = new Question(1, "TestQuestion", "TestAnser");
        when(questionRepo.findById(question.getId())).thenReturn(Optional.of(question));
        Question actualQuestions = questionService.getQuestionById(question.getId());
        assertEquals(question, actualQuestions);
    }

    @Test
    public void getQuestionsStart(){
        String startLetter = "A";
        Question question1 = new Question();
        question1.setId(1);
        question1.setQuestion("Question");
        question1.setAnswer("Answer");
        Question question2 = new Question();
        question2.setId(2);
        question2.setQuestion("Question2");
        question2.setAnswer("Answer");
        List<Question> expectedQuestions = Arrays.asList(question1, question2);
        when(questionRepo.getCodeByQuestionStartingWith(startLetter)).thenReturn(expectedQuestions);
        List<Question> actualQuestions = questionService.getQuestionsStart(startLetter);
        assertNotNull(actualQuestions);
        assertEquals(expectedQuestions.size(), actualQuestions.size());
        assertEquals(expectedQuestions.get(0).getId(), actualQuestions.get(0).getId());
        assertEquals(expectedQuestions.get(0).getQuestion(), actualQuestions.get(0).getQuestion());
        assertEquals(expectedQuestions.get(0).getAnswer(), actualQuestions.get(0).getAnswer());

        assertEquals(expectedQuestions.get(1).getId(), actualQuestions.get(1).getId());
        assertEquals(expectedQuestions.get(1).getQuestion(), actualQuestions.get(1).getQuestion());
        assertEquals(expectedQuestions.get(1).getAnswer(), actualQuestions.get(1).getAnswer());
        verify(questionRepo, times(1)).getCodeByQuestionStartingWith(startLetter);
    }
}