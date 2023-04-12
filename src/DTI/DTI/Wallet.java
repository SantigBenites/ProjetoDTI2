package DTI;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Wallet {
    
    // Coins, NFTs and Requests Storage

    private Long IDCoinCounter = (long) 0;

    private Long IdNFTCounter = (long) 0;

    private Long IdReqCounter = (long) 0;

    //id client
    private Map<Integer,LinkedList<Coin>> coins;

    //
    private Map<Integer,LinkedList<NFT>> nfts;

    //
    private LinkedList<Request> requests;




    // MINT(value)
    public long addCoin(Integer idOwner, Float value){
        IDCoinCounter ++;
        LinkedList<Coin> list ;
        Coin c = new Coin(IDCoinCounter, idOwner);
        c.Mint(value);
        if(!coins.containsKey(idOwner)){list = new LinkedList<Coin>();}
        else{list = coins.get(idOwner);}
        list.add(c);
        coins.put(idOwner, list);
        return IDCoinCounter;
    }

    //SPEND(coins, receiver, value)
    public int spend(Integer Owner, List<Long> coinsToSpend, Integer receiver, float value){
        LinkedList<Coin> coinsOwner = coins.get(Owner);
        List<Long> ids = new LinkedList<Long>();
        List<Coin> coinsUsed = new LinkedList<Coin>();
        float sum = 0;
        if(coinsOwner.size() == 0 ){return 0;}
        for (Coin c : coinsOwner){
            ids.add(c.getId());
        }
        for (Long id: coinsToSpend){
            if(!ids.contains(id)){return 0;}
            for (Coin c: coinsOwner){
                if(c.getId() == id){
                    sum += c.getValue();
                    coinsUsed.add(c);
                }
            }
        }
        if(sum<value){return 0;}

        //remove coins used from Owner
        for (Coin c : coinsUsed){
            coinsOwner.remove(c);
        }
        coins.put(Owner, coinsOwner);

        addCoin(receiver, value);
        addCoin(Owner, sum-value);

        return 0;
    }

    //my_coins
    public LinkedList<Coin> getCoins(Integer idOwner){
        return coins.get(idOwner);

    }
    //my_nft
    public LinkedList<NFT> getNFT(Integer id){
        return nfts.get(id);
    }
    //MINT_NFT (name and url)
    public long addNFT(Integer idOwner, String name, String url){
        IdNFTCounter ++;
        LinkedList<NFT> list ;
        NFT nft  = new NFT(IdNFTCounter, idOwner);
        nft.Mint(name, url);
        if(!nfts.containsKey(idOwner)){list = new LinkedList<NFT>();}
        else{list = nfts.get(idOwner);}
        list.add(nft);
        nfts.put(idOwner, list);
        return IdNFTCounter;
    }

    // REQUEST_NFT_TRANSFER
    // Each user can create at most one purchase offer per NFT.


    NFT nftToBuy;
    LinkedList<Coin> coinsUsed;
    float value;
    Boolean validity;
    Boolean processed;

    private NFT getNft(Long nftId){
        for (LinkedList<NFT> nftlist : nfts.values()){
            for(NFT n : nftlist){
                if(n.getId() == nftId){
                    return n;
                }

            }
        }
        return null;
    }



    private LinkedList<Coin> getCoinsById(List<Long> coinsID){
        LinkedList<Coin> list = new LinkedList<Coin>();
        for (LinkedList<Coin> coinlists : coins.values()){
            for(Coin c : coinlists){
                for (Long coinId: coinsID){
                    if(c.getId() == coinId){
                        list.add(c);
                    }
                }
            }
        }
        return list;
    }
    public long addRequest(Integer idOwner, Long nftId, List<Long> coins, Float value, Date validity){ 
        NFT nft = getNft(nftId);
        if(nft == null){return 0;}

        LinkedList <Coin> list = getCoinsById(coins);
        if (list.size() == 0){return 0;}

        for(Coin c : list){
            if(c.getOwner() != idOwner){return 0;}
        }

        for (Request r:requests){
            if (r.getCoinsOwner() == idOwner && r.getNFT().equals(nft)){return 0;}
        }
        if (!isValid(validity)){ return 0;}

        IdReqCounter ++;
        requests.add(new Request(nft, list, value, idOwner, validity));
        return IdReqCounter;
    }

    private boolean isValid(Date validity){
        return validity.before(new Date());
    }

    //CANCEL_REQUEST_NFT_TRANSFER
    public void removeRequest(int idClient, long nft){
        for (Request r:requests){
            if(r.getCoinsOwner() == idClient){
                if( r.getNFT().getId() == nft ){
                    requests.remove(r);
                }
            }

        }

    }
    //MY_NFT_REQUEST
    public List<Request> getRequests(long nft, int idClient){
        List<Request> list = new LinkedList<Request>();
        for (Request req: requests){
            if(req.getNFT().getId() == nft){
                list.add(req);
            }
        }
        return list;
    }

    //PROCESS_NFT_TRANSFER(
    public float transfer(Long nft,int idBuyer, Boolean accept){
        if(!accept){return 0 ;}
        float sum = 0;
        for (Request r:requests){
            //Owner of coins == idBuyer
            if(r.getCoinsOwner() == idBuyer){
                // NFT ids req valid and as not been processed
                if( r.getNFT().getId() == nft && isValid(r.getValidity()) && !r.isProcessed()){
                    for (Coin c : r.getCoins()){
                        //if Buyer does not Own Coins
                        //get Coins
                        if(idBuyer != c.getOwner()){return 0;}
                        sum += c.getValue();
                    }

                    if(sum<r.getValue()){return 0;}
                    //(creating two coins, just like in SPEND) and change the ownership of the NFT
                    Coin c = new Coin( 0, r.getNftOwner());
                    c.Mint(r.getValue());

                    //remove coins and add coins



                    //Troco
                    Coin c1 = new Coin( 0, r.getNftOwner());
                    c1.Mint(sum - r.getValue());

                    r.setProcessed(true);

                    NFT nftNewOwner = r.getNFT();
                    nfts.get(r.getNftOwner()).remove(nftNewOwner);

                    nftNewOwner.setOwner(idBuyer);
                    nfts.get(idBuyer).add(nftNewOwner);




                }
            }


        }
        return 0;
        
    }








}
