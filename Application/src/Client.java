import java.util.List;
import java.util.concurrent.TimeUnit;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;

import io.reactivex.Flowable;
import net.sf.saxon.expr.instruct.Choose;
import ethereum.*;
import ethereum.SixG_Strategy.AddStrategyEventResponse;
import ethereum.SixG_Strategy.Strategy;

public class Client {
    static SixG_Strategy strategyContract;
    static Strategy active_strategy;
    static List<Strategy> strategies;
    Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        strategyContract = new StorageCreater().create();
        setStartegies();
        Flowable<AddStrategyEventResponse> flow = strategyContract.addStrategyEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            SixG_Strategy.Strategy strategy = event.strategy;
            strategies.add(strategy);
            active_strategy = chooseActiveStrategy();
            enableStrategy();
        });

        boolean running = true;
        while(running){
            TimeUnit.SECONDS.sleep(10);
        }
    }

    public static void setStartegies() throws Exception{
        strategies = strategyContract.getStrategies().send();
    }

    public static Strategy chooseActiveStrategy(){
        long now = System.currentTimeMillis() / 1000L;
        for(Strategy strat : strategies){
            if(now > strat.startDate.longValue() && now < strat.endDate.longValue() && 
                ( active_strategy == null || strat.priority.intValue() > active_strategy.priority.intValue())) {
                return strat;
            }
        }
        return null; 
    }

    public static void enableStrategy(){
        System.out.println("Connection: " + active_strategy.connectionType);
    }

}
