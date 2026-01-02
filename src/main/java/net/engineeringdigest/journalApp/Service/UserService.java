package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*Create/Save Entry*/
    public void saveEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);

        } catch (Exception e) {
            log.error("Exception", e);
        }
    }


//    public void saveNewEntry(User user) {
//        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//        } catch (Exception e) {
//            log.error("Exception", e);
//        }
//    }

    /*Get all entries*/
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /*Find by ID's*/
    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }
    /*Find by ID's*/

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
