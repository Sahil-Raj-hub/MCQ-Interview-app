package Practice_bot.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Practice_bot.Model.Answer;
import Practice_bot.Service.AnswerService;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/submit")
    public String showAnswerForm(Model model) {
        model.addAttribute("answer", new Answer());
        return "user/attempt_questions";
    }

    @PostMapping("/submit")
    public String submitAnswer(@ModelAttribute Answer answer) {
        answerService.saveAnswer(answer);
        return "redirect:/candidate/dashboard";
    }

    @GetMapping("/list")
    public String listAllAnswers(Model model) {
        List<Answer> answers = answerService.getAllAnswers();
        model.addAttribute("answers", answers);
        return "admin/list_answers"; // Consider creating this view
    }

    @GetMapping("/id/{id}")
    public String getAnswerById(@PathVariable Long id, Model model) {
        Answer answer = answerService.findAnswerById(id);
        if (answer == null) {
            return "error/not_found"; // Optional error handling
        }
        model.addAttribute("answer", answer);
        return "user/view_answers";
    }

    @GetMapping("/question/{questionid}")
    public String getAnswerByQuestionId(@PathVariable Long questionid, Model model) {
        List<Answer> answers = answerService.findByQuestionId(questionid);
        model.addAttribute("answers", answers);
        return "admin/list_answers";
    }

    @GetMapping("/user/{userid}")
    public String getAnswerByUserId(@PathVariable Long userid, Model model) {
        List<Answer> answers = answerService.findByUserId(userid);
        model.addAttribute("answers", answers);
        return "admin/list_answers";
    }
}
