function checkAlbumFilds() {
	var albumTitle = document.getElementById("albumTitle").value;
	var artist = document.getElementById("artistName").value;
	var releasedDay = document.getElementById("releasedDay").value;
	var select = document.getElementById("releaseMonth");
	var releaseMonth = select.options[select.selectedIndex].text;
	var releaseYear =  document.getElementById("releaseYear").value;
	if(albumTitle != "" && artist != "" && releasedDay > 0 && releasedDay < 32 && releaseMonth != "" && releaseYear > 0) {
		return true;
	} else {
		return false;
	}
}


