package Practice_bot.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "\"user\"")

public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	private String password;
	
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User(String name, String email, String password, Role role) {
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	
	public enum Role{
		ADMIN,
		CANDIDATE
		
	}
}

