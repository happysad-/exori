package happysad.exori.core.utils;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;

public class ECDSATest
{
	@Before
	public void setup()
	{
		Security.addProvider(new BouncyCastleProvider());
	}
	
	@Test
	public void testKeypairGenerationSigningAndRecovery()
	{
//		ECKey keypair = new ECKey();
	}
}
