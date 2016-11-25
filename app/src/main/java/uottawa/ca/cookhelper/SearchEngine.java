package uottawa.ca.cookhelper;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class SearchEngine {
    ArrayList<Recipe> recipes;  // The list of recipes
    String searchString;        // The user's input in string form
    ArrayList<String> tokens;
    ArrayList<String> postFix;

    public SearchEngine(ArrayList<Recipe> recipes, String searchString) {
        this.recipes = recipes;
        this.searchString = searchString;
        tokens = tokenize(searchString);
        postFix = toPostfix(tokens);
    }

    private ArrayList<String> tokenize(String s) {
        ArrayList<String> ret = new ArrayList<>();
        String token = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                ret.add(Character.toString(c));
            } else if (c == ')') {
                ret.add(token);
                token = "";
                ret.add(Character.toString(c));
            } else if (c != ' ') {
                token += c;
            } else {
                if (!token.equals("")) {
                    ret.add(token);
                    token = "";
                }
            }
        }
        return ret;
    }

    private ArrayList<String> toPostfix(ArrayList<String> arr) {
        Stack<String> op = new Stack<>();
        ArrayList<String> output = new ArrayList<>();

        for (String t : arr) {
            if (t.equals("AND") || t.equals("OR")) {
                op.push(t);
            } else if (t.equals("(")) {
                op.push(t);
            } else if (t.equals(")")) {
                String popped = op.pop();
                while (!popped.equals("(")) {
                    output.add(popped);
                    popped = op.pop();
                }
            } else {
                output.add(t);
            }
        }

        for (String s : op) {
            if (s.equals("AND") || s.equals("OR")) {
                output.add(s);
            }
        }
        return output;
    }

    public void printTokens() {
        for (String s : tokens) {
            System.out.println(s);
        }
    }

    public void printPostFix() {
        for (String s : postFix) {
            System.out.println(s + " ");
        }
    }
}

