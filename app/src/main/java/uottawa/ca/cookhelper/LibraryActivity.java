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

    ArrayList<Recipe> recipes;                  // List of recipes
    ArrayList<String> recipeNames;              // List of recipe names
    ListView recipeListView;                    // ListView for Recipes
    ArrayAdapter<String> noSelectionList;       // Adapter for recipeListView w/ no selection
    ArrayAdapter<String> multipleSelectionList; // Adapter for recipeListView w/ multiple selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        recipes = createrecipeRecipes();
        recipeNames = new ArrayList<>();

        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }

        recipeListView = (ListView) findViewById(R.id.recipeListView);
        noSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recipeNames);
        multipleSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, recipeNames);

        recipeListView.setAdapter(noSelectionList);

        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                bundle.putSerializable("recipe", recipes.get(position));
                Intent i = new Intent(LibraryActivity.this, RecipeActivity.class);
                i.putExtras(bundle);
                startActivity(i);

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

    private ArrayList<Recipe> createrecipeRecipes() {
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
