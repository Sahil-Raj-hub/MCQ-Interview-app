package Practice_bot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Practice_bot.Model.Question;
import Practice_bot.Model.Question.Category;
import Practice_bot.Model.Question.Difficulty;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(Category category);

    List<Question> findByDifficulty(Difficulty difficulty);

    List<Question> findByExpectedKeywordContainingIgnoreCase(String keyword);

    @Query("SELECT q FROM Question q WHERE LOWER(q.text) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<Question> searchByQuestionText(@Param("text") String text);

	List<Question> findByCategoryAndDifficulty(Category category, Difficulty difficulty);
}
 