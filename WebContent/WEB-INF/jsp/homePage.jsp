<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ge.gngapps.dto.*" import="java.util.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/homePage.css">
	<script type="text/javascript" src="js/homePageDispatcher.js"></script>
	<script type="text/javascript" src="js/homePageAlbumFunctionality.js"></script>
	<script type="text/javascript" src="js/homePageMinorFunctionality.js"></script>
	<script type="text/javascript" src="js/homePageTrackFunctionality.js"></script>
	<script type="text/javascript" src="js/homePageValidators.js"></script>
	<title>Home Page</title>
</head>
<body>
	<div>
		<p id="pageHeader">CRUD</p>
		<%  
			User user = (User) session.getAttribute("user"); 
			@SuppressWarnings("unchecked")
			List<Album> album = (List<Album>) session.getAttribute("album"); 
		%>
		<p>  <%=  user.getfName() %> </p>
		<hr>
	</div>
	<div>
		<p id="message"></p>
	</div>
	<div id="addAlbumSection">
		<table id="createAlbumTable">
			<tr>
				<td><p>Add album</p></td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><input type="text" id="albumTitle"></td>
			</tr>
			<tr>
				<td>Artist:</td>
				<td><input type="text" id="artistName"></td>
			</tr>
			<tr>
				<td>Label:</td>
				<td><input type="text" id="label"></td>
			</tr>
			<tr>
				<td>Released: Day</td>
				<td><input type="number" id="releasedDay"></td>
			</tr>
			<tr>
				<td>Month:</td>
				<td><select id="releaseMonth">
						<option value="1">January</option>
						<option value="2">February</option>
						<option value="3">March</option>
						<option value="4">April</option>
						<option value="5">May</option>
						<option value="6">June</option>
						<option value="7">July</option>
						<option value="8">August</option>
						<option value="9">September</option>
						<option value="10">October</option>
						<option value="11">November</option>
						<option value="12">December</option>
				</select></td>
			</tr>
			<tr>
				<td>Year:</td>
				<td><input type="number" id="releaseYear"></td>
			</tr>
			<tr>
				<td><button id="mainButton" onclick="dispatchProcess(this.innerHTML)">create album</button></td>
			</tr>
		</table>
	</div>
	<div id="existingAlbumsDisplayArea">
		<p>Albums</p>
		<table class="existingAlbumsDisplayArea">
			<thead>
				<tr> <td>Title</td> <td>Artist</td> <td>Label</td> <td>Released</td> <td>Action</td></tr>
			</thead>
			<tbody>
				 <%
					if( album.size() != 0 ) {
						for(int i = 0; i < album.size(); i++) { 
				%>
					<tr> 
						<td> <%= album.get(i).getTitle() %> </td> 
						<td> <%= album.get(i).getArtist() %> </td> 
						<td> <%= album.get(i).getLable() %> </td> 
						<td> <%= album.get(i).getDate() %> </td> 
						<td> <button onclick="showEditTrack('<%=album.get(i).getId()%>')">Edit</button> 
							 <button onclick="deleteAlbum('<%=album.get(i).getId()%>')">Delete</button>
						</td>
					</tr>
				 <% 		
						}
					}
				%> 
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
			window.onload = function () {
				setAlbumAmountMessage();
			}
		</script>
</body>
</html>