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
import java.util.Collections;
import java.util.HashSet;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipes;                   // The list of recipes
    private ArrayList<String> searchResultNames;         // List of recipe names in search results
    private HashSet<Recipe> searchResults;               // HashSet of search results
    private ArrayList<Recipe> sortedSearchResults;       // List of search results sorted by rank
    private ListView searchResultView;                   // ListView for Recipes
    private ArrayAdapter<String> noSelectionList;        // Adapter w/ no list selection
    private final static int SEARCH_RECIPE_REQUEST = 1;  // For returning data from RecipeActivity


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
                // Save the recipe just clicked on so we can fetch its position in the recipe list
                Recipe recipeCache = sortedSearchResults.get(position);
                int recipePosition = 0;

                // Find the index of Recipe recipeCache in the recipe list
                for (Recipe r : recipes) {
                    if (r.getName().equals(recipeCache.getName())) {
                        recipePosition = recipes.indexOf(r);
                    }
                }
                // Remove it since it may be edited
                // (we will re-add it when returning to this activity)
                searchResults.remove(recipeCache);

                // Use bundle to pass serialized data to RecipeActivity
                Bundle bundle = new Bundle();
                bundle.putSerializable("position", recipePosition);
                bundle.putSerializable("recipeList", recipes);
                Intent i = new Intent(SearchActivity.this, RecipeActivity.class);
                i.putExtras(bundle);
                startActivityForResult(i, SEARCH_RECIPE_REQUEST);
            }
        });
    }

    /**
     * Action associated with the Search button.
     *
     * @param view the view
     */
    protected void searchButtonAction(View view) {
        // Reset all match counts
        for (Recipe r : recipes) {
            r.resetMatchCount();
        }

        TextView searchView = (TextView) findViewById(R.id.searchText);
        String searchString = searchView.getText().toString();
        SearchEngine s = new SearchEngine(recipes, searchString);
        searchResults = s.getSearchResults();
        sortedSearchResults = s.getSortedSearchResults();

        s.printPostFix();

        // Update ListView contents
        refreshSearchResultNames();
        refreshListView();
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

        // Get list of recipes and the (possibly) edited recipe from the last activity.
        if (requestCode == SEARCH_RECIPE_REQUEST) {
            if (resultCode == RESULT_OK) {
                recipes = (ArrayList<Recipe>) intent.getSerializableExtra("recipes");
                editedRecipe = (Recipe) intent.getSerializableExtra("recipe");
            }
        }

        // Add the edited recipe to the search results
        // (remember we removed it earlier in the onItemClick method in case it will be edited)
        for (Recipe r : recipes) {
            if (r.getName().equals(editedRecipe.getName())) {
                searchResults.add(r);
            }
        }

        // searchResult HashSet may have changed (if an edit was made), so
        // we must re-create the sortedSearchResults ArrayList
        sortedSearchResults = new ArrayList<>();
        sortedSearchResults.addAll(searchResults);
        Collections.sort(sortedSearchResults); // re-sort after editing recipe
        Collections.reverse(sortedSearchResults);

        // Update ListView contents
        refreshSearchResultNames();
        refreshListView();
    }

    /**
     * Overrides the normal back button function.
     * This method is used to pass the recipes list back to the
     * MainActivity to save any edits made by the user.
     */
    @Override
    public void onBackPressed() {
        // Reset match counts
        for (Recipe r : recipes) {
            r.resetMatchCount();
        }

        Intent intent = getIntent();
        intent.putExtra("recipes", recipes);
        setResult(RESULT_OK, intent);
        finish();
    }

    /************************* Private helper functions *************************/
    /**
     * Creates and sets a new Adapter to refresh the ListView contents.
     */
    private void refreshListView() {
        noSelectionList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, searchResultNames);
        searchResultView.setAdapter(noSelectionList);
    }

    /**
     * Re-initializes the searchResultNames ArrayList with the current search results.
     */
    private void refreshSearchResultNames() {
        searchResultNames = new ArrayList<>();
        for (Recipe r : sortedSearchResults) {
            searchResultNames.add(r.getName());
        }
    }
}
