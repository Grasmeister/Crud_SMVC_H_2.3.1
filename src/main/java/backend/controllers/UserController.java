package backend.controllers;

import backend.model.User;
import backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("users", userService.listUsers());

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
