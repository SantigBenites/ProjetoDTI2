package intol.DTI;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Date;

public class Wallet implements Serializable{
    
    // Coins, NFTs and Requests Storage

    private Long IDCoinCounter = (long) 0;

    private Long IdNFTCounter = (long) 0;

    private Long IdReqCounter = (long) 0;

    //id client
    private Map<Long,LinkedList<Coin>> coins = new HashMap<>();

    //
    private Map<Long,LinkedList<NFT>> nfts = new HashMap<>();

    //
    private LinkedList<Request> requests = new LinkedList<Request>();




    // MINT(value)
    public long addCoin(Long idOwner, Float value){
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
    public long spend(Long Owner, List<Long> coinsToSpend, Long receiver, float value){
        LinkedList<Coin> coinsOwner = coins.get(Owner);
        float sum = 0;
        LinkedList<Coin> list = getCoinsById(coinsToSpend);

        if(list.size() == 0){return 0;}

        for(Coin c : list){
            sum += c.getValue();
            if(c.getOwner() != Owner){
                return 0;
            }
        }
        
        if(sum<value){return 0;}

        //remove coins used from Owner
        for (Coin coin : list){
            coinsOwner.remove(coin);
        }
        coins.put(Owner, coinsOwner);
        addCoin(receiver, value);
        long id = addCoin(Owner, sum-value);

        /*for (LinkedList<Coin> list2 : coins.values()){
            for(Coin c : list2){
                System.out.println( " Id "+ c.getId()+ " Owner "+ c.getOwner() + " value " + c.getValue());
            }
        }*/



        return id;
    }


    //my_coins
    public LinkedList<Coin> getCoins(Long idOwner){
        return coins.get(idOwner);

    }
    //my_nft
    public LinkedList<NFT> getNFT(Long id){
        return nfts.get(id);
    }
    //MINT_NFT (name and url)
    public long addNFT(Long idOwner, String name, String url){
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
    public long addRequest(Long idOwner, Long nftId, List<Long> coins, Float value, Date validity){ 
        NFT nft = getNft(nftId);
        if(nft == null){return 0;}

        LinkedList <Coin> list = getCoinsById(coins);
        if (list.size() == 0){return 0;}

        for(Coin c : list){
            if(c.getOwner() != idOwner){return 0;}
        }

        for (Request r:requests){
            if (r.getCoinsOwner() == idOwner && r.getNFT().equals(nft) && isValid(r.getValidity() )){return 0;}
        }

        IdReqCounter ++;
        requests.add(new Request(nft, list, value, idOwner, validity ));
        return IdReqCounter;
    }

    private boolean isValid(Date validity){
        return new Date().before(validity);
    }

    //CANCEL_REQUEST_NFT_TRANSFER
    public void removeRequest(long idClient, long nft){
        for (Request r:requests){
            if(r.getCoinsOwner() == idClient){
                if( r.getNFT().getId() == nft ){
                    requests.remove(r);
                }
            }

        }

    }
    //MY_NFT_REQUEST
    public LinkedList<Request> getRequests(Long idClient, long nft){
        LinkedList<Request> list = new LinkedList<Request>();
        for (Request req: requests){
            System.out.println(req.getValidity());
            if(req.getNFT().getId() == nft && isValid(req.getValidity())){
                list.add(req);
            }
        }
        return list;
    }

    //PROCESS_NFT_TRANSFER(
    public long transfer(Long Owner, Long nftId,Long idBuyer){
        long id = 0;
        float sum = 0;
        for (Request r:requests){ 

            if(r.getCoinsOwner() == idBuyer && r.getNFT().getId() == nftId && isValid(r.getValidity()) && !r.isProcessed()){

                for (Coin c : r.getCoins()){
                    if(idBuyer != c.getOwner()){return 0;}
                    sum += c.getValue();
                }

                if(sum<r.getValue()){return 0;}

                //exchange nft owner
                NFT nft = getNft(nftId);
                if(nft == null){
                    return 0;
                }
                LinkedList<NFT> list = nfts.get(Owner);
                list.remove(nft);
                nfts.put(Owner, list);

                nft.setOwner(idBuyer);
                LinkedList<NFT> listBuyer = nfts.get(idBuyer);
                listBuyer.add(nft);
                nfts.put(idBuyer, listBuyer);



                //removes coins used
                LinkedList<Coin> coinsFromBuyer = coins.get(idBuyer);
                for(Coin c : r.getCoins()){
                    coinsFromBuyer.remove(c);
                }

                coins.put(idBuyer, coinsFromBuyer);

                //add new coins
                id = addCoin(Owner, r.getValue());
                addCoin(idBuyer, sum - r.getValue());

                //change 
                removeRequest(Owner,nftId);
                r.setProcessed(true);
                requests.add(r);
            }
        }
        return id;

    }








}
