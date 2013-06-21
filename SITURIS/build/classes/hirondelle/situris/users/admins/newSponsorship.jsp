<c:url value="NewPatrocinioAction.apply" var="baseURL"/> 
<w:populate> 
<form action='${baseURL}' method="post" class="user-input"  name='giveMeFocus'>
 <table class="admintables" align="center">
  <tr>
   <td> <label>Begin Date</label> </td>
   <td><input name="beginDate" type="date">YYYYMMDD</td>
  </tr>
  <tr>
   <td> <label>End Date</label> </td>
   <td><input name="endDate" type="date">YYYYMMDD</td>
  </tr>
  <tr>
   <td><label>Description</label></td>
   <td> <textarea name="description" cols="40" rows="5"></textarea></td>
  </tr>
  <tr>
     <td><label>Category</label></td>
     <td>
      <select name="idCat">
       <option> </option>
        <c:forEach var="cat" items="${categorias}" varStatus="index">
          <option value='${cat.id}'>${cat.text}</option>
        </c:forEach>
      </select>
     </td>
    </tr>
    <tr>
     <td><label>Profile</label></td>
     <td>
      <select name="idPerfil">
       <option> </option>
        <c:forEach var="per" items="${perfis}" varStatus="index">
          <option value='${per.id}'>${per.text}</option>
        </c:forEach>
      </select>
     </td>
    </tr>
    <tr>
     <td><label>Event</label></td>
     <td>
      <select name="idEvento">
       <option> </option>
        <c:forEach var="ev" items="${events}" varStatus="index">
          <option value='${ev.id}'>${ev.text}</option>
        </c:forEach>
      </select>
     </td>
    </tr>
    <tr>
     <td><label>Visit</label></td>
     <td>
      <select name="idVisita">
       <option> </option>
        <c:forEach var="vis" items="${visits}" varStatus="index">
          <option value='${vis.id}'>${vis.text}</option>
        </c:forEach>
      </select>
     </td>
    </tr>
</w:populate> 
    <tr><td><input type=hidden value='${idUser}' name='ParentId'></td></tr>
    <tr><td><input type=hidden value='${idProp}' name='Id'></td></tr>
  <tr>
   <td align="center" colspan=2>
      <input type=submit value="Create Sponsorship">
   </td>
  </tr>
 </table>
</form>
<br/>