package Practice_bot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Practice_bot.Model.InterviewResult;
import Practice_bot.Model.InterviewResult.Category;
import Practice_bot.Model.InterviewResult.Difficulty;
import Practice_bot.Model.User;

import java.util.List;


public interface InterviewResultRepository extends JpaRepository<InterviewResult, Long>{


	List<InterviewResult> findByCategory(Category category);
	List<InterviewResult> findByDifficulty(Difficulty difficulty);
	List<InterviewResult> findByUser(User user);

}
