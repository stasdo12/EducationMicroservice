package ua.donetc.PhysicsMicrApplication.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.donetc.PhysicsMicrApplication.entity.Question;
import ua.donetc.PhysicsMicrApplication.repo.QuestionRepo;

import java.util.List;

@Service
@Slf4j
@Component
public class QuestionService {

    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
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


}
