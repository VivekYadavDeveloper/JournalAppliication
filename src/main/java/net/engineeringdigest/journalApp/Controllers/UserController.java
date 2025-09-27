package net.engineeringdigest.journalApp.Controllers;


import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Process of how controller work
 *  CONTROLLER -------> SERVICE -------> REPOSITORY
 *  CONTROLLER <------- SERVICE <------- REPOSITORY
 * */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUsers(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUsers(@RequestBody User user, @PathVariable String userName) {
        /* Create a variable for update user data "userInDB"*/
        User userInDB = userService.findByUserName(userName);
        /*Now check user is available in database or not*/
        if (userInDB != null) {
            /*If available than update the with response*/
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            /*Then, When client send the response save it in userdb*/
            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
/*Time 20:08*/