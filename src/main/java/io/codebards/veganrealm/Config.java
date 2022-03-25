package io.codebards.veganrealm;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class Config extends Configuration {
    @Valid
    @NotNull
    private final DataSourceFactory database = new DataSourceFactory();
    @Valid
    @NotNull
    private final Map<String, Map<String, String>> views = new HashMap<>();

    public DataSourceFactory getDatabase() {
        return database;
    }

    public Map<String, Map<String, String>> getViews() {
        return views;
    }

}
