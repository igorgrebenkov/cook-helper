package uottawa.ca.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {
    Recipe recipe;
    TextView nameView;
    TextView countryView;
    TextView categoryView;
    TextView cookTimeView;
    TextView servingSizeView;
    TextView ingredientsView;
    TextView instructionsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Bundle bundle = getIntent().getExtras();
        for (String key : bundle.keySet()) {
            recipe = (Recipe) bundle.getSerializable(key);
        }

        nameView = (TextView) findViewById(R.id.nameView);
        nameView.setText(recipe.getName());

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

    protected void editButtonAction(View view) {



    }
}
