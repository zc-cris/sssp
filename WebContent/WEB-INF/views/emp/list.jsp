<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${page == null || page.numberOfElements == 0}">
	 	<%-- <c:out value="alert('没有任何记录')"></c:out> --%>	
	 	没有任何记录
	</c:if>
	<c:if test="${page != null && page.numberOfElements >0 }">
		<table border="1" align="center" cellpadding="10" cellspacing="0">
			<tr>
				<th>编号</th>
				<th>名字</th>
				<th>邮箱</th>
				<th>生日</th>
				<th>入职时间</th>
				<th>部门</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${page.content }" var="emp">
				<tr align="center">
					<td>${emp.id }</td>
					<td>${emp.name }</td>
					<td>${emp.email }</td>
					<td>
						<fmt:formatDate value="${emp.birth }" pattern="yyyy-MM-dd" />
					</td>
					<td>
						<fmt:formatDate value="${emp.createTime }" pattern="yyyy-MM-dd hh:mm:ss" />
					</td>
					<td>${emp.department.name }</td>
					<td><a href="">修改</a></td>
					<td><a href="">删除</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="8">
					共 ${page.totalElements } 条记录
					共 ${page.totalPages } 页
					当前第 ${page.number +1 } 页
					<c:if test="${(page.number +1) > 1 }">
						<a href="?pageNo=${page.number +1 - 1 }">上一页</a>
					</c:if>
					
					<c:if test="${(page.number +1) < page.totalPages }">
						<a href="?pageNo=${page.number +1 + 1 }">下一页</a>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>

</body>
</html>