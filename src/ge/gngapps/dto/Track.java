package ge.gngapps.dto;

public class Track {
	private int id;
	private int positInAlbum;
	private String title;
	private String duration;
	private int albumId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPositInAlbum() {
		return positInAlbum;
	}
	public void setPositInAlbum(int positInAlbum) {
		this.positInAlbum = positInAlbum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	
	
}
