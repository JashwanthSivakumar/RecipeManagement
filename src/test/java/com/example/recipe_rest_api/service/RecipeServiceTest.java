package com.example.recipe_rest_api.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.recipe_rest_api.model.Recipe;
import com.example.recipe_rest_api.repository.RecipeRepo;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
	
	@Mock
	private RecipeRepo reciperepo;
	
	@InjectMocks
	private RecipeService recipeservice;
	
	private Recipe recipeOne;
	private Recipe recipeTwo;
	
	@BeforeEach
	void setUp() {
		recipeOne = new Recipe();
		recipeOne.setId(1L);
        recipeOne.setName("Sambar Rice");
        
		recipeTwo = new Recipe();
		recipeTwo.setId(1L);
        recipeTwo.setName("Vegatables Rice");
	}
	
	@Test
	@DisplayName("Testing the Recipe Details")
	public void testSaveRecipe() {
		when(reciperepo.save(recipeOne)).thenReturn(recipeOne);
		Recipe savedRecipe = recipeservice.saveRecipe(recipeOne);
		assertThat(savedRecipe).isNotNull();
		assertThat(savedRecipe.getName()).isEqualTo("Sambar Rice");
		verify(reciperepo,times(1)).save(recipeOne);
	}
	
	@Test
	public void testGetRecipeById() {
		when(reciperepo.findById(1L)).thenReturn(Optional.of(recipeOne));	
		Recipe recipe = recipeservice.getRecipeById(1L);
		assertThat(recipe).isNotNull();
		assertThat(recipe.getName()).isEqualTo("Sambar Rice");
		verify(reciperepo,times(1)).findById(1L);
	}
	
	@Test
	public void getAllRecipe() {
		when(reciperepo.findAll()).thenReturn(Arrays.asList(recipeOne,recipeTwo));
		List<Recipe> recipe = recipeservice.fetchAllRecipe();
		assertThat(recipe).isNotNull();
		assertThat(recipe).hasSize(2);
		verify(reciperepo,times(1)).findAll();
	}
	
	@Test
	public void deleteRecipe() {
		doNothing().when(reciperepo).deleteById(1L);
		recipeservice.deleteRecipe(1L);
		verify(reciperepo,times(1)).deleteById(1L);
	}
}


















