

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import jp.ethereum.contracts.Storage;

public class Client {
    static Storage storage;
    static Strategy strategy;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        StorageCreater sc = new StorageCreater();
        storage = sc.create();
        Flowable<ValueChangedEventResponse> flow = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, storage.getContractAddress());
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

    public void updateStrategy(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, int priority, int x, int y, int radius, int connectionType){
        strategy.setStartDate(startDay, startMonth, startYear);
        strategy.setEndDate(endDay, endMonth, endYear);
        strategy.setPriority(priority);
        strategy.setConnectionType(connectionType);
        strategy.setLocation(x, y, radius);
    }

    public Strategy chooseActiveStrategy(){
        Strategy strat = null;
        return strat;
    }

}
