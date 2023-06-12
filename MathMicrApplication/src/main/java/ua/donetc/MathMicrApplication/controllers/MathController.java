package ua.donetc.MathMicrApplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.donetc.MathMicrApplication.entity.Question;
import ua.donetc.MathMicrApplication.service.MathService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MathController {


    private final MathService mathService;

    @Autowired
    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/questions-plus")
    public List<Question> getRandomQuestionsPlus(@RequestParam int amount) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(mathService.getRandomPlus());
        }
        return questions;
    }

    @GetMapping("/questions-minus")
    public List<Question> getRandomQuestionsMinus(@RequestParam int amount) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(mathService.getRandomMinus());
        }
        return questions;
    }

    @GetMapping("/questions-devote")
    public List<Question> getRandomQuestionsDevote(@RequestParam int amount) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(mathService.getRandomDevote());
        }
        return questions;
    }
    @GetMapping("/questions-multiply")
    public List<Question> getRandomQuestionsMultiply(@RequestParam int amount) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            questions.add(mathService.getRandomMultiply());
        }
        return questions;
    }



}
