package net.engineeringdigest.journalApp.Controllers;


import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/* Process of how controller work
 *  CONTROLLER -------> SERVICE -------> REPOSITORY
 *  CONTROLLER <------- SERVICE <------- REPOSITORY
 * */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUsers(@RequestBody User user) {
        /*Get the logged-in username from security context holder*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        /* Create a variable for update user data "userInDB"*/
        User userInDB = userService.findByUserName(userName);
        /*Then, When client send the response save it in userdb*/
        /*If available than update the with response*/
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveEntry(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsersById(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}