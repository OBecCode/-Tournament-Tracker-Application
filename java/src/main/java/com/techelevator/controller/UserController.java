package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import com.techelevator.model.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("")

public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @RequestMapping(path = "/account/{id}", method = RequestMethod.PUT)
    public User updateAccount(@PathVariable long id, @RequestBody User user)throws UserNotFoundException{

        if (userDao.updateAccount(user) != null){
            return user;
        }else {
            throw new UserNotFoundException();
        }
    }
}
