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
import java.util.Collections;
import java.util.Iterator;

/**
 * The class LibraryActivity is the screen where users may view the list of recipes.
 * Recipes are sorted in alphabetical order.
 * <p>
 * It extends AppCompatActivity.
 */
public class LibraryActivity extends AppCompatActivity {

    private ArrayList<Recipe> recipes;                  // List of recipes
    private ArrayList<String> recipeNames;              // List of recipe names
    private ListView recipeListView;                    // ListView for Recipes
    private ArrayAdapter<String> noSelectionList;       // Adapter w/ no selection
    private ArrayAdapter<String> multipleSelectionList; // Adapter w/ multiple selection
    private boolean isEditing;                          // Flag true -> recipe list is being edited
    private final static int RECIPE_REQUEST = 0;        // Used to pass recipe list to RecipeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        recipes = (ArrayList<Recipe>) getIntent().getSerializableExtra("recipeList");
        Collections.sort(recipes); // Sort for alphabetical order

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
                    switchToRecipeActivity(position);
                }
            }
        });
    }


    protected void addButtonAction(View view) {
        recipes.add(new Recipe());
        switchToRecipeActivity(recipes.size() - 1);
    }

    /**
     * Action associated with the edit button.
     * 1. Makes recipeListView allow multiple selections (for deleting)
     * 2. Makes delete/cancel buttons visible.
     *
     * @param view the view
     */
    protected void editBtnAction(View view) {
        isEditing = true;
        makeRecipeListMultipleSelection();
        modifyButtonVisibility(View.VISIBLE);
    }

    /**
     * Action associated with the cancel button.
     * 1. Makes recipeListView allow no selections
     * 2. Makes delete/cancel buttons invisible.
     *
     * @param view the view
     */
    protected void cancelBtnAction(View view) {
        isEditing = false;
        makeRecipeListNoSelection();
        modifyButtonVisibility(View.INVISIBLE);
    }

    /**
     * Iterates through the recipeListView looking for indexes
     * of selected list items, removes those items from the list
     * and updates the view to reflect the modified list.
     *
     * @param view the view
     */
    protected void deleteBtnAction(View view) {
        // Holds references to the recipe names we want to delete
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
        Iterator<Recipe> iterRecipes = recipes.iterator();
        Iterator<String> iterRecipeNames = recipeNames.iterator();

        // Remove recipe names for recipes that were deleted the list
        while (iterRecipeNames.hasNext()) {
            String next = iterRecipeNames.next();
            for (String n : toDelete) {
                if (next.equals(n)) {
                    iterRecipeNames.remove();
                }
            }
        }

        // Remove recipes that were deleted from the list
        while (iterRecipes.hasNext()) {
            Recipe next = iterRecipes.next();
            for (String n : toDelete) {
                if (next.getName().equals(n)) {
                    iterRecipes.remove();
                }
            }
        }
        // Used to refresh the Recipe list view to reflect deletions
        makeRecipeListMultipleSelection();
    }

    /**
     * Overrides the normal back button function.
     * This method is used to pass the recipes list back to the
     * MainActivity to save any edits made by the user.
     */
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("recipes", recipes);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Used to get the list of recipes from the RecipeActivity so that
     * recipes may be edited.
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
        Collections.sort(recipes);  // Re-sort for alphabetical order
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

    private void switchToRecipeActivity(int position) {
        // Use bundle to pass serialized data to RecipeActivity
        Bundle bundle = new Bundle();
        bundle.putSerializable("position", position);  // index of clicked recipe
        bundle.putSerializable("recipeList", recipes);
        Intent i = new Intent(LibraryActivity.this, RecipeActivity.class);
        i.putExtras(bundle);
        startActivityForResult(i, RECIPE_REQUEST);
    }
}
