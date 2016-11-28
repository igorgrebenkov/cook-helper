package uottawa.ca.cookhelper;

import java.util.ArrayList;

/**
 * The static class RecipeGenerator has one method that creates a list of
 * example recipes that are loaded by the app for demonstration.
 */
public class RecipeGenerator {

    /**
     * Creates a list of example recipes.
     * @return the list of example recipes
     */
    public static ArrayList<Recipe> generateRecipes() {
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
                "4 scoops chocolate Ice Cream\n" +
                        "2 cups Milk\n" +
                        "1 can Whipped Cream",
                "1. Put ice cream and milk into a blender.\n" +
                        "2. Blend until shake is a creamy consistency.\n" +
                        "3. Pour into glass and cover with whipped cream.");

        Recipe c = new Recipe("Bagel Cream Cheese & Lox",
                "American",
                "Breakfast",
                10,
                1,
                "4tbs Cream Cheese \n" +
                        "9 oz. Lox \n" +
                        "1 Bagel",
                "1. Cut bagel in half horizontally. \n" +
                        "2. Spread cream cheese on both sides of the bagel. \n" +
                        "3. Cover both sides of the bagel with lox.");

        Recipe d = new Recipe("Bagel with Cream Cheese",
                "American",
                "Breakfast",
                10,
                1,
                "4tbs Cream Cheese \n" +
                        "1 Bagel",
                "1. Cut bagel in half horizontally. \n" +
                        "2. Spread cream cheese on both sides of the bagel.");

        Recipe e = new Recipe("Bagel w/ Cream Cheese, Lox & Onions",
                "American",
                "Breakfast",
                10,
                1,
                "4tbs Cream Cheese \n" +
                        "1 Bagel",
                "1. Cut bagel in half horizontally. \n" +
                        "2. Spread cream cheese on both sides of the bagel. \n" +
                        "3. Cut onions into thin slices and place on the bagel.\n" +
                        "4. Cover both sides of the bagel with lox.");

        Recipe f = new Recipe("Hamburger",
                "American",
                "Dinner",
                45,
                4,
                "1 lb. Ground Beef \n" +
                        "4 Hamburger Buns\n" +
                        "1 Onion\n" +
                        "Ketchup\n" +
                        "Mustard\n" +
                        "2 tsp. Salt\n" +
                        "2 tsp. Pepper",
                "1. Season ground beef well with salt and pepper and mix thoroughly. \n" +
                        "2. Separate ground beef into 4 portions and form into patties. \n" +
                        "3. Cook patties in an oiled, hot pan at" +
                        "med-hi heat for four minutes a side.\n" +
                        "4. Put patties on buns and top with sliced onion, ketchup and mustard.");

        Recipe g = new Recipe("Cheeseburger",
                "American",
                "Dinner",
                45,
                4,
                "1 lb. Ground Beef \n" +
                        "4 Hamburger Buns\n" +
                        "4 slices American cheese\n" +
                        "1 Onion\n" +
                        "Ketchup\n" +
                        "Mustard\n" +
                        "2 tsp. Salt\n" +
                        "2 tsp. Pepper",
                "1. Season ground beef well with salt and pepper and mix thoroughly. \n" +
                        "2. Separate ground beef into 4 portions and form into patties. \n" +
                        "3. Cook patties in an oiled, hot pan at " +
                        "med-hi heat for four minutes a side.\n" +
                        "4. Place cheese on patties.\n" +
                        "5. Put patties on buns and top with sliced onion, ketchup and mustard.\n");

        Recipe h = new Recipe("Bacon Cheeseburger",
                "American",
                "Dinner",
                45,
                4,
                "1 lb. Ground Beef \n" +
                        "4 Hamburger Buns\n" +
                        "4 slices American cheese\n" +
                        "8 slices Bacon\n" +
                        "1 Onion\n" +
                        "Ketchup\n" +
                        "Mustard\n" +
                        "2 tsp. Salt\n" +
                        "2 tsp. Pepper",
                "1. Season ground beef well with salt and pepper and mix thoroughly. \n" +
                        "2. Separate ground beef into 4 portions and form into patties. \n" +
                        "3. Cook patties in an oiled, hot pan at " +
                        "med-hi heat for four minutes a side.\n" +
                        "4. While patties are cooking, fry bacon " +
                        "in a pan at med heat until crispy.\n" +
                        "5. Place cheese and bacon on patties.\n" +
                        "6. Put patties on buns and top with sliced onion, ketchup and mustard.");

