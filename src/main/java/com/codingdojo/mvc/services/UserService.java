package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Recipe;
import com.codingdojo.mvc.models.User;
import com.codingdojo.mvc.repositories.UserRepository;
import com.codingdojo.mvc.repositories.RecipeRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    
    public UserService(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

	public List<Recipe> allRecipes() {
		// TODO Auto-generated method stub
		return recipeRepository.findAll();
	}

	public Recipe createRecipe(Recipe r) {
        return recipeRepository.save(r);
    }

	public List<User> allUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public Recipe findRecipe(Long id) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if(optionalRecipe.isPresent()) {
            return optionalRecipe.get();
        } else {
            return null;
        }
	}

	public void deleteRecipe(Long id) {
		// TODO Auto-generated method stub
		Recipe t = this.findRecipe(id);
		if(t!=null) {
			recipeRepository.deleteById(id);			
		}		
		
	}

	public Recipe updateRecipe(@Valid Recipe r) {
		// TODO Auto-generated method stub
		return recipeRepository.save(r);
	}

	public List<Recipe> findByIngredient(String ingredient) {
		// TODO Auto-generated method stub
		return null;
	}
	
}