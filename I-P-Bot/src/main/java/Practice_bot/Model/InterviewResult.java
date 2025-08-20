package Practice_bot.Model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class InterviewResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private int totalScore;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = false)
	private User user;

	private Difficulty difficulty;
	private Category category;
	public InterviewResult(int totalScore, User user,Difficulty difficulty, Category category) {
		
		this.totalScore = totalScore;
		this.user = user;
		this.category=category;
		this.difficulty=difficulty;
	}
	public enum Difficulty{EASY,MEDIUM,HARD}
	public enum Category{JAVA,SQL,SPRING}
	

}
