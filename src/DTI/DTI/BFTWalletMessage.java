package DTI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

public class BFTWalletMessage implements Serializable{
    private MessageType type;
    private float value;
    private List<Coin> coins;
    private long receiver;
    private String name;
    private String uri;
    private Boolean validity;
    private NFT nft;
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

    public void valueSet(float value){
        this.value = value;
    }

    public float valueGet(){
        return this.value;
    }

    public void coinsSet(List<Coin> value){
        this.coins = value;
    }

    public List coinsGet(){
        return this.coins;
    }

    public void receiverSet(long value){
        this.receiver = value;
    }

    public long receiverGet(){
        return this.receiver;
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

    public void validitySet(Boolean value){
        this.validity = value;
    }

    public Boolean validityGet(){
        return this.validity;
    }

    public void nftSet(NFT value){
        this.nft = value;
    }

    public NFT nftGet(){
        return this.nft;
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
