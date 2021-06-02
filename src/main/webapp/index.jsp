<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<h1>Add New Department</h1>
<form action="SaveServlet" method="post">
    <table>
        <tr><td>Name:</td><td><input type="text" name="name"/></td></tr>
        <tr><td>Id:</td><td><input type="text" name="id"/></td></tr>
        <tr><td colspan="2"><input type="submit" value="Save Department"/></td></tr>
    </table>
</form>

<br/>
<a href="DepartmentRetrieveServlet">view departments</a>

</body>
</html>