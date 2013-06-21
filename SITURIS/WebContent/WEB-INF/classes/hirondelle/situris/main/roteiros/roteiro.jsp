<c:set value='GestaoRoteirosAction.change' var='baseURL'/>


<w:txtFlow>
 <w:populate using="itemForEdit">
  <form action='${baseURL}' method="post" class="user-input">
   <input name="IdRoteiro" type="hidden">
   <table align="center">
    <tr>
     <td><label>Name</label></td>
     <td><input name="Nome" type="text"></td>
    </tr>
     <tr>
     <td><label>Description</label></td>
     <td><textarea name="Descricao" alt='Max 255 characters rows='6'  cols='30'></textarea> </td>
    </tr>
    
    <tr>
     <td><label>Interest Type</label></td>
     <td>
      <select name="TipoInteresse">
        <c:forEach var="itemsListing" items="${itemsForListing}" varStatus="index">
          <option value='${itemsListing.idTipoInteresse}'>${itemsListing.descricao}</option>
        </c:forEach>
      </select>
     </td>
    </tr>
 </w:populate>
    <tr>
     <td align="center" colspan=2>
      <input type="submit" value="Update">
     </td>
    </tr>
    </table>
</form>
</w:txtFlow>
 