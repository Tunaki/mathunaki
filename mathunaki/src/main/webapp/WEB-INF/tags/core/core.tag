<%@tag description="Core tag for fields" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tc" tagdir="/WEB-INF/tags/core" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="property" required="true" %>
<%@attribute name="name" required="true" %>
<%@attribute name="readProperty" fragment="true" %>

<s:bind path="${property}">
  <tr>
    <td>
      <sf:label path="${property}">
        <s:message code="${name}.${property}" />
        <c:if test="${required and !readOnly}">
          <span class="required">*</span>
        </c:if>
      </sf:label>
    </td>
    <td>
      <c:choose>
        <c:when test="${readOnly}">
          <c:choose>
            <c:when test="${not empty readProperty}">
              <jsp:invoke fragment="readProperty" />
            </c:when>
            <c:otherwise>
              <c:out value="${status.value}" />
            </c:otherwise>
          </c:choose>
        </c:when>
        <c:otherwise>
          <jsp:doBody />
        </c:otherwise>
      </c:choose>
    </td>
    <td>
      <c:if test="${status.error}">
        <span class="help-inline">${status.errorMessage}</span>
      </c:if>
    </td>
  </tr>
</s:bind>