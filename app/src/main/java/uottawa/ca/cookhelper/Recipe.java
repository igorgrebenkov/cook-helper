package uottawa.ca.cookhelper;

import java.io.Serializable;

/**
 * The class Recipe is the model of the app.
 * <p>
 * It holds various attributes belonging to a recipe.
 * <p>
 * It implements Serializable so the list of recipes may be passed between activities.
 * It implements Comparable so a list of recipes may be sorted.
 */
public class Recipe implements Serializable, Comparable<Recipe> {
    private String name;                // Recipe name
    private String countryStyle;        // Recipe country of origin
    private String categoryOfRecipe;    // Recipe category
    private int timeToCook;             // Recipe cooking time
    private int servingSize;            // Recipe serving size
    private String listOfIngredients;   // List of ingredients in the Recipe
    private String instructions;        // List of instructions in the Recipe
    private int matchCount;             // Number of matches (used for searches)

    /**
     * No argument constructor (used for adding a recipe).
     */
    public Recipe() {
        name = "";
        countryStyle = "";
        categoryOfRecipe = "";
        timeToCook = 0;
        servingSize = 0;
        listOfIngredients = "";
        instructions = "";
        matchCount = 0;
    }

    /**
     * All-argument constructor.
     *
     * @param name              the recipe name
     * @param countryStyle      the recipe's country of origin
     * @param categoryOfRecipe  the recipe's category
     * @param timeToCook        the recipe's cooking time
     * @param servingSize       the recipe's serving size
     * @param listOfIngredients the recipe's list of ingredients
     * @param instructions      the recipe's list of instructions
     */
    public Recipe(String name, String countryStyle, String categoryOfRecipe,
                  int timeToCook, int servingSize, String listOfIngredients, String instructions) {
        this.name = name;
        this.countryStyle = countryStyle;
        this.categoryOfRecipe = categoryOfRecipe;
        this.timeToCook = timeToCook;
        this.servingSize = servingSize;
        this.listOfIngredients = listOfIngredients;
        this.instructions = instructions;
        this.matchCount = 0;
    }

    /**
     * Getter for the Recipe's name.
     *
     * @return the Recipe's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the Recipe's name.
     *
     * @param name the Recipe's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the Recipe's country of origin.
     *
     * @return the Recipe's country of origin
     */
    public String getCountryStyle() {
        return countryStyle;
    }

    /**
     * Setter for the Recipe's country of origin.
     *
     * @param countryStyle the Recipe's country of origin
     */
    public void setCountryStyle(String countryStyle) {
        this.countryStyle = countryStyle;
    }

    /**
     * Getter for the Recipe's category.
     *
     * @return the Recipe's category
     */
    public String getCategoryOfRecipe() {
        return categoryOfRecipe;
    }

    /**
     * Setter for the Recipe's category.
     *
     * @param categoryOfRecipe the Recipe's category
     */
    public void setCategoryOfRecipe(String categoryOfRecipe) {
        this.categoryOfRecipe = categoryOfRecipe;
    }

    /**
     * Getter for the Recipe's cooking time.
     *
     * @return the Recipe's cooking time.
     */
    public int getTimeToCook() {
        return timeToCook;
    }

    /**
     * Setter for the Recipe's cooking time.
     *
     * @param timeToCook the Recipe's cooking time
     */
    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }

    /**
     * Getter for the Recipe's serving size.
     *
     * @return the Recipe's serving size.
     */
    public int getServingSize() {
        return servingSize;
    }

    /**
     * Setter for the Recipe's serving size.
     *
     * @param servingSize the Recipe's serving size.
     */
    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    /**
     * Getter for the Recipe's list of ingredients.
     *
     * @return the Recipe's list of ingredients
     */
    public String getListOfIngredients() {
        return listOfIngredients;
    }

    /**
     * Setter for the Recipe's list of ingredients.
     *
     * @param listOfIngredients the Recipe's list of ingredients
     */
    public void setListOfIngredients(String listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }

    /**
     * Getter for the Recipe's list of instructions.
     *
     * @return the Recipe's list of instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Setter for the Recipe's list of instructions.
     *
     * @param instructions the Recipe's list of instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * Getter for the matchCount.
     *
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
     * Resets the matchCount to 0.
     */
    public void resetMatchCount() {
        matchCount = 0;
    }

    /**
     * Returns comparison of matchCount if not equal to zero.
     * Else, returns comparison of Name strings.
     *
     * @param r the recipe we are comparing with
     * @return the result of the comparison
     */
    @Override
    public int compareTo(Recipe r) {
        // Opposite of expected result for comparing integers
        // This is to be able to rank by decreasing matchCount if matchCounts are unequal
        // Or by alphabetical order of Recipe name otherwise
        int matchCountCompare = r.getMatchCount() - this.getMatchCount();

        int nameCompare = this.getName().compareTo(r.getName());

        if (matchCountCompare != 0) {
            return matchCountCompare;
        } else {
            return nameCompare;
        }
    }
}
