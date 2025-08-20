package Practice_bot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "common/index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "common/about";
	}

}
