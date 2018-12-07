package com.codingdojo.mvc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.codingdojo.mvc.models.Recipe;
import com.codingdojo.mvc.models.User;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	List<Recipe> findAll();

	List<Recipe> findByUser(User id);
	List<Recipe> findByIngredient(String[] ingredient);

}
