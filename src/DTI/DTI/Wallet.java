package DTI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Wallet {
    
    // Coins, NFTs and Requests Storage
    private Map<Long,LinkedList<Coin>> coins;
    private Map<Long,LinkedList<NFT>> nfts;
    private LinkedList<Request> requests;
}
