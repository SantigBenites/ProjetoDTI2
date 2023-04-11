package DTI;

public class NFT {
    
    long id;
    int Owner;
    String name;
    String URI;

    public NFT(long id, int Owner){
        this.id = id;
        this.Owner = Owner;
    }

    public long Mint(String name, String URI){
        this.name = name;
        this.URI = URI;
        return this.id;
    }

}
