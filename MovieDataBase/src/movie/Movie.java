package movie;

import java.io.Serializable;

/** 
 * Ez az osztály tárolja egy film adatait. Megvalósítja a Serializable interfészt, hogy ki lehessen
 * menteni fájlba az adatbázishoz hozzáadott filmeket.
 */
public class Movie implements Serializable{
	
	//a film címe
	private String title;
	
	//a film rendezõje
	private String director;
	
	//a film premiere
	private String premier;
	
	//a film hossza percben
	private int length;
	
	//ez a változó a kijelöléshez szükséges. ennek segítségével lehet filmet törölni
	private boolean marked;
	
	//ez a változó pedig a kereséshez kell. akkor lesz az értéke true, ha a film megfelel a keresési feltételeknek
	private boolean found;
	
	
	//az adatok beállítása a konstruktorban
	public Movie(String title, String director, String premier, int length){
		this.title = title;
		this.director = director;
		this.premier = premier;
		this.length = length;
		this.marked = false;
		this.found = false;
	}
	
	/**
	 * a film címének lekérdezése
	 * @return - a film címe
	 */
	public String getTitle(){return title;}
	
	/**
	 * a film hosszának lekérdezése
	 * @return - a film hossza percekben
	 */
	public int getLength(){return length;}
	
	/**
	 * a film premierének lekérdezése
	 * @return - a film premierének dátuma
	 */
	public String getPremier(){return premier;}
	
	/**
	 * a film rendezõjének lekérdezése
	 * @return - a film rendezõje
	 */
	public String getDirector(){return director;}
	
	/**
	 * annak lekérdezése, hogy ki van-e jelölve az adott film
	 * @return - true, ha ki van jelölve, false, ha nincs
	 */
	public boolean isMarked(){return marked;}
	
	/**
	 * a kijelölés beállítása
	 * @param value - true vagy false, amire állítani szeretnénk a marked értékét
	 */
	public void setMarked(boolean value){this.marked = value;}
	
	/**
	 * a found értékének lekérdezése
	 * @return - true, ha megfelel a keresési feltételeknek, false, ha nem
	 */
	public boolean isFound(){return found;}
	
	/**
	 * a found beállítása
	 * @param value - az érték, amire beállítjuk
	 */
	public void setFound(boolean value){this.found = value;}
}