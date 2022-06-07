import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.web3j.protocol.core.DefaultBlockParameterName;
import io.reactivex.Flowable;
import ethereum.*;
import ethereum.SixG_Strategy.StrategyChangeEventResponse;
import ethereum.SixG_Strategy.Strategy;

public class Client {
    static SixG_Strategy strategyContract;
    static Strategy active_strategy;
    static List<Strategy> strategies;
    static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
        strategyContract = new ContractCreater().create();
        setStrategies();
        active_strategy = chooseActiveStrategy();
        enableStrategy();
        // Event flowable creating new thread. Triggers when change in list
        Flowable<StrategyChangeEventResponse> flow = strategyContract.strategyChangeEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            setStrategies();
            active_strategy = chooseActiveStrategy();
            enableStrategy();
        });
        // loop checking expiration or start
        while(true){
            TimeUnit.SECONDS.sleep(10);
            if (chooseActiveStrategy() != active_strategy){
                active_strategy = chooseActiveStrategy();
                enableStrategy();
            }
        }
    }

    public static void setStrategies() throws Exception{
        // get strategy list from contract
        strategies = strategyContract.getStrategies().send();
    }

    // Iterate list and check dates and priority in order to choose
    public static Strategy chooseActiveStrategy(){
        long now = System.currentTimeMillis() / 1000L;
        Strategy returnStrat = null;
        for(Strategy strat : strategies){
            if(now > strat.startDate.longValue() && now < strat.endDate.longValue() && 
                ( returnStrat == null || strat.priority.intValue() > returnStrat.priority.intValue())) {
                    returnStrat = strat;
                    // If max priority is found then no other will be chosen
                    if(returnStrat.priority.intValue() == 2) {
                        break;
                    }
            }
        }
        return returnStrat; 
    }

    public static void enableStrategy() throws IOException, InterruptedException{
        if(active_strategy != null) {
            // Turn bluetooth off it set to WIFI
            if (active_strategy.connectionType.intValue() == 0) {
                Process proc = runtime.exec("bluetoothctl power off");
                proc.waitFor();
            // Turn bluetooth on it set to DATA
            } else if (active_strategy.connectionType.intValue() == 1) {
                Process proc = runtime.exec("bluetoothctl power on");
                proc.waitFor();
            }
        }
    }
}
