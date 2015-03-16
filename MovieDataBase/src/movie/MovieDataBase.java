package movie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Ez az osztály alkotja az adatbázist. Itt vannak eltárolva a filmek adatai és a hozzájuk
 * kapcsolódó metódusok is ebben az osztályban vannak definiálva. Az osztály megvalósítja
 * az AbstractTableModel interfészt, hogy a táblázat át tudja venni az adatokat.
 */
public class MovieDataBase extends AbstractTableModel{

	//ArrayList a filmek tárolásához
	List<Movie> dataBase = new ArrayList<Movie>();

	/*az AbstractTableModel megvalósítandó függvényei*/

	/**
	 * a táblázathoz szükséges sorok száma
	 * @return - sorok száma
	 */
	@Override
	public int getRowCount() {
		return dataBase.size();
	}

	/**
	 * a táblázathoz szükséges oszlopok száma
	 * @return - az oszlopok száma, itt: 6, mert 6 oszlopunk lesz
	 */
	@Override
	public int getColumnCount() {
		return 6;
	}

	/**
	 * a táblázat celláinak értékei
	 * @param rowIndex - sorindex
	 * @param columnIndex - oszlopindex
	 * @return - az adott cella megfelelõ tartalma
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
	 * a fejlécek beállítása
	 * @param index - oszlopindex
	 * @return - a megfelelõ oszlop fejlécének neve
	 */
	public String getColumnName(int index){
		if(index == 0)
			return "Cím";
		else if(index == 1)
			return "Rendezõ";
		else if(index == 2)
			return "Premier";
		else if(index == 3)
			return "Hossz";
		else if(index == 4)
			return "Találat";
		else
			return "Kijelöl";
	}
	
	/**
	 * az adatok megjelenítése a megfelelõ formátumban
	 * @param index - oszlopindex
	 * @return - a megfelelõ típus
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
	 * az utolsó (kiválasztás) cellát szerkeszthetõvé tesszük
	 * @param row - sorindex (nincs rás szükség)
	 * @param column - oszlopindex
	 * @return - szerkesztheõ-e vagy nem
	 */
	public boolean isCellEditable(int row, int column){
		if(column == 5)
			return true;
		else
			return false;
	}
	
	/**
	 * az átállított elemet elmentjük a listában
	 * @param value - az érték amire átállítjuk
	 * @param row - sorindex
	 * @param column - oszlopindex
	 */
	public void setValueAt(Object value, int row, int column){
		if(column == 5)
			dataBase.get(row).setMarked((Boolean)value);
		this.fireTableDataChanged();
	}
	
	/**
	 * egy film hozzáadása az adatbázishoz. ha már benne van az adatbázisban, nem adjuk hozzá
	 * @param m - az adatbázishoz hozzáadandó film
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
	 * a kijelölt filmek törlése az adatbázisból
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
	 * adott tulajdonsággal rendelkezõ filmek törlése az adatbázisból
	 * @param option - szempont, ami alapján törölni akarunk filmeket
	 * @param thing - ha az adott attribútuma a filmnek egyezik ezzel a sztringgel, vagy integerrel, akkor töröljük
	 */
	public void delete(String option, String thing){
			
			if(option.equals("cím")){
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
			
			if(option.equals("rendezõ")){
				for (Iterator<Movie> iter = dataBase.listIterator(); iter.hasNext(); ) {
				    Movie a = iter.next();
				    if (a.getDirector().equals(thing))
				        iter.remove();
				}
			}
			this.fireTableDataChanged();
	}
	
	/**
	 * adott tulajdonsággal rendelkezõ filmek keresése
	 * @param option - szempont, ami alapján keresünk
	 * @param thing - ha az adott attribútuma a filmnek egyezik ezzel a sztringgel, vagy integerrel, akkor a found attribútumát truera állítjuk
	 */
	public void searchIf(String option, String thing){
		
		if(option.equals("cím")){
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
		
		else if(option.equals("rendezõ")){
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
