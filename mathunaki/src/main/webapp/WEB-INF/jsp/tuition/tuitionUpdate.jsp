<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tu" tagdir="/WEB-INF/tags/user" %>

<t:page>
  <sf:form method="POST"
           action="${pageContext.request.contextPath}/user/${user.id}/update"
           modelAttribute="user">
    <tu:userForm mode="UPDATE" />
    <div>
      <button name="cancel" type="submit" class="btn">
        <s:message code="button.cancel" />
      </button>
      <button name="update" type="submit" class="btn">
        <s:message code="button.update" />
      </button>
    </div>
  </sf:form>
</t:page>