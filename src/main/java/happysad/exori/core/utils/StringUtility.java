package happysad.exori.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.util.encoders.Base64Encoder;

public class StringUtility
{
	public static String generateSHA256Hash(String data)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(data.getBytes("UTF-8"));
			
			StringBuffer buffer = new StringBuffer();
			
			for(int i = 0; i < hash.length; i++)
			{
				String hexValue = Integer.toHexString(0xff & hash[i]);
				
				if(hexValue.length() == 1)
					buffer.append('0');
				
				buffer.append(hexValue);
			}
			
			return buffer.toString();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static String generateStringFromPublicKey(PublicKey key)
	{
		try
		{
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			X509EncodedKeySpec spec = keyFactory.getKeySpec(key, X509EncodedKeySpec.class);
			
			Base64Encoder encoder = new Base64Encoder();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			encoder.encode(spec.getEncoded(), 0, spec.getEncoded().length, stream);
				
			return stream.toString();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(NoSuchAlgorithmException nsae)
		{
			nsae.printStackTrace();
		}
		catch(InvalidKeySpecException ikse)
		{
			ikse.printStackTrace();
		}
		
		return null;
	}
}
