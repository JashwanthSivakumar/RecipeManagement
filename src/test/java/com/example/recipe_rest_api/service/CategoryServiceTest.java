package com.example.recipe_rest_api.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.recipe_rest_api.model.Recipe;
import com.example.recipe_rest_api.repository.CategoryRepo;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class CategoryServiceTest {
	
	@Mock
	private CategoryRepo categoryrepo;
	
	@InjectMocks
	private CategoryService categoryservice;
	
	private Recipe recipe1;
	private Recipe recipe2;
	
	@BeforeEach 
	void setUp() {
		recipe1 = new Recipe("Briyani","Good", 34);
		recipe2 = new Recipe("Shawarma","Average",120);
	}
	
	
}
