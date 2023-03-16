package com.coiffure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.coiffure.model.User;
import com.coiffure.repository.UserRepository;
import com.coiffure.service.UserServises;
import com.coiffure.Constants;
import com.coiffure.model.Message;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
    public String homePage() {
        return "user/dashboard";
    }

    @Autowired
    UserServises userServices;


    @Autowired
	private UserRepository userRepository;

    @GetMapping("/users")
	public String listUsers(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

    @GetMapping("/users/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<User> page = userRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listUsers", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "users";
	}

    @GetMapping("/users/new")
	public String showUserNewForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", userRepository.findAll());
		return "user_form";
	}

    @PostMapping("/create")
    public ResponseEntity<Message> addNewUser(@RequestBody User user) {
        try {
            User returnedUser = userServices.saveUser(user);

            return new ResponseEntity<Message>(
                    new Message("Upload Successfully!",  Arrays.asList(returnedUser), null, null,null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(
                    new Message("Fail to post a new User!", null, null, null, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieveinfos")
    public ResponseEntity<Message> retrieveUserInfo() {

        try {
            List<User> userInfos = userServices.getUserInfos();

            return new ResponseEntity<Message>(new Message("Get Users' Infos!",  userInfos, null,null 
                    , ""),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Fail!", null, null, null, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findone/{id}")
    public ResponseEntity<Message> getUserById(@PathVariable Long id) {
        try {
            Optional<User> optUser = userServices.getUserById(id);

            if (optUser.isPresent()) {
                return new ResponseEntity<Message>(new Message("Successfully! Retrieve a user by id = " + id,
                Arrays.asList(optUser.get()), null, null,""), HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(
                        new Message("Failure -> NOT Found a user by id = " + id, null, null, null, ""),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Message> updateUserById(@RequestBody User _user, @PathVariable Long id) {
        try {
            if (userServices.checkExistedUser(id)) {
                User user = userServices.getUserById(id).get();

                // set new values for user
                user.setFirstName(_user.getFirstName());
                user.setLastName(_user.getLastName());
                user.setMobile(_user.getMobile());
                user.setPassword(_user.getPassword());
                user.setEmail(_user.getEmail());
                user.setEnabled(_user.getEnabled());
                user.setLocked(_user.getLocked());
                user.setRole(_user.getRole());
                // user.setPassword(_user.getPassword());

                // save the change to database
                userServices.updateUser(user);

                return new ResponseEntity<Message>(new Message("Successfully! Updated a User " + "with id = " + id,
                Arrays.asList(user), null, null, ""), HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(
                        new Message("Failer! Can NOT Found a User " + "with id = " + id, null,  null,null, ""),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Message> deleteUserById(@PathVariable Long id) {
        try {
            // checking the existed of a User with id
            if (userServices.checkExistedUser(id)) {
                userServices.deleteUserById(id);

                return new ResponseEntity<Message>(
                        new Message("Successfully! Delete a User with id = " + id, null, null, null, ""),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<Message>(
                        new Message("Failer! Can NOT Found a User " + "with id = " + id, null, null, null, ""),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Failure", null, null, null, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
