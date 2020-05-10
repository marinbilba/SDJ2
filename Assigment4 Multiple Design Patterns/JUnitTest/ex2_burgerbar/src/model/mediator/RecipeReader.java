package model.mediator;

import model.domain.Recipe;

public class RecipeReader implements RecipeProvider
{
  String fileName;

  public RecipeReader(String fileName)
  {
    this.fileName = fileName;
  }

  @Override public Recipe getRecipeById(String id)
  {
    return null;
  }

  @Override public Recipe getRecipeByName(String name)
  {
    return null;
  }

  @Override public void updateRecipe(Recipe recipe)
  {

  }
}
