<%-- Register a new user. Simple action with a single operation. --%>
<c:url value="EditAccountAction.apply" var="baseURL"/> 
<w:populate> 
<form action='${baseURL}' method="post" class="user-input"  name='giveMeFocus'>
<br/><br/>
<div align="center">
 <table align="center">
  <tr>
   <td> <label>Name</label> </td>
   <td><input name="Name" type="text"></td>
  </tr>
  <tr>
   <td><label>Email</label></td>
   <td><input name="Email" type="text"></td>
  </tr>
  <tr>
   <td><label>New Password</label></td>
   <td><input name="Password" type="password"></td>
  </tr>
  <tr>
   <td><label>Confirm Password</label></td>
   <td><input name="PasswordConfirm" type="password"></td>
  </tr>
</w:populate> 
  <tr>
   <td align="center" colspan=2>
      <input type=submit value="Update">
   </td>
  </tr>
 </table>
</form>
</div>
<br/>