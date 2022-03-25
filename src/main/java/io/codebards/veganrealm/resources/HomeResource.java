package io.codebards.veganrealm.resources;

import io.codebards.veganrealm.api.Recipe;
import io.codebards.veganrealm.db.Dao;
import io.codebards.veganrealm.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    private final Dao dao;

    public HomeResource(Dao dao) {
        this.dao = dao;
    }

    @GET
    public HomeView getHome(@QueryParam("q") String q) {
        List<Recipe> recipes;
        if (q.equals("")) {
            recipes = dao.findAll();
        } else {
            recipes = dao.find(q);
        }
        return new HomeView(recipes);
    }

}
