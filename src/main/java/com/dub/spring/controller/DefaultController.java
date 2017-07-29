package com.dub.spring.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.dub.spring.services.ChatRoomService;

@Controller
public class DefaultController {
	
	@Autowired
	ChatRoomService chatRoomService;
	
	@Value("${myapp.chatroom.url}")
	private String chatRoomUrl; 
	
	@GetMapping({"/", "/backHome", "/index"})
	public String home1() {
        return "index";
    }
	
	@GetMapping({"/chatroom/{chatRoomId}"})
	public String chatroom1(@PathVariable int chatRoomId, ModelMap model) {
	
		String username;
		if(SecurityContextHolder.getContext().getAuthentication() instanceof
                UsernamePasswordAuthenticationToken) {
			username = SecurityContextHolder
									.getContext().getAuthentication().getName();
				
			model.addAttribute("chatRoomUrl", chatRoomUrl);
			model.addAttribute("username", username);
			model.addAttribute("chatRoomId", chatRoomId);
			model.addAttribute("chatRoomName", chatRoomService.getChatRoomName(chatRoomId));
			return "chat";
		} else {
			return "error";
		}
    }
	

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
    	
    	if(SecurityContextHolder.getContext().getAuthentication() instanceof
                UsernamePasswordAuthenticationToken) {
    		modelAndView.setViewName("index");
            return modelAndView;
    	}
    		
    	modelAndView.setViewName("login");
    	return modelAndView;
    }
    
    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	// Save current locale before actual logout to enable i18n display
    	Locale locale = LocaleContextHolder.getLocale();
    	
    	if (auth != null) { 
            new SecurityContextLogoutHandler().logout(request, response, auth);
        	auth = SecurityContextHolder.getContext().getAuthentication();
        }

        return "redirect:/login?logout" + "&locale=" + locale.toString();
    }
    

    

}
