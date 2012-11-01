<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags/tuition" %>
<t:page>
<div>
  <c:if test="${not empty error}">
    <s:message code="${error}" />
  </c:if>
</div>
<sf:form modelAttribute="tuition" action="${pageContext.request.contextPath}/tuition/${tuition.id}" method="POST">
  <tt:tuitionForm mode="READ" />
  <div>
    <button name="backToList" type="submit">
      <s:message code="button.backToList" />
    </button>
    <button type="submit" formaction="${pageContext.request.contextPath}/tuition/${tuition.id}/update">
      <s:message code="button.update" />
    </button>
    <button name="delete" type="submit">
      <s:message code="button.delete" />
    </button>
  </div>
</sf:form>
</t:page>