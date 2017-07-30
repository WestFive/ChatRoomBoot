package com.dub.spring.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dub.spring.entities.MyUser;
import com.dub.spring.entities.UserAuthority;
import com.dub.spring.exceptions.DuplicateUserException;
import com.dub.spring.services.UserService;

@Controller
public class RegisterController {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired UserService userService;
	
	@GetMapping(value = "register")
	public String register(ModelMap model) {
		model.addAttribute("registerForm", new RegisterForm());		
		return "register";
	}
	
	@PostMapping(value = "register")
	public String register(
			@Valid @ModelAttribute("registerForm") RegisterForm form,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "register";
		} else {
			MyUser user = new MyUser();
			Set<UserAuthority> authorities = new HashSet<>();
			authorities.add(new UserAuthority("CHAT"));
					
			String newPassword = form.getPassword();
				
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setAuthorities(authorities);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			
			user.setUsername(form.getUsername());
			
			try {
				userService.saveUser(user, newPassword);
				log.info("New user registered: " + form.getUsername());
				return "registerSuccess";
			} catch (DuplicateUserException e) {
				result.rejectValue("username", "duplicate", "name already present");
				return "register";			
			} catch (RuntimeException e) {
				return "error";
			}
			
		}
			
	}

	
    public static class RegisterForm
    {
     	@NotNull(message = "{validate.username.required}")
    	@Size(min = 1, message = "{validate.username.required}")
        private String username;
	    
     	@NotNull(message = "{validate.password.required}")
    	@Size(min = 1, message = "{validate.password.required}")
        private String password;
        
        public RegisterForm()
        {	
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
    }
	
}
