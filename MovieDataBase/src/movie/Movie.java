package movie;

import java.io.Serializable;

/** 
 * Ez az oszt�ly t�rolja egy film adatait. Megval�s�tja a Serializable interf�szt, hogy ki lehessen
 * menteni f�jlba az adatb�zishoz hozz�adott filmeket.
 */
public class Movie implements Serializable{
	
	//a film c�me
	private String title;
	
	//a film rendez�je
	private String director;
	
	//a film premiere
	private String premier;
	
	//a film hossza percben
	private int length;
	
	//ez a v�ltoz� a kijel�l�shez sz�ks�ges. ennek seg�ts�g�vel lehet filmet t�r�lni
	private boolean marked;
	
	//ez a v�ltoz� pedig a keres�shez kell. akkor lesz az �rt�ke true, ha a film megfelel a keres�si felt�teleknek
	private boolean found;
	
	
	//az adatok be�ll�t�sa a konstruktorban
	public Movie(String title, String director, String premier, int length){
		this.title = title;
		this.director = director;
		this.premier = premier;
		this.length = length;
		this.marked = false;
		this.found = false;
	}
	
	/**
	 * a film c�m�nek lek�rdez�se
	 * @return - a film c�me
	 */
	public String getTitle(){return title;}
	
	/**
	 * a film hossz�nak lek�rdez�se
	 * @return - a film hossza percekben
	 */
	public int getLength(){return length;}
	
	/**
	 * a film premier�nek lek�rdez�se
	 * @return - a film premier�nek d�tuma
	 */
	public String getPremier(){return premier;}
	
	/**
	 * a film rendez�j�nek lek�rdez�se
	 * @return - a film rendez�je
	 */
	public String getDirector(){return director;}
	
	/**
	 * annak lek�rdez�se, hogy ki van-e jel�lve az adott film
	 * @return - true, ha ki van jel�lve, false, ha nincs
	 */
	public boolean isMarked(){return marked;}
	
	/**
	 * a kijel�l�s be�ll�t�sa
	 * @param value - true vagy false, amire �ll�tani szeretn�nk a marked �rt�k�t
	 */
	public void setMarked(boolean value){this.marked = value;}
	
	/**
	 * a found �rt�k�nek lek�rdez�se
	 * @return - true, ha megfelel a keres�si felt�teleknek, false, ha nem
	 */
	public boolean isFound(){return found;}
	
	/**
	 * a found be�ll�t�sa
	 * @param value - az �rt�k, amire be�ll�tjuk
	 */
	public void setFound(boolean value){this.found = value;}
}