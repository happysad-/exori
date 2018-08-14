package happysad.exori.core.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import org.junit.Before;
import org.junit.Test;

public class TestStringUtility
{
	public static final String EXPECTED_TEST_HASH = "94ee059335e587e501cc4bf90613e0814f00a7b08bc7c648fd865a2af6a22cc2";
	public static final String EXPECTED_TEST_STRING = "MEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAEb8ijKNLeycrDwT81KLzo14JbVvfiJYnIKpB/SLbd/C/qk/lcA89ZYlb9oH50kCxw";
	public static final String PUBLIC_KEY_X = "6fc8a328d2dec9cac3c13f3528bce8d7825b56f7e22589c8";
	public static final String PUBLIC_KEY_Y = "2a907f48b6ddfc2fea93f95c03cf596256fda07e74902c70";
	
	@Before
	public final void setup()
	{
		
	}

	@Test
	public final void testGenerateSHA256Hash()
	{		
		assertEquals(EXPECTED_TEST_HASH, StringUtility.generateSHA256Hash("TEST"));
	}

	@Test
	public final void testGenerateStringFromPublicKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException
	{
		final KeyFactory factory = KeyFactory.getInstance("ECDSA", "BC");
		final RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(PUBLIC_KEY_X), new BigInteger(PUBLIC_KEY_Y));
		final PublicKey publicKey = factory.generatePublic(publicKeySpec);
		
		assertEquals(EXPECTED_TEST_STRING, publicKey);
	}

}
