package io.codebards.veganrealm.db;

import io.codebards.veganrealm.api.Recipe;
import io.codebards.veganrealm.api.Search;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface Dao {

    @SqlQuery("SELECT 'bidu'")
    String healthCheck();

    @SqlQuery("SELECT COUNT(*) FROM recipe")
    int countAllRecipes();

    @SqlQuery("SELECT * FROM recipe")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> findAll();

    @SqlQuery("SELECT * FROM recipe WHERE name LIKE '%' || :terms || '%'")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> find(@Bind("terms") String terms);
}
