package intol.DTI;

import java.util.LinkedList;

public class test {
    public static void main(String[] args) {
        Wallet w = new Wallet();
        long i = w.addCoin((long)56, 50.0f);
        System.out.println(i);
        i = w.addCoin((long)56, 50.0f);
        System.out.println(i);

        LinkedList<Coin> ll = w.getCoins((long)56);
        for (Coin c : ll){
            System.out.println(c.getId());
        }


    }
    
}
