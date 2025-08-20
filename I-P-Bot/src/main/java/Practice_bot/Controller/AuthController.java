package Practice_bot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Practice_bot.Model.User;
import Practice_bot.Service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "common/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.saveUser(user);
            return "redirect:/auth/login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "common/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "common/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            if ("ADMIN".equalsIgnoreCase(user.getRole().name())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/user_dashboard";
            }
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "common/login";
        }
    }
}
