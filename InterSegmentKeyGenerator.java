import java.io.*;
import java.security.*;

/* *************************************************************************** **
 *  Class File: InterSegmentKeyGenerator.java
 *      Author: Mark Stramaglia (MiraCosta College)
 * Description: Contains a static generateKey() method which accepts a
 *              properly formatted input String, and returns the SHA-512
 *              interSegmentKey hash which can be submitted to the
 *              Cal-PASS Plus MMAP API Placement method.
 * *************************************************************************** **
 *   Revisions: 1.0 - 2018-01-02: Initial version.
 * *************************************************************************** */

public class InterSegmentKeyGenerator
{
	public static String generateKey(String message)
	{
		MessageDigest md;
        	byte[] messageBytes = null;
		String out = "";
        
		// Convert the string to Character Set UTF-16LE before hashing
		try
		{
			messageBytes = message.getBytes("UTF-16LE");
		}
		catch (UnsupportedEncodingException e) 
		{
			System.out.println("Unsupported character set" + e);
		}

		// Generate the SHA-512 for the input message
		try
		{
			md= MessageDigest.getInstance("SHA-512");

		    	md.update(messageBytes);

		    	byte[] mb = md.digest();
		    	for (int i = 0; i < mb.length; i++)
			{
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2)
				{
			    		s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out += s;
			}
		}
		catch (NoSuchAlgorithmException e)
		{
		    System.out.println("ERROR: " + e.getMessage());
		}

	    	// Return the SHA-512 hash as a String
		return out;
	}
	
	
    public static void main(String[] args)
    {
		// For Student: John, Smith, M, 19950101
		// Input Should Be: JOHSMIM19950101
		// Expected Output: 38351cb48a87f9ed2b30b815f048b7b75172292c53afb70cf4c77ab19346dcba5bc2caf9188007434b9ac5af27f272bb417839a80fe56513cc397286b4195945
		
		// Testing: Invoke the generateKey method and print results to console
		System.out.println(generateKey("JOHSMIM19950101"));
    }
}
