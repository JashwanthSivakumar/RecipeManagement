package com.example.recipe_rest_api.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipe_rest_api.exception.ResourceNotFound;
import com.example.recipe_rest_api.model.Category;
import com.example.recipe_rest_api.model.Ingredient;
import com.example.recipe_rest_api.model.Recipe;
import com.example.recipe_rest_api.service.CategoryService;
import com.example.recipe_rest_api.service.IngredientService;
import com.example.recipe_rest_api.service.RecipeService;
 
@RestController
public class AppController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("Hello Welcome to Recipe APIs", HttpStatus.OK);
	}
	
	@PostMapping("/category/save")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		
		try {
			Category result = categoryService.saveCategory(category);
			return new ResponseEntity<Category>(result,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
 
	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> displayAllCategories(){
		
		List<Category> categoryList = categoryService.fetchAllCategory();
		return new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
	}
	
	@PostMapping("/recipe/add")
	public ResponseEntity<Recipe> addReceipe(@RequestBody Recipe r){
		List<Ingredient> ingredients = r.getIngredients();
		for(Ingredient i:ingredients) {
			i.setRecipe(r);
		}
		Recipe result = recipeService.saveRecipe(r);
		return new ResponseEntity<Recipe>(result,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/ingredient/save") public ResponseEntity<?>
	 * saveIngredient(@RequestBody Ingredient ingredients){ Recipe recipe =
	 * recipeService.getRecipeById(ingredients.getRecipe().getId());
	 * ingredients.setRecipe(recipe); Ingredient ingredient =
	 * ingredientService.saveIngredient(ingredients); return new
	 * ResponseEntity<Ingredient>(ingredient,HttpStatus.OK); }
	 */
	
	@PutMapping("/category/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id,@RequestBody Category category){
		Category result = categoryService.getById(id);
		result.setName(category.getName());
		categoryService.saveCategory(result);
		return new ResponseEntity<Category>(result,HttpStatus.OK);
	}
	
	@GetMapping("/recipe/{id}")
	public ResponseEntity<Recipe> getByRecipeId(@PathVariable Long id) {
		Recipe recipe = recipeService.getRecipeById(id);
		if(recipe == null) {
			throw new ResourceNotFound("Recipe Id Not Exist"+id);
		}
		else {
			return new ResponseEntity<Recipe>(recipe,HttpStatus.OK);
		}
	}
	
	@GetMapping("/recipe/all")
	public ResponseEntity<List<Recipe>> viewAllRecipe(){
		List<Recipe> recipe = recipeService.fetchAllRecipe();
		return new ResponseEntity<List<Recipe>>(recipe,HttpStatus.OK);
	}
	
//	@GetMapping("/recipe/{id}")
//	public ResponseEntity<Recipe> getById(@PathVariable Long id){
//		Recipe recipe = recipeService.getRecipeById(id);
//		return new ResponseEntity<Recipe>(recipe,HttpStatus.OK);
//	}
	
	@PutMapping("/recipe/update/{id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe){
		Recipe result = recipeService.getRecipeById(id);
		result.setName(recipe.getName());
		result.setDescription(recipe.getDescription());
		result.setCookingTime(recipe.getCookingTime());
		result.setCategory(recipe.getCategory());
		result.setIngredients(recipe.getIngredients());
//		List<Ingredient> listIngredient = ingredientService.fetchAllIngredient();
		for(Ingredient ingredient: recipe.getIngredients()) {
			ingredient.setRecipe(result);
		}
		recipeService.saveRecipe(result);
		return new ResponseEntity<Recipe>(result,HttpStatus.OK);
	}
	
	@DeleteMapping("/recipe/delete/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable Long id){
		recipeService.deleteRecipe(id);
		return new ResponseEntity<String>("Deleted Success"+id,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/category/delete/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id){
		categoryService.deleteCategory(id);
		return new ResponseEntity<String>("Deleted Success"+id,HttpStatus.CREATED);
	}
}	

