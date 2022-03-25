<#-- @ftlvariable name="" type="io.codebards.veganrealm.views.HomeView" -->
<#import "/libs/commons.ftl" as com>
<!DOCTYPE html>
<html lang="en">
<@com.head title="Vegan Realm - Your friendly vegan resources search engine" description="A hand-picked curated vegan resources search engine featuring all the best information there is on the web - recipes, health, clothing, travel, and more." />
<body>
<h1>Vegan Realm</h1>

<form action="/" method="get">
    <input id="search" type="search" name="q">
    <input type="submit" value="search" />
</form>
<#list recipes as recipe>
    <div class="recipe">
        <h2>${recipe.name}</h2>
        <h3>Author: ${recipe.author}</h3>
        <strong>Ingredients</strong>
    </div>
</#list>
<@com.populateSearchFromQueryParams />
</body>
</html>