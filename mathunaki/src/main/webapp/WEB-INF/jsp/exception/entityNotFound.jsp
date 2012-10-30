<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page>
  <s:message code="${exception.message}" arguments="${exception.id}" />
</t:page>