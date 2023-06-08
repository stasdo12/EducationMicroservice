package ua.donetc.HistoryMicrApplication.services;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.donetc.HistoryMicrApplication.dto.QuestionDTO;
import ua.donetc.HistoryMicrApplication.entity.Question;
import ua.donetc.HistoryMicrApplication.repo.QuestionRepo;

import java.util.List;

@Service
@Slf4j
@Component
public class QuestionService {

    private final QuestionRepo questionRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public QuestionService(QuestionRepo questionRepo, ModelMapper modelMapper) {
        this.questionRepo = questionRepo;
        this.modelMapper = modelMapper;
    }

    public List<Question> getAllQuestions() {
        log.info("Found all the Question");
        return questionRepo.findAll();
    }

    public Question saveQuestion(Question question){
        log.info("Save Question");
        return questionRepo.save(question);
    }

    public void deleteQuestion(int id){
        log.info("Delete Question");
        questionRepo.deleteById(id);
    }

    public Question updateQuestion(Question updatingQuestion){
        log.info("Update Question");
        return questionRepo.save(updatingQuestion);
    }

    public Question getQuestionById(int id){
        log.info("GetQuestionById Question");
       return questionRepo.findById(id).orElse(null);
    }

    public List<Question> getQuestionsStart(String startLetter){
        return questionRepo.getCodeByQuestionStartingWith(startLetter);

    }

    public Question conventToQuestion(QuestionDTO questionDTO){
        return modelMapper.map(questionDTO, Question.class);
    }



}
