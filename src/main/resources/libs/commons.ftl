<#macro head title description>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1,shrink-to-fit=no">
        <link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200;300;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="/assets/css/styles.css" media="screen"/>
        <title>${title}</title>
        <meta name="description" content="${description}" />
    </head>
</#macro>

<#macro footer recipeCount>
    <footer>
        <p class="footer-text">Currently serving ${recipeCount} delicious recipes.</p>
        <p class="footer-text">© 2023 <a href="https://codebards.io/">Code Bards</a>. All rights reserved. <a href="https://codebards.io/policies">Terms &amp; Privacy</a>.</p>
    </footer>
</#macro>

<#macro populateSearchFromQueryParams>
    <script>
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        document.getElementById("search-input").value = urlParams.get("q");
    </script>
</#macro>