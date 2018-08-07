package happysad.exori.core;

import com.google.gson.GsonBuilder;

import happysad.exori.core.utils.StringUtility;
import happysad.exori.core.wallet.Wallet;

public class App
{
	public static void main(String[] args)
	{
//		System.out.println("TEST: " + StringUtility.generateSHA256Hash("TEST"));
		Blockchain exoriBlockchain = new Blockchain();
		
		System.out.println("Adding first block.");
		
		exoriBlockchain.getBlockchain().add(new Block("First block of the chain.", "0"));
		
		exoriBlockchain.getBlockchain().get(0).mineBlock(Blockchain.difficulty);
		
		exoriBlockchain.addBlock(new Block("Block 1", exoriBlockchain.getBlockchain().get(0).hash));
		
		exoriBlockchain.getBlockchain().get(1).mineBlock(Blockchain.difficulty);
		
		exoriBlockchain.addBlock(new Block("Block 2", exoriBlockchain.getBlockchain().get(1).hash));
		
		exoriBlockchain.getBlockchain().get(2).mineBlock(Blockchain.difficulty);
		
		exoriBlockchain.addBlock(new Block("Block 3", exoriBlockchain.getBlockchain().get(2).hash));
		
		if(exoriBlockchain.isBlockchainValid())
			System.out.println("Blockchain valid.");
		else
			System.out.println("Error! Blockchain invalid.");
		
		exoriBlockchain.getBlockchain().forEach(block -> System.out.println("[" + block.getTimestamp() + ":" + block.getData() + "]" + block.hash));
	
		String gsonFormattedBlockchain = new GsonBuilder().setPrettyPrinting().create().toJson(exoriBlockchain.getBlockchain());
	
		System.out.println(gsonFormattedBlockchain);
		
		Wallet wallet = new Wallet();
		
		wallet.generateKeyPair();
		
//		System.out.println("Private Key: " + wallet.privateKey.toString());
		
		System.out.println("Public Key: " + StringUtility.generateStringFromPublicKey(wallet.getPublicKey()));
		
		/* Hibernate Testing */
		
//		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//		
//		try
//		{
//			final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//			
//			sessionFactory.getMetamodel().getEntities().forEach(e -> System.out.println(e.toString()));
//		}
//		catch(Exception e)
//		{
//			StandardServiceRegistryBuilder.destroy(registry);
//		}
	}
	
	public App()
	{
		
	}
}
