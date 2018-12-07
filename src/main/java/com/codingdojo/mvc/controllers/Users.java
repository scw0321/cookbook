package com.codingdojo.mvc.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.mvc.models.Recipe;
import com.codingdojo.mvc.models.User;
import com.codingdojo.mvc.services.UserService;
import com.codingdojo.mvc.validator.UserValidator;

@Controller
public class Users {
    private final UserService userService;
    private final UserValidator userValidator;
    
    public Users(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "loginPage.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
                
    	userValidator.validate(user,result);
    	if(result.hasErrors()) {
    		return "loginPage.jsp";
    	}
    	else {
    		userService.registerUser(user);
    		session.setAttribute("userId", user.getId());
    		
    		return "redirect:/recipes";
    	}
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, User user) {
        
    	if(userService.authenticateUser(email, password)) {
    		User theuser = userService.findByEmail(email);
    		session.setAttribute("userId", theuser.getId());
    				
    		return "redirect:/recipes";
    	}
    	model.addAttribute("error", "Invalid login");
    	
    	return "loginPage.jsp";
    	
    }
    
    @RequestMapping("/recipes")
    public String home(HttpSession session, Model model) {
        
    	if(session.getAttribute("userId")==null) {
    		return "redirect:/";
    	}
    	Long id =(long)session.getAttribute("userId");
    	User loginUser = userService.findUserById(id);
    	model.addAttribute("user", loginUser); 
    	List<Recipe> recipe = userService.allRecipes();
        model.addAttribute("recipe", recipe);
    	return "homePage.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {        
    	session.invalidate();
    	return "redirect:/";
    }
    
    @RequestMapping("/recipes/new")
    public String newRecipe(@ModelAttribute("recipe") Recipe recipe, HttpSession session, Model model) {
    	
		model.addAttribute("recipe", recipe);
		
        return "new.jsp";
    }
    @RequestMapping(value="/recipes/new", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, HttpSession session,  Model model) {    	
    	
    	if(result.hasErrors()) {
    		model.addAttribute("error", "Invalid!!Re-enter your recipe");   		
        	return "new.jsp";
    	}
    	
    	userService.createRecipe(recipe);
		return "redirect:/recipes";

    }
    @RequestMapping("/recipes/{id}")
    public String index(Model model,@PathVariable("id") Long id) {
  	Recipe recipe = userService.findRecipe(id);
      model.addAttribute("recipe", recipe);
      return "show.jsp";
    }
    @RequestMapping(value="/recipes/{id}/delete", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
      userService.deleteRecipe(id);
      return "redirect:/recipes";
    }
    
    @RequestMapping("/recipes/{id}/edit")    
	public String edit(@PathVariable("id") Long id, HttpSession session, Recipe recipe, Model model) {
    	    			
	    Recipe updateRecipe = userService.findRecipe(id);
	    model.addAttribute("recipe", updateRecipe);
	    return "edit.jsp";
	}
	        
	@RequestMapping(value="/recipes/{id}", method=RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result) {
	    if (result.hasErrors()) {
	    	   
	        return "edit.jsp";
	   } else {	        
			userService.updateRecipe(recipe);
	        return "redirect:/recipes";
	    }
	}
	//find by search
	@RequestMapping(value ="/search", method =RequestMethod.GET)
	public String search(@RequestParam(value="ingredient") String ingredient, Model model) {
		model.addAttribute("ingredient", ingredient);
		URL url = new URL("https://api.edamam.com/search?q="+ingredient+"&app_id=659e59e4&app_key=fa04c651c0c6cfe0d7ac0216c4742e2a&from=0&to=3&calories=591-722&health=alcohol-free")
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		if(responseCode != 200) {
			System.out.println("URL API responseCode != 200");
		}
		else {
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	        	response.append(inputLine);
	        }
	        in.close();
	        //Read JSON response and print
	        JSONObject myResponse = new JSONObject(response.toString());
	        System.out.println("result after Reading JSON Response");
	        System.out.println("hits- "+myResponse.getString("hits"));
		}
//		return "redirect:https://api.edamam.com/search?q="+ingredient+"&app_id=659e59e4&app_key=fa04c651c0c6cfe0d7ac0216c4742e2a&from=0&to=3&calories=591-722&health=alcohol-free";
		return "redirect:/recipes";
	}
	
	//see the api AJAX search under src/main/java/com.codingdojo.mvc.controllers/UsersApi.java
	
	@RequestMapping("https://api.edamam.com/search?q={ingredient}&app_id=659e59e4&app_key=fa04c651c0c6cfe0d7ac0216c4742e2a&from=0&to=3&calories=591-722&health=alcohol-free")
	public String searchByArtist(@PathVariable("ingredient") String ingredient, Model model) {
		List<Recipe> ingredientRecipes = userService.findByIngredient(ingredient);
		model.addAttribute("ingredientRecipes", ingredientRecipes);
		return "homePage.jsp";
	}


	
}
	
  
