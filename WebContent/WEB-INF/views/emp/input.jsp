<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-3.3.1.min.js">
</script>

<script type="text/javascript">
	$(function() {
		$("#validateName").change(function() {
			var name = $(this).val();
			name = $.trim(name);
			$(this).val(name);

			// 如果是更新操作的页面回显请求，那么一定是有 oldName值的
			var oldName = $("#oldName").val();
			oldName = $.trim(oldName);
			// 当用户修改后的名字和 他之前的名字是一模一样的，那么就不向后台发送 ajax 验证请求
			if(oldName != null && oldName != "" && oldName == name){
				return;
			}
			
			var url = "${pageContext.request.contextPath }/validateName";
			var data = {
				"name" : name,
				"time" : new Date()
			};
			$.post(url, data, function(data) {
				if (data == 0) {
				} else if (data == 1) {
					alert("该用户名不可用");
					// 用户名不可用的话直接前台清除其文本内容
					$("#validateName").val("");
				} else {
					alert("网络错误！");
				}
			})
		})
		
	})
</script>

</head>
<body>
	<!-- 因为新增用户数据和修改用户数据我们都放在一张jsp页面，所以再进行update 操作的页面回显时需要进行判断以确定用户原本数据的名字是什么
		防止发送 无谓的ajax 请求到后台去验证 -->
	<c:if test="${employee.id != null }">
		<input type="hidden" value="${employee.name }" id="oldName"/>
	</c:if>


	<form:form action="${pageContext.request.contextPath }/emp"
		method="post" modelAttribute="employee">
		
		<!-- update操作的页面回显需要使用隐藏域存储 指定的name和value，指定该请求到达后台后变成 PUT 请求类型 -->
		<c:if test="${employee.id != null }">
			<form:hidden path="id" />
			<input type="hidden" value="PUT" name="_method"/>
		</c:if>
		
		姓名：<form:input path="name" id="validateName" />
		<br>
		邮箱：<form:input path="email" />
		<br>
		生日：<form:input path="birth" />
		<br>
		部门：
			<form:select path="department.id" items="${departments }"
			itemLabel="name" itemValue="id"></form:select>
		<br>
		<input type="submit" value="提交">
	</form:form>
</head>
</body>
</html>