package io.codebards.veganrealm;

import io.codebards.veganrealm.db.Dao;
import io.codebards.veganrealm.resources.HomeResource;
import io.codebards.veganrealm.resources.StatisticsResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.jdbi.v3.core.Jdbi;

import java.util.Map;

public class App extends Application<Config> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(Config config) {
                return config.getDatabase();
            }
        });
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new ViewBundle<>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(Config config) {
                return config.getViews();
            }
        });
    }

    @Override
    public void run(Config config, Environment environment) {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, config.getDatabase(), "main");

        final Dao dao = jdbi.onDemand(Dao.class);
        final StatisticsResource statisticsResource = new StatisticsResource(dao);
        final HomeResource homeResource = new HomeResource(dao);

        environment.jersey().register(statisticsResource);
        environment.jersey().register(homeResource);
    }

}
