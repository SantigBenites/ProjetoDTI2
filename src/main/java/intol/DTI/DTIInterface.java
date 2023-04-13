package intol.DTI;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import intol.DTI.BFTWallet;
import intol.DTI.BFTWallet.Pair;
import intol.DTI.BFTWallet.Triple;

public class DTIInterface {

    public static void main(String[] args) throws IOException {

        int clientId = (args.length > 0) ? Integer.parseInt(args[0]) : 1001;
        BFTWallet BFTWallet = new BFTWallet(clientId);

        Console console = System.console();

        System.out.println("\nCommands:\n");

        System.out.println("\tMY_COINS");
        System.out.println("\tMINT");
        System.out.println("\tSPEND");

        System.out.println("\tMY_NFTS");
        System.out.println("\tMINT_NFT");
        System.out.println("\tREQUEST_NFT_TRANSFER");
        System.out.println("\tCANCEL_REQUEST_NFT_TRANSFER");
        System.out.println("\tMY_NFT_REQUESTS");
        System.out.println("\tPROCESS_NFT_TRANSFER");

        System.out.println("\tEXIT: Terminate this client\n");


        while (true){
            String cmd = console.readLine("\n  > ");
            if (cmd.equalsIgnoreCase("MY_COINS")){

                List<Pair<Long, Float>> myCoins = BFTWallet.MY_COINS();
                
                if (myCoins == null) {
                    System.out.println("No coins available");
                    continue;
                }

                System.out.println("ID Value");
                for (Pair<Long,Float> pair : myCoins) {
                    System.out.println(pair);
                }

            }else if(cmd.equalsIgnoreCase("MINT")){

                int coin_value;
                try {
                    coin_value = Integer.parseInt(console.readLine("Enter a coin value: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe value is supposed to be an integer!\n");
                    continue;
                }

                long newCoinID = BFTWallet.MINT(coin_value);

                if (newCoinID == -1) {
                    System.out.println("Wasn't able to a mint new coin");
                    continue;
                }else{
                    System.out.println("\tNew coin has ID " + newCoinID + "\n");
                }

            }else if(cmd.equalsIgnoreCase("SPEND")){

                System.out.println("\tEnter coins to be used!\n");
                List<Long> coins = new LinkedList<>();
                
                try {
                    String CoinIDs = console.readLine("Enter a coin IDs with a space in between: ");
                    for (String ID : CoinIDs.split(" ")) {
                        coins.add((long) Integer.parseInt(ID));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\tThe coin ID is supposed to be an integer!\n");
                    continue;
                }

                long receiver;
                try {
                    receiver = Integer.parseInt(console.readLine("Enter a receiver ID: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe receiver ID is supposed to be an integer!\n");
                    continue;
                }

                int coin_value;
                try {
                    coin_value = Integer.parseInt(console.readLine("Enter a coin value: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe value is supposed to be an integer!\n");
                    continue;
                }

                long newCoinID = BFTWallet.SPEND(coins,receiver,coin_value);
                if(newCoinID == -1){
                    System.out.println("\tCoin created in return has ID " + newCoinID + "\n");
                }else{
                    System.out.println("\tFailed to buy coin\n");
                }

            }else if(cmd.equalsIgnoreCase("MY_NFTS")){

                List<Triple<Long,String,String>> myNFTs = BFTWallet.MY_NFT();

                if (myNFTs == null) {
                    System.out.println("No NFT's available");
                    continue;
                }

                System.out.println("ID Name URI");
                for (Triple<Long,String,String> triple : myNFTs) {
                    System.out.println(triple);
                }

            }else if(cmd.equalsIgnoreCase("MINT_NFT")){

                String name = console.readLine("Enter nft name: ");
                String uri = console.readLine("Enter nft uri: ");

                long newNFTID = BFTWallet.MINT_NFT(name,uri);

                if(newNFTID == -1){
                    System.out.println("\tWasn't able to create the NFT\n");
                }else{
                    System.out.println("\tNew NFT with ID " + newNFTID + "\n");
                }

            }else if(cmd.equalsIgnoreCase("REQUEST_NFT_TRANSFER")){

                long NFT_ID;
                try {
                    NFT_ID = Integer.parseInt(console.readLine("Enter a NFT ID: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe ID is supposed to be an integer!\n");
                    continue;
                }

                System.out.println("\tEnter coins to be used!\n");
                List<Long> coins = new LinkedList<>();

                try {
                    String CoinIDs = console.readLine("Enter a coin IDs with a space in between: ");
                    for (String ID : CoinIDs.split(" ")) {
                        coins.add((long) Integer.parseInt(ID));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\tThe coin ID is supposed to be an integer!\n");
                    continue;
                }
                

                int request_value;
                try {
                    request_value = Integer.parseInt(console.readLine("Enter the request value: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe value is supposed to be an integer!\n");
                    continue;
                }

                SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date validity_value;
                try {
                    validity_value = dateInput.parse(console.readLine("Enter the date with format yyyy-MM-dd HH:mm: "));
                } catch (ParseException e) {
                    System.out.println("\tWrong date format\n");
                    continue;
                }

                long newNFTID = BFTWallet.REQUEST_NFT_TRANSFER(NFT_ID,coins,request_value,validity_value);

                if(newNFTID == -1){
                    System.out.println("\tWasn't able to create the new request\n");
                }else{
                    System.out.println("\tA new request for NFT of ID " + NFT_ID + " has been made with value " + request_value + " and will expire in  " + validity_value + " \n");
                }


            }else if(cmd.equalsIgnoreCase("CANCEL_REQUEST_NFT_TRANSFER")){

                long request_ID;
                try {
                    request_ID = Integer.parseInt(console.readLine("Enter request ID you want to cancel: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe ID is supposed to be an integer!\n");
                    continue;
                }

                long canceledRequestID = BFTWallet.CANCEL_REQUEST_NFT_TRANSFER(request_ID);

                if(canceledRequestID == -1){
                    System.out.println("\tWasn't able to remove request\n");
                }else{
                    System.out.println("\tRequest was cancelled\n");
                }


            }else if(cmd.equalsIgnoreCase("MY_NFT_REQUESTS")){

                long NFT_ID;
                try {
                    NFT_ID = Integer.parseInt(console.readLine("Enter a NFT ID: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe ID is supposed to be an integer!\n");
                    continue;
                }

                List<Triple<Long, Float, Date>> NFTRequests = BFTWallet.MY_NFT_REQUEST(NFT_ID);

                if (NFTRequests == null) {
                    System.out.println("No NFT requests available");
                    continue;
                }

                System.out.println("Issuer Value Validity");
                for (Triple<Long, Float, Date> triple : NFTRequests) {
                    System.out.println(triple);
                }

            }else if(cmd.equalsIgnoreCase("PROCESS_NFT_TRANSFER")){

                long NFT_ID;
                try {
                    NFT_ID = Integer.parseInt(console.readLine("Enter a NFT ID: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe ID is supposed to be an integer!\n");
                    continue;
                }

                long buyer_ID;
                try {
                    buyer_ID = Integer.parseInt(console.readLine("Enter a buyer ID: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe ID is supposed to be an integer!\n");
                    continue;
                }

                Boolean accept;
                try {
                    accept = Boolean.parseBoolean(console.readLine("Do you accept this request (respond has a Boolean): "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe ID is supposed to be an integer!\n");
                    continue;
                }

                long newCoin = BFTWallet.PROCESS_NFT_TRANSFER(NFT_ID, buyer_ID, accept);

                if(newCoin == 0 ){
                    System.out.println("\tThere was an error in processing the transfer\n");
                }else if(newCoin == -1){
                    System.out.println("\tYour refused the transfer, the request was removed\n");
                }else{
                    System.out.println("\tThe request was accepted the value was put in coin with ID: " + newCoin + "\n");
                }

            }else{
                System.out.println("\tInvalid command :P\n");
            }



        }

    }

}