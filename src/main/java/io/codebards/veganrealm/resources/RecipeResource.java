package io.codebards.veganrealm.resources;

import io.codebards.veganrealm.api.Facet;
import io.codebards.veganrealm.api.Recipe;
import io.codebards.veganrealm.api.Results;
import io.codebards.veganrealm.db.Dao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/recipes")
public class RecipeResource {

    private final Dao dao;

    public RecipeResource(Dao dao) {
        this.dao = dao;
    }

    @GET
    public List<Recipe> getRecipes() {
        return new ArrayList<>();
    }

    @GET
    @Path("/{keyword}")
    public Results getResults(@PathParam("keyword") String keyword) {
        Results results = new Results(keyword);
        results.setRecipes(dao.findAllRecipes(keyword));
        List<Integer> ids = results.getRecipes().stream().map(Recipe::getId).collect(Collectors.toList());
        List<Facet> facets = new ArrayList<>();
        if (!ids.isEmpty()) {
            Facet authorFacet = new Facet("author");
            authorFacet.setFacetValues(dao.listAuthorFacetValues(ids));
            facets.add(authorFacet);
        }
        results.setFacets(facets);
        return results;
    }

    @GET
    @Path("/date/{keyword}")
    public List<Recipe> getRecipesSortedByDate(@PathParam("keyword") String keyword) {
        return dao.findAllRecipesSortedByDate(keyword);
    }

}
