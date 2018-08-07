package happysad.exori.core.wallet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.hibernate.annotations.GenericGenerator;

import happysad.exori.core.utils.StringUtility;

@Entity
@Table(name = "WALLETS")
public class Wallet
{
	private long id;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public Wallet()
	{
		
	}
	
	public void generateKeyPair()
	{
		try
		{
			Security.addProvider(new BouncyCastleProvider());
//			BouncyCastle bc;
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			keyPairGenerator.initialize(ecSpec, random);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
			
			System.out.println("PM: " + keyPair.getPublic().toString());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public long getID()
	{
		return id;
	}
	
	@Column(name = "PUBLIC_ADDRESS")
	public String getPublicAddress()
	{
		return StringUtility.generateStringFromPublicKey(publicKey);
	}
	
	
	public PrivateKey getPrivateKey()
	{
		return privateKey;
	}
	
	public PublicKey getPublicKey()
	{
		return publicKey;
	}
}
