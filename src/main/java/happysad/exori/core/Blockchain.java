package happysad.exori.core;

import java.util.ArrayList;
import java.util.List;

public class Blockchain
{
	private List<Block> blockchain;
	public static int difficulty = 5;
	
	public Blockchain()
	{
		blockchain = new ArrayList<Block>();
	}
	
	public boolean isBlockchainValid()
	{
		Block previousBlock, currentBlock;
		
		for(int i = 1; i < blockchain.size(); i++)
    	{
    		previousBlock = blockchain.get(i - 1);
    		currentBlock = blockchain.get(i);
    		
    		if(!currentBlock.hash.equals(currentBlock.calculateHash()))
    			return false;
    		
    		if(!previousBlock.hash.equals(currentBlock.previousHash))
    			return false;
    	}
    	
    	return true;
	}
	
	public void addBlock(Block block)
	{
		blockchain.add(block);
	}
	
	public List<Block> getBlockchain()
	{
		return blockchain;
	}
}
