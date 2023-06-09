package intol.DTI;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Request implements Serializable {
    
    
    NFT nftToBuy;
    LinkedList<Coin> coinsUsed;
    float value;
    Long issuer;
    Date validity;

    public Request( NFT nftToBuy, LinkedList<Coin> coinsUsed, float value, long issuer, Date validity){
        this.nftToBuy = nftToBuy;
        this.coinsUsed = coinsUsed;
        this.value = value;
        this.validity = validity;
        this.issuer = issuer;
    }

    public Long getIssuer() {
        return this.issuer;
    }

    public long getNftOwner(){
        return this.nftToBuy.getOwner();
    }

    public NFT getNFT(){
        return this.nftToBuy;
    }

    public long getCoinsOwner(){
        return this.coinsUsed.getLast().getOwner();
    }


    public List<Coin> getCoins(){
        return this.coinsUsed;
    }

    public float getValue() {
        return this.value;
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
        && req.validity == this.validity 
        && req.coinsUsed.equals(this.coinsUsed);

    }



    @Override
    public String toString(){
        return  this.nftToBuy + " " + this.coinsUsed + " " + this.value + " " + this.validity.getTime() + " " + this.issuer;
    }





}
