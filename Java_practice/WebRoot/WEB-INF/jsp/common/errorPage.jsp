<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
body{
	background-color: highlight;

}

</style>
<title>系统异常</title>
</head>
<body>
 <h1 align="center">系统出错了，请联系管理员</h1>
<s:debug></s:debug>
<s:property value="%{exception.message}%"/>
</body>
</html>