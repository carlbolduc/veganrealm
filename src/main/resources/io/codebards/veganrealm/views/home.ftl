<#-- @ftlvariable name="" type="io.codebards.veganrealm.views.HomeView" -->
<#import "/libs/commons.ftl" as com>
<!DOCTYPE html>
<html lang="en">
<@com.head title="Vegan Realm - Your friendly vegan resources search engine" description="A hand-picked curated vegan resources search engine featuring all the best information there is on the web - recipes, health, clothing, travel, and more." />
<body>
<h1>Vegan Realm</h1>

<div id="search">
    <form action="/" method="get">
        <input id="search-input" type="search" name="q">
        <input type="submit" value="search" />
    </form>
</div>
<div id="results">
    <#list recipes as recipe>
        <div class="recipe">
            <a href="${recipe.url}">
                <img class="recipe-picture" src="${recipe.photo}" alt="${recipe.name}">
            </a>
            <h2><a href="${recipe.url}">${recipe.name}</a></h2>
            <p class="small">By ${recipe.author}</p>
        </div>
    </#list>
</div>
<@com.populateSearchFromQueryParams />
</body>
</html>