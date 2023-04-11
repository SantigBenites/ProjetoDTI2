package DTI;

public class Coin {
        
    long id;
    int Owner;
    float value;


    public Coin(long id, int Owner) {
        this.id = id;
        this.Owner = Owner;

    }

    public long Mint(float value){
        this.value = value;
        return this.id;
    }

}
