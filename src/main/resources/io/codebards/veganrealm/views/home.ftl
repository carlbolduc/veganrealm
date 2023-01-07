<#-- @ftlvariable name="" type="io.codebards.veganrealm.views.HomeView" -->
<#setting number_format="computer">
<#import "/libs/commons.ftl" as com>
<!DOCTYPE html>
<html lang="en">
<@com.head title="Vegan Realm - Your friendly vegan recipe search engine" description="A search engine featuring hand-picked curated vegan recipe blogs from all over the world." />
<body>

<div id="root">
    <div id="container">
        <h1><a href="/">Vegan Realm</a></h1>
        <div id="search">
            <form action="/" method="get">
                <input id="search-input" type="search" name="q" value="${search.terms}"><button id="search-button" type="submit"><span id="search-icon">🔍</span></button>
            </form>
        </div>
        <div id="results">
            <#list search.recipes as recipe>
                <div class="recipe">
                    <a href="${recipe.url}">
                        <img class="recipe-picture" src="${recipe.photo}" alt="${recipe.name}">
                        <h2>${recipe.name}</h2>
                        <p class="recipe-author">${recipe.author}</p>
                    </a>
                </div>
            </#list>
        </div>
        <div id="pager">
            <ul>
                <#list pages as page>
                    <li>
                        <#if 12 * page.number() - 12 == search.offset>
                            <span class="current-page">${page.number()}</span>
                        <#else>
                            <a href="/?q=${search.terms}&o=${12 * page.number() - 12}">${page.number()}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
    <@com.footer recipeCount=search.total />
</div>
<@com.populateSearchFromQueryParams />
</body>
</html>