package io.codebards.veganrealm.api;

import java.util.ArrayList;
import java.util.List;

public class Search {
    private String terms;
    private int total = 0;
    private int offset = 0;
    private List<Recipe> recipes = new ArrayList<>();

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }


    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
