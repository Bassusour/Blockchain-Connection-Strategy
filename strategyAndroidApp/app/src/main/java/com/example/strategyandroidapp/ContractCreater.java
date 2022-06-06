package com.example.strategyandroidapp;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;


public class ContractCreater {
    static SixG_Strategy strategyContract;
    static TransactionManager tm;
    static Credentials cr;
    static ContractGasProvider cgp;
    public static Web3j web3j;

    public static SixG_Strategy create(String url, String keyStorePath, String code, String contractAddress)
            throws Exception {
        web3j = Web3j.build(new HttpService(url));
        cr = WalletUtils.loadCredentials(code, keyStorePath);
        tm = new RawTransactionManager(web3j, cr);
        cgp = new DefaultGasProvider();
        Log.i("web3j", contractAddress);
        strategyContract = SixG_Strategy.load(contractAddress, web3j, tm, cgp);
        Log.i("web3j",strategyContract.getLenght().send().toString());
        return strategyContract;
    }

}
