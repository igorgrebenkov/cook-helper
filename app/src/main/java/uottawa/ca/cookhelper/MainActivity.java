package uottawa.ca.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;

/**
 * The class MainActivity is the home screen for the app.
 *
 * It extends AppCompatActivity.
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipes;            // List of recipes
    private final static int RECIPE_REQUEST = 0;  // Used to return recipes list from RecipeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load example recipes
        System.out.println("test");
        recipes = RecipeGenerator.generateRecipes();
    }

    /**
     * Action associated with the recipe list button.
     * @param view the view
     */
    protected void recipeListButtonAction(View view) {
        Intent i = new Intent(this, LibraryActivity.class);
        i.putExtra("recipeList", recipes);
        startActivityForResult(i, RECIPE_REQUEST);
    }

    /**
     * Action associated with the search recipes button.
     * @param view the view
     */
    protected void searchRecipesButtonAction(View view) {
        Intent i = new Intent(this, SearchActivity.class);
        i.putExtra("recipeList", recipes);
        startActivityForResult(i, RECIPE_REQUEST);
    }

    /**
     * Action associated with the instructions button.
     * @param view the view
     */
    protected void instructionsButtonAction(View view) {
        Intent i = new Intent(this, InstructionsActivity.class);
        startActivity(i);
    }

    /**
     * Action associated with the about button.
     * @param view the view
     */
    protected void aboutButtonAction(View view) {
        Intent i = new Intent(this, AboutActivity.class);
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
}
