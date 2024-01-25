package final_sc;

import static org.junit.Assert.*;
import org.junit.Test;
public class Testing {

    RecordKeeper recordKeeper = new RecordKeeper();

	@Test
	public void recordKeeperLoginTest1() {
		
		
		recordKeeper.setInputForTesting("keeper1", "k33pR@cord$");
		recordKeeper.login();
		assertEquals("keeper1",recordKeeper.getUsername());
		assertEquals("k33pR@cord$",recordKeeper.getPassword());
	}
	

	@Test
	public void recordKeeperLoginTest2() {
		
		
		recordKeeper.setInputForTesting("essaam", "k33pR@cord$");
		recordKeeper.login();
		assertEquals("keeper1",recordKeeper.getUsername());
		assertEquals("k33pR@cord$",recordKeeper.getPassword());
	}
	
	@Test
	public void recordKeeperLoginTest3() {
		
		
		recordKeeper.setInputForTesting("keeper1", "essam2003");
		recordKeeper.login();
		assertEquals("keeper1",recordKeeper.getUsername());
		assertEquals("k33pR@cord$",recordKeeper.getPassword());
	}
	
	
	@Test
	public void recordKeeperLoginTest4() {
		
		
		recordKeeper.setInputForTesting("essam", "essam2003");
		recordKeeper.login();
		assertEquals("keeper1",recordKeeper.getUsername());
		assertEquals("k33pR@cord$",recordKeeper.getPassword());
	}
	

}
