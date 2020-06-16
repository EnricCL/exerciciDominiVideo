package dominiVideos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Crea un programa en Java amb l’estructura de domini de les classes:
 * - Video: esta format por una URL, un títol i una llista de tags*.
 * - Usuari: esta format per un nom, cognom, password i una data de registre.
 * Un usuari pot crear nous vídeos i veure un llistat dels seus videos.
 * Un tag es un text amb una paraula, tenir en compte que un video pot tenir varis tags.
 * La estructura no ha de permetre afegir camps buits, en cas de que n’hi hagi ha de retornar una excepció.
 * 
 * @author Enric Comes
 *
 */

public class MainDominiVideo {

	public static void main(String[] args) {
				
		Scanner entrada = new Scanner(System.in);
		
		String nom, cognom, password, data, dia, mes, agno, nomAcces, passwordAcces, url, titol,tag, resposta, opcio="-1";
		boolean creat=false, accedit=false, demana=true;
		//int opcio=-1;
		
		Usuari usuari1 = null;
		
		ArrayList<Video> llistaVideos = new ArrayList<>() ;
		
		while(!creat) {
			//Es demanen les dades de l'usuari i es comproba que no siguin camps buits
			System.out.println("Introdueix un nom:");
			nom = entrada.nextLine();
			try {
				comprobarCamp(nom);
			}catch(campNul cn) {
				cn.printStackTrace();
			}
			System.out.println("Introdueix un cognom:");
			cognom = entrada.nextLine();
			try {
				comprobarCamp(cognom);
			}catch(campNul cn) {
				cn.printStackTrace();
			}
			System.out.println("Introdueix una clau:");
			password = entrada.nextLine();
			try {
				comprobarCamp(password);
			}catch(campNul cn) {
				cn.printStackTrace();
			}
			
			if(nom.isBlank() || cognom.isBlank() || password.isBlank()) {
				System.out.println("No has introduït totes les dades, tornem a començar.");
			}else {
			
				//Instància de GregorianCalendar per a obtenir dia, mes i any actual automàticament
				Calendar c = new GregorianCalendar();
				dia = Integer.toString(c.get(Calendar.DATE));
				mes = Integer.toString(c.get(Calendar.MONTH)+1); //El primer mes té valor 0
				agno = Integer.toString(c.get(Calendar.YEAR));
				data = dia+"/"+mes+"/"+agno;
				
				//Instància de Usuari que s'ha creat
				usuari1 = new Usuari(nom, cognom, password, data);
				System.out.println("S'ha creat l'usuari amb les dades següents: "
								+"\nNom: "+usuari1.getNom()
								+"\nCognom: "+usuari1.getCognom()
								+"\nClau: "+usuari1.getPassword()
								+"\nData de Registre: "+usuari1.getData());
				creat=true;
			}
		}//while creat
		
		
		//Accès a l'usuari creat
		while(!accedit) {
			System.out.println("Introdueix el nom de l'usuari que has creat:");
			nomAcces = entrada.nextLine();
		
			if(nomAcces.equals(usuari1.getNom())) {
				System.out.println("Introdueix la clau d'accès:");
				passwordAcces = entrada.nextLine();
			
				if(passwordAcces.equals(usuari1.getPassword())) {
					System.out.println("Has accedit!");
					accedit=true;
				}else {
					System.out.println("La clau d'accès és incorrecte.");
				}
			
			}else {
				System.out.println("L'usuari introduït no és el que has creat ara.");
			}
		}//while accès usuari creat
		
		//Si ha accedit, es mostren 3 opcions fins que marqui 0 per a sortir
		if(accedit) {
			
			while(opcio!="0") {
				System.out.println("Què vols fer?"
					+ "\n1 - Crear un video nou"
					+ "\n2- Llistar els videos propis"
					+ "\n0 - Sortir");
				opcio = entrada.nextLine();
				//entrada.nextLine();
				
				if(opcio.equals("0")) {
					System.out.println("Programa finalitzat.");
					System.exit(0);
				
				}else if(opcio.equals("1")){
					
					//Creació d'un Video
					
					//Es demanen les dades i també es comproba que no hi hagi dades buides
					System.out.println("Introdueix la URL del video:");
					url = entrada.nextLine();
					try {
						comprobarCamp(url);
					}catch(campNul cn) {
						cn.printStackTrace();
					}
					System.out.println("Introdueix un titol per al video:");
					titol = entrada.nextLine();
					try {
						comprobarCamp(titol);
					}catch(campNul cn) {
						cn.printStackTrace();
					}
					
					//El video pot tenir més d'un tag, es crea llista i es demana
					ArrayList<String> tags = new ArrayList<>() ;
					while(demana) {
						System.out.println("Introdueix un tag:");
						tag = entrada.nextLine();
						try {
							comprobarCamp(tag);
						}catch(campNul cn) {
							cn.printStackTrace();
						}
						tags.add(tag);
						
						//Per si es vol afegir un altre tag més
						System.out.println("Vols afegir un altre tag?(Si o No)");
						resposta = entrada.nextLine();
						if(!resposta.equalsIgnoreCase("si")){
							demana=false;
						}
					}
					
					if(url.isBlank() || titol.isBlank() || tags.isEmpty()) {
						System.out.println("Al haver una dada buïda, el video no serà afegit.");
						
					}else {
						//Es crea el video i s'agefeix a un llistat de videos
						Video nou_video = new Video(url, titol, tags);
						llistaVideos.add(nou_video);
						System.out.println("El video "+titol+" s'ha afegit al compte de l'usuari!");
					}
					demana=true;
	
					
				} else if(opcio.contentEquals("2")) {
					
					//Veure el llistat dels seus videos, fent Iterator de la llista de videos acumulat
					
					System.out.println("Llistat de videos:");
					Iterator<Video> it = llistaVideos.iterator();
					Video videoActual;
					while(it.hasNext()) {
						videoActual = it.next();
						System.out.println("Titol: "+videoActual.getTitol()
											+"\nURL: "+videoActual.getUrl());
						System.out.print("Tags: ");
						
						//Bucle per mostrar la subllista de tags que té cada video emmagatzemat
						for(int i=0; i<videoActual.getTags().size() ; i++) {
							if(i==(videoActual.getTags().size()-1)) {
								System.out.print(videoActual.getTags().get(i)+".");//si és l'ultim
							}else {
								System.out.print(videoActual.getTags().get(i)+", ");
							}
						}
						System.out.println("\n");
					}
					
				} else {
					System.out.println("No has seleccionat cap de les altres opcions.");
				}
				
			}//while opcio

		}//if accedit
		
		entrada.close();
		
	}
	
	/*Funció per comprobar si el camp és buït o no
	 * @param String a comprobar
	 * @return throw amb missatge d'error
	 */
	static void comprobarCamp(String camp) throws campNul{
		if(camp.equals("")) {
			throw new campNul("No es poden esciure camps buits!");
		}
	}


}

// Classe heredara d'Exception per a crear una excepció personalitzada
class campNul extends Exception{
	public campNul(){
	}
	public campNul(String msj_error){
		super(msj_error);
	}
}
