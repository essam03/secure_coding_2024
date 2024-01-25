package final_sc;

import static org.junit.Assert.*;

import org.junit.Test;

public class test_doctor {

	@Test
	public void doctorLoginTest1() {
		
		Doctor doc= new Doctor();
		doc.setInputForTesting("essam03", "E$sam2003");
		doc.login();
		System.out.println(doc.getPassword());
		assertEquals("essam03",doc.getUsername());
		assertEquals("E$sam2003",doc.getPassword());
	}
	
/*public void doctorLoginTest2() {
		
		Doctor doc= new Doctor();
		doc.setInputForTesting("keeper1", "E$sam2003");
		doc.login();
		assertEquals("keeper1",doc.getUsername());
		assertEquals("k33pR@cord$",doc.getPassword());
	}*/

	

}
