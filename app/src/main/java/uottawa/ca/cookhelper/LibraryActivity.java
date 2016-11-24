package uottawa.ca.cookhelper;

import android.nfc.Tag;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static uottawa.ca.cookhelper.R.id.deleteBtn;

public class LibraryActivity extends AppCompatActivity {

    ArrayList<String> test = new ArrayList<String>();
    ListView recipeListView;                    // ListView for Recipes
    ArrayAdapter<String> noSelectionList;   // Adapter for recipeListView w/ no selection
    ArrayAdapter<String> multipleSelectionList; // Adapter for recipeListView w/ multiple selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        test.add("test1");
        test.add("test2");
        test.add("test3");
        test.add("test4");
        test.add("test5");

        recipeListView = (ListView) findViewById(R.id.recipeListView);
        noSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, test);
        multipleSelectionList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, test);

        recipeListView.setAdapter(noSelectionList);
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

        // Holds references to Recipes to delete
        ArrayList<String> toDelete = new ArrayList<>();

        // Checks the recipeListView for checked items, and adds the
        // Recipes at that index to the toDelete ArrayList
        SparseBooleanArray checked = recipeListView.getCheckedItemPositions();
        for (int i = 0; i < recipeListView.getCount(); i++) {
            if (checked.get(i)) {
                toDelete.add(test.get(i));
            }
        }

        // Delete the selected items from the Recipe list
        for (String s : toDelete) {
            test.remove(s);
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
}
