package Practice_bot.Service;

import Practice_bot.Model.Question;
import Practice_bot.Model.Question.Category;
import Practice_bot.Model.Question.Difficulty;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    Question getQuestionById(Long id);

    List<Question> getAllQuestions();

    List<Question> getQuestionsByCategory(Category category);

    List<Question> getQuestionsByDifficulty(Difficulty difficulty);

	List<Question> getQuestionsByCategoryAndDifficulty(Category category, Difficulty difficulty);
}
