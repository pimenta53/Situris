<%-- Register a new user. Simple action with a single operation. --%>
<c:url value="RegisterAction.apply" var="baseURL"/> 
<w:populate> 
<form action='${baseURL}' method="post" class="user-input"  name='giveMeFocus'>
 <table align="center">
  <tr>
   <td> <label>UserName</label> </td>
   <td><input name="UserName" type="text"></td>
  </tr>
  <tr>
   <td> <label>Name</label> </td>
   <td><input name="Name" type="text"></td>
  </tr>
  <tr>
   <td><label>Email</label></td>
   <td><input name="Email" type="text"></td>
  </tr>
  <tr>
   <td><label>Password</label></td>
   <td><input name="Password" type="password"></td>
  </tr>
  <tr>
   <td><label>Confirm Password</label></td>
   <td><input name="PasswordConfirm" type="password"></td>
  </tr>
  <tr>
   <td><label>Captcha</label></td>
   <td>
     <%--     <input name="recaptcha_response_field" type="text">  --%>
     <tags:captcha/>
   </td>
  </tr>
</w:populate> 
  <tr>
   <td align="center" colspan=2>
      <input type=submit value="Register">
   </td>
  </tr>
 </table>
</form>
<br/>
