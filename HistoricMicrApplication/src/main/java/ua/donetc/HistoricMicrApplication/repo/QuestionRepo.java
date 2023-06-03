package ua.donetc.HistoricMicrApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.donetc.HistoricMicrApplication.entity.Question;


@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}
