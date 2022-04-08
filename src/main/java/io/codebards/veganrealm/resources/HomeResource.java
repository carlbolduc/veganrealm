package io.codebards.veganrealm.resources;

import io.codebards.veganrealm.api.Search;
import io.codebards.veganrealm.db.Dao;
import io.codebards.veganrealm.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    private final Dao dao;

    public HomeResource(Dao dao) {
        this.dao = dao;
    }

    @GET
    public HomeView getHome(@QueryParam("q") String q, @QueryParam("o") Integer o) {
        Search search = new Search();
        search.setTerms(q == null ? "" : q);
        search.setOffset(o == null ? 0 : o);
        if (q == null || q.equals("")) {
            search.setTotal(dao.countAllRecipes());
            search.setRecipes(dao.findAll(search.getOffset()));
        } else {
            search.setTotal(dao.countRecipes(q));
            search.setRecipes(dao.findRecipeByIds(q, search.getOffset()));
        }
        return new HomeView(search);
    }

}
