<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.loggedInUser != null}">
	<script type="text/javascript">
		parent.location.href = "/xv4/home";
	</script>
</c:if>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width">
<title>Login · Oracle DB Lite Manager : ODBLM</title>
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
	<div
		style="width: 400px; margin-left: auto; margin-right: auto; margin-top: -24px; align: center; -moz-border-radius: 5px; border-radius: 5px; background: #EEEEEE; margin-top: 100px; -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset; -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset; box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3), 0 0 40px rgba(0, 0, 0, 0.1) inset;">
		<form method="post" action="login">
			<table width="300" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align="center" valign="middle"></td>
				</tr>
				<tr>
					<td align="center">
						<div>
							<h2>Oracle DB Manager Lite</h2>
						</div>
					</td>
				</tr>
				<tr>
					<td align="center" valign="left">&nbsp;</td>
				</tr>
				<tr>
					<td align="center" valign="left">
						<h3>Database Login</h3>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle">&nbsp;</td>
				</tr>
				<tr>
				<tr>
					<td align="center"><span class="controls"> <input
							name="login" type="text" id="${inputError }" placeholder="Login"
							class="span3">
					</span></td>
				</tr>
				<tr>
					<td align="center"><span class="controls"> <input
							name="password" type="password" id="${inputError }"
							placeholder="Password" class="span3">
					</span></td>
				</tr>
				<tr>
					<td align="center">
						<button type="submit" style="width: 255px;">Sign in</button>
					</td>
				</tr>
				${error }
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center">Click here to learn how to get started</td>
				</tr>
				<tr>
					<td align="center">&nbsp;</td>
				</tr>
				<tr>
					<td align="center"><img src="content/img/orcl-2.png"
						width="90" height="26"> &nbsp; <img
						src="content/img/odblm.png" width="90" height="26"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>

		</form>

	</div>
</body>
</html>