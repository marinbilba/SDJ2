package model.mediator;

import model.domain.Recipe;

public interface RecipeProvider
{
  Recipe getRecipeById(String id);
  Recipe getRecipeByName(String name);
  void updateRecipe(Recipe recipe);
}
