package Practice_bot.ServiceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import Practice_bot.Model.Answer;
import Practice_bot.Repository.AnswerRepository;
import Practice_bot.Service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{
	
	private final AnswerRepository ansRepo;
	
	public AnswerServiceImpl(AnswerRepository answerRepository) {
		this.ansRepo=answerRepository;
	}

	@Override
	public Answer saveAnswer(Answer answer) {
		return ansRepo.save(answer);
	}

	@Override
	public List<Answer> getAllAnswers() {
		return ansRepo.findAll();
	}

	

	@Override
	public void deleteAnswer(Long id) {
		ansRepo.deleteById(id);			
	}

	@Override
	public List<Answer> findByQuestionId(Long questionid) {
		return ansRepo.findByQuestionId(questionid);
	}

	@Override
	public List<Answer> findByUserId(Long userid) {
		return ansRepo.findByUserId(userid);
	}

	@Override
	public Answer findAnswerById(Long id) {
		return ansRepo.findById(id).orElseThrow(()->new RuntimeException("Answer id not found "+id));
	}

	

}
