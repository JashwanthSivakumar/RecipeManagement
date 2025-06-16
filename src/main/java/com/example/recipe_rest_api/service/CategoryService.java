package com.example.recipe_rest_api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.recipe_rest_api.model.Category;
import com.example.recipe_rest_api.repository.CategoryRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepo cr;
	
	public Category saveCategory(Category category) {
		return cr.save(category);
	}
	
	public void deleteCategory(Long id) {
		cr.deleteById(id);
	}
	
	public List<Category> fetchAllCategory(){
		return cr.findAll();
	}
	
	public Category getById(Long id) {
		return cr.findById(id).orElse(null);
	}
}
