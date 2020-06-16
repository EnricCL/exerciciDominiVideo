package dominiVideos;
/**
 * - Usuari: esta format per un nom, cognom, password i una data de registre.
 * 
 * @author Enric Comes
 *
 */
public class Usuari {
	
	private String nom;
	private String cognom;
	private String password;
	private String data;
	
	public Usuari(String nom, String cognom, String password, String data) {
		this.nom = nom;
		this.cognom = cognom;
		this.password = password;
		this.data = data;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	

}
