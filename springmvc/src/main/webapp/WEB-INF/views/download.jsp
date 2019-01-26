<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/20
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Download the page</title>
</head>
<body>
<h4>Click the link to download</h4>
<a href="download?filename=${requestScope.fileinfo.file.originalFilename}">${requestScope.fileinfo.file.originalFilename}</a>
</body>
</html>

