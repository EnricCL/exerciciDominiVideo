package dominiVideos;

import java.util.ArrayList;

/**
 * - Video: esta format por una URL, un títol i una llista de tags*.
 * Un tag es un text amb una paraula, tenir en compte que un video pot tenir varis tags.
 * 
 * @author Enric Comes
 *
 */
public class Video {
	
	private String url;
	private String titol;
	private ArrayList<String> tags;
	
	public Video(String url, String titol, ArrayList<String> tags) {
		this.url = url;
		this.titol = titol;
		this.tags = tags;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	
	

}
