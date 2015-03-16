package movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A Movie oszt�ly met�dusainak tesztel�s�hez l�trehozott oszt�ly.
 *
 */
public class MovieTest {
	
	private Movie movie;
	
	/**
	 * l�trehozunk egy filmet, amin a met�dusokat tesztelj�k
	 */
	@Before
	public void setUp(){
		movie = new Movie("Back to the Future", "Robert Zemecis", "1985.07.03.", 116);
	}
	
	/**
	 * egy �j film marked attrib�tuma val�ban false
	 */
	@Test
	public void testIsMarked(){
		Assert.assertEquals(false, movie.isMarked());
	}
	
	/**
	 * egy �j film found attrib�tuba val�ban false
	 */
	@Test
	public void testIsFound(){
		Assert.assertEquals(false, movie.isFound());
	}
	
	/**
	 * helyes-e a getTitle() visszat�r�si �rt�ke
	 */
	@Test
	public void testgetTitle(){
		Assert.assertEquals("Back to the Future", movie.getTitle());
	}
}
