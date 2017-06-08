function setAlbumAmountMessage () {
	var albumAmountMessage = document.getElementById("message");
	var albumsTable = document.getElementById("existingAlbumsDisplayArea").childNodes[3];
	if(!albumsTable) {
		albumAmountMessage.innerHTML = "You have no albums curently";
	} else {
		var albumsAmount = albumsTable.rows.length - 1;
		if(albumsAmount == 1) {
			albumAmountMessage.innerHTML = "You have " + albumsAmount + " album curently";
		} else {
			albumAmountMessage.innerHTML = "You have " + albumsAmount + " albums curently";
		}
	}
}

function removeElement(elementId) {
	var elementToBeRemoved = document.getElementById(elementId);
	document.body.removeChild(elementToBeRemoved);
}
