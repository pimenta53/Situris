<%@ include file="/WEB-INF/TagHeader.jspf" %>

<div id=loginbox>
  <c:if test="${empty name}">
    <c:url var='loginURL' value='/users/showaccount/ShowAccountAction.show' />
    <a class=g href='${loginURL}' title='Login'>Login</a>  
    <c:url var='registarURL' value='/pub/register/RegisterAction.show' />
    
    <a class=w href='' title='espaco'>--</a>  
        
    <a class=g href='${registarURL}' title='Register'>Register</a>
  </c:if>    
  <c:if test="${not empty name}">
    <w:show ifRoleNot="admin">
    <c:url var="proposalURL" value="/users/proposal/ProposalAction.show"/>
    <a class=g href='${proposalURL}' title='Proposal'>Make a Proposal</a>
    <a class=w href='' title='espaco'>---</a> 
    </w:show>
    
    <c:url var="accountURL" value="/users/showaccount/ShowAccountAction.show"/>
    <a class=g href='${accountURL}' title='Account'>Account</a>
    <a class=w href='' title='espaco'>---</a>  
    
    <c:url var="logoutURL" value="/users/logout/LogoutAction.apply"/>
    <a class=g href='${logoutURL}' title='Logout'>Logout</a>
  </c:if>
</div>  

  
<div id=searchbox>
  <c:url value="/pub/search/SearchAction.search" var="searchURL"/> 
  
  <div align="center">
    <form method="GET" action= '${searchURL}' name='giveMeFocus'>
      <table>
        <tr>
          <td><input type="text" name="Search Text" title='3..255 characters'></td>
          <td><input type="submit" value="Search"></td>
        </tr>
      </table>
    </form>
  </div>
</div>