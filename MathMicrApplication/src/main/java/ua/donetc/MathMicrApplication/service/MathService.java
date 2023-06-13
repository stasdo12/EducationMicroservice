package ua.donetc.MathMicrApplication.service;

import org.springframework.stereotype.Service;
import ua.donetc.MathMicrApplication.entity.Question;

import java.util.Random;

@Service
public class MathService {

    private final Random random = new Random();


    public Question getRandom(){
        int max = 100;
        int a = random.nextInt(max);
        int b = random.nextInt(max);

        int operation = random.nextInt(4);

        String question;
        String answer;

        switch (operation) {
            case 0 -> {
                question = a + " + " + b + "=?";
                answer = String.valueOf(a + b);
            }
            case 1 -> {
                question = a + " - " + b + "=?";
                answer = String.valueOf(a - b);
            }
            case 2 -> {
                question = a + " / " + b + "=?";
                answer = String.valueOf(a / b);
            }
            case 3 -> {
                question = a + " * " + b + "=?";
                answer = String.valueOf(a * b);
            }
            default -> {
                question = "";
                answer = "";
            }
        }
        return Question.builder().question(question).answer(answer).build();

    }

    public Question getRandomPlus() {
        int max = 100;
        int a = random.nextInt(max);
        int b = random.nextInt(max);
       return Question.builder().question(a+" + "+b+" =?").answer(String.valueOf(a+b)).build();

    }

    public Question getRandomMinus() {
        int max = 100;
        int a = random.nextInt(max);
        int b = random.nextInt(max);
        return Question.builder().question(a+ " - "+b+" =?").answer(String.valueOf(a-b)).build();

    }


    public Question getRandomDevote() {
        int max = 100;
        int a = random.nextInt(max);
        int b = random.nextInt(max);
        return Question.builder().question(a+" / "+b+" =?").answer(String.valueOf(a/b)).build();

    }
    public Question getRandomMultiply() {
        int max = 100;
        int a = random.nextInt(max);
        int b = random.nextInt(max);
        return Question.builder().question(a+" * "+b+" =?").answer(String.valueOf(a*b)).build();

    }

}
