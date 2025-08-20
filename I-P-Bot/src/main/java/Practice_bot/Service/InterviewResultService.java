package Practice_bot.Service;

import java.util.List;


import Practice_bot.Model.InterviewResult;
import Practice_bot.Model.InterviewResult.Category;
import Practice_bot.Model.InterviewResult.Difficulty;

public interface InterviewResultService {
	InterviewResult saveInterviewResult(InterviewResult result);
	
	List<InterviewResult> getAllResults();
	List<InterviewResult> getResultsByCategory(Category category);
	List<InterviewResult> getResultsByDifficuty(Difficulty difficulty);
	InterviewResult getResultById(Long id);
	void DeleteById(Long id);
	

}
