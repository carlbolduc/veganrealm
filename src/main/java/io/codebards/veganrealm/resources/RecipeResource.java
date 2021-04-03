package io.codebards.veganrealm.resources;

import io.codebards.veganrealm.api.Recipe;
import io.codebards.veganrealm.api.Results;
import io.codebards.veganrealm.api.Search;
import io.codebards.veganrealm.db.Dao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Path("/recipes")
public class RecipeResource {

    private final Dao dao;

    public RecipeResource(Dao dao) {
        this.dao = dao;
    }

    @GET
    public Response getRecipes(@QueryParam("q") String q) {
        Response response;
        String decodedQuery = new String(Base64.getDecoder().decode(q), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Search search = mapper.readValue(decodedQuery, Search.class);
            Results results = new Results();
            List<Recipe> recipes = dao.searchRecipes(search);
            if (recipes.size() == 20) {
                results.setHasMore(true);
                results.setNextOffset(search.getOffset() + 20L);
            } else {
                results.setHasMore(false);
                results.setNextOffset(0L);
            }
            results.setRecipes(recipes);
            response = Response.status(Response.Status.OK).entity(results).build();
        } catch (IOException e) {
            e.printStackTrace();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

}
