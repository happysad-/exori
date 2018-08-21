package happysad.exori.core.utils;

import static org.junit.Assert.*;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;

public class TestStringUtility extends StringUtility
{
	public static final String EXPECTED_TEST_HASH = "94ee059335e587e501cc4bf90613e0814f00a7b08bc7c648fd865a2af6a22cc2";
	
	@Before
	public void setUp() throws Exception
	{
		Security.addProvider(new BouncyCastleProvider());
	}

	@Test
	public final void testGenerateSHA256Hash()
	{
		assertEquals(EXPECTED_TEST_HASH, StringUtility.generateSHA256Hash("TEST"));
	}

	@Test
	public final void testApplyECDSASignature()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testVerifyECDSASignature()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetStringFromKey()
	{
		fail("Not yet implemented"); // TODO
	}

}
