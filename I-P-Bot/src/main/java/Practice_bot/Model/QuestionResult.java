package Practice_bot.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResult {
	  private String questionText;
	    private String submittedAnswer;
	    private String correctAnswer;
	    private boolean correct;
}
