package Practice_bot.Service;

import java.util.List;

import Practice_bot.Model.Answer;

public interface AnswerService {
	Answer saveAnswer(Answer answer);
	
	List<Answer> getAllAnswers();

	void  deleteAnswer(Long id);
	List<Answer> findByQuestionId(Long questionid);
	List<Answer> findByUserId(Long userid);
	Answer findAnswerById(Long Id);
}
