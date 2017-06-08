function collectAlbumData() {
	var album = {};
	var albumTitle = document.getElementById("albumTitle").value;
	var artistName = document.getElementById("artistName").value;
	var albumLabel = document.getElementById("label").value;
	var releasedDay = document.getElementById("releasedDay").value;
	var select = document.getElementById("releaseMonth");
	var releaseMonth = select.options[select.selectedIndex].value;
	var releaseYear =  document.getElementById("releaseYear").value;
	var completeDate = releaseYear + "-" + releaseMonth + "-" + releasedDay;
	album.albumTitle = albumTitle;
	album.artistName = artistName;
	album.albumLabel = albumLabel;
	album.releaseDate = completeDate;
	return album;
}

function persistAlbumData(albumData, trackData) {
	var trackIndex = 1;
	var infoIndex = 1;
	var requestParams = "albumTitle="+albumData.albumTitle + "&artistName=" + albumData.artistName + "&albumLabel=" + albumData.albumLabel + "&releaseDate=" + albumData.releaseDate;
	for ( index = 0; index < trackData.length; index ++ ) {
		requestParams += "&track" + trackIndex + "info" + infoIndex + "=" + trackData[index];
		if ((infoIndex % 3) == 0) {
			trackIndex ++;
			infoIndex = 1;
			continue;
		}
		infoIndex ++;
	}
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			displayAlbums(JSON.parse(this.responseText));
		}
	};
	xhttp.open("POST", "http://localhost:8080/SimpleServletProject/persistAlbumData", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(requestParams);
}

function displayAlbums (albumCollection) {
	if (document.getElementById("existingAlbumsDisplayArea")) {
		removeElement("existingAlbumsDisplayArea");
	}
	var div = document.createElement("div");
	div.setAttribute("id", "existingAlbumsDisplayArea");
	var table = document.createElement("table"); 
	table.setAttribute("class", "existingAlbumsDisplayArea");
	var thead = document.createElement("thead");
	var tbody = document.createElement("tbody");
	var row = document.createElement("tr");
	var cell1 = document.createElement("td");
	var cell2 = document.createElement("td");
	var cell3 = document.createElement("td");
	var cell4 = document.createElement("td");
	var cell5 = document.createElement("td");
	var cell1Content = document.createTextNode("Title");
	var cell2Content = document.createTextNode("Artist");
	var cell3Content = document.createTextNode("Label");
	var cell4Content = document.createTextNode("Released");
	var cell5Content = document.createTextNode("Action");
	
	cell1.appendChild(cell1Content);
	cell2.appendChild(cell2Content);
	cell3.appendChild(cell3Content);
	cell4.appendChild(cell4Content);
	cell5.appendChild(cell5Content);
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	thead.appendChild(row)
	table.appendChild(thead);
	table.appendChild(tbody);
	div.appendChild(table);
	document.body.appendChild(div);
	for ( i = 0; i < albumCollection.length; i++ ) {
		var row = document.createElement("tr");
		
		var cell1 = document.createElement("td");
		var cell2 = document.createElement("td");
		var cell3 = document.createElement("td");
		var cell4 = document.createElement("td");
		var cell5 = document.createElement("td");
		
		var cell1Content = document.createTextNode(albumCollection[i].title);
		var cell2Content = document.createTextNode(albumCollection[i].artist);
		var cell3Content = document.createTextNode(albumCollection[i].lable);
		var cell4Content = document.createTextNode(albumCollection[i].date);
		
		var cell5ContentEditButton = document.createElement("input");
		cell5ContentEditButton.type = "button";
		cell5ContentEditButton.value = "Edit";
		var albumId = albumCollection[i].id;
		cell5ContentEditButton.onclick = function () {
			return showEditTrack(albumId);
		};
		var cell5ContentDeleteButton = document.createElement("input");
		cell5ContentDeleteButton.type = "button";
		cell5ContentDeleteButton.value = "Delete";
		cell5ContentDeleteButton.onclick = function() {
			return deleteAlbum(albumId);
		}
		cell1.appendChild(cell1Content);
		cell2.appendChild(cell2Content);
		cell3.appendChild(cell3Content);
		cell4.appendChild(cell4Content);
		cell5.appendChild(cell5ContentEditButton);
		cell5.appendChild(cell5ContentDeleteButton);
		row.appendChild(cell1);
		row.appendChild(cell2);
		row.appendChild(cell3);
		row.appendChild(cell4);
		row.appendChild(cell5);
		tbody.appendChild(row);
	}
	setAlbumAmountMessage();			
}

function deleteAlbum (albumId) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			displayAlbums(JSON.parse(this.responseText));
		}
	};
	xhttp.open("POST", "http://localhost:8080/SimpleServletProject/deleteAlbum", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("albumId=" + albumId);
} 					


