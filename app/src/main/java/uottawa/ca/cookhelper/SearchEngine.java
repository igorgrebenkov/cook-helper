package uottawa.ca.cookhelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class SearchEngine {
    private ArrayList<Recipe> recipes;  // The list of recipes
    private String searchString;        // The user's input in string form
    private ArrayList<String> tokens;
    private ArrayList<String> postFix;
    private HashSet<Recipe> searchResults;

    public SearchEngine(ArrayList<Recipe> recipes, String searchString) {
        this.recipes = recipes;
        this.searchString = searchString;
        tokens = tokenize(searchString);
        postFix = toPostfix(tokens);
        searchResults = evaluate();
    }

    public HashSet<Recipe> getSearchResults() {
        return searchResults;
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

        if (!token.equals("")) {
            ret.add(token);
        }

        for (int i = 0; i < ret.size() - 1; i++) {
            String curr = ret.get(i);
            String next = ret.get(i + 1);

            if (!(curr.equals("(") || curr.equals("AND") || curr.equals("OR"))) {
                if (!(next.equals(")") || next.equals("AND") || next.equals("OR"))) {
                    curr = curr + " " + next;
                    ret.add(i, curr);
                    ret.remove(i + 1);
                    ret.remove(i + 1);
                }
            }
        }
        return ret;
    }

    private ArrayList<String> toPostfix(ArrayList<String> arr) {
        Stack<String> op = new Stack<>();
        ArrayList<String> output = new ArrayList<>();

        for (String t : arr) {
            if (t.equals("AND") || t.equals("OR") || t.equals("NOT")) {
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
            if (s.equals("AND") || s.equals("OR") || s.equals("NOT")) {
                output.add(s);
            }
        }
        return output;
    }

    private HashSet<Recipe> evaluate() {
        Stack<String> eval = new Stack<>();
        ArrayList<String> operands = new ArrayList<>();
        HashSet<Recipe> evaluated = new HashSet<>();
        String lastOperator = "";

        if (postFix.size() == 1) {
            for (Recipe r : recipes) {
                for (String t : postFix) {
                    if (r.getListOfIngredients().contains(t) ||
                            r.getCategoryOfRecipe().equals(t) ||
                            r.getCountryStyle().equals(t)) {
                        evaluated.add(r);
                    }
                }
            }
        } else {
            for (String t : postFix) {
                if (t.equals("AND") || t.equals("OR") || t.equals("NOT")) {
                    lastOperator = t;
                    while (!eval.isEmpty()) {
                        operands.add(eval.pop());
                    }
                    evaluated.addAll(performOperation(t, operands, evaluated));
                    operands = new ArrayList<>();
                } else {
                    eval.push(t);
                }
            }
        }
        return evaluated;
    }

    private HashSet<Recipe> performOperation(String operator, ArrayList<String> operands,
                                             HashSet<Recipe> currentSearchResults) {
        HashSet<Recipe> result = new HashSet<>();


        if (currentSearchResults != null &&
                operands.size() == 1 &&
                operator.equals("AND")) {
            Iterator<Recipe> i = currentSearchResults.iterator();

            while (i.hasNext()) {
                Recipe r = i.next();
                if (!r.getListOfIngredients().contains(operands.get(0)) &&
                        !r.getCategoryOfRecipe().equals(operands.get(0)) &&
                        !r.getCountryStyle().equals(operands.get(0))) {
                    i.remove();
                }
            }

            for (Recipe r : currentSearchResults) {

            }
        } else if (operator.equals("OR")) {
            for (Recipe r : recipes) {
                for (String o : operands) {
                    if (r.getListOfIngredients().contains(o) ||
                            r.getCategoryOfRecipe().equals(o) ||
                            r.getCountryStyle().equals(o)) {
                        result.add(r);
                        //s.incrementMatchCount();
                    }
                }
            }
        } else if (operator.equals("AND")) {
            for (Recipe r : recipes) {
                int matchCount = 0;
                for (String o : operands) {
                    if (r.getListOfIngredients().contains(o) ||
                            r.getCategoryOfRecipe().equals(o) ||
                            r.getCountryStyle().equals(o)) {
                        matchCount++;
                    }
                }
                if (matchCount == operands.size() && matchCount != 0) {
                    result.add(r);
                    //s.incrementMatchCount();
                }
            }
        }
        return result;
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

    public void printSearchResults() {
        for (Recipe r : searchResults) {
            System.out.println(r.getName());
        }
    }
}

