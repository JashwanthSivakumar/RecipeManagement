package com.example.recipe_rest_api.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "RecipeName")
	private String name;
	@Column(name = "RecipeDescription")
	private String description;
	@Column(name = "Time")
	private int cookingTime;
	
	public Recipe() {
		super();
	}
	
	@OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	@ManyToOne
	private Category category ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Recipe(String name, String description, int cookingTime) {
		super();
		this.name = name;
		this.description = description;
		this.cookingTime = cookingTime;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", cookingTime=" + cookingTime
				+ "]";
	}
}
