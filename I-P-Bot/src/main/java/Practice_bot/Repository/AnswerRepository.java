package Practice_bot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Practice_bot.Model.Answer;
import java.util.List;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	List<Answer> findByQuestionId(Long questionid);
	List<Answer> findByUserId(Long userid);

}