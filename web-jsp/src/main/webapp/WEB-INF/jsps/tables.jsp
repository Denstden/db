<%@ page import="java.util.List" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.table.Table" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tables</title>
</head>
<body>
<table>
    <%
        List<Table> tables = (List<Table>)request.getAttribute("tables");
        for (Table table : tables) {%>
    <tr>
        <td>
            <% out.println(table.getTableName());%>
        </td>
    </tr>
    <% }%>
</table>
</body>
</html>


</body>
</html>
