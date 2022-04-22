import java.util.Arrays;
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
        setStrategies();
        Flowable<AddStrategyEventResponse> flow = strategyContract.addStrategyEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            setStrategies();
            active_strategy = chooseActiveStrategy();
            enableStrategy();
        });

        boolean running = true;
        while(running){
            System.out.println("We runnin / sleeping");
            TimeUnit.SECONDS.sleep(10);
            checkActiveStrategy();
        }
    }

    public static void setStrategies() throws Exception{
        strategies = strategyContract.getStrategies().send();
    }

    public static Strategy chooseActiveStrategy(){
        long now = System.currentTimeMillis() / 1000L;
        Strategy returnStrat = null;
        for(Strategy strat : strategies){
            System.out.println("aloo");
            if(now > strat.startDate.longValue() && now < strat.endDate.longValue() && 
                ( returnStrat == null || strat.priority.intValue() > returnStrat.priority.intValue())) {
                    System.out.println("aloo in");
                    returnStrat = strat;
            }
        }
        return returnStrat; 
    }

    public static void enableStrategy(){
        if(active_strategy != null)
        System.out.println("Connection: " + active_strategy.connectionType);
    }

    public static void checkActiveStrategy() {
        if(active_strategy.endDate.longValue() < (System.currentTimeMillis() / 1000L)){
            active_strategy = chooseActiveStrategy();
            enableStrategy();
        }
    }

}
