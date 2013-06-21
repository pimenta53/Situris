<%@include file="/WEB-INF/TagHeader.jspf"%>
<jsp:useBean id="now" class="java.util.Date" />
  
  <div id="footer" title='Version: 4.5.1.0 - January 21, 2011'>
  Copyright &copy; <fmt:formatDate value="${now}" pattern="yyyy" /> SITURIS
   - <c:url var='aboutURL' value='/About.jsp' />
   <a href='${aboutURL}' title='About'>About</a> 
   <a> | </a>
   <a href="mailto:pg20190@alunos.uminho.pt?subject=Complaint about SITURIS">Send a complaint</a>
  </div>
