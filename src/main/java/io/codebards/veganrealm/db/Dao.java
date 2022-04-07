package io.codebards.veganrealm.db;

import io.codebards.veganrealm.api.Recipe;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface Dao {

    @SqlQuery("SELECT 'bidu'")
    String healthCheck();

    @SqlQuery("SELECT COUNT(*) FROM recipe")
    int countAllRecipes();

    @SqlQuery("SELECT * FROM recipe LIMIT 24")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> findAll();

    @SqlQuery("SELECT * FROM search_recipes WHERE search_recipes MATCH :terms ORDER BY rank LIMIT 24")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> findRecipeByIds(@Bind("terms") String terms);
}
