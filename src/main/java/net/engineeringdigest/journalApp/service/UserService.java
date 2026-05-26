package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    public UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try {
            user.setRoles(Arrays.asList("USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            log.info("INFO");
            log.error("ERROR");
            log.warn("WARNING");
            log.debug("DEBUG");
            log.trace("TRACE");
            return false;
        }

    }

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public void saveAdmin(User user) {
        user.setRoles(Arrays.asList("USER","ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
