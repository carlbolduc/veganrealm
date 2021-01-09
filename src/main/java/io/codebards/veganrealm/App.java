package io.codebards.veganrealm;

import io.codebards.veganrealm.db.Dao;
import io.codebards.veganrealm.resources.RecipeResource;
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
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        bootstrap.addBundle(new UrlRewriteBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(Config config, Environment environment) {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, config.getDatabase(), "main");

        final Dao dao = jdbi.onDemand(Dao.class);
        final StatisticsResource statisticsResource = new StatisticsResource(dao);
        final RecipeResource recipeResource = new RecipeResource(dao);

        if (config.getThirdPartyFactory().getEnv().equals("development")) {
            setupCors(environment);
        }

        environment.jersey().register(statisticsResource);
        environment.jersey().register(recipeResource);
    }

    private void setupCors(Environment environment) {
        final FilterRegistration.Dynamic filterRegistration = environment.servlets().addFilter("crossOriginRequests", CrossOriginFilter.class);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        filterRegistration.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filterRegistration.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        filterRegistration.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "PUT,GET,POST,DELETE,OPTIONS");
    }
}
