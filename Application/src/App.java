
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import io.reactivex.Flowable;
import jp.ethereum.contracts.Storage;
import jp.ethereum.contracts.Storage.ValueChangedEventResponse;

public class App {
    static Storage storage;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        StorageCreater sc = new StorageCreater();
        storage = sc.create();
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, storage.getContractAddress());
        sc.getWeb3().ethLogFlowable(filter).subscribe(event -> {
            System.out.println("Event received");
            System.out.println(event);
        }, error -> {
            System.out.println("Error: " + error);
        });
    }

    public void store() throws Exception{
        storage.store().send();
    }

}
