<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tu" tagdir="/WEB-INF/tags/user" %>
<t:page>
<div>
  <c:if test="${not empty error}">
    <s:message code="${error}" />
  </c:if>
</div>
<sf:form modelAttribute="user" action="${pageContext.request.contextPath}/user/${user.id}" method="POST">
  <tu:userForm mode="READ" />
  <div>
    <button name="backToList" type="submit">
      <s:message code="button.backToList" />
    </button>
    <button type="submit" formaction="${pageContext.request.contextPath}/user/${user.id}/update">
      <s:message code="button.update" />
    </button>
    <button name="delete" type="submit">
      <s:message code="button.delete" />
    </button>
    <c:choose>
      <c:when test="${user.status eq 'ENABLED'}">
        <button name="disable" type="submit">
          <s:message code="button.disable" />
        </button>
      </c:when>
      <c:otherwise>
        <button name="enable" type="submit">
          <s:message code="button.enable" />
        </button>
      </c:otherwise>
    </c:choose>
  </div>
</sf:form>
</t:page>