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
      <td><a href="#" id="searchUser" >Recherche utilisateur</a></td>
    </tr>
    <tc:list property="userLevel" readOnly="${mode eq 'READ'}" required="required" name="tuition" items="${userLevelList}" itemLabel="label" />
    <tc:textArea property="description" readOnly="${mode eq 'READ'}" name="tuition" />
    <tc:file property="resource" readOnly="${mode eq 'READ'}" name="tuition" />
  </table>
</fieldset>

<script>
$(function() {
	$("#userSearchDialog").dialog({
		autoOpen: false,
		width: "auto",
		modal: true
	});
	$("#searchUser").click(function() {
		$("#userSearchDialog").dialog("open");
	});
});
function handleSearchClick() {
$.getJSON('userSearchDialog',
	{name : $("#name").val()},
	function() {
// 		$("#userSearchTable tbody").empty();
// 		if (jQuery.isEmptyObject(data)) {
// 			$("#userSearchTable tbody").append(
// 				'<tr><td colspan="5">' +
// 				'<s:message code="application.table.noresults" />' +
// 				'</td></tr>'
// 			);
// 		}
// 		for (var index in data) {
// 			$("#userSearchTable tbody").append(
// 				'<tr>' +
// 			        '<td><a href="#" id="userSelect' + data[index].id + '">' + data[index].firstName + '</a></td>' +
//                     '<td>' + data[index].lastName + '</td>' +
//                     '<td>' + data[index].address + '</td>' +
//                     '<td>' + data[index].state.label + '</td>' +
//                     '<td>' + data[index].price + '</td>' +
//                 '</tr>');
// 		}
	}
);}
</script>
<div id="userSearchDialog">
  <form>
    <fieldset>
      <label for="name">Nom</label>
      <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
    </fieldset>
    <input type="submit" value="Chercher" onclick="handleSearchClick()"/>
  </form>
  <span id="test"></span>
  <table id="userSearchTable">
    <thead>
      <tr>
        <th><s:message code="user.firstName" /></th>
        <th><s:message code="user.lastName" /></th>
        <th><s:message code="user.address" /></th>
        <th><s:message code="user.status" /></th>
        <th><s:message code="user.price" /></th>
      </tr>
    </thead>
    <tbody>
      <c:choose>
        <c:when test="${empty userSearchList}">
          <tr>
            <td colspan="5">Pas de résultats</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach items="${userSearchList}" var="user">
            <tr>
              <td><c:out value="${user.firstName}"/></td>
              <td><c:out value="${user.lastName}"/></td>
              <td><c:out value="${user.address}"/></td>
              <td><c:out value="${user.alive}"/></td>
              <td><c:out value="${user.status.label}"/></td>
              <td><c:out value="${user.price}"/></td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </tbody>
  </table>
</div>