

import java.util.List;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import io.reactivex.Flowable;
import ethereum.*;
import ethereum.Storage.ChangeStrategyEventResponse;

public class Client {
    static Storage storage;
    static Strategy strategy;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        StorageCreater sc = new StorageCreater();
        storage = sc.create();
        Flowable<ChangeStrategyEventResponse> flow = storage.changeStrategyEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            List<Strategy> strategies = event.strategies;
            
        });
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
