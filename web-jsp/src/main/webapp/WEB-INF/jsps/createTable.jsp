<%@ page import="java.util.List" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.database.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create table</title>
</head>
<body>
<form method="POST" action="createTable">
    <label>Database:</label><br>
    <select name="dbName">
        <% List<Database> databases = (List<Database>)request.getAttribute("databases");
            for(Database database : databases) {
        %>
        <option><% out.println(database.getDatabaseName());%></option>
        <%}%>
    </select><br>

    <label>Table name:</label><br>
    <input type="text" name="tableName"><br>
    <input type="submit" value="Create table"/>
</form>
</body>
</html>
