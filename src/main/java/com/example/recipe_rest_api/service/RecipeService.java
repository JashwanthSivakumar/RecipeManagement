package com.example.recipe_rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe_rest_api.model.Ingredient;
import com.example.recipe_rest_api.model.Recipe;
import com.example.recipe_rest_api.repository.RecipeRepo;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class RecipeService {
	@Autowired
	private RecipeRepo rr;
	
	public Recipe saveRecipe(Recipe recipe) {
		return rr.save(recipe);
	}
	
	public Recipe getRecipeById(Long id) {
		return rr.findById(id).orElse(null);
	}
	
	public void deleteRecipe(Long id) {
		rr.deleteById(id);
	}
	
	public List<Recipe> fetchAllRecipe(){
		return rr.findAll();
	}

}
