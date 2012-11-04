<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>
<table>
  <thead>
    <tr>
      <th><s:message code="tuition.user" /></th>
      <th><s:message code="tuition.userLevel" /></th>
      <th><s:message code="tuition.description" /></th>
      <th><s:message code="tuition.resource" /></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${tuitionList}" var="tuition">
      <tr>
        <td>
          <s:url value="{tuitionId}" var="tuitionURL">
            <s:param name="tuitionId" value="${tuition.id}" />
          </s:url>
          <a href="${tuitionURL}"><c:out value="${tuition.user}" /></a>
        </td>
        <td><s:message code="${tuition.userLevel}" /></td>
        <td><c:out value="${tuition.description}" /></td>
        <td><c:out value="Link to resource" /></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<div>
  <form action="${pageContext.request.contextPath}/tuition/create">
    <button type="submit"><s:message code="button.create.tuition" /></button>
  </form>
</div>
</t:page>