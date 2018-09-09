package controllers;

import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import repositories.QuestionRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping("/")
    public String home(Model model) {

        Date date = new Date();
        model.addAttribute("currenttime", date.toString());

        List<Question> question = questionRepository.findAll();
        Collections.sort(question);
        model.addAttribute("question", question);

        return "index";
    }
}