package io.codebards.veganrealm.views;

import io.codebards.veganrealm.api.Recipe;
import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class HomeView extends View {
    private List<Recipe> recipes;
    public HomeView(List<Recipe> recipes) {
        super("home.ftl", StandardCharsets.UTF_8);
        this.recipes = recipes;
    }


    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
