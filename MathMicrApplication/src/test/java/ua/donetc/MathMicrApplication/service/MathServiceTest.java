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

        Question question = mathService.getRandom();
        String generatedQuestion = question.getQuestion();
        String generatedAnswer = question.getAnswer();


        Assertions.assertNotNull(generatedQuestion);
        Assertions.assertNotNull(generatedAnswer);
        Assertions.assertTrue(generatedQuestion.matches("\\d+ [+\\-/*] \\d+=\\?"));
        String questionWithoutSpaces = generatedQuestion.replace(" ", "");

        int operand1EndIndex = 0;
        while (operand1EndIndex < questionWithoutSpaces.length()
                && Character.isDigit(questionWithoutSpaces.charAt(operand1EndIndex))) {
            operand1EndIndex++;
        }
        int operand2StartIndex = operand1EndIndex + 1;


        String operand1 = questionWithoutSpaces.substring(0, operand1EndIndex);
        String operand2 = questionWithoutSpaces.substring(operand2StartIndex, questionWithoutSpaces.length() - 2);
        String operator = questionWithoutSpaces.substring(operand1EndIndex, operand2StartIndex);

        int a = Integer.parseInt(operand1);
        int b = Integer.parseInt(operand2);
        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "/" -> a / b;
            case "*" -> a * b;
            default -> throw new IllegalArgumentException("Invalid operator");
        };
        Assertions.assertEquals(result, Integer.parseInt(generatedAnswer));
    }




    @Test
    public void getRandomPlusTest() {
        Question question = mathService.getRandomPlus();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();
        Assertions.assertTrue(expectedQuestion.matches("\\d+ \\+ \\d+ =\\?"));
        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" \\+ ")[0].trim()) +
                        Integer.parseInt(expectedQuestion.split(" \\+ ")[1]
                                .replace(" =?", "").trim()));

}

    @Test
    void getRandomMinus() {

        Question question = mathService.getRandomMinus();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();

        Assertions.assertTrue(expectedQuestion.matches("\\d+ - \\d+ =\\?"));

        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" - ")[0].trim()) -
                        Integer.parseInt(expectedQuestion.split(" - ")[1]
                                .replace(" =?", "").trim()));

    }

    @Test
    void getRandomDevote() {
        Question question = mathService.getRandomDevote();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();
        Assertions.assertTrue(expectedQuestion.matches("\\d+ / \\d+ =\\?"));

        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" / ")[0].trim()) /
                        Integer.parseInt(expectedQuestion.split(" / ")[1]
                                .replace(" =?", "").trim()));

    }

    @Test
    void getRandomMultiply() {
        Question question = mathService.getRandomMultiply();
        String expectedQuestion = question.getQuestion();
        String expectedAnswer = question.getAnswer();
        Assertions.assertTrue(expectedQuestion.matches("\\d+ \\* \\d+ =\\?"));
        Assertions.assertEquals(Integer.parseInt(expectedAnswer),
                Integer.parseInt(expectedQuestion.split(" \\* ")[0].trim()) *
                        Integer.parseInt(expectedQuestion.split(" \\* ")[1]
                                .replace(" =?", "").trim()));
    }
}