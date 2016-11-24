package uottawa.ca.cookhelper;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String name;
    private String countryStyle;
    private String categoryOfRecipe;
    private int timetoCook;
    private int servingSize;
    private String listOfIngredients;
    private String instructions;

    public Recipe(String name, String countryStyle, String categoryOfRecipe, int timetoCook, int servingSize, String listOfIngredients, String instructions) {
        this.name = name;
        this.countryStyle = countryStyle;
        this.categoryOfRecipe = categoryOfRecipe;
        this.timetoCook = timetoCook;
        this.servingSize = servingSize;
        this.listOfIngredients = listOfIngredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryStyle() {
        return countryStyle;
    }

    public void setCountryStyle(String countryStyle) {
        this.countryStyle = countryStyle;
    }

    public String getCategoryOfRecipe() {
        return categoryOfRecipe;
    }

    public void setCategoryOfRecipe(String categoryOfRecipe) {
        this.categoryOfRecipe = categoryOfRecipe;
    }

    public int getTimetoCook() {
        return timetoCook;
    }

    public void setTimetoCook(int timetoCook) {
        this.timetoCook = timetoCook;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(String listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
