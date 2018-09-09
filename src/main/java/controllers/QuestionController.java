package controllers;

import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repositories.QuestionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/polls")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @RequestMapping("/")
    @ResponseBody
    public List<Question> getAll() {
        List<Question> question = questionRepository.findAll();
        Collections.sort(question);
        return question;
    }

    @PostMapping("/")
    public String create(
            @RequestParam String question
    ) {
        Question input = new Question(question);
        input = questionRepository.save(input);
        return "redirect:/";
    }

    @GetMapping("/{id}/upvote")
    public String upvote(
            @PathVariable("id") long id
    ) {
        Optional optional = questionRepository.findById(id);
        Question input = (Question) optional.get();
        if (input != null) {
            input.votes++;
            questionRepository.save(input);
        }
        return "redirect:/";
    }

    @GetMapping("/{id}/downvote")
    public String downvote(
            @PathVariable("id") long id
    ) {
        Optional optional = questionRepository.findById(id);
        Question input = (Question) optional.get();
        if (input != null) {
            input.votes--;
            questionRepository.save(input);
        }
        return "redirect:/";
    }
}