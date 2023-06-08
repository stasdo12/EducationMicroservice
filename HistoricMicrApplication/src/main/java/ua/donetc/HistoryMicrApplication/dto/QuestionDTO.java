package ua.donetc.HistoryMicrApplication.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    @NotEmpty
    private String question;
    @NotEmpty
    private String answer;

}
