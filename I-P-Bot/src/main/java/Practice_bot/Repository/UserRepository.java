package Practice_bot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Practice_bot.Model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	String deleteByEmail(String email);


}