        Recipe i = new Recipe("Poutine",
                "Canadian",
                "Lunch",
                35,
                2,
                "1 lb. Frozen French Fries \n" +
                        "1 Can Beef Gravy\n" +
                        "1 lb Cheese Curds",
                "1. Deep fry french fries at 375 F for about 12 minutes\n " +
                        "until they are golden brown and crispy. \n" +
                        "2. While french fries are frying, heat " +
                        "gravy in a saucepan on med-hi heat. \n" +
                        "3. Serve fries in two bowls, and cover with hot gravy.\n" +
                        "4. Top with cheese curds.");

        Recipe j = new Recipe("Bacon Poutine",
                "Canadian",
                "Lunch",
                35,
                2,
                "1 lb. Frozen French Fries \n" +
                        "1 Can Beef Gravy\n" +
                        "1 lb Cheese Curds\n" +
                        "1/2 lb Minced Bacon",
                "1. Deep fry french fries at 375 F for about 12 minutes\n" +
                        "until they are golden brown and crispy. \n" +
                        "2. While french fries are frying, heat " +
                        "gravy in a saucepan on med-hi heat. \n" +
                        "3. Serve fries in two bowls, and cover with hot gravy.\n" +
                        "4. Top with cheese curds and bacon.");

        Recipe k = new Recipe("Crepes",
                "French",
                "Breakfast",
                60,
                8,
                "2 Eggs \n" +
                        "1 cup Milk\n" +
                        "2/3 cup All-Purpose Flour\n" +
                        "1 pinch Salt\n" +
                        "1 1/2 tsp Vegetable Oil",
                "1. In a blender combine eggs, milk, flour, salt and oil.\n" +
                        "2. Process until smooth.\n" +
                        "3. Cover and refrigerate for 30 minutes.\n" +
                        "4. Heat skillet over med-hi heat and brush with oil.\n" +
                        "5. Pour 1/4 cup of crepe batter into pan while tilting it to completely" +
                        "coat the surface.\n" +
                        "6. Cook 2 to 5 minutes, turning once, until golden.\n" +
                        "7. Repeat for remaining batter.");

        Recipe l = new Recipe("Oranges in Red Wine",
                "Italian",
                "Dessert",
                20,
                12,
                "6 Large Navel Oranges \n" +
                        "1 750mL bottle Italian Red Wine",
                "1. Divide oranges among 12 (5-to-8 ounce) juice glasses.\n" +
                        "2. Top with wine and let stand for 5 minutes.");

        Recipe m = new Recipe("Cheese Pizza",
                "Italian",
                "Lunch",
                30,
                2,
                "1 12-inch Pre-Baked Pizza Crust \n" +
                        "1 can Tomato Sauce\n" +
                        "4 oz. Shredded Mozzarella Cheese",
                "1. Preheat oven to 400 F.\n" +
                        "2. Cover pizza crust with an even layer of tomato sauce.\n" +
                        "3. Sprinkle cheese on pizza until surface is covered.\n" +
                        "4. Bake until cheese is golden brown and " +
                        "bubbly (approximately 10 minutes).");

        Recipe n = new Recipe("Pepperoni Pizza",
                "Italian",
                "Lunch",
                30,
                2,
                "1 12-inch Pre-Baked Pizza Crust \n" +
                        "1 can Tomato Sauce\n" +
                        "4 oz. Shredded Mozzarella Cheese\n" +
                        "10 oz. Sliced Pepperoni",
                "1. Preheat oven to 400 F." +
                        "2. Cover pizza crust with an even layer of tomato sauce.\n" +
                        "3. Sprinkle cheese on pizza until surface is covered.\n" +
                        "4. Top with pepperoni.\n" +
                        "5. Bake until cheese is golden brown and " +
                        "bubbly (approximately 10 minutes).");

        ArrayList<Recipe> r = new ArrayList<>();
        r.add(a);
        r.add(b);
        r.add(c);
        r.add(d);
        r.add(e);
        r.add(f);
        r.add(g);
        r.add(h);
        r.add(i);
        r.add(j);
        r.add(k);
        r.add(l);
        r.add(m);
        r.add(n);
        return r;
    }
}
