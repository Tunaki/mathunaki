<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@tag description="Overall page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<html>
<head>
  <title><s:message code="application.title" /></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery-ui-1.9.1.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css" />
  <script src="${pageContext.request.contextPath}/resources/js/jquery-1.8.2.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.9.1.js"></script>
</head>
<body>
  <div id="header">
    <jsp:invoke fragment="header" />
  </div>
  <div style="margin: auto;">
    <div style="float: left; width: 250px;">
      <jsp:invoke fragment="menu" />
    </div>
    <div id="body" style="margin-left: 260px;">
      <jsp:doBody />
    </div>
  </div>
  <div id="footer">
    <jsp:invoke fragment="footer" />
  </div>
</body>
</html>