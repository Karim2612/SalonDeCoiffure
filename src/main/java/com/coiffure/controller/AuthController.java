package com.coiffure.controller;

import com.coiffure.model.User;
import com.coiffure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {
    // private int id = 1001;
    User user2 = new User();
    @Autowired
    UserService userService;

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "User registered successfully!");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }
        List<Object> userPresentObj = userService.isUserPresent(user);
        if ((Boolean) userPresentObj.get(0)) {
            model.addAttribute("successMessage", userPresentObj.get(1));
            return "auth/register";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/login";
    }

    public void firstAdminCreator() {

        try {
            String url = "jdbc:mysql://localhost:3306/coiffure-data";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement st = conn.createStatement();

            // int idd = st.executeUpdate("SELECT ID FROM USER");
            // if (idd != id)
            // st.executeUpdate("INSERT INTO User " +
            // "VALUES ('" + this.id
            // + "', NOW(), 'veronica.getaz@eduvaud.ch',1,'veronica','getaz', 0
            // ,'0788888888','$2a$12$s3bGFnzYxvwIBXuTp4P4qOnZXtuRjnoOR7LyMuHWIdiByB68xdrZ6',
            // 'ADMIN',NOW())");

            st.executeUpdate("INSERT INTO User " +
            "VALUES (1001, NOW(), 'veronica.getaz@eduvaud.ch',1,'veronica','getaz', 0 ,'0788888888','$2a$12$s3bGFnzYxvwIBXuTp4P4qOnZXtuRjnoOR7LyMuHWIdiByB68xdrZ6', 'ADMIN', NOW())");
            
            st.executeUpdate("INSERT INTO User " +
            "VALUES (1002, NOW(), 'reza@eduvaud.ch',1,'reza','tavassoli', 0 ,'0789999999','$2a$12$s3bGFnzYxvwIBXuTp4P4qOnZXtuRjnoOR7LyMuHWIdiByB68xdrZ6', 'USER', NOW())");
            
            st.executeUpdate("INSERT INTO User " +
            "VALUES (1003, NOW(), 'karim@eduvaud.ch',1,'karim','hamoudi', 0 ,'0787777777','$2a$12$s3bGFnzYxvwIBXuTp4P4qOnZXtuRjnoOR7LyMuHWIdiByB68xdrZ6', 'USER', NOW())");
            

            st.executeUpdate("INSERT INTO Servise " +
            "VALUES (2001, NOW(), 'Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam.', 'HAIRCUT', 15)");

            st.executeUpdate("INSERT INTO Servise " +
            "VALUES (2002, NOW(), 'Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam.', 'BEARD TRIM', 15)");
        
            st.executeUpdate("INSERT INTO Servise " +
            "VALUES (2003, NOW(), 'Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam.', 'MANS SHAVE', 15)");
        
            st.executeUpdate("INSERT INTO Servise " +
            "VALUES (2004, NOW(), 'Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam.', 'HAIR DYEING', 15)");
        
            st.executeUpdate("INSERT INTO Servise " +
            "VALUES (2005, NOW(), 'Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam.', 'MUSTACHE', 15)");
        
            st.executeUpdate("INSERT INTO Servise " +
            "VALUES (2006, NOW(), 'Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam.', 'STACKING', 15)");
        

            st.executeUpdate("INSERT INTO Rdv " +
            "VALUES (3001, NOW(), NOW(), 'Coupe simple', 2001, 1003)");
            
            st.executeUpdate("INSERT INTO Rdv " +
            "VALUES (3002, NOW(), NOW(), 'Coupe pas simple', 2002, 1002)"); 
            
            st.executeUpdate("INSERT INTO Rdv " +
            "VALUES (3003, NOW(), NOW(), 'Coupe tres simple', 2003, 1003)");  
           

          

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
