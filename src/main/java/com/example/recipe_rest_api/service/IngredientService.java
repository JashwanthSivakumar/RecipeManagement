package com.example.recipe_rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe_rest_api.model.Category;
import com.example.recipe_rest_api.model.Ingredient;
import com.example.recipe_rest_api.repository.IngredientRepo;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class IngredientService {
	
	@Autowired
	private IngredientRepo ir;
	
	public Ingredient saveIngredient(Ingredient ingredient) {
		return ir.save(ingredient);
	}
	
	public void deleteIngredient(Long id) {
		ir.deleteById(id);
	}
	
	public List<Ingredient> fetchAllIngredient(){
		return ir.findAll();
	}
	
	public Ingredient getById(Long id) {
		return ir.findById(id).orElse(null);
	}
}
