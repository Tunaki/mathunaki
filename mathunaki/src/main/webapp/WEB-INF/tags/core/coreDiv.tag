<%@tag description="Core tag for fields" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tc" tagdir="/WEB-INF/tags/core" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="bean" required="true" %>
<%@attribute name="property" required="true" %>
<%@attribute name="name" required="true" %>
<%@attribute name="readProperty" fragment="true" %>

<c:if test="${empty required}">
  <c:set var="required" value="false" />
</c:if>
<s:bind path="${property}">
  <div class="control-group ${status.error ? 'error' : '' }">
    <sf:label path="${property}" cssClass="control-label">
      <s:message code="${name}.${property}" />
      <c:if test="${required and !readOnly}">
        <span class="required">*</span>
      </c:if>
    </sf:label>
    <div class="controls">
      <c:choose>
        <c:when test="${readOnly}">
          <c:choose>
            <c:when test="${not empty readProperty}">
              <jsp:invoke fragment="readProperty" />
            </c:when>
            <c:otherwise>
              <c:choose>
                <c:when test="${empty status.value}">
                  <s:message code="value.empty" />
                </c:when>
                <c:otherwise>
                  <c:out value="${status.value}" />
                </c:otherwise>
              </c:choose>
            </c:otherwise>
          </c:choose>
        </c:when>
        <c:otherwise>
          <jsp:doBody />
        </c:otherwise>
      </c:choose>
      <c:if test="${status.error}">
        <span class="help-inline">${status.errorMessage}</span>
      </c:if>
    </div>
  </div>
</s:bind>