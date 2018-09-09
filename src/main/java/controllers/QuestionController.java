package controllers;



import com.sun.tools.internal.xjc.model.Model;
import models.Question;

import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping ("/polls")
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