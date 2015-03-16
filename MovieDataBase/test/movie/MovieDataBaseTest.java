package movie;

import java.util.ArrayList;
import java.util.List;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A MovieDataBase oszt�ly met�dusainak tesztel�s�hez l�trehozott oszt�ly.
 */
public class MovieDataBaseTest {
	
	MovieDataBase moviedatabase;
	
	/**
	 * kezd�skor l�trehozunk egy MovieDataBase-t �s beleratkunk p�r filmet a tesztel�shez
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
	 * val�ban annyi film van-e az adatb�zisban, mint amennyit beleraktunk
	 */
	@Test
	public void testRowCount(){
		Assert.assertEquals(3, moviedatabase.getRowCount());
	}
	
	/**
	 * helyes-e a getValueAt() met�dus visszat�r�si �rt�ke
	 */
	@Test
	public void testValueAt(){
		Assert.assertEquals("Deja Vu", moviedatabase.getValueAt(1,0));
	}
}
