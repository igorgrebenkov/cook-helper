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
import java.util.HashSet;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipes;                      // The list of recipes
    private ArrayList<String> searchResultNames;                  // The list of recipe names
    private HashSet<Recipe> searchResults;
    private ListView searchResultView;                // ListView for Recipes
    private ArrayAdapter<String> noSelectionList;   // Adapter w/ no selection
    private Recipe recipeCache;
    private final static int SEARCH_RECIPE_REQUEST = 1;  // Used to return recipes list from RecipeActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recipes = (ArrayList<Recipe>) getIntent().getSerializableExtra("recipeList");

        searchResultNames = new ArrayList<>();

        // Adapter init
        searchResultView = (ListView) findViewById(R.id.searchResultsListView);

        noSelectionList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResultNames);
        searchResultView.setAdapter(noSelectionList);

        // If the noSelectionList adapter is active, the onItemClickListener
        // will open the recipe selected by the user in a RecipeActivity
        searchResultView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                ArrayList<Recipe> searchList = new ArrayList<>(searchResults);
                recipeCache = searchList.get(position);
                int recipePosition = 0;

                for (Recipe r : recipes) {
                    if (r.getName().equals(recipeCache.getName())) {
                        recipePosition = recipes.indexOf(r);
                    }
                }
                searchResults.remove(recipeCache);
                // Use bundle to pass serialized data to RecipeActivity
                Bundle bundle = new Bundle();
                bundle.putSerializable("position", recipePosition);  // index of clicked recipe
                bundle.putSerializable("recipeList", recipes);
                Intent i = new Intent(SearchActivity.this, RecipeActivity.class);
                i.putExtras(bundle);
                startActivityForResult(i, SEARCH_RECIPE_REQUEST);
            }
        });
    }

    protected void searchButtonAction(View view) {
        searchResultNames = new ArrayList<>();

        TextView searchView = (TextView) findViewById(R.id.searchText);
        String searchString = searchView.getText().toString();
        SearchEngine s = new SearchEngine(recipes, searchString);
        searchResults = s.getSearchResults();

        for (Recipe r : searchResults) {
            searchResultNames.add(r.getName());
        }

        noSelectionList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResultNames);
        searchResultView.setAdapter(noSelectionList);
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
        Recipe editedRecipe = null;
        if (requestCode == SEARCH_RECIPE_REQUEST) {
            if (resultCode == RESULT_OK) {
                recipes = (ArrayList<Recipe>) intent.getSerializableExtra("recipes");
                editedRecipe = (Recipe) intent.getSerializableExtra("recipe");
            }
        }

        for (Recipe r : recipes) {
            if (r.getName().equals(editedRecipe.getName())) {
                searchResults.add(r);
            }
        }

        searchResultNames = new ArrayList<>();
        for (Recipe r : searchResults) {
            searchResultNames.add(r.getName());
        }

        noSelectionList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResultNames);
        searchResultView.setAdapter(noSelectionList);
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
