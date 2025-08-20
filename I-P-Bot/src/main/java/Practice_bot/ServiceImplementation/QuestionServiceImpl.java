package Practice_bot.ServiceImplementation;

import org.springframework.stereotype.Service;
import Practice_bot.Model.Question;
import Practice_bot.Model.Question.Category;
import Practice_bot.Model.Question.Difficulty;
import Practice_bot.Repository.QuestionRepository;
import Practice_bot.Service.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsByCategory(Category category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> getQuestionsByDifficulty(Difficulty difficulty) {
        return questionRepository.findByDifficulty(difficulty);
    }

    @Override
    public List<Question> getQuestionsByCategoryAndDifficulty(Category category, Difficulty difficulty) {
        return questionRepository.findByCategoryAndDifficulty(category, difficulty);
    }

	
}
