package io.codebards.veganrealm.api;

public record Page(String terms, int number) {

    public String getTerms() {
        return terms;
    }

    public int getNumber() {
        return number;
    }
}
