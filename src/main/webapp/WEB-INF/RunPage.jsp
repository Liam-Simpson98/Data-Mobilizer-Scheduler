<%-- 
    Document   : RunPage
    Created on : Mar. 28, 2021, 10:40:11 p.m.
    Author     : Liam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Mailer</title>
    </head>
    <body>
        <form method="post" action="Run">
            <h1>Attach Resource for Mailing</h1>
            <div>
                <label for="email">Recipient Email:</label>
                <p><input type="text" id="email" name="email" value="${email}" required></p>
            </div>
            <div>
                <input class="button" type="submit" value="Send Mail" name="Run">
            </div>
        </form>
        <p class="text">${buttonPress}</p>
    </body>
</html>
