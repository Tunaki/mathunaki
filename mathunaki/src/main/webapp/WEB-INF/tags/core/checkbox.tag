<%@tag description="Tag for advanced checkbox field" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tc" tagdir="/WEB-INF/tags/core" %>
<%@attribute name="readOnly" type="java.lang.Boolean" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="property" required="true" %>
<%@attribute name="name" required="true" %>

<tc:core property="${property}"
         readOnly="${readOnly}"
         required="${required}"
         name="${name}">
  <jsp:attribute name="readProperty">
    <s:message code="${status.value}" />
  </jsp:attribute>
  <jsp:body>
    <sf:checkbox path="${property}" cssClass="checkbox" />
  </jsp:body>
</tc:core>