package Practice_bot.ServiceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import Practice_bot.Model.User;
import Practice_bot.Repository.UserRepository;
import Practice_bot.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepo=userRepository;
	}

	@Override
	public User saveUser(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public List<User> getallUser() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepo.findById(id).orElseThrow(()->new RuntimeException("id not found"+id));
	}

	

	@Override
	public User getUserByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public String deleteByEmail(String email) {
		 userRepo.deleteByEmail(email);
		 return "User with email "+email+" successfully deleted";
	}

	@Override
	public String deleteById(Long Id) {
		userRepo.deleteById(Id);
		 return "User with email "+Id+" successfully deleted";

		
	}

}
