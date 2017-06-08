package ge.gngapps.dto;

public class Album {
	private int id;
	private String title;
	private String artist;
	private String lable;
	private String date;
	private int albumOwnerId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAlbumOwnerId() {
		return albumOwnerId;
	}
	public void setAlbumOwnerId(int albumOwnerId) {
		this.albumOwnerId = albumOwnerId;
	}
	
	
}
