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

    public long getId(){
        return this.id;
    }
    
    @Override
    public boolean equals(Object o){

        if (o == this) {
            return true;
        }
        if (!(o instanceof Coin)) {
            return false;
        }

        Coin c = (Coin) o;

        return c.id == this.id && c.Owner == this.Owner && c.value == this.value;

    }

    public int getOwner() {
        return this.Owner;
    }

}
