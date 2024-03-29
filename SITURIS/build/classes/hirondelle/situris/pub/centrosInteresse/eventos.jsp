<div class="container">
	<c:forEach var="item" items="${itemsForListing}" varStatus="index">
		<div class="box3">
      <h3>${item.nome}</h3>
      <c:if test="${not empty item.estrelas}">
        <c:set var="estrelasRot" value="${item.estrelas}"/>
        <% for(int j = 1; j <= (Integer)pageContext.getAttribute("estrelasRot"); j++) {%>
          <img class="no-margin" src="../../images/star.png" title="star" width="2%">
        <% } %>
      </c:if>
      <c:if test="${not empty item.descricao}">
        <p>${item.descricao}</p>
      </c:if>
			<c:url value="/pub/centrosInteresse/EventoAction.show" var="repoURL">
				<c:param name='ParentId' value='${item.idEvento}' />
			</c:url>
			<a class=g HREF=${repoURL } title='Details'>Details</a>
		</div>

</c:forEach>
</div>
