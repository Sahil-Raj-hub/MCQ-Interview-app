package Practice_bot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Practice_bot.Model.InterviewResult;
import Practice_bot.Service.InterviewResultService;

import java.util.List;

@Controller
@RequestMapping("/result")
public class InterviewResultController {

    private final InterviewResultService interviewResultService;

    public InterviewResultController(InterviewResultService interviewResultService) {
        this.interviewResultService = interviewResultService;
    }

    @GetMapping("/add")
    public String showAddResultForm(Model model) {
        model.addAttribute("result", new InterviewResult());
        return "admin/add_result";
    }

    @PostMapping("/add")
    public String createInterviewResult(@ModelAttribute InterviewResult interviewResult) {
        interviewResultService.saveInterviewResult(interviewResult);
        return "redirect:/result/list";
    }

    @GetMapping("/list")
    public String getAllInterviewResults(Model model) {
        List<InterviewResult> results = interviewResultService.getAllResults();
        model.addAttribute("results", results);
        return "admin/list_results";
    }

    @GetMapping("/id/{id}")
    public String getInterviewResultById(@PathVariable Long id, Model model) {
        InterviewResult result = interviewResultService.getResultById(id);
        if (result == null) {
            return "error/not_found";
        }
        model.addAttribute("result", result);
        return "admin/view_result";
    }

    @PostMapping("/delete/{id}")
    public String deleteInterviewResult(@PathVariable Long id) {
        interviewResultService.DeleteById(id);
        return "redirect:/result/list";
    }
}
