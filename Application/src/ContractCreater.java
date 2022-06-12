import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import ethereum.SixG_Strategy;

public class ContractCreater {
    static SixG_Strategy strategyContract;
    static String address = "0xF7737b3307383FF2B8ebbfa9043D5602a02a3D6e";
    static TransactionManager tm;
    static Credentials cr;
    static ContractGasProvider cgp;
    public static Web3j web3j;

    // Method to create connection to contract and object
    public SixG_Strategy create() throws IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException{
        web3j = Web3j.build(new HttpService("http://172.20.10.10:8545"));
        ECKeyPair keyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard("code", keyPair);
        cr = Credentials.create(Wallet.decrypt("code", wallet));
        tm = new RawTransactionManager(web3j, cr);
        cgp = new DefaultGasProvider();
        strategyContract = SixG_Strategy.load(address, web3j, tm, cgp);
        System.out.println("Connection successful");
        return strategyContract;
    }
}
