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
<title>Create Tablespace � Oracle DB Lite Manager : ODBLM</title>
<meta name="robots" content="noindex, nofollow">
<meta name="description"
	content="Le projet consiste � d�velopper une application web permettant d'administrer un serveur de bases de donn�es Oracle." />
<meta name="keywords"
	content="projet, khaled, jouini, jsp, jsf, oracle, 11g, 12c, 10g, admin, sys, dba, dbms, sgbd, base, donn�es, ali, ben messaoud, dni, isitcom, ing�nieur, engineer, t�l�informatique, tablespace, redo log files, undo, segment, index, table, annulation, dbwr, logwr" />
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
<script type='text/javascript'>
	//<![CDATA[ 
	$(window)
			.load(
					function() {
						$(function() {
							var scntDiv = $('#p_scents');
							var i = $('#p_scents p').size() + 1;

							$('.addScnt')
									.live(
											'click',
											function() {
												$(
														'<p><table width="425" border="0" cellspacing="0" cellpadding="0">  <tr>    <td colspan="3"><input type="text" id="p_scnt" name="smallFileName" value="" placeholder="Smallfile Tablespace Name" class="span4" style="width:363px;" /></td>    <td width="56" align="left"><a style="margin-bottom:5px;" href="#" class="remScnt"><img src="content/img/remove_file.png"></a></td>  </tr>  <tr>    <td width="144" colspan="2">    	<input type="text" id="p_scnt2" name="smallFileSize" value="" placeholder="Smallfile Tablespace Size" class="input-large"  />    </td>    <td width="155">    	<select name="smallFileSizeUnit" class="input-medium" >          <option value="K">K</option>          <option value="M">M</option>          <option value="G">G</option>          <option value="T">T</option>        </select>     </td>    <td>&nbsp;</td>  </tr>  <tr>    <td width="70">    	<label class="checkbox">            <input type="checkbox" value="reuse" name="reuse[]">            <span class="metro-checkbox">Reuse</span>        </label>    </td>    <td colspan="2">    	<table width="201" border="0" align="right" cellpadding="0" cellspacing="0">          <tr>            <td width="85"><label style="padding-right:10px; padding-top:4px;">Autoextend</label></td>            <td width="58">                <label class="radio">                    <input type="radio" name="autoextend[]"  value="on" >                    <span class="metro-radio">On</span>                </label>             </td>            <td width="58">                <label class="radio">                    <input type="radio" name="autoextend"  value="off" >                    <span class="metro-radio">Off</span>                </label>             </td>                   </tr>        </table>    </td>    <td>&nbsp;</td>  </tr></table></p>')
														.appendTo(scntDiv);
												i++;
												return false;
											});

							$('.remScnt').live('click', function() {
								if (i > 2) {
									$(this).parents('p').remove();
									i--;
								}
								return false;
							});
						});

					});//]]>
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
				<li><a href="tablespaceManage">Manage Tablespaces</a> <span
					class="divider">/</span></li>
				<li class="active">Create Tablespace</li>
			</ul>
		</div>
		<!-- End breadcrumbs
		================================================== -->


		<!-- Start alert
		================================================== -->
		${errors}${success}
		
		<!-- end alert================================================== -->


		<div style="padding-bottom: 2px;">
			<div style="display: inline-block; float: left;">
				<h2>Create Tablespace</h2>
			</div>
			<div style="display: inline-block; float: right; padding-top: 4px;">
				<form class="form-search">
					<input type="text" class="input-medium search-query">
					<button type="submit" class="btn">Search</button>
				</form>
			</div>
		</div>
		<br> <br>
		<div>
			<legend></legend>
		</div>
		<div>
			<form method="post" action="tablespaceAddProcess">
				<table width="958" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="right" valign="top"><label
							style="padding-right: 10px; padding-top: 4px;">Tablespace
								name*</label></td>
						<td align="left"><input name="tablespaceName"
							id="tablespacename" type="text" placeholder="Tablespace name"
							class="span4"></td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="200" align="right" valign="top"><label
							style="padding-right: 10px; padding-top: 4px;">Extent
								managment</label></td>
						<td width="615" align="left"><label class="radio"> <input
								type="radio" name="optionsExtentManagment" value="local" checked>
								<span class="metro-radio">Locally managed</span>
						</label> <label class="radio"> <input type="radio"
								name="optionsExtentManagment" value="doctionnary"> <span
								class="metro-radio">Dictionnary managed</span>
						</label></td>
						<td width="143" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top"><label
							style="padding-right: 10px; padding-top: 4px;">Type</label></td>
						<td align="left"><table width="368" border="0"
								cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="2"><label class="radio"> <input
											type="radio" name="optionsTablespaceType" value="permanent"
											checked> <span class="metro-radio">Permanent</span>
									</label></td>
								</tr>
								<tr>
									<td width="40">&nbsp;</td>
									<td width="328"><label class="checkbox"> <input
											type="checkbox" value="true"
											name="SetPermenantTablespaceAsDefaultTablespace"> <span
											class="metro-checkbox">Set as a default tablespace</span>
									</label></td>
								</tr>
								<tr>
									<td colspan="2"><label class="radio"> <input
											type="radio" name="optionsTablespaceType" value="temporary">
											<span class="metro-radio">Temporary</span>
									</label></td>
								</tr>
								<tr>
									<td width="40">&nbsp;</td>
									<td width="328"><label class="checkbox"> <input
											type="checkbox" value="true"
											name="SetTemporaryTablespaceAsDeafaultTemporaryTablespace">
											<span class="metro-checkbox">Set as a deafault
												temporary tablespace</span>
									</label></td>
								</tr>
							</table></td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top"><label
							style="padding-right: 10px; padding-top: 4px;">Status</label></td>
						<td align="left"><label class="radio"> <input
								type="radio" name="optionsTablespaceStatus" value="rw" checked>
								<span class="metro-radio">Read Write</span>
						</label> <label class="radio"> <input type="radio"
								name="optionsTablespaceStatus" value="ro"> <span
								class="metro-radio">Read Only</span>
						</label> <label class="radio"> <input type="radio"
								name="optionsTablespaceStatus" value="offline"> <span
								class="metro-radio">Offline</span>
						</label></td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top">&nbsp;</td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" valign="top"><label
							style="padding-right: 10px; padding-top: 4px;">Datafiles</label></td>
						<td align="left"><table width="500" border="0"
								cellspacing="0" cellpadding="0">
								<tr>
									<td colspan="3"><label class="radio"> <input
											type="radio" name="optionsStorage" value="UseBigFiles"
											checked> <span class="metro-radio">Use Bigfile
												Tablespace</span>
									</label></td>
								</tr>
								<tr>
									<td colspan="3"><label class="radio"> <input
											type="radio" name="optionsStorage" value="UseSmallFiles"
											checked> <span class="metro-radio">Use
												Smallfiles Tablespace</span>
									</label></td>
								</tr>
								<tr>
									<td width="40">&nbsp;</td>
									<td width="460" colspan="2">
										<div id="p_scents">
											<p>
											<table width="425" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td colspan="3"><input type="text" id="p_scnt"
														name="smallFileName" value=""
														placeholder="Smallfile Tablespace Name" class="span4"
														style="width: 363px;" /></td>
													<td width="56" align="left"><a href="#"
														class="addScnt" title="Add another datafile"><img
															src="content/img/add_file.png"></a></td>
												</tr>
												<tr>
													<td width="144" colspan="2"><input type="text"
														id="p_scnt2" name="smallFileSize" value=""
														placeholder="Smallfile Tablespace Size"
														class="input-large" /></td>
													<td width="155"><select name="smallFileSizeUnit"
														class="input-medium">
															<option value="K">K</option>
															<option value="M">M</option>
															<option value="G">G</option>
															<option value="T">T</option>
													</select></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td width="70"><label class="checkbox"> <input
															type="checkbox" value="reuse" name="reuse[]"> <span
															class="metro-checkbox">Reuse</span>
													</label></td>
													<td colspan="2">
														<table width="201" border="0" align="right"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="85"><label
																	style="padding-right: 10px; padding-top: 4px;">Autoextend</label></td>
																<td width="58"><label class="radio"> <input
																		type="radio" name="autoextend[]" value="on">
																		<span class="metro-radio">On</span>
																</label></td>
																<td width="58"><label class="radio"> <input
																		type="radio" name="autoextend" value="off"> <span
																		class="metro-radio">Off</span>
																</label></td>
															</tr>
														</table>
													</td>
													<td>&nbsp;</td>
												</tr>
											</table>
											</p>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
								</tr>
							</table></td>
						<td align="left" valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3" align="right" valign="top">
							<div class="form-actions" align="right">
								<button type="submit" class="btn">Save changes</button>
								<button type="button" class="btn">Cancel</button>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<div style="width: 958px; margin-left: auto; margin-right: auto;">

		<br>
		<br>
		<br>Copyright � 2013 Ali Ben Messaoud - Oracle Lite DBMS Project. All rights
		reserved. Hosted by (mt) Home.
	</div>

</body>
</html>



