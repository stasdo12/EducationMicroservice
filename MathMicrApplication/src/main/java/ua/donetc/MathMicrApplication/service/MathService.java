package ua.donetc.MathMicrApplication.service;

import org.springframework.stereotype.Service;
import ua.donetc.MathMicrApplication.entity.Question;

import java.util.Random;

@Service
public class MathService {

    private final Random random = new Random();

    public Question getRandom() {
        int max = 1000;
        int a = random.nextInt(max);
        int b = random.nextInt(max);
       return Question.builder().question(a+" + "+b+" =?").answer(String.valueOf(a+b)).build();

    }
}
