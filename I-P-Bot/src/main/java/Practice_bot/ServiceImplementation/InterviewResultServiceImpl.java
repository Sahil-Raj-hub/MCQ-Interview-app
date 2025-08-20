package Practice_bot.ServiceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import Practice_bot.Model.InterviewResult;
import Practice_bot.Model.InterviewResult.Category;
import Practice_bot.Model.InterviewResult.Difficulty;
import Practice_bot.Repository.InterviewResultRepository;
import Practice_bot.Service.InterviewResultService;

@Service
public class InterviewResultServiceImpl implements InterviewResultService {
	
	private final InterviewResultRepository viewResRepo;
	public InterviewResultServiceImpl(InterviewResultRepository interviewResultRepository) {
		this.viewResRepo=interviewResultRepository;
	}

	@Override
	public InterviewResult saveInterviewResult(InterviewResult result) {
		return viewResRepo.save(result);
	}

	

	@Override
	public List<InterviewResult> getAllResults() {
		return viewResRepo.findAll();
	}

	

	@Override
	public List<InterviewResult> getResultsByDifficuty(Difficulty difficulty) {
		return viewResRepo.findByDifficulty(difficulty);
	}

	@Override
	public List<InterviewResult> getResultsByCategory(Category category) {
		return viewResRepo.findByCategory(category);
	}

	@Override
	public InterviewResult getResultById(Long id) {
		return viewResRepo.findById(id).orElseThrow(()->new RuntimeException("Result Id not found"+id));
	}

	@Override
	public void DeleteById(Long id) {
		viewResRepo.deleteById(id);
	}
}
