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
    static String address = "0xF89536E2321aAd09581d94C616BA1E9d2D2a004b";
    static TransactionManager tm;
    static Credentials cr;
    static ContractGasProvider cgp;
    public static Web3j web3j;

    public SixG_Strategy create() throws IOException, CipherException{
        System.out.println("Hello, World!");
        //Web3j web3j = Web3j.build(new UnixIpcService("/home/devlechain/chainData/ethereum/69_30000_ethash_0/geth.ipc"));
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8454"));
        cr = WalletUtils.loadCredentials("password", "/home/devlechain/chainData/ethereum/80_80000_ethash_0/keystore/UTC--2022-03-22T07-47-32.014897337Z--9a1c53c4b5bee3152a8fe0b995b186cfa6c78b38");
        tm = new RawTransactionManager(web3j, cr);
        cgp = new DefaultGasProvider();
        strategyContract = SixG_Strategy.load(address, web3j, tm, cgp);
        return strategyContract;
    }

    public Web3j getWeb3() {
        return web3j;
    }
}
