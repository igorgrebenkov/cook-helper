package uottawa.ca.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ArrayList<Recipe> recipes;                      // The list of recipes
    ArrayList<String> recipeNames;                  // The list of recipe names
    private ListView recipeListView;                // ListView for Recipes
    private ArrayAdapter<String> noSelectionList;   // Adapter w/ no selection
    private final static int RECIPE_REQUEST = 0;  // Used to return recipes list from RecipeActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recipes = (ArrayList<Recipe>) getIntent().getSerializableExtra("recipeList");

        // Recipe names used for recipeListView labels
        recipeNames = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }

        // Adapter init
        recipeListView = (ListView) findViewById(R.id.searchResultsListView);

        noSelectionList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, recipeNames);
        recipeListView.setAdapter(noSelectionList);

        // If the noSelectionList adapter is active, the onItemClickListener
        // will open the recipe selected by the user in a RecipeActivity
        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                // Use bundle to pass serialized data to RecipeActivity
                Bundle bundle = new Bundle();
                bundle.putSerializable("position", position);  // index of clicked recipe
                bundle.putSerializable("recipeList", recipes);
                Intent i = new Intent(SearchActivity.this, RecipeActivity.class);
                i.putExtras(bundle);
                startActivityForResult(i, RECIPE_REQUEST);
            }
        });
    }

    protected void searchButtonAction(View view) {
        TextView searchView = (TextView) findViewById(R.id.searchText);
        String searchString = searchView.getText().toString();
        SearchEngine s = new SearchEngine(recipes, searchString);
        s.printPostFix();
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

        // Update recipe names with the returned recipeList
        recipeNames = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }

        // Update the adapters to reflect current Recipe models
        noSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recipeNames);
        recipeListView.setAdapter(noSelectionList);

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
}
