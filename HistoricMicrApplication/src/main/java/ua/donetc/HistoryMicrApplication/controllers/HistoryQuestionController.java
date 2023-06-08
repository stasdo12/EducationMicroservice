package ua.donetc.HistoryMicrApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.donetc.HistoryMicrApplication.dto.QuestionDTO;
import ua.donetc.HistoryMicrApplication.entity.Question;
import ua.donetc.HistoryMicrApplication.services.QuestionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HistoryQuestionController {


    private final QuestionService questionService;

    @Autowired
    public HistoryQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/questions")
    public List<QuestionDTO> getQuestions(@RequestParam int amount) {
        List<Question> questions = questionService.getAllQuestions();
        Collections.shuffle(questions);
        return questions.stream().limit(amount)
                .map(questionService::conventToQuestionDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/question/{id}")
    public QuestionDTO getQuestionById(@PathVariable("id") int id) {
        return questionService.conventToQuestionDTO(questionService.getQuestionById(id));
    }

    @GetMapping("/question-start/{title}")
    public List<Question> getQuestionStartWith(@PathVariable("title") String title){
        return questionService.getQuestionsStart(title);
    }

    @PostMapping("/add-question")
    public Question addQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.saveQuestion(questionService.conventToQuestion(questionDTO));
    }

    @Transactional
    @PatchMapping("update-question/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id,
                                                   @RequestBody Question updatingQuestion) {
        Question exQuestion = questionService.getQuestionById(id);
        exQuestion.setQuestion(updatingQuestion.getQuestion());
        exQuestion.setAnswer(updatingQuestion.getAnswer());
        updatingQuestion = questionService.updateQuestion(updatingQuestion);
        return ResponseEntity.ok(updatingQuestion);

    }

    @Transactional
    @DeleteMapping("/delete-question/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") int id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
