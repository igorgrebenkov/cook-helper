package uottawa.ca.cookhelper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    private Recipe recipe;
    private ArrayList<Recipe> recipes;
    private int recipePosition;
    private TextView nameView;
    private TextView countryView;
    private TextView categoryView;
    private TextView cookTimeView;
    private TextView servingSizeView;
    private TextView ingredientsView;
    private TextView instructionsView;
    private ArrayList<TextView> textViews;
    private final static int INPUT_NONE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Get the position of the currently viewed recipe in the recipeList
        // and get the recipe list
        Bundle bundle = getIntent().getExtras();
        for (String key : bundle.keySet()) {
            if (key.equals("position")) {
                recipePosition = (int) bundle.getSerializable(key);
            } else {
                recipes = (ArrayList<Recipe>) bundle.getSerializable(key);
            }
        }
        recipe = recipes.get(recipePosition);

        updateTextViews();
    }

    /**
     * Action associated with the edit button.
     * Makes the text fields editable.
     * @param view the view
     */
    protected void editButtonAction(View view) {
        nameView.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        modifyButtonVisibility(View.VISIBLE);
    }

    protected void cancelButtonAction(View view) {
        modifyButtonVisibility(View.INVISIBLE);
        updateTextViews();
    }

    /**
     * Action associated with the OK button.
     * Update the Recipe's model with the user's edits and hides the keyboard.
     * @param view the view
     */
    protected void okButtonAction(View view) {
        // Hide keyboard
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        nameView.setInputType(INPUT_NONE);
        recipes.get(recipePosition).setName(nameView.getText().toString());
        updateTextViews();
        modifyButtonVisibility(View.INVISIBLE);
    }

    /**
     * Updates the text views with the Recipe model.
     */
    protected void updateTextViews() {
        nameView = (EditText) findViewById(R.id.nameView);
        nameView.setText(recipe.getName());
        nameView.setInputType(INPUT_NONE);
        nameView.setBackgroundColor(0);

        countryView =(TextView) findViewById(R.id.countryView);
        countryView.setText(recipe.getCountryStyle());

        categoryView =(TextView) findViewById(R.id.categoryView);
        categoryView.setText(recipe.getCategoryOfRecipe());

        cookTimeView =(TextView) findViewById(R.id.cookTimeView);
        cookTimeView.setText(recipe.getTimetoCook() + " min.");

        servingSizeView =(TextView) findViewById(R.id.servingSizeView);
        if (recipe.getServingSize() == 1) {
            servingSizeView.setText(recipe.getServingSize() + " person");
        } else {
            servingSizeView.setText(recipe.getServingSize() + " people");
        }

        ingredientsView = (TextView) findViewById(R.id.ingredientsView);
        ingredientsView.setText(recipe.getListOfIngredients());

        instructionsView = (TextView) findViewById(R.id.instructionsView);
        instructionsView.setText(recipe.getInstructions());
    }

    /**
     * Overrides the normal back button function.
     * This method is used to pass the recipes list back to the
     * library activity to save any edits made by the user.
     */
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("recipes", recipes);
        setResult(RESULT_OK, intent);
        finish();
    }

    /************************* Private helper functions *************************/
    /**
     * Modifies the visibility of delete/cancel buttons.
     *
     * @param visibility the desired visibility for the buttons
     */
    private void modifyButtonVisibility(int visibility) {
        Button okButton = (Button) findViewById(R.id.okBtn);
        okButton.setVisibility(visibility);
        Button cancelButton = (Button) findViewById(R.id.cancelBtn);
        cancelButton.setVisibility(visibility);
    }
}
