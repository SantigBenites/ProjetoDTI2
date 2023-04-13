package intol.DTI;

import java.io.Serializable;

public class Coin implements Serializable {
        
    long id;
    Long Owner;
    float value;


    public Coin(long id, long Owner) {
        this.id = id;
        this.Owner = Owner;

    }

    public long Mint(float value){
        this.value = value;
        return this.id;
    }

    public float getValue(){
        return this.value;
    }

    public long getId(){
        return this.id;
    }

    public long getOwner() {
        return this.Owner;
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


}
