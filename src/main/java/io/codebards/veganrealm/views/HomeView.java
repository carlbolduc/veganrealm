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
            // add just a few pages
            double pagesCount = search.getTotal() / 12d;
            if (pagesCount < 11) {
                for (double i = 1d; i <= Math.ceil(pagesCount); i++) {
                    pages.add(new Page(search.getTerms(), (int) i));
                }
            } else {
                if (search.getOffset() == 0) {
                    // get first 10 pages
                    for (double i = 1d; i <= 10; i++) {
                        pages.add(new Page(search.getTerms(), (int) i));
                    }
                } else {
                    double pageNumber = (double) search.getOffset() / 12 + 1;
                    if (pageNumber < 6) {
                        // get first 10 pages
                        for (double i = 1d; i <= 10; i++) {
                            pages.add(new Page(search.getTerms(), (int) i));
                        }
                    } else {
                        // get 4 before and 5 after
                        for (double i = pageNumber - 4; i <= Math.ceil(pageNumber + 4); i++) {
                            pages.add(new Page(search.getTerms(), (int) i));
                        }
                    }
                }
            }
        }
    }
}
