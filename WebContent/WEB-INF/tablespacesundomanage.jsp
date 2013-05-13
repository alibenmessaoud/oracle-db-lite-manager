<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.loggedInUser == null}">
	<script type="text/javascript">
		parent.location.href = "/xv4/login";
	</script>
</c:if>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width">
<title>Manage Undo Tablespace · Oracle DB Lite Manager : ODBLM</title>
<meta name="robots" content="noindex, nofollow">
<meta name="description"
	content="Le projet consiste à développer une application web permettant d'administrer un serveur de bases de données Oracle." />
<meta name="keywords"
	content="projet, khaled, jouini, jsp, jsf, oracle, 11g, 12c, 10g, admin, sys, dba, dbms, sgbd, base, données, ali, ben messaoud, dni, isitcom, ingénieur, engineer, téléinformatique, tablespace, redo log files, undo, segment, index, table, annulation, dbwr, logwr" />
<meta name="author" content="Ali Ben Messaoud" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="content/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="content/css/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="content/css/bootmetro.css">
<link rel="stylesheet" type="text/css"
	href="content/css/bootmetro-tiles.css">
<link rel="stylesheet" type="text/css"
	href="content/css/bootmetro-charms.css">
<link rel="stylesheet" type="text/css"
	href="content/css/metro-ui-light.css">
<link rel="stylesheet" type="text/css" href="content/css/icomoon.css">
<link rel="stylesheet" type="text/css" href="content/css/demo.css">
<link rel="stylesheet" type="text/css"
	href="scripts/google-code-prettify/prettify.css">
<link rel="shortcut icon" href="content/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="content/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="content/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="content/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="content/ico/apple-touch-icon-57-precomposed.png">
<script src="scripts/modernizr-2.6.1.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write("<script src='scripts/jquery-1.8.2.min.js'>\x3C/script>")
</script>
<script type="text/javascript"
	src="scripts/google-code-prettify/prettify.js"></script>
<script type="text/javascript" src="scripts/jquery.mousewheel.js"></script>
<script type="text/javascript" src="scripts/jquery.scrollTo.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/bootmetro.js"></script>
<script type="text/javascript" src="scripts/bootmetro-charms.js"></script>
<script type="text/javascript" src="scripts/demo.js"></script>
<script type="text/javascript" src="scripts/holder.js"></script>
<script type="text/javascript">
	$(".metro").metro();
</script>
<link rel="stylesheet" type="text/css" href="content/css/my-css.css">
<style type="text/css">
padding-right-10 {
	padding-right: 10px;
}
</style>
</head>
<body>
	<!-- Start Subhead
	================================================== -->
	<header id="nav-bar" class="container-fluid"
		style="width: 818px; margin-left: auto; margin-right: auto; margin-top: -24px; background-color: rgba(182, 182, 182, 0.7);">
		<div class="row-fluid">
			<div class="span8">
				<div id="header-container">
					<a id="backbutton" class="win-backbutton" title="Go home!"
						href="home"></a>
					<div style="width: 300px; margin-bottom: -6px; margin-top: 5px;">
						<h4>
							<img src="content/img/db-icon.png" width="16" height="20"
								align="absbottom"> Oracle DB Lite Manager
						</h4>
					</div>
					<div class="dropdown">
						<a class="header-dropdown dropdown-toggle accent-color"
							data-toggle="dropdown" href="#"> Home <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="./tablespacesManage">Manage tablespaces</a></li>
							<li><a href="./tablespacesUndoManage">Manage UNDO
									tablespace</a></li>
							<li><a href="./redoManage">Manage Redo Log Files</a></li>
							<li><a href="./usersManage">Manage users</a></li>
							<li><a href="./queryManage">Execute query</a></li>
							<li class="divider"></li>
							<li><a href="./home">Home</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="top-info" class="pull-right"
				style="float: right; margin-right: -70px; margin-top: 5px;">
				<a href="userView?userID=${sessionScope.loggedInUser}"
					class="pull-left">
					<div class="top-info-block">
						<h3>${sessionScope.loggedInUser}</h3>
						<h4>Oracle</h4>
					</div>
					<div class="top-info-block">
						<b class="icon-user"></b>
					</div>
				</a>
				<hr class="separator pull-left" />
				<a id="settings" class="pull-left" onclick="window.location.href = 'logout'"> <b
					class="icon-exit"></b>
				</a>
			</div>
		</div>
	</header>
	<!-- End Subhead
	================================================== -->


	<div
		style="width: 958px; margin-left: auto; margin-right: auto; margin-top: -20px;">
		<!-- Start breadcrumbs
		================================================== -->
		<div id="bread_crumbs" style="margin-bottom: -20px;">
			<ul class="breadcrumb">
				<li><a href="home">Home</a> <span class="divider">/</span></li>
				<li>Manage Undo Tablespaces</li>
			</ul>
		</div>
		<!-- End breadcrumbs
		================================================== -->


		<!-- Start alert
		================================================== -->

		<!-- End alert
		================================================== -->


		<div style="padding-bottom: 2px;">
			<div style="display: inline-block; float: left;">
				<h2>Undo Tablespaces List</h2>
			</div>
			<div style="display: inline-block; float: right; padding-top: 4px;">
				<a href="undoTablespaceAdd" class="btn btn-primary">Create Undo Tablespace</a>
			</div>
		</div>
		<br> <br>
		<div>
			<legend> </legend>
		</div>
		<div>
			<table width="958" border="0" cellspacing="0" cellpadding="0"
				class="table table-striped table-bordered">
				<tr>
					<td width="300">Name</td>
					<td width="388">Stats</td>
					<td width="60">Status</td>
					<td width="220">Action</td>
				</tr>
				${tablespaces }


			</table>


		</div>

	</div>
	<div style="width: 958px; margin-left: auto; margin-right: auto;">

		<br>
		<br>
		<br>Copyright © 2013 Ali Ben Messaoud - Oracle Lite DBMS Project. All rights
		reserved. Hosted by (mt) Home.
	</div>
</body>
</html>