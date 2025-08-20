package Practice_bot.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Practice_bot.Model.Question;
import Practice_bot.Model.User;
import Practice_bot.Repository.UserRepository;
import Practice_bot.Service.InterviewResultService;
import Practice_bot.Service.QuestionService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final QuestionService questionService;
    private final InterviewResultService interviewResultService;
    private final UserRepository userRepository;

    public AdminController(QuestionService questionService,
                           InterviewResultService interviewResultService,
                           UserRepository userRepository) {
        this.questionService = questionService;
        this.interviewResultService = interviewResultService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/admin_dashboard";
    }

    @GetMapping("/add_question")
    public String addQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("categories", Question.Category.values());
        model.addAttribute("difficulties", Question.Difficulty.values());
        return "admin/add_question";
    }

    @PostMapping("/add_question")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        question.setId(null);
        questionService.saveQuestion(question);
        return "redirect:/admin/list_questions";
    }

    @GetMapping("/list_questions")
    public String listQuestions(Model model) {
        model.addAttribute("questions", questionService.getAllQuestions());
        return "admin/list_questions";
    }

    @GetMapping("/list_users")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/list_users";
    }

    @GetMapping("/view_results")
    public String viewResults(Model model) {
        model.addAttribute("results", interviewResultService.getAllResults());
        return "admin/view_results";
    }
}
