package uottawa.ca.cookhelper;

import java.util.ArrayList;

public class SearchEngine {
    ArrayList<Recipe> recipes;  // The list of recipes
    String searchString;        // The user's input in string form
    ArrayList<String> tokens;

    public SearchEngine(ArrayList<Recipe> recipes, String searchString) {
        this.recipes = recipes;
        this.searchString = searchString;
        tokens = tokenize(searchString);
    }

    private ArrayList<String> tokenize(String s) {
        ArrayList<String> ret = new ArrayList<>();
        String token = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                token += c;
            } else {
                ret.add(token);
                token = "";
            }
        }
        ret.add(token);
        return ret;
    }

    public void printTokens() {
        for (String s : tokens) {
            System.out.println(s);
        }
    }
}
