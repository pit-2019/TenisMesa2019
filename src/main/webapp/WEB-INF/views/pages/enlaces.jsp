<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class='container'>
<div class='row lista-links'>
<c:forEach var="noticia" items="${listaNoticias}">
<div class='col-sm-3' >
<div class='panel panel-default' >
<div class='panel-heading'  style='min-height:100px'>
${noticia.titulo}
</div>
<div class='panel-body' style='height:190px'>
${noticia.imagen}
</div>
<div class='panel-footer'>
<b>Por: ${noticia.autor}</b>
${noticia.fecha}

</div>
</div>
</div>
</c:forEach>

</div>
</div>