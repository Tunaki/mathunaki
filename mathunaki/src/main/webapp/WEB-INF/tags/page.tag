<%@tag description="Page template"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<t:layout>
  <jsp:attribute name="header">
    <jsp:include page="/WEB-INF/template/header.jsp" />
  </jsp:attribute>
  <jsp:attribute name="footer">
    <jsp:include page="/WEB-INF/template/footer.jsp" />
  </jsp:attribute>
  <jsp:attribute name="menu">
    <jsp:include page="/WEB-INF/template/menu.jsp" />
  </jsp:attribute>
  <jsp:body>
    <jsp:doBody />
  </jsp:body>
</t:layout>