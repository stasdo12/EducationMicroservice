package ua.donetc.HistoryMicrApplication.exception;

public class QuestionException extends RuntimeException  {

    public QuestionException() {
        super();
    }
    @Override
    public String getMessage() {
        return "This id is incorrect";
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
