package Practice_bot.Model;


import Practice_bot.Model.Question.Category;
import Practice_bot.Model.Question.Difficulty;
import lombok.Data;

@Data
public class UserSelection {
	 private Category category;
	 private Difficulty difficulty;
}
