package ua.donetc.HistoricMicrApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.donetc.HistoricMicrApplication.entity.Question;
import ua.donetc.HistoricMicrApplication.repo.QuestionRepo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HistoryQuestionController {


    private final QuestionRepo questionRepo;

    @Autowired
    public HistoryQuestionController(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }


    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam int amount) {
        List<Question> questions = questionRepo.findAll();
        Collections.shuffle(questions);
        return questions.stream().limit(amount).collect(Collectors.toList());
    }

    @PostMapping("/add-question")
    public void addQuestion(@RequestBody Question question){
        questionRepo.save(question);
    }
}
