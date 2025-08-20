package Practice_bot.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "question_id",nullable = false)
	private Question question;
	
	@Lob
	private String answertext;
	
	private int score;

	public Answer(User user, Question question, String answertext ,int score) {
		
		this.user = user;
		this.question = question;
		this.answertext = answertext;
		this.score=score;
	}
	

}
