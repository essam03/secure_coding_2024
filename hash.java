package final_sc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class hash {
	
	
		static String GetHash(String value) {
		String result="";
		try{
		MessageDigest md=MessageDigest.getInstance("SHA-256");	
		result=encode(md.digest(value.getBytes(StandardCharsets.UTF_8)));
		
		}catch(NoSuchAlgorithmException e)
		{
			
			System.out.println("the lgorthim is not exist");
			
			}
		return result;
		}

		private static String encode(byte[] data) {
			// TODO Auto-generated method stub
			return Base64.getEncoder().encodeToString(data);
		}
		
		
		
	
	
	
	
	
	public static void main(String[] args) {
		String word ="Awamleh";
		String hashedValue= GetHash(word);
		System.out.println(hashedValue);

	}


}
