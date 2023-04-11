package DTI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Wallet {
    
    // Coins, NFTs and Requests Storage

    //id client
    private Map<Long,LinkedList<Coin>> coins;

    //
    private Map<Long,LinkedList<NFT>> nfts;

    //
    private LinkedList<Request> requests;

    public long addCoin(Long idOwner, Coin coin){
        if (!coins.keySet().contains(idOwner)){return -1;}
        LinkedList<Coin> list = coins.get(idOwner);
        if(list.contains(coin)){return -1;}
        list.add(coin);
        coins.put(idOwner, list);
        return coin.getId();
    }

    public LinkedList<Coin> getCoins(Long idOwner){
        return coins.get(idOwner);

    }

    public LinkedList<NFT> getNFT(Long id){
        return nfts.get(id);
    }

    public long addNFT(Long idOwner, NFT nft){
        if (!nfts.keySet().contains(idOwner)){return -1;}
        LinkedList<Coin> list = nfts.get(idOwner);
        if(list.contains(nft)){return -1;}
        list.add(nft);
        nfts.put(idOwner, list);
        return nft.getId();
    }

    // Each user can create at most one purchase offer per NFT.
    public long addRequest(Request req){
        //NFT nft = req.getNFT();
        if (!requests.contains(req)){return -1;}
        return 0;

    }

    public removeRequest(NFT nft){}

    public getRequest(NFT nft){}

    public transfer(Long idrequest, Long idBuyer){}








}
