package ua.donetc.HistoryMicrApplication.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donetc.HistoryMicrApplication.entity.Question;

import java.util.List;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {


    List<Question> getCodeByQuestionStartingWith(String startLetter);

}
