package movie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Ez az oszt�ly alkotja az adatb�zist. Itt vannak elt�rolva a filmek adatai �s a hozz�juk
 * kapcsol�d� met�dusok is ebben az oszt�lyban vannak defini�lva. Az oszt�ly megval�s�tja
 * az AbstractTableModel interf�szt, hogy a t�bl�zat �t tudja venni az adatokat.
 */
public class MovieDataBase extends AbstractTableModel{

	//ArrayList a filmek t�rol�s�hoz
	List<Movie> dataBase = new ArrayList<Movie>();

	/*az AbstractTableModel megval�s�tand� f�ggv�nyei*/

	/**
	 * a t�bl�zathoz sz�ks�ges sorok sz�ma
	 * @return - sorok sz�ma
	 */
	@Override
	public int getRowCount() {
		return dataBase.size();
	}

	/**
	 * a t�bl�zathoz sz�ks�ges oszlopok sz�ma
	 * @return - az oszlopok sz�ma, itt: 6, mert 6 oszlopunk lesz
	 */
	@Override
	public int getColumnCount() {
		return 6;
	}

	/**
	 * a t�bl�zat cell�inak �rt�kei
	 * @param rowIndex - sorindex
	 * @param columnIndex - oszlopindex
	 * @return - az adott cella megfelel� tartalma
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 Movie movie = dataBase.get(rowIndex);
		 switch(columnIndex) {
			 case 0: return movie.getTitle();
			 case 1: return movie.getDirector();
			 case 2: return movie.getPremier();
			 case 3: return movie.getLength();
			 case 4: return movie.isFound();
			 default: return movie.isMarked();
		 }
	}
	
	/**
	 * a fejl�cek be�ll�t�sa
	 * @param index - oszlopindex
	 * @return - a megfelel� oszlop fejl�c�nek neve
	 */
	public String getColumnName(int index){
		if(index == 0)
			return "C�m";
		else if(index == 1)
			return "Rendez�";
		else if(index == 2)
			return "Premier";
		else if(index == 3)
			return "Hossz";
		else if(index == 4)
			return "Tal�lat";
		else
			return "Kijel�l";
	}
	
	/**
	 * az adatok megjelen�t�se a megfelel� form�tumban
	 * @param index - oszlopindex
	 * @return - a megfelel� t�pus
	 */
	public Class<?> getColumnClass(int index){
		if(index == 4 || index == 5)
			return Boolean.class;
		else if(index == 3)
			return Integer.class;
		else
			return String.class;
	}
	
	/**
	 * az utols� (kiv�laszt�s) cell�t szerkeszthet�v� tessz�k
	 * @param row - sorindex (nincs r�s sz�ks�g)
	 * @param column - oszlopindex
	 * @return - szerkeszthe�-e vagy nem
	 */
	public boolean isCellEditable(int row, int column){
		if(column == 5)
			return true;
		else
			return false;
	}
	
	/**
	 * az �t�ll�tott elemet elmentj�k a list�ban
	 * @param value - az �rt�k amire �t�ll�tjuk
	 * @param row - sorindex
	 * @param column - oszlopindex
	 */
	public void setValueAt(Object value, int row, int column){
		if(column == 5)
			dataBase.get(row).setMarked((Boolean)value);
		this.fireTableDataChanged();
	}
	
	/**
	 * egy film hozz�ad�sa az adatb�zishoz. ha m�r benne van az adatb�zisban, nem adjuk hozz�
	 * @param m - az adatb�zishoz hozz�adand� film
	 */
	public void addMovie(Movie m){
		boolean exist = false;
		for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
		    Movie a = iter.next();
		    if(m.getTitle().equals(a.getTitle()) && m.getLength() == a.getLength() && 
		    		m.getPremier().equals(a.getPremier()) && m.getDirector().equals(a.getDirector()))
		    	exist = true;  	
		}
		if(!exist)
			dataBase.add(m);
		this.fireTableDataChanged();
	}
	
	/**
	 * a kijel�lt filmek t�rl�se az adatb�zisb�l
	 */
	public void delete(){
		for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
		    Movie a = iter.next();
		    if (a.isMarked())
		        iter.remove();
		}
		this.fireTableDataChanged();
	}
	
	/**
	 * adott tulajdons�ggal rendelkez� filmek t�rl�se az adatb�zisb�l
	 * @param option - szempont, ami alapj�n t�r�lni akarunk filmeket
	 * @param thing - ha az adott attrib�tuma a filmnek egyezik ezzel a sztringgel, vagy integerrel, akkor t�r�lj�k
	 */
	public void delete(String option, String thing){
			
			if(option.equals("c�m")){
				for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
				    Movie a = iter.next();
				    if (a.getTitle().equals(thing))
				        iter.remove();
				}
			}
			
			if(option.equals("hossz")){
				for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
				    Movie a = iter.next();
				    if (Integer.valueOf(a.getLength()).equals(Integer.valueOf(thing)))
				        iter.remove();
				}
			}
			
			if(option.equals("premier")){
				for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
				    Movie a = iter.next();
				    if (a.getPremier().equals(thing))
				        iter.remove();
				}
			}
			
			if(option.equals("rendez�")){
				for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
				    Movie a = iter.next();
				    if (a.getDirector().equals(thing))
				        iter.remove();
				}
			}
			this.fireTableDataChanged();
	}
	
	/**
	 * adott tulajdons�ggal rendelkez� filmek keres�se
	 * @param option - szempont, ami alapj�n keres�nk
	 * @param thing - ha az adott attrib�tuma a filmnek egyezik ezzel a sztringgel, vagy integerrel, akkor a found attrib�tum�t truera �ll�tjuk
	 */
	public void searchIf(String option, String thing){
		
		if(option.equals("c�m")){
			for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext();) {
			    Movie a = iter.next();
			    if (a.getTitle().equals(thing))
			    	a.setFound(true);
			    else
			    	a.setFound(false);
			}
		}
		
		else if(option.equals("hossz")){
			for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext();) {
			    Movie a = iter.next();
			    if (Integer.valueOf(a.getLength()).equals(Integer.valueOf(thing)))
			    	a.setFound(true);
			    else
			    	a.setFound(false);
			}
		}
		
		else if(option.equals("premier")){
			for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext();) {
			    Movie a = iter.next();
			    if (a.getPremier().equals(thing))
			    	a.setFound(true);
			    else
			    	a.setFound(false);
			}
		}
		
		else if(option.equals("rendez�")){
			for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext();) {
			    Movie a = iter.next();
			    if (a.getDirector().equals(thing))
			    	a.setFound(true);
			    else
			    	a.setFound(false);
			}
		}
		this.fireTableDataChanged();
	}
}
