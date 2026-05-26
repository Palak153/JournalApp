package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserDetailServiceImpl;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> create_user(@RequestBody User user){
        try{
            boolean b = userService.saveNewUser(user);
            if(b)
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }
}
