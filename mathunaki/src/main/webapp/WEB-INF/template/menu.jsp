<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<ul class="nav nav-list">
  <li><a href="${pageContext.request.contextPath}/user/list"><s:message code="menu.user" /></a></li>
  <li><a href="${pageContext.request.contextPath}/tuition/list"><s:message code="menu.tuition" /></a></li>
  <li><a href="#"><s:message code="menu.schedule" /></a></li>
</ul>
<script>
$(document).ready(function () {
    $('.nav-list > li').click(function (e) {
        //e.preventDefault();
        $('.nav-list > li').removeClass('active');
        $(this).addClass('active');                
    });            
});
</script>