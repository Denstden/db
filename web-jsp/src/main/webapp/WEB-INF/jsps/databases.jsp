<%@ page import="java.util.List" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.database.Database" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Databases</title>
</head>
<body>
    <table>
        <%
    List<Database> databases = (List<Database>)request.getAttribute("databases");
    for (Database database : databases) {%>
            <tr>
                <td>
                    <% out.println(database.getDatabaseName());%>
                </td>
            </tr>
        <% }%>
    </table>
</body>
</html>
