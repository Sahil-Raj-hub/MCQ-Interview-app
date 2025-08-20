package Practice_bot.Service;

import java.util.List;

import Practice_bot.Model.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getallUser();
	
	User getUserById(Long id);
	
	String deleteById(Long Id);

	
	User getUserByEmail(String email);
	User findByEmail(String email);
	String deleteByEmail(String email);


}
