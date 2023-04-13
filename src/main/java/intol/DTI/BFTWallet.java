package intol.DTI;

import bftsmart.tom.ServiceProxy;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BFTWallet {
    private final Logger logger = LoggerFactory.getLogger("bftsmart");
    private final ServiceProxy serviceProxy;
    private final long id;
    
    public BFTWallet(int id) {
        serviceProxy = new ServiceProxy(id);
        this.id = id;
    }

    public List<Pair<Long,Float>> MY_COINS(){

        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.MY_COINS);
            request.userSet(id);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeUnordered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return null;
        }

        if (rep.length == 0) {
            return null;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            if(response.coinsGet() == null){
                return null;
            }
            LinkedList<Pair<Long,Float>> myCoins = new LinkedList<Pair<Long,Float>>();
            for (Coin b : response.coinsGet()) {
                myCoins.add(new Pair<Long,Float>(b.id,b.value));
            }
            return myCoins;
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return null;
        }
        
    }
    
    public long MINT(int value){

        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.MINT);
            request.userSet(id);
            request.valueSet(value);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return -1;
        }

        if (rep.length == 0) {
            return -1;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            return response.mintedCoinIDGet();
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return -1;
        }
    }
    
    public long SPEND(List<Long> coins, long receiver, int value){
        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.SPEND);
            request.userSet(id);
            request.usedCoinsSet(coins);
            request.receiverSet(receiver);
            request.valueSet(value);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return -1;
        }

        if (rep.length == 0) {
            return -1;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            return response.returnCoinGet();
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return -1;
        }
    }
    
    public List<Triple<Long,String,String>> MY_NFT(){
        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.MY_NFT);
            request.userSet(id);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeUnordered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return null;
        }

        if (rep.length == 0) {
            return null;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            if(response.nftsGet() == null){
                return null;
            }
            LinkedList<Triple<Long,String,String>> myNFTs = new LinkedList<Triple<Long,String,String>>();
            for (NFT b : response.nftsGet()) {
                myNFTs.add(new Triple<Long,String,String>(b.id,b.name,b.URI));
            }
            return myNFTs;
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return null;
        }
    }
    
    public long MINT_NFT(String name, String URI){
        
        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.MINT_NFT);
            request.userSet(id);
            request.nameSet(name);
            request.uriSet(URI);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return -1;
        }

        if (rep.length == 0) {
            return -1;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            return response.mintedCoinIDGet();
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return -1;
        }
    }
    
    public long REQUEST_NFT_TRANSFER(Long nft, List<Long> coins, int value, Date validity){
        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.REQUEST_NFT_TRANSFER);
            request.userSet(id);
            request.NftIDSet(nft);
            request.usedCoinsSet(coins);
            request.valueSet(value);
            request.validitySet(validity);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return -1;
        }

        if (rep.length == 0) {
            return -1;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            return response.requestIDGet();
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return -1;
        }
    }
    
    public long CANCEL_REQUEST_NFT_TRANSFER(long nft){
        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.CANCEL_REQUEST_NFT_TRANSFER);
            request.userSet(id);
            request.NftIDSet(nft);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return -1;
        }

        if (rep.length == 0) {
            return -1;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            return response.requestIDGet();
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return -1;
        }
    }
    
    public List<Triple<Long,Float,Date>> MY_NFT_REQUEST(long nft){
        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.MY_NFT_REQUEST);
            request.userSet(id);
            request.NftIDSet(nft);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return null;
        }

        if (rep.length == 0) {
            return null;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            if(response.nftRequestsGet() == null){
                return null;
            }
            LinkedList<Triple<Long,Float,Date>> myRequests = new LinkedList<Triple<Long,Float,Date>>();
            for (Request b : response.nftRequestsGet()) {
                myRequests.add(new Triple<Long,Float,Date>(b.issuer, b.value, b.validity));
            }
            return myRequests;
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return null;
        }
    }
    
    public long PROCESS_NFT_TRASNFER(long nft, long buyer, Boolean accept){

        if(!accept){
            return -1;
        }

        byte[] rep;
        try {
            BFTWalletMessage request = new BFTWalletMessage();
            request.typeSet(MessageType.PROCESS_NFT_TRASNFER);
            request.userSet(id);
            request.NftIDSet(nft);
            request.buyerSet(buyer);

            //invokes BFT-SMaRt
            rep = serviceProxy.invokeOrdered(BFTWalletMessage.toBytes(request));
        } catch (IOException e) {
            logger.error("Failed to send GET request");
            return -1;
        }

        if (rep.length == 0) {
            return -1;
        }
        try {
            BFTWalletMessage response = BFTWalletMessage.fromBytes(rep);
            return response.returnCoinGet();
        } catch (ClassNotFoundException | IOException ex) {
            logger.error("Failed to deserialized response of GET request");
            return -1;
        }
    }
    
    public class Pair<L,R> {

        private final L left;
        private final R right;
      
        public Pair(L left, R right) {
          assert left != null;
          assert right != null;
      
          this.left = left;
          this.right = right;
        }
      
        public L getLeft() { return left; }
        public R getRight() { return right; }
      
        @Override
        public int hashCode() { return left.hashCode() ^ right.hashCode(); }
      
        @Override
        public boolean equals(Object o) {
          if (!(o instanceof Pair)) return false;
          Pair pairo = (Pair) o;
          return this.left.equals(pairo.getLeft()) &&
                 this.right.equals(pairo.getRight());
        }

        @Override
        public String toString(){
            return this.left + " " + this.right;
        }
      
    }

    public class Triple<T, U, V> {

        private final T first;
        private final U second;
        private final V third;
    
        public Triple(T first, U second, V third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    
        public T getFirst() { return first; }
        public U getSecond() { return second; }
        public V getThird() { return third; }

        @Override
        public String toString(){
            return this.first + " " + this.second + " " + this.third;
        }
    }

}
