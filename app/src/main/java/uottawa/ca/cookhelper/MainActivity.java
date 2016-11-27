package uottawa.ca.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipes;            // List of recipes
    private final static int RECIPE_REQUEST = 0;  // Used to return recipes list from RecipeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recipes = createTestRecipes();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void recipeListButtonAction(View view) {
        Intent i = new Intent(this, LibraryActivity.class);
        i.putExtra("recipeList", recipes);
        startActivityForResult(i, RECIPE_REQUEST);
    }

    protected void searchRecipesButtonAction(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        i.putExtra("recipeList", recipes);
        startActivityForResult(i, RECIPE_REQUEST);
    }

    protected void instructionsButtonAction(View view) {
        Intent i = new Intent(this, InstructionsActivity.class);
        startActivity(i);
    }

    /**
     * Used to pass the list of recipes back from the RecipeActivity so that
     * recipes may be edited and saved.
     *
     * @param requestCode the requestCode associated with the ActivityResult
     * @param resultCode  the resultCode associated with the ActivityResult
     * @param intent      the source intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RECIPE_REQUEST) {
            if (resultCode == RESULT_OK) {
                recipes = (ArrayList<Recipe>) intent.getSerializableExtra("recipes");
            }
        }
    }

    private ArrayList<Recipe> createTestRecipes() {
        Recipe a = new Recipe("Tomato Sauce Pasta",
                "Italian",
                "Dinner",
                45,
                1,
                "3 Tomatoes\n" + "4 oz. Pasta \n" +
                        "2 Cloves Garlic \n" +
                        "3 tsp Salt",
                "1. Mash tomatoes into a paste. \n" +
                        "2. Crush garlic and add to tomato paste with the salt. \n" +
                        "3. Saute sauce at med-hi heat for 20 minutes. \n" +
                        "4. While the sauce is sauteeing, boil pasta until al-dente. \n" +
                        "5. Strain pasta and mix in a bowl with the sauce.");

        Recipe b = new Recipe("Chocolate Milkshake",
                "American",
                "Dessert",
                20,
                2,
                "4 scoops chocolate ice cream\n" +
                        "2 cups Milk\n" +
                        "1 can Whipped cream",
                "1. Put ice cream and milk into a blender.\n" +
                        "2. Blend until shake is a creamy consistency.\n" +
                        "3. Pour into glass and cover with whipped cream.");

        Recipe c = new Recipe("Bagel & Lox with Cream Cheese",
                "Jewish",
                "Breakfast",
                20,
                2,
                "4tbs Cream Cheese \n" +
                        "9 oz. Lox \n" +
                        "1 Bagel",
                "1. Cut bagel in half horizontally. \n" +
                        "2. Spread cream cheese on both sides of the bagel. \n" +
                        "3. Cover both sides of the bagel with lox.");

        Recipe d = new Recipe("Bagel with Cream Cheese",
                "American",
                "Breakfast",
                20,
                2,
                "4tbs Cream Cheese \n" +
                        "1 Bagel",
                "1. Cut bagel in half horizontally. \n" +
                        "2. Spread cream cheese on both sides of the bagel.");
        ArrayList<Recipe> r = new ArrayList<>();
        r.add(a);
        r.add(b);
        r.add(c);
        r.add(d);
        return r;
    }
}
