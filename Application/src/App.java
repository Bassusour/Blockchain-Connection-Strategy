
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
        Flowable<ValueChangedEventResponse> flow = storage.valueChangedEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(tx -> {
            int oldNumber = tx.oldNumber.intValue();
            int newNumber = tx.newNumber.intValue();
            System.out.println("oldnumber:" + oldNumber + " " + "New number" + newNumber);
        });
    }

    public void store() throws Exception{
        storage.store().send();
    }

}
