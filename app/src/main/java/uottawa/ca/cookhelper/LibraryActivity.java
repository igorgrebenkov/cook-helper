package uottawa.ca.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {

    private ArrayList<Recipe> recipes;                  // List of recipes
    private ArrayList<String> recipeNames;              // List of recipe names
    private ListView recipeListView;                    // ListView for Recipes
    private ArrayAdapter<String> noSelectionList;       // Adapter w/ no selection
    private ArrayAdapter<String> multipleSelectionList; // Adapter w/ multiple selection
    private boolean isEditing;                          // Flag indicates recipe list is being edited
    private final static int RECIPE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        recipes = createTestRecipes();

        // Recipe names used for recipeListView labels
        recipeNames = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }

        // Adapter init
        recipeListView = (ListView) findViewById(R.id.recipeListView);
        noSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recipeNames);
        multipleSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, recipeNames);
        recipeListView.setAdapter(noSelectionList);
        isEditing = false;

        // If the noSelectionList adapter is active, the onItemClickListener
        // will open the recipe selected by the user in a RecipeActivity
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (!isEditing) {
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("position", position);
                    bundle.putSerializable("recipeList", recipes);
                    Intent i = new Intent(LibraryActivity.this, RecipeActivity.class);
                    i.putExtras(bundle);
                    startActivityForResult(i, RECIPE_REQUEST);
                }
            }
        });
    }

    /**
     * Action associated with the edit button.
     * 1. Makes recipeListView allow multiple selections (for deleting)
     * 2. Makes delete/cancel buttons visible.
     *
     * @param view the view
     */
    protected void editBtnAction(View view) {
        makeRecipeListMultipleSelection();
        modifyButtonVisibility(View.VISIBLE);
        isEditing = true;
    }

    /**
     * Action associated with the cancel button.
     * 1. Makes recipeListView allow no selections
     * 2. Makes delete/cancel buttons invisible.
     *
     * @param view the view
     */
    protected void cancelBtnAction(View view) {
        makeRecipeListNoSelection();
        modifyButtonVisibility(View.INVISIBLE);
        isEditing = false;
    }

    /**
     * Iterates through the recipeListView looking for indexes
     * of selected list items, removes those items from the list
     * and updates the view to reflect the modified list.
     *
     * @param view the view
     */
    protected void deleteBtnAction(View view) {

        // Holds references to recipe names to delete
        ArrayList<String> toDelete = new ArrayList<>();

        // Checks the recipeListView for checked items, and adds the
        // recipe name at that index to the toDelete ArrayList
        SparseBooleanArray checked = recipeListView.getCheckedItemPositions();
        for (int i = 0; i < recipeListView.getCount(); i++) {
            if (checked.get(i)) {
                toDelete.add(recipeNames.get(i));
            }
        }

        // Delete the selected items from the Recipe list and recipe name list
        for (String s : toDelete) {
            for (Recipe r : recipes) {
                if (r.getName().equals(s)) {
                    recipes.remove(r);
                }
            }
            recipeNames.remove(s);
        }

        // Used to refresh the Recipe list view to reflect deletions
        makeRecipeListMultipleSelection();
    }

    /**
     * Used to pass the list of recipes back from the RecipeActivity so that
     * recipes may be edited.
     *
     * @param requestCode the requestCode associated with the ActivityResult
     * @param resultCode  the resultCode associated with the ActivityResult
     * @param intent the source intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RECIPE_REQUEST) {
            if (resultCode == RESULT_OK) {
                recipes = (ArrayList<Recipe>) intent.getSerializableExtra("recipes");
            }
        }

        // Update recipe names with the returned recipeList
        recipeNames = new ArrayList<String>();
        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }

        // Update the adapters to reflect current Recipe models
        noSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recipeNames);
        multipleSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, recipeNames);
        makeRecipeListNoSelection();

    }

    /************************* Private helper functions *************************/
    /**
     * Makes the recipeListView allow no selections.
     */
    private void makeRecipeListNoSelection() {
        recipeListView.setAdapter(noSelectionList);
        recipeListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    /**
     * Makes the recipeListView allow multiple selections.
     */
    private void makeRecipeListMultipleSelection() {
        recipeListView.setAdapter(multipleSelectionList);
        recipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    /**
     * Modifies the visibility of delete/cancel buttons.
     *
     * @param visibility the desired visibility for the buttons
     */
    private void modifyButtonVisibility(int visibility) {
        Button deleteButton = (Button) findViewById(R.id.deleteBtn);
        deleteButton.setVisibility(visibility);
        Button cancelButton = (Button) findViewById(R.id.cancelBtn);
        cancelButton.setVisibility(visibility);
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
                        "2 cups milk\n" +
                        "1 can whipped cream",
                "1. Put ice cream and milk into a blender. \n" +
                        "2. Blend until shake is a creamy consistency. \n" +
                        "3. Pour into glass and cover with whipped cream.");

        Recipe c = new Recipe("Bagel & Lox with Cream Cheese",
                "American",
                "Breakfast",
                20,
                2,
                "4tbs Cream Cheese \n" +
                        "9 oz. Lox \n" +
                        "1 Bagel",
                "1. Cut bagel in half horizontally. \n" +
                        "2. Spread cream cheese on both sides of the bagel. \n" +
                        "3. Cover both sides of the bagel with lox.");
        ArrayList<Recipe> r = new ArrayList<>();
        r.add(a);
        r.add(b);
        r.add(c);
        return r;
    }
}
