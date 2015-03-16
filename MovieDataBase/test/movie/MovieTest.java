package movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A Movie osztály metódusainak teszteléséhez létrehozott osztály.
 *
 */
public class MovieTest {
	
	private Movie movie;
	
	/**
	 * létrehozunk egy filmet, amin a metódusokat teszteljük
	 */
	@Before
	public void setUp(){
		movie = new Movie("Back to the Future", "Robert Zemecis", "1985.07.03.", 116);
	}
	
	/**
	 * egy új film marked attribútuma valóban false
	 */
	@Test
	public void testIsMarked(){
		Assert.assertEquals(false, movie.isMarked());
	}
	
	/**
	 * egy új film found attribútuba valóban false
	 */
	@Test
	public void testIsFound(){
		Assert.assertEquals(false, movie.isFound());
	}
	
	/**
	 * helyes-e a getTitle() visszatérési értéke
	 */
	@Test
	public void testgetTitle(){
		Assert.assertEquals("Back to the Future", movie.getTitle());
	}
}
