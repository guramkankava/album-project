function createTrackInputInterface(trackCollection) {
	var div = document.createElement("div");
	div.setAttribute('id', 'divForTrackes')
	var table = document.createElement("table");
	table.setAttribute('id', 'tableForTrackes')
	var row = document.createElement("tr");
	var cell1 = document.createElement("td");
	var cell2 = document.createElement("td");
	var cell3 = document.createElement("td");
	var cell4 = document.createElement("td");
	var cell1Content = document.createTextNode("Track");
	var cell2Content = document.createTextNode("Title");
	var cell3Content = document.createTextNode("Duration");
	var cell4Content = document.createTextNode("Action");
	cell1.appendChild(cell1Content);	
	cell2.appendChild(cell2Content);
	cell3.appendChild(cell3Content);
	cell4.appendChild(cell4Content);
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	table.appendChild(row);
	div.appendChild(table);
	document.body.appendChild(div);
	if ( !trackCollection ) {
		addNewRowToTrackInputInterface(table);
	} else if ( trackCollection.length > 0 ) {
		displayTrackes(table, trackCollection);
	}
}

function addNewRowToTrackInputInterface(table, trackCollection) {
		var row = document.createElement("tr");
		var cell1 = document.createElement("td");	
		var cell2 = document.createElement("td");
		var cell3 = document.createElement("td");
		var cell4 = document.createElement("td");
		var rowsAmount = table.rows.length * 4; 
		
		var cell1Content = document.createElement("input");
		cell1Content.type = "number";
		cell1Content.setAttribute("id", "content" + rowsAmount++);
		cell1Content.setAttribute("class", "trackInfo");
		
		var cell2Content = document.createElement("input");
		cell2Content.type = "text";
		cell2Content.setAttribute("id", "content" + rowsAmount++);
		cell2Content.setAttribute("class", "trackInfo");
		
		var cell3Content = document.createElement("input");
		cell3Content.type = "text";
		cell3Content.setAttribute("id", "content" + rowsAmount);
		cell3Content.setAttribute("class", "trackInfo");
	
		var cell4Content = document.createElement("input");
		cell4Content.type = "button";
		cell4Content.value = "Add";
		
		cell4Content.onclick = function() {
			var content1 = document.getElementById('content' + (rowsAmount-2)).value;
			var content2 = document.getElementById('content' + (rowsAmount-1)).value;
			var content3 = document.getElementById('content' + (rowsAmount)).value;
			if (content1 > 0 && content2 != "" && content3 != "") {
				return addNewRowToTrackInputInterface(table);	
			}
		};
		cell1.appendChild(cell1Content);
		cell2.appendChild(cell2Content);
		cell3.appendChild(cell3Content);
		cell4.appendChild(cell4Content);
		row.appendChild(cell1);
		row.appendChild(cell2);
		row.appendChild(cell3);
		row.appendChild(cell4);
		table.appendChild(row);
}

function displayTrackes(table, trackCollection) {
	for (i = 0; i < trackCollection.length; i++) {
		var row = document.createElement("tr");
		var cell1 = document.createElement("td");	
		var cell2 = document.createElement("td");
		var cell3 = document.createElement("td");
		var cell4 = document.createElement("td");
		
		var cell1Content = document.createElement("input");
		cell1Content.type = "text";
		cell1Content.value = trackCollection[i].positInAlbum;
		
		var cell2Content = document.createElement("input");
		cell2Content.type = "text";
		cell2Content.value = trackCollection[i].title;
		
		var cell3Content = document.createElement("input");
		cell3Content.type = "text";
		cell3Content.value = trackCollection[i].duration;
		
		cell1.appendChild(cell1Content);
		cell2.appendChild(cell2Content);
		cell3.appendChild(cell3Content);
		row.appendChild(cell1);
		row.appendChild(cell2);
		row.appendChild(cell3);
		table.appendChild(row);
	}
}

function collectTrackData () {
	var allTrackData = [];
	if ( document.getElementById("tableForTrackes") ) {
		var trackAddingTable = document.getElementById("tableForTrackes");
		var trackInputFileds = trackAddingTable.getElementsByClassName("trackInfo");
		for( index = 0; index < trackInputFileds.length; index ++ ) {
			allTrackData[index] = trackInputFileds[index].value;
		}
	}
	return allTrackData;
}

function showEditTrack(albumId) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var mainButton = document.getElementById('mainButton');
			mainButton.innerHTML = "show album";
			removeElement("existingAlbumsDisplayArea");
			createTrackInputInterface(JSON.parse(this.responseText));
		}
	};
	xhttp.open("GET", "http://localhost:8080/SimpleServletProject/GetTrack"+"?albumId="+albumId, true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}