package intol.DTI;

import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        Wallet w = new Wallet();
        long i = w.addCoin((long)56, 50.0f);
        
        //System.out.println(i);
        i = w.addCoin((long)56, 50.0f);
        //System.out.println(i);

        LinkedList<Coin> ll = w.getCoins((long)56);
        for (Coin c : ll){
            System.out.println(c.getId());
            System.out.println(c.getOwner());
        }

        i = w.addCoin((long)68, 50.0f);
        List<Long> l = new LinkedList<Long>();
        l.add(i);

        long s = w.spend((long)68, l, (long)56, 60.0f);
        System.out.println(s);


    }
    
}
