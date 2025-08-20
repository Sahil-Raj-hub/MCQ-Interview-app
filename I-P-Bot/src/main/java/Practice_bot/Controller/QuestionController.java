package Practice_bot.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Practice_bot.Model.Question;
import Practice_bot.Model.Question.Category;
import Practice_bot.Model.Question.Difficulty;
import Practice_bot.Model.QuestionResult;
import Practice_bot.Model.UserSelection;
import Practice_bot.Service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add_question")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "admin/add_question";
    }

    @PostMapping("/add_question")
    public String createQuestion(@ModelAttribute Question question) {
        questionService.saveQuestion(question);
        return "redirect:/questions/list";
    }

    @GetMapping("/list")
    public String getAllQuestions(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "admin/list_questions";
    }

    @GetMapping("/id/{id}")
    public String getQuestionById(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            return "error/not_found";
        }
        model.addAttribute("question", question);
        return "admin/view_question";
    }

    @GetMapping("/category/{category}")
    public String getQuestionsByCategory(@PathVariable Category category, Model model) {
        List<Question> questions = questionService.getQuestionsByCategory(category);
        model.addAttribute("questions", questions);
        return "admin/list_filtered_questions";
    }

    @GetMapping("/difficulty/{difficulty}")
    public String getQuestionsByDifficulty(@PathVariable Difficulty difficulty, Model model) {
        List<Question> questions = questionService.getQuestionsByDifficulty(difficulty);
        model.addAttribute("questions", questions);
        return "admin/list_filtered_questions";
    }

    @GetMapping("/quiz-selection")
    public String showQuizSelectionForm(Model model) {
        model.addAttribute("userSelection", new UserSelection());
        return "user/quiz_selection";
    }

    @PostMapping("/attempt-questions")
    public String startQuiz(@ModelAttribute UserSelection userSelection, Model model) {
        List<Question> questions = questionService.getQuestionsByCategoryAndDifficulty(
            userSelection.getCategory(), userSelection.getDifficulty()
        );
        model.addAttribute("questions", questions);
        return "user/attempt_questions";
    }

    @PostMapping("/submit_answers")
    public String submitAnswers(@RequestParam Map<String, String> allParams, Model model) {
        int attemptedCount = 0;
        int correctCount = 0;
        List<QuestionResult> results = new ArrayList<>();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("answers[")) {
                attemptedCount++;

                Long questionId = Long.parseLong(entry.getKey().substring(8, entry.getKey().length() - 1));
                String submittedLabel = entry.getValue();

                Question question = questionService.getQuestionById(questionId);
                String correctLabel = question.getCorrectAnswer();

                String selectedText = switch (submittedLabel) {
                    case "A" -> question.getOptionA();
                    case "B" -> question.getOptionB();
                    case "C" -> question.getOptionC();
                    case "D" -> question.getOptionD();
                    default -> "Invalid";
                };

                String correctText = switch (correctLabel) {
                    case "A" -> question.getOptionA();
                    case "B" -> question.getOptionB();
                    case "C" -> question.getOptionC();
                    case "D" -> question.getOptionD();
                    default -> "Invalid";
                };

                boolean isCorrect = submittedLabel.equals(correctLabel);
                if (isCorrect) correctCount++;

                QuestionResult result = new QuestionResult();
                result.setQuestionText(question.getText());
                result.setSubmittedAnswer(selectedText);
                result.setCorrectAnswer(correctText);
                result.setCorrect(isCorrect);

                results.add(result);
            }
        }

        model.addAttribute("results", results);
        model.addAttribute("attemptedCount", attemptedCount);
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("totalQuestions", results.size());

        return "user/results_answers";
    }
}
