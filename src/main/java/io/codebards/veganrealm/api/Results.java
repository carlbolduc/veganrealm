package io.codebards.veganrealm.api;

import java.util.List;

public class Results {
    private List<Recipe> recipes;
    private Boolean hasMore;
    private Long nextOffset;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Long getNextOffset() {
        return nextOffset;
    }
    
    public void setNextOffset(Long nextOffset) {
        this.nextOffset = nextOffset;
    }

}
