<html>
<body>
<c:set value='LocalAction' var='baseURL'/> 
<tags:setFormTarget using='${baseURL}' />
<div id="block">
<div id="tituloPagina">
<h1>Routes in ${itemForEdit.nomePais}</h1></div>
          <div class="direitaPais">
			
                 <div id="roteiros"> 
                    <c:if test="${not empty PontosReferencia || not empty Roteiros || not empty Visitas || not empty Eventos}">

                        <c:if test="${not empty Roteiros}">

                           <c:forEach var='rot' items='${Roteiros}'>

                              <div id="back">
                            <div class="direitaRoteiro">
  
                                           <c:if test="${rot.privado == 0}">
                                           <c:url value="/pub/roteiros/RoteiroAction.show" var="rotURL">
                                            <c:param name='ParentId' value='${rot.idInfo}' />
                                            </c:url>   
                                           </c:if>
    
                                            <c:if test="${rot.privado != 0}">
                                              <c:url value="/main/roteiros/RoteiroAction_PRM.show" var="rotURL">
                                              <c:param name='ParentId' value='${rot.idInfo}' />
                                               </c:url>   
                                            </c:if>
                                            
    
                                         <div id="nomeRoteiro">    <a href='${rotURL}'>${rot.nome}</a></div>
                                                 <br/>
                                              <div id="estrelasRoteiros">  
                                             <c:if test="${not empty rot.estrelas}">
                                              <c:set var="estrelasRot" value="${rot.estrelas}"/>
                                          
                                               <% for(int i = 1; i <= (Integer)pageContext.getAttribute("estrelasRot"); i++) {%>
                                                <img  src="../../images/star.png" title="star" width="20px;">    <% } %>
                                              </c:if>
                                              </div> 
                                              <div id="roTipo">
                     						  <c:if test="${not empty rot.tipoInteresse}">
                       							  ${rot.tipoInteresse}
                       							 </c:if>
                       							 </div>
                                    <!--       <c:if test="${not empty rot.descricao}">
                                              <li><b>Description:</b> ${rot.descricao}</li>   
                                             </c:if> -->
   
                     						 
                                        </div>

                                   <div id="left">
                                       <div id="imagemPequena">

                                       

                                          </div>
                                      </div>
   
                                     
                                      </div>
 
     
                                 </c:forEach>
                           </c:if>
                        </c:if>
                   </div>


                 </div>
          <div class="rightEventos">
                  <c:if test="${not empty Eventos}">

                    <h3>Events in ${itemForEdit.nomePais}</h3>
  
                     <c:forEach var='ev' items='${Eventos}'>
                       <div id="cadaEvento">
  
  	                  <c:if test="${ev.privado == 0}">
                        <c:url value="/pub/centrosInteresse/EventoAction.show" var="evURL">
                          <c:param name='ParentId' value='${ev.idInfo}' />
                        </c:url> 
                       </c:if>
                      <c:if test="${ev.privado != 0}">
                       <c:url value="/main/centrosInteresse/EventoAction_PRM.show" var="evURL">
                        <c:param name='ParentId' value='${ev.idInfo}' />
                       </c:url> 
                      </c:if>

      
                    <div id="nomeEvento">
                      <a href='${evURL}'>${ev.nome}</a>
                   </div>
                  

    <!--  <c:if test="${not empty ev.descricao}">
        <li><b>Description:</b> ${ev.descricao}</li>
      </c:if>--> 
      
                    <div id="datas">
                        <c:if test="${not empty ev.dataInicio}">
                        ${ev.dataInicio} 
                          </c:if>
  
                        <!--   <c:if test="${not empty ev.dataFim}">
                         ${ev.dataFim}
                         </c:if>-->
                    </div>
                    <div id="tipo">
                       <c:if test="${not empty ev.tipoInteresse}">
                         ${ev.tipoInteresse}
                        </c:if>
                        </div>
                          <c:if test="${not empty ev.estrelas}">
                        <c:set var="estrelasEv" value="${ev.estrelas}"/>
   
                        <% for(int i = 1; i <= (Integer)pageContext.getAttribute("estrelasEv"); i++) {%>
                         <img class="no-margin" src="../../images/star.png" title="star" width="2%">
                          <% } %>
      
                         </c:if>
                      </div>
                   </c:forEach>
                     </c:if>
                  </div>
            </div>


<div id="visitas">
        <c:if test="${not empty Visitas}">
           <h3>Visits</h3>
           <br/>
        <c:forEach var='vis' items='${Visitas}'>
	<span id="cadaVisita">
         <c:if test="${vis.privado == 0}">
           <c:url value="/pub/centrosInteresse/VisitaAction.show" var="visURL">
            <c:param name='ParentId' value='${vis.idInfo}' />
            </c:url> 
         </c:if>
       <c:if test="${vis.privado != 0}">
         <c:url value="/main/centrosInteresse/VisitaAction_PRM.show" var="visURL">
          <c:param name='ParentId' value='${vis.idInfo}' />
         </c:url> 
      </c:if>
      <div class="inline">
  	          <span class="visitasPais">
            
           

               <a href='${visURL}'>${vis.nome}</a>
          
                 <br/>
                       <img   src="../../images/pizzas.jpeg" title="star" width="100px" height="100px;" margin="2%">
        
                           </span>
         </div>
         <!--  <c:if test="${not empty vis.descricao}">
           <li><b>Description:</b> ${vis.descricao}</li>   
            </c:if>-->
          <!--  <c:if test="${not empty vis.tipoInteresse}">
            ${vis.tipoInteresse}
           </c:if>
           <c:if test="${not empty vis.estrelas}">
           <c:set var="estrelasVis" value="${vis.estrelas}"/>
        
            <% for(int i = 1; i <= (Integer)pageContext.getAttribute("estrelasVis"); i++) {%>
             <img class="no-margin" src="../../images/star.png" title="star" width="2%">
             <% } %>
          </c:if>--> 
      </span>
         </c:forEach>

      </c:if>
</div>
<!--
<c:if test="${not empty PontosReferencia}">
  <h3>Reference Points</h3>
  <c:forEach var='ptRef' items='${PontosReferencia}'>
 
    <c:if test="${ptRef.privado == 0}">
     <c:url value="/pub/pontosReferencias/VerPontoAction.show" var="ptRefURL">
       <c:param name='ParentId' value='${ptRef.idInfo}' />
     </c:url>   
    </c:if>
    <c:if test="${ptRef.privado != 0}">
     <c:url value="/main/pontosReferencia/PontoAction_PRM.list" var="ptRefURL">
       <c:param name='ParentId' value='${ptRef.idInfo}' />
     </c:url>   
    </c:if>
    
    <b></b> <a href='${ptRefURL}'>${ptRef.nome}</a>
    <blockquote>
    <ul>
      <c:if test="${not empty ptRef.descricao}">
        <li><b>Description:</b> ${ptRef.descricao}</li>
      </c:if>
      <c:if test="${not empty ptRef.estrelas}">
      <c:set var="estrelasPR" value="${ptRef.estrelas}"/>
         <li><b>Average Rating:</b>
        <% for(int i = 1; i <= (Integer)pageContext.getAttribute("estrelasPR"); i++) {%>
          <img class="no-margin" src="../../images/star.png" title="star" width="2%">
        <% } %>
        </li>
      </c:if>
    </ul>
    </blockquote>
  </c:forEach>
<p/>
</c:if>

  -->
  <br/>
<br/>
</body>


</html>
