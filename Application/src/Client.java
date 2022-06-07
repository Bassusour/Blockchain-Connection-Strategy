import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        System.out.println("Hello, World!");
        strategyContract = new ContractCreater().create();
        setStrategies();
        active_strategy = chooseActiveStrategy();
        enableStrategy();
        // System.out.println(strategies.get(0).startDate.toString());
        // System.out.println(System.currentTimeMillis() / 1000L);
        // System.out.println(strategies.get(0).endDate.toString());
        Flowable<StrategyChangeEventResponse> flow = strategyContract.strategyChangeEventFlowable(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST);
        flow.subscribe(event -> {
            System.out.println("In event");
            setStrategies();
            active_strategy = chooseActiveStrategy();
            enableStrategy();
        });

        boolean running = true;
        while(running){
            TimeUnit.SECONDS.sleep(10);
            if (chooseActiveStrategy() != active_strategy){
                active_strategy = chooseActiveStrategy();
                enableStrategy();
            }
        }
    }

    public static void setStrategies() throws Exception{
        strategies = strategyContract.getStrategies().send();
    }

    public static Strategy chooseActiveStrategy(){
        System.out.println("In choose");
        long now = System.currentTimeMillis() / 1000L;
        Strategy returnStrat = null;
        for(Strategy strat : strategies){
            if(now > strat.startDate.longValue() && now < strat.endDate.longValue() && 
                ( returnStrat == null || strat.priority.intValue() > returnStrat.priority.intValue())) {
                    returnStrat = strat;
                    if(returnStrat.priority.intValue() == 2) {
                        break;
                    }
            }
        }
        return returnStrat; 
    }

    public static void enableStrategy() throws IOException, InterruptedException{
        System.out.println("In enable");
        if(active_strategy != null) {
            System.out.println("Connection: " + active_strategy.connectionType);
            System.out.println("Desc: " + new String(active_strategy.description, StandardCharsets.UTF_8));
            if (active_strategy.connectionType.intValue() == 0) {
                Process proc = runtime.exec("bluetoothctl power off");
                proc.waitFor();
            } else if (active_strategy.connectionType.intValue() == 1) {
                Process proc = runtime.exec("bluetoothctl power on");
                proc.waitFor();
            }
        }
    }
}
