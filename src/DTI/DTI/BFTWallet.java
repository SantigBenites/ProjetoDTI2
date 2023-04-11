package DTI;

import bftsmart.tom.ServiceProxy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BFTWallet {
    private final Logger logger = LoggerFactory.getLogger("bftsmart");
    private final ServiceProxy serviceProxy;

    // Coins, NFTs and Requests Storage
    private Map<Long,LinkedList<Coin>> coins;
    private Map<Long,LinkedList<NFT>> nfts;
    private LinkedList<Request> requests;
    
    public BFTWallet(int id) {
        serviceProxy = new ServiceProxy(id);
        coins =  new HashMap<Long,LinkedList<Coin>>(); 
        nfts = new HashMap<Long,LinkedList<NFT>>();
        requests = new LinkedList<Request>();
    }

    public int MY_COINS(){
        
    return 1;
    }
    
    public int MINT(){
        
    return 1;
    }
    
    public int SPEND(){
        
    return 1;
    }
    
    public int MY_NFT(){
        
    return 1;
    }
    
    public int MINT_NFT(){
        
    return 1;
    }
    
    public int REQUEST_NFT_TRANSFER(){
        
    return 1;
    }
    
    public int CANCEL_REQUEST_NFT_TRANSFER(){
        
    return 1;
    }
    
    public int MY_NFT_REQUEST(){
        
    return 1;
    }
    
    public int PROCESS_NFT_TRASNFE(){
        
    return 1;
    }
    

}
