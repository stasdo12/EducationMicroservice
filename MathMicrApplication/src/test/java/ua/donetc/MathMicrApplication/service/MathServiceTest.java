package ua.donetc.MathMicrApplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ua.donetc.MathMicrApplication.entity.Question;

@SpringBootTest
class MathServiceTest {

    private MathService mathService;

    @BeforeEach
    void setUp() {
        mathService = new MathService();
    }

    @Test
    void getRandom() {
        //TODO
    }


    @Test
    public void getRandomPlusTest() {
        Question question = mathService.getRandomPlus();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();
        Assertions.assertTrue(expectedQuestion.matches("\\d+ \\+ \\d+ =\\?"));
        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" \\+ ")[0].trim()) +
                        Integer.parseInt(expectedQuestion.split(" \\+ ")[1].replace(" =?", "").trim()));

}

    @Test
    void getRandomMinus() {

        Question question = mathService.getRandomMinus();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();

        Assertions.assertTrue(expectedQuestion.matches("\\d+ - \\d+ =\\?"));

        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" - ")[0].trim()) -
                        Integer.parseInt(expectedQuestion.split(" - ")[1].replace(" =?", "").trim()));

    }

    @Test
    void getRandomDevote() {
        Question question = mathService.getRandomDevote();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();
        Assertions.assertTrue(expectedQuestion.matches("\\d+ / \\d+ =\\?"));

        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" / ")[0].trim()) /
                        Integer.parseInt(expectedQuestion.split(" / ")[1].replace(" =?", "").trim()));

    }

    @Test
    void getRandomMultiply() {
        Question question = mathService.getRandomMultiply();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();
        Assertions.assertTrue(expectedQuestion.matches("\\d+ \\* \\d+ =\\?"));
        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" \\* ")[0].trim()) *
                        Integer.parseInt(expectedQuestion.split(" \\* ")[1].replace(" =?", "").trim()));
    }
}