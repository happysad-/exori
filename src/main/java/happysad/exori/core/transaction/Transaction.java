package happysad.exori.core.transaction;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

import happysad.exori.core.App;
import happysad.exori.core.utils.StringUtility;

public class Transaction
{
	public String transactionID;
	public PublicKey tx; // Sender
	public PublicKey rx; // Receiver
	public float value;
	public byte[] signature;
	
	public List<TransactionInput> inputs;
	public List<TransactionOutput> outputs;
	
	private static int sequence = 0;
	
	public Transaction(PublicKey tx, PublicKey rx, float value, List<TransactionInput> inputs)
	{
		this.tx = tx;
		this.rx = rx;
		this.value = value;
		this.inputs = inputs;
	}
	
	private String calculateHash()
	{
		sequence++;
		
//		return StringUtility.generateSHA256Hash(StringUtility.generateStringFromPublicKey(rx) + StringUtility.generateStringFromPublicKey(rx) + Float.toString(value) + sequence);
		return StringUtility.generateSHA256Hash(StringUtility.getStringFromKey(rx) + StringUtility.getStringFromKey(tx) + Float.toString(value) + sequence);
	}
	
	public void generateSignature(PrivateKey privateKey)
	{
		String data = StringUtility.getStringFromKey(rx) + StringUtility.getStringFromKey(tx) + Float.toString(value);
		signature = StringUtility.applyECDSASignature(privateKey, data);
	}
	
	public boolean verifySignature()
	{
		String data = StringUtility.getStringFromKey(rx) + StringUtility.getStringFromKey(tx) + Float.toString(value);
		return StringUtility.verifyECDSASignature(rx, data, signature);
	}
	
	public boolean processTransaction()
	{
		if(!verifySignature())
			return false;
		
		inputs.forEach(i -> i.utxo = App.UTXOs.get(i.txOutputID));
		
		return false;
	}
}
