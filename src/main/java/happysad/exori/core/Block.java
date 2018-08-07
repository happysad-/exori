package happysad.exori.core;

import java.util.Date;

import happysad.exori.core.utils.StringUtility;

public class Block
{
	public String hash;
	public String previousHash;
	private String data;
	private long timestamp;
	private int nonce;
	
	public Block(String data, String previousHash)
	{
		this.data = data;
		this.previousHash = previousHash;
		timestamp = new Date().getTime();
		hash = calculateHash();
	}
	
	public String calculateHash()
	{
		return StringUtility.generateSHA256Hash(previousHash + Long.toString(timestamp) + Integer.toString(nonce) + data);
	}
	
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!hash.substring(0, difficulty).equals(target))
		{
			nonce++;
			hash = calculateHash();
		}
	}
	
	public String getData()
	{
		return data;
	}
	
	public long getTimestamp()
	{
		return timestamp;
	}
}
