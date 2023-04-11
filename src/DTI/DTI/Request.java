package DTI;

import java.util.LinkedList;

public class Request {
    
    NFT nftToBuy;
    LinkedList<Coin> coinsUsed;
    float value;
    Boolean validity;
    Boolean processed;

    public Request( NFT nftToBuy, LinkedList<Coin> coinsUsed, float value, Boolean validity){
        this.nftToBuy = nftToBuy;
        this.coinsUsed = coinsUsed;
        this.value = value;
        this.validity = validity;
        this.processed = false;
    }

    public NFT getNFT(){
        return this.nftToBuy();
    }



}
