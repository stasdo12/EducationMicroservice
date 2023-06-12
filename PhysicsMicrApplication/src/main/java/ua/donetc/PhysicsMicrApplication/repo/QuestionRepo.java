package ua.donetc.PhysicsMicrApplication.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donetc.PhysicsMicrApplication.entity.Question;

import java.util.List;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> getCodeByQuestionStartingWith(String startLetter);

}
