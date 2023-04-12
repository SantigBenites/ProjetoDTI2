package DTI;

import java.util.Date;
import java.util.LinkedList;

public class Request {
    
    NFT nftToBuy;
    LinkedList<Coin> coinsUsed;
    float value;
    long issuer;
    Date validity;
    Boolean processed;

    public Request( NFT nftToBuy, LinkedList<Coin> coinsUsed, float value, long issuer, Date validity){
        this.nftToBuy = nftToBuy;
        this.coinsUsed = coinsUsed;
        this.value = value;
        this.validity = new Date(validity.getTime() + (10 * 60000));
        this.issuer = issuer;
        this.processed = false;
    }

    public NFT getNFT(){
        return this.nftToBuy;
    }

    public int getOwner(){
        return this.coinsUsed.getLast().getOwner();
    }

    public boolean isValid() {
        return this.validity;
    }

    @Override
    public boolean equals(Object o){

        if (o == this) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }

        Request req = (Request) o;


        return req.nftToBuy.equals(this.nftToBuy) && req.value == this.value 
        && req.validity == this.validity && this.processed == req.processed
        && req.coinsUsed.equals(this.coinsUsed);

    }



}
