<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/18
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- 这行声明表示使用SpringMVC的表单标签库 -->
<html>
<head>
    <title>register page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div id="global">
        <form:form modelAttribute="user2" method="post" action="register2">
            <fieldset>
                <legend>register</legend>
                <p>
                    <label>name:</label>
                    <form:input path="name" />
                    <form:errors path="name" cssStyle="color:red"/>
                </p>
                <p>
                    <label>password:</label>
                    <form:password path="password" />
                    <form:errors path="password" cssStyle="color:red"/>
                </p>
                <p>
                    <label>age:</label>
                    <form:input path="age" />
                    <form:errors path="age" cssStyle="color:red"/>
                </p>
                <p>
                    <label>phone:</label>
                    <form:input path="phone" />
                    <form:errors path="phone" cssStyle="color:red"/>
                </p>
                <p>
                    <label>email:</label>
                    <form:input path="email" />
                    <form:errors path="email" cssStyle="color:red"/>
                </p>
                <p id="buttons">
                    <input id="submit" type="submit" value="register">
                    <input id="reset" type="reset" value="reset">

                </p>
            </fieldset>
        </form:form>
    </div>
</body>
</html>
