package movie;

import java.util.ArrayList;
import java.util.List;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A MovieDataBase osztály metódusainak teszteléséhez létrehozott osztály.
 */
public class MovieDataBaseTest {
	
	MovieDataBase moviedatabase;
	
	/**
	 * kezdéskor létrehozunk egy MovieDataBase-t és beleratkunk pár filmet a teszteléshez
	 */
	@Before
	public void setUp(){
		moviedatabase = new MovieDataBase();
		//moviedatabase.dataBase = new ArrayList<Movie>();
		moviedatabase.dataBase.add(new Movie("Interstellar", "Christopher Nolan", "2010.07.08.", 169));
		moviedatabase.dataBase.add(new Movie("Deja Vu", "Tony Scott", "2006.11.22.", 126));
		moviedatabase.dataBase.add(new Movie("The Conjuring", "James Wan", "2013.06.08.", 112));
	}
	
	/**
	 * valóban annyi film van-e az adatbázisban, mint amennyit beleraktunk
	 */
	@Test
	public void testRowCount(){
		Assert.assertEquals(3, moviedatabase.getRowCount());
	}
	
	/**
	 * helyes-e a getValueAt() metódus visszatérési értéke
	 */
	@Test
	public void testValueAt(){
		Assert.assertEquals("Deja Vu", moviedatabase.getValueAt(1,0));
	}
}
