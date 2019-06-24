<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  


<html>
<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <title>Tenis de Mesa 2019</title>
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/font-awesome.min.css" rel="stylesheet">
	    <link href="css/prettyPhoto.css" rel="stylesheet">
	    <link href="css/price-range.css" rel="stylesheet">
	    <link href="css/animate.css" rel="stylesheet">
		<link href="css/main.css" rel="stylesheet">
		<link href="css/responsive.css" rel="stylesheet">
	    <!--[if lt IE 9]>
	    <script src="js/html5shiv.js"></script>
	    <script src="js/respond.min.js"></script>
	    <![endif]-->       
	    <link rel="shortcut icon" href="images/ico/favicon.ico">
	    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
	    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
	    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
	    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
	    
	    <link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css" rel="stylesheet">
	    <link href="css/jquery.loading.css" rel="stylesheet">
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css">
	    

		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.scrollUp.min.js"></script>
		<script src="js/price-range.js"></script>
	    <script src="js/jquery.prettyPhoto.js"></script>
	    <script src="js/main.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment-with-locales.js"></script>
    	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    	<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
    	<script src="js/jquery.loading.js"></script>
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    	 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js"></script>
    	<script src="js/commons.js"></script>
    	
    	
		<script src="js/ckeditor.js"></script>
		<script src="js/adapters/jquery.js"></script>
    	
</head>
<body id="idBodyGlobal">

	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
	<jsp:include page="/WEB-INF/views/pages/confirm_action.jsp"></jsp:include>
</body>
</html>