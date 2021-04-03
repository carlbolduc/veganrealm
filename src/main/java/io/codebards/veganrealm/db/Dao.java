package io.codebards.veganrealm.db;

import io.codebards.veganrealm.api.Recipe;
import io.codebards.veganrealm.api.Search;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface Dao {

    @SqlQuery("SELECT 'bidu'")
    String healthCheck();

    @SqlQuery("SELECT COUNT(*) FROM recipes")
    int countAllRecipes();

    @SqlQuery("SELECT id, author, title, link, image_link, ingredients, published_at\n" +
              "FROM recipes\n" +
              "WHERE weighted_tsv @@ plainto_tsquery(:terms)\n" +
              "ORDER BY ts_rank(weighted_tsv, plainto_tsquery(:terms)) DESC, id DESC\n" +
              "LIMIT 20 OFFSET :offset")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> searchRecipes(@BindBean Search search);

}
