package backend;

import backend.config.AppConfigBack;
import backend.service.UserService;
import frontend.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfigBack.class);

        UserService userService = context.getBean(UserService.class);

        userService.cleanUsersTable();
        User user1 = new User("User1", "Lastname1", 12, 110, 24);
        User user2 = new User("User2", "Lastname2", 35, 180, 74);
        User user3 = new User("User3", "Lastname3", 62, 170, 84);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.toString());
            System.out.println();
        }
        user3.setAge(45);
        System.out.println(userService.findUserById(2L));

//        userService.updateUserById(user3);
//        userService.removeUserById(user2);
        List<User> users2 = userService.listUsers();
        for (User user : users2) {
            System.out.println("Id = " + user.toString());
            System.out.println();
        }
//        userService.cleanUsersTable();
//        List<User> users3 = userService.listUsers();
//        for (User user : users3) {
//            System.out.println("Id = "+user.toString());
//            System.out.println();
//        }

        context.close();
    }
}
