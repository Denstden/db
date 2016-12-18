<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.column.Column" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.field.Field" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.record.Record" %>
<%@ page import="ua.kiev.unicyb.tcct.domain.table.Table" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%out.println((String)request.getAttribute("tableName"));%></title>
</head>
<body>
<% Table table = (Table) request.getAttribute("tableView");
    Set<Column> columns = table.getColumns();
    List<Record> records = table.getRecords();
%>
<table>
    <tr>
        <%for (Column column : columns) {%>
        <th>
            <%out.println(column.getColumnName());%>
        </th>
        <%}%>
    </tr>

    <% for (Record record : records) {%>
    <tr>
        <% for (Map.Entry<Column, Field> field : record.getFields().entrySet()) {%>
        <td>
            <%out.println(field.getValue().getValue()); %>
        </td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
