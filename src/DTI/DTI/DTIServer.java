package DTI;

import bftsmart.tom.MessageContext;
import bftsmart.tom.ServiceReplica;
import bftsmart.tom.server.defaultservices.DefaultSingleRecoverable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class DTIServer extends DefaultSingleRecoverable{
    private final Logger logger = LoggerFactory.getLogger("bftsmart");
    private final ServiceReplica replica;
    private Wallet replicaWallet;

    //The constructor passes the id of the server to the super class
    public DTIServer(int id) {
        replicaWallet = new Wallet();
        replica = new ServiceReplica(id, this, this);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Use: java BFTMapServer <server id>");
            System.exit(-1);
        }
        new DTIServer(Integer.parseInt(args[0]));
    }

    @Override
    public byte[] appExecuteOrdered(byte[] command, MessageContext msgCtx) {
        try {
            BFTWalletMessage response = new BFTWalletMessage();
            BFTWalletMessage request = BFTWalletMessage.fromBytes(command);
            MessageType cmd = request.typeGet();

            logger.info("Ordered execution of a {} request from {}", cmd, msgCtx.getSender());

            switch (cmd) {
                case MINT:

                    if(request.userGet() != 4){
                        return null;
                    }

                    long newCoinId = replicaWallet.addCoin(request.userGet(), request.valueGet());
                    response.mintedCoinIDSet(newCoinId);

                    return BFTWalletMessage.toBytes(response); 
                case SPEND:

                    long returnCoinID = replicaWallet.spend(request.userGet(), request.usedCoinsGet(), request.receiverGet(), request.validityGet());
                    response.returnCoinSet(returnCoinID);

                    return BFTWalletMessage.toBytes(response); 
                case MINT_NFT:

                    long newNFTID = replicaWallet.addNFT(request.userGet(), request.nameGet(), request.uriGet());
                    response.mintedCoinIDSet(newNFTID);

                    return BFTWalletMessage.toBytes(response); 
                case REQUEST_NFT_TRANSFER:

                    long newRequestID = replicaWallet.addRequest(request.userGet(),request.NftIDGet(), request.usedCoinsGet(), request.valueGet(), request.validityGet());
                    response.requestIDSet(newRequestID);

                    return BFTWalletMessage.toBytes(response); 
                case CANCEL_REQUEST_NFT_TRANSFER:

                    replicaWallet.removeRequest(request.userGet(), request.NftIDGet());

                    return BFTWalletMessage.toBytes(response); 
                case PROCESS_NFT_TRASNFER:

                    long newCoinPaymentID = replicaWallet.transfer(request.userGet(),request.NftIDGet(),request.buyerGet());
                    response.returnCoinSet(newCoinPaymentID);

                    return BFTWalletMessage.toBytes(response); 

            }

            return null;
        }catch (IOException | ClassNotFoundException ex) {
            logger.error("Failed to process ordered request", ex);
            return new byte[0];
        }
    }

    @Override
    public byte[] appExecuteUnordered(byte[] command, MessageContext msgCtx) {
        try {
            BFTWalletMessage response = new BFTWalletMessage();
            BFTWalletMessage request = BFTWalletMessage.fromBytes(command);
            MessageType cmd = request.typeGet();

            logger.info("Unordered execution of a {} request from {}", cmd, msgCtx.getSender());

            switch (cmd) {
                //read operations on the map
                case MY_COINS:
                    
                    return BFTWalletMessage.toBytes(response);
            
                case MY_NFT:

                    return BFTWalletMessage.toBytes(response);
                    
                case MY_NFT_REQUEST:
                    
                    return BFTWalletMessage.toBytes(response);
                }
        } catch (IOException | ClassNotFoundException ex) {
            logger.error("Failed to process unordered request", ex);
            return new byte[0];
        }
        return null;
    }

    @Override
    public byte[] getSnapshot() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(replicaWallet);
            out.flush();
            bos.flush();
            return bos.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace(); //debug instruction
            return new byte[0];
        }
    }

    @Override
    public void installSnapshot(byte[] state) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(state);
             ObjectInput in = new ObjectInputStream(bis)) {
             replicaWallet = (Wallet) in.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace(); //debug instruction
        }
    }
}
