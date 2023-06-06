package ua.donetc.EXAMINATOR.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.donetc.EXAMINATOR.entity.Exam;
import ua.donetc.EXAMINATOR.entity.Questions;
import ua.donetc.EXAMINATOR.entity.Section;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class ExamController {


    private final RestTemplate restTemplate;



    public ExamController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> spec) {
       List<Section> sections = spec.entrySet().stream()
               .map(this::getUrl)
               .map(url -> restTemplate.getForObject(url, Questions[].class))
               .map(Arrays::asList)
               .map(Section::new).toList();


        return Exam.builder().title("EXAM").section(sections).build();
    }

    private String getUrl(Map.Entry<String, Integer> entry) {
        return "http://"+entry.getKey()+"/api/questions?amount="+entry.getValue();
    }
}
