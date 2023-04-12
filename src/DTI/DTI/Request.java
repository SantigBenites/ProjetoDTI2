package DTI;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Request {
    
    NFT nftToBuy;
    LinkedList<Coin> coinsUsed;
    float value;
    Date validity;
    Boolean processed;

    public Request( NFT nftToBuy, LinkedList<Coin> coinsUsed, float value, Date validity){
        this.nftToBuy = nftToBuy;
        this.coinsUsed = coinsUsed;
        this.value = value;
        this.validity = validity;
        this.processed = false;
    }

    public int getNftOwner(){
        return this.nftToBuy.getOwner();
    }

    public NFT getNFT(){
        return this.nftToBuy;
    }

    public int getCoinsOwner(){
        return this.coinsUsed.getLast().getOwner();
    }


    public List<Coin> getCoins(){
        return this.coinsUsed;
    }

    public boolean isProcessed() {
        return this.processed;
    }
    public float getValue() {
        return this.value;
    }
    public void setProcessed(boolean b) {
        this.processed = b;
    }

    public Date getValidity() {
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
