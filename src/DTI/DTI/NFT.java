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

    public long getId(){
        return this.id;
    }


    @Override
    public boolean equals(Object o){

        if (o == this) {
            return true;
        }
        if (!(o instanceof NFT)) {
            return false;
        }

        NFT nft = (NFT) o;

        return nft.id == this.id && nft.Owner == this.Owner && nft.name.equals(this.name) && this.URI.equals(nft.URI);

    }

}
