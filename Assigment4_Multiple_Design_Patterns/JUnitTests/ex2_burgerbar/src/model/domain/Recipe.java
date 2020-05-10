package model.domain;

import java.util.Arrays;

public class Recipe
{
  private String id;
  private String name;
  private String[] ingredients;

  public Recipe(String id, String name, String[] ingredients)
  {
    this.id = id;
    this.name = name;
    this.ingredients = ingredients;
  }

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String[] getIngredients()
  {
    return ingredients;
  }

  public Burger createBurger(){
    return new Burger(name);
  }

  @Override public String toString()
  {
    return "Recipe{" + "id='" + id + '\'' + ", name='" + name + '\''
        + ", ingredients=" + Arrays.toString(ingredients) + '}';
  }
}
