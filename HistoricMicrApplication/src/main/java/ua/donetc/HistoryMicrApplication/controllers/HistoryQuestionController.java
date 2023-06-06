package ua.donetc.HistoryMicrApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
    public List<Question> getQuestions(@RequestParam int amount) {
        List<Question> questions = questionService.getAllQuestions();
        Collections.shuffle(questions);
        return questions.stream().limit(amount).collect(Collectors.toList());
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable("id") int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/question-start/{title}")
    public List<Question> getQuestionStartWith(@PathVariable("title") String title){
        return questionService.getQuestionsStart(title);
    }

    @PostMapping("/add-question")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
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
