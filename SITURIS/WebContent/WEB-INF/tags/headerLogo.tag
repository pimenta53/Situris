<%@ include file="/WEB-INF/TagHeader.jspf" %>
<%@ attribute name="title" required="true" rtexprvalue="true" %>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="keywords" content="situris">
  <meta name="description" content="Sistema de Informação Turística.">
  <title>SITURIS - ${title}</title>
  <c:url var='stylesheetURL' value='/css/stylesheet10.css' />
  <link rel="stylesheet" type="text/css" href='${stylesheetURL}'  media="all"> 
  <div>
    <c:url var="logotipo" value="/images/situris.png" />
    <c:url var='homePageURL' value='/pub/home/HomeAction.list' />
    <a href='${homePageURL}' title='Home page'>
      <img class="no-margin" src="${logotipo}" title="SITURIS"  border="0"></a>
    
    <div align="center">
      <tags:headerOptions/>
      <tags:headerMenu/>
      <tags:showFocus/>
    </div>
  </div>
</head>
