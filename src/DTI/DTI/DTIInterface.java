package DTI;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import DTI.BFTWallet;
import DTI.BFTWallet.Pair;

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

                for (Pair<Long,Float> pair : myCoins) {
                    System.out.println(pair);
                }

            }else if(cmd.equalsIgnoreCase("MINT")){

                long key;
                try {
                    key = Integer.parseInt(console.readLine("Enter a coin id: "));
                } catch (NumberFormatException e) {
                    System.out.println("\tThe key is supposed to be an integer!\n");
                    continue;
                }

                BFTWallet.MINT(clientId);

            }else if(cmd.equalsIgnoreCase("SPEND")){

            }else if(cmd.equalsIgnoreCase("MY_NFTS")){

            }else if(cmd.equalsIgnoreCase("MINT_NFT")){

            }else if(cmd.equalsIgnoreCase("REQUEST_NFT_TRANSFER")){

            }else if(cmd.equalsIgnoreCase("CANCEL_REQUEST_NFT_TRANSFER")){

            }else if(cmd.equalsIgnoreCase("MY_NFT_REQUESTS")){

            }else if(cmd.equalsIgnoreCase("PROCESS_NFT_TRANSFER")){

            }else{
                System.out.println("\tInvalid command :P\n");
            }



        }

    }

}