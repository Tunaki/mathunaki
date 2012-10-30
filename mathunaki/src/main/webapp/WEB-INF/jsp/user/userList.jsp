<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>
<table>
  <thead>
    <tr>
      <th><s:message code="user.firstName" /></th>
      <th><s:message code="user.lastName" /></th>
      <th><s:message code="user.address" /></th>
      <th><s:message code="user.email" /></th>
      <th><s:message code="user.price" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${userList}" var="user">
      <tr>
        <td>
          <s:url value="{userId}" var="userURL">
            <s:param name="userId" value="${user.id}" />
          </s:url>
          <a href="${userURL}"><c:out value="${user.firstName}"/></a>
        </td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.address}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.price}"/></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<div>
  <form action="${pageContext.request.contextPath}/user/create">
    <button type="submit"><s:message code="button.create.user" /></button>
  </form>
</div>
</t:page>