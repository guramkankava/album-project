function dispatchProcess (buttonValue) {
	var mainButton = document.getElementById('mainButton');
	if(buttonValue == "create album") {
		if(checkAlbumFilds()) {
			mainButton.innerHTML = "Done editing";
			if(document.getElementById("existingAlbumsDisplayArea")) {
				removeElement("existingAlbumsDisplayArea");
			}
			createTrackInputInterface();
		}
	} else if(buttonValue == "Done editing") {
		mainButton.innerHTML = "create album";
		var albumData = collectAlbumData();
		var trackData = collectTrackData();
		removeElement("divForTrackes");
		persistAlbumData(albumData, trackData);
	} else if (buttonValue == "show album") {
		removeElement("divForTrackes");
		mainButton.innerHTML = "create album";
		window.location.reload(true);
	}
}

