package uottawa.ca.cookhelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class SearchEngine {
    private ArrayList<Recipe> recipes;          // The list of recipes
    private ArrayList<String> tokens;           // The list of tokens
    private ArrayList<String> postFix;          // The list of tokens in postfix notation
    private HashSet<Recipe> searchResults;      // The set of search results
    private ArrayList<Recipe> sortedSearchResults;

    public SearchEngine(ArrayList<Recipe> recipes, String searchString) {
        this.recipes = recipes;
        tokens = tokenize(searchString);
        postFix = toPostfix(tokens);
        searchResults = evaluate();
        sortedSearchResults = new ArrayList<>();
        sortedSearchResults.addAll(searchResults);
        Collections.sort(sortedSearchResults);
        Collections.reverse(sortedSearchResults);
    }

    /**
     * Getter for the Set of search results.
     *
     * @return the set of search results
     */
    public HashSet<Recipe> getSearchResults() {
        return searchResults;
    }

    /**
     * Getter for search results sorted by match count.
     * @return the sorted search results
     */
    public ArrayList<Recipe> getSortedSearchResults() {
        return sortedSearchResults;
    }

    /**
     * A simple tokenizer that parses the user's search string into individual tokens.
     *
     * @param s the string to tokenize
     * @return a list of tokens
     */
    private ArrayList<String> tokenize(String s) {
        ArrayList<String> ret = new ArrayList<>();
        String token = "";

        // Build token list
        //   - Each word separated by white space is saved
        //     in a string and added to the list of tokens.
        //   - Parentheses are also saved as tokens since they
        //     may be used to indicate operator precedence
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                ret.add(Character.toString(c));
            } else if (c == ')') {
                if (!token.equals("")) {
                    ret.add(token);
                    token = "";
                }
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

        // Add remaining token if it exists
        if (!token.equals("")) {
            ret.add(token);
        }

        // When searching for ingredients, we may want a token
        // that is longer than one word. e.g., "ice cream".
        //
        // This section iterates through the tokens and checks if the
        // token following it  in the list is an operator. If it is not, the current and
        // next string are concatenated with a space and added to the token list.
        for (int i = 0; i < ret.size() - 1; i++) {
            String curr = ret.get(i);
            String next = ret.get(i + 1);
            if (!(curr.equals("(") ||
                    curr.equals("AND") ||
                    curr.equals("OR") ||
                    curr.equals("NOT"))) {
                if (!(next.equals(")") ||
                        next.equals("AND") ||
                        next.equals("OR") ||
                        next.equals("NOT"))) {
                    curr = curr + " " + next;
                    ret.add(i, curr);
                    ret.remove(i + 1);
                    ret.remove(i + 1);
                }
            }
        }
        return ret;
    }

    /**
     * Converts a list of tokens in infix notation to a
     * list of tokens in postfix notation.
     * @param arr the list of tokens in infix notation
     * @return the list of tokens in postfix notation
     */
    private ArrayList<String> toPostfix(ArrayList<String> arr) {
        Stack<String> op = new Stack<>();               // stack for operator tokens
        ArrayList<String> output = new ArrayList<>();   // the postfix token list

        // When an operator or open parentheses is encountered, push to stack op.
        // When a non-operator is encountered, add it to the output list.
        // When a closed parentheses is encountered, pop everything off the
        // stack to the output list until we pop an open parentheses.
        for (String t : arr) {
            switch (t) {
                case "AND":
                case "OR":
                case "NOT":
                    op.push(t);
                    break;
                case "(":
                    op.push(t);
                    break;
                case ")":
                    String popped = op.pop();
                    while (!popped.equals("(")) {
                        output.add(popped);
                        popped = op.pop();
                    }
                    break;
                default:
                    output.add(t);
                    break;
            }
        }

        // Adds any remaining operators to the output list
        for (String s : op) {
            if (s.equals("AND") || s.equals("OR") || s.equals("NOT")) {
                output.add(s);
            }
        }
        return output;
    }

    /**
     * Evaluates a boolean expression in postfix notation from a list of tokens.
     * The result is a HashSet of recipes that comprise the search results.
     * A HashSet was used primarily because it avoids adding duplicate entries.
     * @return the HashSet of search results
     */
    private HashSet<Recipe> evaluate() {
        Stack<String> toEvaluate = new Stack<>();       // Operands for an operation are pushed here
        ArrayList<String> operands = new ArrayList<>(); // List of operands for an operation
        HashSet<Recipe> evaluated = new HashSet<>();    // The search result HashSet

        // Case with just a single token
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
            // Iterate through the tokens
            // If not an operand, push the token to the toEvaluate stack.
            // If an operand, generate operand list by popping until toEvaluate is empty
            // and perform the operation.
            for (String t : postFix) {
                if (t.equals("AND") || t.equals("OR")) {
                    while (!toEvaluate.isEmpty()) {
                        operands.add(toEvaluate.pop());
                    }
                    evaluated.addAll(performOperation(t, operands, evaluated));
                    operands = new ArrayList<>();
                } else if (t.equals("NOT")){
                    operands.add(toEvaluate.pop());
                    evaluated.addAll(performOperation(t, operands, evaluated));
                    operands = new ArrayList<>();
                } else {
                    toEvaluate.push(t);
                }
            }
        }
        return evaluated;
    }

    /**
     * Performs a boolean operation consisting of one operator and a set of operands.
     * @param operator the operator
     * @param operands the operands
     * @param currentSearchResults the current list of recipes in the search results
     * @return
     */
    private HashSet<Recipe> performOperation(String operator, ArrayList<String> operands,
                                             HashSet<Recipe> currentSearchResults) {
        HashSet<Recipe> result = new HashSet<>();
        switch (operator) {
            // Since matches are sorted by rank, AND and OR operations have the same
            // code: we check if there's a match, and increment that recipe's matchCount
            //
            // Say we have recipeA that has tomatoes and onions
            // and we have recipeB that has tomatoes only
            // (Tomatoes AND Onions) would rank recipeA as the best choice
            // (Tomatoes OR Onions) would also rank recipeA as the best choice,
            //  since it has more matches.
            // The operator really makes no difference with AND/OR to get the correct ranking.
            case "AND":
            case "OR":
                for (Recipe r : recipes) {
                    for (String o : operands) {
                        if (r.getListOfIngredients().contains(o) ||
                                r.getCategoryOfRecipe().equals(o) ||
                                r.getCountryStyle().equals(o)) {
                            r.incrementMatchCount();
                            result.add(r);
                        }
                    }
                }
                break;
            case "NOT":
                for (Recipe r : recipes) {
                    for (String o : operands) {
                        if (!r.getListOfIngredients().contains(o) &&
                                !r.getCategoryOfRecipe().equals(o) &&
                                !r.getCountryStyle().equals(o)) {
                            r.incrementMatchCount();
                            result.add(r);
                        }
                    }
                }
                break;
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

