package uottawa.ca.cookhelper;

import java.io.Serializable;
import java.util.Arrays;

public class Recipe implements Serializable, Comparable<Recipe> {
    private String name;                // Recipe name
    private String countryStyle;        // Recipe country of origin
    private String categoryOfRecipe;    // Recipe category
    private int timetoCook;             // Recipe cooking time
    private int servingSize;            // Recipe serving size
    private String listOfIngredients;   // List of ingredients in the Recipe
    private String instructions;        // List of instructions in the Recipe
    private int matchCount;             // Number of matches (used for searches)

    public Recipe() {
        char[] chars = new char[40];
        Arrays.fill(chars, ' ');
        String s = new String(chars);
        name = s;
        countryStyle = s;
        categoryOfRecipe = s;
        timetoCook = 0;
        servingSize = 0;
        listOfIngredients = s;
        instructions = s;
        matchCount = 0;
    }

    /**
     * Getter for the matchCount.
     * @return the matchCount
     */
    public int getMatchCount() {
        return matchCount;
    }

    /**
     * Increments the matchCount.
     */
    public void incrementMatchCount() {
        matchCount++;
    }

    /**
     * Decrements the matchCount.
     */
    public void decrementMatchCount() {
        matchCount--;
    }

    /**
     * Resets the matchCount to 0.
     */
    public void resetMatchCount() {
        matchCount = 0;
    }

    public Recipe(String name, String countryStyle, String categoryOfRecipe,
                  int timetoCook, int servingSize, String listOfIngredients, String instructions) {
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

    @Override
    public int compareTo(Recipe r) {
        int matchCountCompare = this.getMatchCount() - r.getMatchCount();
        int nameCompare = this.getName().compareTo(r.getName());

        if (matchCountCompare != 0) {
            return matchCountCompare;
        } else {
            return nameCompare;
        }
    }
}
