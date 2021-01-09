package io.codebards.veganrealm.resources;

import io.codebards.veganrealm.db.Dao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/statistics")
public class StatisticsResource {
    private final Dao dao;

    public StatisticsResource(Dao dao) {
        this.dao = dao;
    }

    @Path("/health-check")
    @GET
    public String getHealthCheck() {
        return dao.healthCheck();
    }

    @GET
    @Path("/recipes-count")
    public int recipesCount() {
        return dao.countAllRecipes();
    }

}
