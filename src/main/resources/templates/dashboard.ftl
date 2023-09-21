<html lang="en">
    <head>
    <title>Dashboard</title>
    </head>

    <body>
        <h1>Dashboard</h1>
        <#if userName??>
        <p>Current logged user is : ${userName}</p>
        </#if>
        <a href="/users"> Go to users </a>
        <a href="/logout"> Log out </a>
    </body>
</html>