package intol.DTI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class BFTWalletMessage implements Serializable{
    private MessageType type;
    // MY_Coins()
    private long user;
    private List<Coin> coins;
    // MINT(value)
    private float value;
    private long mintedCoinID;
    // SPEND(coins, receiver, value)
    private List<Long> usedCoins;
    private long receiver;
    private long returnCoin;
    // MY_NFTS()
    private List<NFT> nfts;
    // MINT_NFT(name, uri)
    private String name;
    private String uri;
    private long mintedNFTID;
    // REQUEST_NFT_TRANSFER(nft, coins, value, validity)
    private long nftID;
    private Date validity;
    private long requestID;
    // MY_NFT_REQUESTS(nft)
    private List<Request> nftRequests;
    // PROCESS_NFT_TRANSFER(nft, buyer, accept)
    private long buyer;
    private Boolean accept;

    public BFTWalletMessage() {
    }

    public static <K,V> byte[] toBytes(BFTWalletMessage message) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(message);

        objOut.flush();
        byteOut.flush();

        return byteOut.toByteArray();
    }

    public static <K,V> BFTWalletMessage fromBytes(byte[] rep) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(rep);
        ObjectInputStream objIn = new ObjectInputStream(byteIn);
        return (BFTWalletMessage) objIn.readObject();
    }


    public void typeSet(MessageType value){
        this.type = value;
    }

    public MessageType typeGet(){
        return this.type;
    }

    public long userGet(){
        return this.user;
    }

    public void userSet(Long value){
        this.user = value;
    }

    public void valueSet(float value){
        this.value = value;
    }

    public float valueGet(){
        return this.value;
    }

    public void mintedCoinIDSet(Long value){
        this.mintedCoinID = value;
    }

    public long mintedCoinIDGet(){
        return this.mintedCoinID;
    }

    public void coinsSet(List<Coin> value){
        this.coins = value;
    }

    public List<Coin> coinsGet(){
        return this.coins;
    }

    public void usedCoinsSet(List<Long> value){
        this.usedCoins = value;
    }

    public List<Long> usedCoinsGet(){
        return this.usedCoins;
    }

    public void receiverSet(long value){
        this.receiver = value;
    }

    public long receiverGet(){
        return this.receiver;
    }

    public void returnCoinSet(long value){
        this.returnCoin = value;
    }

    public long returnCoinGet(){
        return this.returnCoin;
    }

    public void nftsSet(List<NFT> value){
        this.nfts = value;
    }

    public List<NFT> nftsGet(){
        return this.nfts;
    }

    public void nameSet(String value){
        this.name = value;
    }

    public String nameGet(){
        return this.name;
    }

    public void uriSet(String value){
        this.uri = value;
    }

    public String uriGet(){
        return this.uri;
    }

    public void mintedNFTIDSet(long value){
        this.mintedNFTID = value;
    }

    public long mintedNFTIDGet(){
        return this.mintedNFTID;
    }

    public void NftIDSet(long value){
        this.nftID = value;
    }

    public long NftIDGet(){
        return this.nftID;
    }

    public void validitySet(Date value){
        this.validity = value;
    }

    public Date validityGet(){
        return this.validity;
    }

    public void requestIDSet(Long value){
        this.requestID = value;
    }

    public Long requestIDGet(){
        return this.requestID;
    }

    public void nftRequestsSet(List<Request> value){
        this.nftRequests = value;
    }

    public List<Request> nftRequestsGet(){
        return this.nftRequests;
    }

    public void buyerSet(long value){
        this.buyer = value;
    }

    public long buyerGet(){
        return this.buyer;
    }

    public void acceptSet(Boolean value){
        this.accept = value;
    }

    public Boolean acceptGet(){
        return this.accept;
    }

}
