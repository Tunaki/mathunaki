<%@tag description="Tag for advanced input text field" %>
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
    
  </jsp:attribute>
  <jsp:body>
    <input type="file" name="file" />
  </jsp:body>
</tc:core>