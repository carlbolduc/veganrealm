package io.codebards.veganrealm.views;

import io.codebards.veganrealm.api.Page;
import io.codebards.veganrealm.api.Search;
import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends View {
    private final Search search;
    private final List<Page> pages = new ArrayList<>();

    public HomeView(Search search) {
        super("home.ftl", StandardCharsets.UTF_8);
        this.search = search;
        generatePages(search);
    }

    public Search getSearch() {
        return search;
    }

    public List<Page> getPages() {
        return pages;
    }

    private void generatePages(Search search) {
        if (search.getTotal() > 12) {
            double pageNumber = search.getTotal() / 12d;
            for (double i = 1d; i <= Math.ceil(pageNumber); i++) {
                pages.add(new Page(search.getTerms(), (int) i));
            }
        }
    }
}
