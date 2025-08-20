package Practice_bot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Practice_bot.Model.Question;
import Practice_bot.Model.Question.Category;
import Practice_bot.Model.Question.Difficulty;
import Practice_bot.Model.QuestionResult;
import Practice_bot.Service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final QuestionService questionService;

    public UserController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/user_dashboard")
    public String dashboard() {
        return "user/user_dashboard";
    }

    @GetMapping("/attempt_questions")
    public String showQuestions(@RequestParam("category") Category category,
                                @RequestParam("difficulty") Difficulty difficulty,
                                Model model) {
        List<Question> questions = questionService.getQuestionsByCategoryAndDifficulty(category, difficulty);
        model.addAttribute("questions", questions);
        return "user/attempt_questions";
    }

    @GetMapping("/quiz_select")
    public String showQuizSelector() {
        return "user/quiz_select";
    }

    @PostMapping("/submit_answer")
    public String submitAnswers(@RequestParam("questionIds") List<Long> questionIds,
                                @RequestParam Map<String, String> allParams,
                                Model model) {

        List<QuestionResult> results = new ArrayList<>();
        int correctCount = 0;

        for (Long qId : questionIds) {
            String key = "answer_" + qId;
            String submittedAnswer = allParams.get(key);

            Question question = questionService.getQuestionById(qId);
            boolean isCorrect = submittedAnswer != null && submittedAnswer.equalsIgnoreCase(question.getCorrectAnswer());
            if (isCorrect) correctCount++;

            QuestionResult result = new QuestionResult(
                question.getText(),
                submittedAnswer,
                question.getCorrectAnswer(),
                isCorrect
            );

            results.add(result);
        }

        model.addAttribute("results", results);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("total", results.size());

        return "user/results_answers";
    }
}
