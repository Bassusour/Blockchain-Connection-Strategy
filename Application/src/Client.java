

import java.util.List;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import io.reactivex.Flowable;
import net.sf.saxon.expr.instruct.Choose;
import ethereum.*;
import ethereum.SixG_Strategy.AddStrategyEventResponse;
import ethereum.SixG_Strategy.Strategy;

public class Client {
    static SixG_Strategy storage;
    static Strategy active_strategy;
    static List<Strategy> strategies;
    Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        StorageCreater sc = new StorageCreater();
        storage = sc.create();
        setStartegies();
        Flowable<AddStrategyEventResponse> flow = storage.addStrategyEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            SixG_Strategy.Strategy strategy = event.strategy;
            strategies.add(strategy);
            active_strategy = chooseActiveStrategy();
            enableStrategy();
        });
    }

    public static void setStartegies() throws Exception{
        strategies = storage.getStrategies().send();
    }

    public static Strategy chooseActiveStrategy(){
        long now = System.currentTimeMillis() / 1000L;
        for(Strategy strat : strategies){
            if(now > strat.startDate.longValue() && now < strat.endDate.longValue() && strat.priority.intValue() > active_strategy.priority.intValue()) {
                return strat;
            }
        }
        return null; 
    }

    public static void enableStrategy(){
        System.out.println("Connection: " + active_strategy.connectionType);
    }

}
