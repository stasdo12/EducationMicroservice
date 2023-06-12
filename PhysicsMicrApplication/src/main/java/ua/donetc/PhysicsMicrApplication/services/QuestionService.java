package ua.donetc.PhysicsMicrApplication.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.donetc.PhysicsMicrApplication.dto.QuestionDTO;
import ua.donetc.PhysicsMicrApplication.entity.Question;
import ua.donetc.PhysicsMicrApplication.exception.QuestionException;
import ua.donetc.PhysicsMicrApplication.repo.QuestionRepo;
import org.modelmapper.ModelMapper;

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

    public Question getQuestionById(int id){
        log.info("GetQuestionById Question");
        return questionRepo.findById(id).orElseThrow(QuestionException::new);
    }
    public Question updateQuestion(Question updatingQuestion){
        log.info("Update Question");
        return questionRepo.save(updatingQuestion);
    }
    public List<Question> getQuestionsStart(String startLetter){
        return questionRepo.getCodeByQuestionStartingWith(startLetter);

    }



    public Question convertToQuestion(QuestionDTO questionDTO){
        log.info("convert QuestionDTO To Question");
        return modelMapper.map(questionDTO, Question.class);
    }

    public QuestionDTO convertToQuestionDTO(Question question){
        log.info("convert Question To QuestionDTO");
        return modelMapper.map(question, QuestionDTO.class);
    }

}
