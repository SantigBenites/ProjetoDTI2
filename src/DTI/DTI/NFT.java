package DTI;

public class NFT {
    
    long id;
    Long Owner;
    String name;
    String URI;

    public NFT(long id, long Owner){
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

    public long getOwner(){
        return this.Owner;
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



    public void setOwner(Long idBuyer) {
        this.Owner = idBuyer;
    }




}
