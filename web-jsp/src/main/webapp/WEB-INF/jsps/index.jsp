<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Database</title>
</head>
<body>
    <div>
        <form method="GET" action="createDatabaseView">
            <input type="submit" value="Create database"/>
        </form>
        <form method="GET" action="allDatabases">
            <input type="submit" value="All databases"/>
        </form>
        <form method="GET" action="createTableView">
            <input type="submit" value="Create table"/>
        </form>
        <form method="GET" action="allTables">
            <input type="submit" value="All tables"/>
            <label>Db name:</label>
            <select name="dbName">
                <% List<String> dbNames = (List<String>)request.getAttribute("dbNames");
                    for (String dbName : dbNames) {%>
                <option><%out.println(dbName);%></option>
                <%}%>
            </select>
        </form>
        <form method="GET" action="table">
            <input type="submit" value="Get table content"/>
            <label>Db name:</label>
            <select name="dbName" onchange="">
                <% for (String dbName : dbNames) {%>
                <option><%out.println(dbName);%></option>
                <%}%>
            </select>
            <label>Table name:</label>
            <input name="tableName"/>
        </form>

        <form method="GET" action="intersection">
            <input type="submit" value="Get tables intersection"/>
            <label>Db1 name:</label>
            <select name="dbName1">
                <% for (String dbName : dbNames) {%>
                <option><%out.println(dbName);%></option>
                <%}%>
            </select>
            <label>Table1 name:</label>
            <input name="tableName1"/>
            <label>Db2 name:</label>
            <select name="dbName2">
                <% for (String dbName : dbNames) {%>
                <option><%out.println(dbName);%></option>
                <%}%>
            </select>
            <label>Table2 name:</label>
            <input name="tableName2"/>
        </form>
    </div>
</body>
</html>
