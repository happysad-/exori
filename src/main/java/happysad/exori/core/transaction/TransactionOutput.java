package happysad.exori.core.transaction;

import java.security.PublicKey;

import happysad.exori.core.utils.StringUtility;

public class TransactionOutput
{
	public String id;
	public PublicKey rx;
	public float value;
	public String parentTXID;
	
	public TransactionOutput(PublicKey rx, float value, String parentTXID)
	{
		this.rx = rx;
		this.value = value;
		this.parentTXID = parentTXID;
		this.id = StringUtility.generateSHA256Hash(StringUtility.getStringFromKey(rx) + Float.toString(value) + parentTXID);
	}
	
	public boolean belongsTo(PublicKey publicKey)
	{
		return (publicKey == rx);
	}
}
