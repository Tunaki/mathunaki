<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags/tuition" %>

<t:page>
  <sf:form method="POST"
           action="${pageContext.request.contextPath}/tuition/create"
           modelAttribute="tuition"
           enctype="multipart/form-data">
    <tt:tuitionForm mode="CREATE" />
    <div>
      <button name="cancel" type="submit" class="btn">
        <s:message code="button.cancel" />
      </button>
      <button name="create" type="submit" class="btn">
        <s:message code="button.create" />
      </button>
    </div>
  </sf:form>
</t:page>