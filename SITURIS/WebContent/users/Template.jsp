<%@ page contentType="text/html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>

<tags:headerLogo title='${param.TTitle}'/>

<body>
<tags:headerAndMenu/> 
<h2><w:txt>${param.TTitle}</w:txt></h2>
<tags:displayMessages/>

<%-- Menu --%>
<div id="nav-menu_Account">
  <ul>
    <c:url var='showAccountURL' value='/users/showaccount/ShowAccountAction.show' />
    <li><a href='${showAccountURL}' title='Show Account Details'>Account Details</a></li>
    <c:url var='editAccountURL' value='/users/editaccount/EditAccountAction.show' />
    <li><a href='${editAccountURL}' title='Edit Account'>Edit Account</a></li>
    <w:show ifRoleNot="admin">
      <c:url var='checkProposalsURL' value='/users/clients/checkProposalsAction.list' />
      <li><a href='${checkProposalsURL}' title='Check Proposals'>Check Proposals</a></li>
    </w:show>
    <w:show ifRole="admin">
      <c:url var='checkAllProposalsURL' value='/users/admins/checkProposalsAction.list' />
      <li><a href='${checkAllProposalsURL}' title='Check All Proposals'>Check All Proposals</a></li>
      <c:url var='checkAllSponsorshipsURL' value='/users/admins/checkSponsorshipsAction.list' />
      <li><a href='${checkAllSponsorshipsURL}' title='Check All Sponsorships'>Check All Sponsorships</a></li>
      <c:url var='checkFinancesURL' value='/users/admins/CheckFinancesAction.list' />
      <li><a href='${checkFinancesURL}' title='Check Finances'>Finances</a></li>
    </w:show>
    <w:show ifRole="client">
      <c:url var='checkSponsorshipsURL' value='/users/clients/checkSponsorshipAction.list' />
      <li><a href='${checkSponsorshipsURL}' title='Check Sponsorships'>Check Sponsorships</a></li>
    </w:show>
    <c:url var='deleteAccountURL' value='/users/deleteaccount/DeleteAccountAction.show' />
    <li><a href='${deleteAccountURL}' title='Delete Account'>Delete Account</a></li>
  </ul>
</div>

<div class="body">
 <c:if test="${not empty param.TBody}">
   <jsp:include page='${param.TBody}' flush="true"/>
 </c:if>
 <c:if test="${empty param.TBody}">
 <jsp:include page="../Error.html" flush="true"/>
 </c:if>
</div>
 <tags:footer/>
</body>
</html>