package frontend.controllers;

import backend.config.AppConfigBack;
import backend.service.UserService;
import frontend.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
//    private final UserService userService;

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfigBack.class);

    UserService userService = context.getBean(UserService.class);
//    @Autowired
//    public IndexController(UserServiceImpl userService) {
//        this.userService = userService;
//    }


    @GetMapping(value = "/")
    public String index(Model model) {
        List<User> userList = new ArrayList<>();
        userList = userService.listUsers();
        model.addAttribute("users", userList);

        return "index";
    }

    @GetMapping("/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "/view";
    }

    @GetMapping("/createUser")
    public String creatUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/createUser";
    }


    @PostMapping()
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("lastName") String lastname,
                             @RequestParam("age") int age,
                             @RequestParam("height") int height,
                             @RequestParam("weight") int weight,
                             Model model) {
        User user = new User(name, lastname, age, height, weight);
        userService.add(user);
        return "redirect:/";

    }

//    @PostMapping()
//    public String createUser(@ModelAttribute("user") User user){
//        userService.createUser(user);
//        return "redirect:/";
//    }

    @GetMapping("/{id}/updateUser")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "/updateUser";

    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }


}
