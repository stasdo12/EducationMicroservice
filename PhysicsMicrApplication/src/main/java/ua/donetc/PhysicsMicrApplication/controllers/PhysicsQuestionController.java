package ua.donetc.PhysicsMicrApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.donetc.PhysicsMicrApplication.entity.Question;
import ua.donetc.PhysicsMicrApplication.exception.QuestionException;
import ua.donetc.PhysicsMicrApplication.services.QuestionService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PhysicsQuestionController {


    private final QuestionService questionService;

    @Autowired
    public PhysicsQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam int amount) {
        List<Question> questions = questionService.getAllQuestions();
        Collections.shuffle(questions);
        return questions.stream().limit(amount).collect(Collectors.toList());
    }



    @PostMapping("/add-question")
    public Question addQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }


    @Transactional
    @DeleteMapping("/delete-question/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") int id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/question/{id}")
    public Question getQuestionById(@PathVariable("id") int id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/question-start/{title}")
    public List<Question> getQuestionStartWith(@PathVariable("title") String title){
        return questionService.getQuestionsStart(title);
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

    @ExceptionHandler(QuestionException.class)
    public ResponseEntity<String> handleQuestionException(QuestionException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("This id is incorrect EXCEPTION: " + ex);
    }
}
