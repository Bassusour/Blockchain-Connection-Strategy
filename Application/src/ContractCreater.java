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
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import ethereum.SixG_Strategy;

public class ContractCreater {
    static SixG_Strategy strategyContract;
    static String address = "0xe086E3F3df3350C4B71E8FA9837A8eB6dE119DfF";
    static TransactionManager tm;
    static Credentials cr;
    static ContractGasProvider cgp;
    public static Web3j web3j;

    public SixG_Strategy create() throws IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException{
        web3j = Web3j.build(new HttpService("http://192.168.1.241:8545"));
        ECKeyPair keyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard(EthBasis.password, keyPair);
        cr = Credentials.create(Wallet.decrypt(EthBasis.password, wallet));
        tm = new RawTransactionManager(web3j, cr);
        cgp = new DefaultGasProvider();
        strategyContract = SixG_Strategy.load(address, web3j, tm, cgp);
        return strategyContract;
    }
}
