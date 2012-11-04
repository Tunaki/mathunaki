<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tc" tagdir="/WEB-INF/tags/core" %>
<%@attribute name="mode" required="true" description="create|read|update" %>

<fieldset>
  <legend><s:message code="legend.tuition" /></legend>
  <table>
    <tr>
      <td><sf:label path="user"><s:message code="tuition.user" /></sf:label></td>
      <td>
        <c:choose>
          <c:when test="${mode eq 'READ'}">
            <c:out value="${tuition.user.firstName} ${tuition.user.lastName}" />
          </c:when>
          <c:otherwise>
            <input type="hidden" id="userId" name="userId" />
            <span id="userName"></span>
            <button id="searchUser" type="button">
              <s:message code="button.search" />
            </button>
          </c:otherwise>
        </c:choose>
      </td>
      <td>
        <s:bind path="user">
          <c:if test="${status.error}">
            <span class="help-inline">${status.errorMessage}</span>
          </c:if>
        </s:bind>
      </td>
    </tr>
    <tc:list property="userLevel" readOnly="${mode eq 'READ'}" required="required" name="tuition" items="${userLevelList}" itemLabel="label" />
    <tc:textArea property="description" readOnly="${mode eq 'READ'}" name="tuition" />
    
  </table>
</fieldset>