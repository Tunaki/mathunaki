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
    $.getJSON('userSearchDialog', {name : $("#name").val()}, function(data) {
        $tbody = $("#userSearchTable tbody");
        $tbody.empty();
        if (jQuery.isEmptyObject(data)) {
            $tbody.append(
                '<tr><td colspan="6">' +
                  '<s:message code="application.table.noresults" />' +
                '</td></tr>'
            );
        }
        for (var index in data) {
            $tbody.append(
                '<tr onclick="handleTableRowClick('+index+')" class="clickable-table-row">' +
                  '<td style="display: none;">' + data[index].id + '</td>' +
                  '<td>' + data[index].firstName + '</td>' +
                  '<td>' + data[index].lastName + '</td>' +
                  '<td>' + data[index].address + '</td>' +
                  '<td>' + data[index].status + '</td>' +
                  '<td>' + data[index].price + '</td>' +
                '</tr>');
        }
    });
}
function handleTableRowClick(index) {
    $tr = $("#userSearchTable tbody tr").eq(index);
    $("#userId").val($tr.find("td:first").html());
    $searchedUserName = $("#userName");
    $searchedUserName.empty();
    $searchedUserName.append($tr.find("td:eq(1)").html() + ' ' + $tr.find("td:eq(2)").html());
    $("#userSearchDialog").dialog("close");
}
</script>
<div id="userSearchDialog">
  <form>
    <fieldset>
      <label for="name"><s:message code="user.lastName" /></label>
      <input type="text" name="name" id="name" />
    </fieldset>
    <button type="button" onclick="handleSearchClick()"><s:message code="button.search" /></button>
  </form>
  <table id="userSearchTable" class="clickable-table">
    <thead>
      <tr>
        <th style="display: none;" />
        <th><s:message code="user.firstName" /></th>
        <th><s:message code="user.lastName" /></th>
        <th><s:message code="user.address" /></th>
        <th><s:message code="user.status" /></th>
        <th><s:message code="user.price" /></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td colspan="6"><s:message code="application.table.noresults" /></td>
      </tr>
    </tbody>
  </table>
</div>