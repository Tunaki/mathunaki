<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="tc" tagdir="/WEB-INF/tags/core" %>
<%@attribute name="mode" required="true" description="create|read|update" %>

<fieldset>
  <legend><s:message code="legend.user" /></legend>
  <table>
    <tc:text property="firstName" readOnly="${mode eq 'READ'}" required="required" name="user" />
    <tc:text property="lastName" readOnly="${mode eq 'READ'}" required="required" name="user" />
    <tc:textArea property="address" readOnly="${mode eq 'READ'}" required="required" name="user" />
    <tc:text property="email" readOnly="${mode eq 'READ'}" name="user" />
    <tc:text property="phoneNumber" readOnly="${mode eq 'READ'}" name="user" />
    <tc:text property="phoneNumberParent" readOnly="${mode eq 'READ'}" name="user" />
    <tc:text property="phoneNumber2" readOnly="${mode eq 'READ'}" name="user" />
    <tc:textArea property="information" readOnly="${mode eq 'READ'}" name="user" />
    <tc:checkbox property="state" readOnly="${mode eq 'READ'}" required="required" name="user" />
    <tc:text property="price" readOnly="${mode eq 'READ'}" required="required" name="user" />
  </table>
</fieldset>