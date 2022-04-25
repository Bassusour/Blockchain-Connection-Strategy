import java.io.IOException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import ethereum.SixG_Strategy;

public class StorageCreater {
    static SixG_Strategy strategyContract;
    static String address = "0x211409Cd22CC6D5Bee88567a6a43394a8395da92";
    static TransactionManager tm;
    static Credentials cr;
    static ContractGasProvider cgp;
    public static Web3j web3j;

    public SixG_Strategy create() throws IOException, CipherException{
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        cr = WalletUtils.loadCredentials(EthBasis.password, EthBasis.credentials);
        tm = new RawTransactionManager(web3j, cr);
        cgp = new DefaultGasProvider();
        strategyContract = SixG_Strategy.load(address, web3j, tm, cgp);
        return strategyContract;
    }
}
