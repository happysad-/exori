package happysad.exori.core.transaction;

public class TransactionInput
{
	public String txOutputID;
	public TransactionOutput utxo;
	
	public TransactionInput(String txOutputID)
	{
		this.txOutputID = txOutputID;
	}
}
