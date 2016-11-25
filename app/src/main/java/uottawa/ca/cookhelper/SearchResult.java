package uottawa.ca.cookhelper;


public class SearchResult {
    Recipe recipe;
    private int matchCount;
    public SearchResult(Recipe r) {
        recipe = r;
        matchCount = 0;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void incrementMatchCount() {
        matchCount++;
    }
}
