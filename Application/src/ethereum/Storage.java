package ethereum;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Storage extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50611150806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063a020f7721161005b578063a020f772146100ef578063bed2a42e1461010b578063c5d2215814610129578063d574ea3d1461014557610088565b80631f7b6d321461008d5780633a3f45b7146100ab57806362ec4d34146100b557806387302037146100d3575b600080fd5b610095610179565b6040516100a29190610dc6565b60405180910390f35b6100b361017f565b005b6100bd610181565b6040516100ca9190610d2f565b60405180910390f35b6100ed60048036038101906100e891906108fb565b6102f0565b005b61010960048036038101906101049190610968565b610561565b005b610113610752565b6040516101209190610dc6565b60405180910390f35b610143600480360381019061013e9190610928565b61075c565b005b61015f600480360381019061015a91906108fb565b6107cb565b604051610170959493929190610d73565b60405180910390f35b60015481565b565b60606000805480602002602001604051908101604052809291908181526020016000905b828210156102e757838290600052602060002090600402016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff16600181111561028757610286610fde565b5b600181111561029957610298610fde565b5b81526020016003820160019054906101000a900460ff1660028111156102c2576102c1610fde565b5b60028111156102d4576102d3610fde565b5b81525050815260200190600101906101a5565b50505050905090565b60008054905081108015610305575060008111155b61030e57600080fd5b600060016000805490506103229190610e47565b815481106103335761033261103c565b5b9060005260206000209060040201600082815481106103555761035461103c565b5b906000526020600020906004020160008201816000016000820160009054906101000a900463ffffffff168160000160006101000a81548163ffffffff021916908363ffffffff1602179055506000820160049054906101000a900463ffffffff168160000160046101000a81548163ffffffff021916908363ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060018201548160010155600282015481600201556003820160009054906101000a900460ff168160030160006101000a81548160ff0219169083600181111561045e5761045d610fde565b5b02179055506003820160019054906101000a900460ff168160030160016101000a81548160ff0219169083600281111561049b5761049a610fde565b5b021790555090505060008054806104b5576104b461100d565b5b60019003818190600052602060002090600402016000808201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600182016000905560028201600090556003820160006101000a81549060ff02191690556003820160016101000a81549060ff02191690555050905560008054905060018190555050565b600060405180606001604052808963ffffffff1681526020018863ffffffff1681526020018763ffffffff16815250905060006040518060a001604052808381526020018781526020018681526020018560018111156105c4576105c3610fde565b5b81526020018460028111156105dc576105db610fde565b5b8152509050600081908060018154018082558091505060019003906000526020600020906004020160009091909190915060008201518160000160008201518160000160006101000a81548163ffffffff021916908363ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908363ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050602082015181600101556040820151816002015560608201518160030160006101000a81548160ff021916908360018111156106cc576106cb610fde565b5b021790555060808201518160030160016101000a81548160ff021916908360028111156106fc576106fb610fde565b5b021790555050506000805490506001819055507f7f00a24f8dcc1c7160f2e05c044fb94cb50b3c2c9adfe6f07b47e88b87da0cbf600060405161073f9190610d51565b60405180910390a1505050505050505050565b6000600154905090565b60008054905082108015610771575060008210155b61077a57600080fd5b806000838154811061078f5761078e61103c565b5b906000526020600020906004020160030160016101000a81548160ff021916908360028111156107c2576107c1610fde565b5b02179055505050565b600081815481106107db57600080fd5b9060005260206000209060040201600091509050806000016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060010154908060020154908060030160009054906101000a900460ff16908060030160019054906101000a900460ff16905085565b6000813590506108b6816110cc565b92915050565b6000813590506108cb816110dc565b92915050565b6000813590506108e0816110ec565b92915050565b6000813590506108f581611103565b92915050565b6000602082840312156109115761091061106b565b5b600061091f848285016108d1565b91505092915050565b6000806040838503121561093f5761093e61106b565b5b600061094d858286016108d1565b925050602061095e858286016108bc565b9150509250929050565b600080600080600080600060e0888a0312156109875761098661106b565b5b60006109958a828b016108e6565b97505060206109a68a828b016108e6565b96505060406109b78a828b016108e6565b95505060606109c88a828b016108d1565b94505060806109d98a828b016108d1565b93505060a06109ea8a828b016108a7565b92505060c06109fb8a828b016108bc565b91505092959891949750929550565b6000610a168383610c0c565b60e08301905092915050565b6000610a2e8383610c74565b60e08301905092915050565b6000610a4582610e06565b610a4f8185610e36565b9350610a5a83610de1565b8060005b83811015610a8b578151610a728882610a0a565b9750610a7d83610e1c565b925050600181019050610a5e565b5085935050505092915050565b6000610aa382610e11565b610aad8185610e36565b9350610ab883610df1565b8060005b83811015610ae85781610acf8882610a22565b9750610ada83610e29565b925050600181019050610abc565b5085935050505092915050565b610afe81610eef565b82525050565b610b0d81610eef565b82525050565b610b1c81610f01565b82525050565b610b2b81610f01565b82525050565b606082016000820151610b476000850182610d20565b506020820151610b5a6020850182610d20565b506040820151610b6d6040850182610d20565b50505050565b606082016000820151610b896000850182610d20565b506020820151610b9c6020850182610d20565b506040820151610baf6040850182610d20565b50505050565b606082016000808301549050610bca81610f47565b610bd76000860182610d20565b50610be181610f7b565b610bee6020860182610d20565b50610bf881610f95565b610c056040860182610d20565b5050505050565b60e082016000820151610c226000850182610b31565b506020820151610c356060850182610d02565b506040820151610c486080850182610d02565b506060820151610c5b60a0850182610af5565b506080820151610c6e60c0850182610b13565b50505050565b60e082016000808301610c8a6000860182610bb5565b5060018301549050610c9b81610f2d565b610ca86060860182610d02565b5060028301549050610cb981610f2d565b610cc66080860182610d02565b5060038301549050610cd781610f13565b610ce460a0860182610af5565b50610cee81610f61565b610cfb60c0860182610b13565b5050505050565b610d0b81610ed5565b82525050565b610d1a81610ed5565b82525050565b610d2981610edf565b82525050565b60006020820190508181036000830152610d498184610a3a565b905092915050565b60006020820190508181036000830152610d6b8184610a98565b905092915050565b600060e082019050610d886000830188610b73565b610d956060830187610d11565b610da26080830186610d11565b610daf60a0830185610b04565b610dbc60c0830184610b22565b9695505050505050565b6000602082019050610ddb6000830184610d11565b92915050565b6000819050602082019050919050565b60008190508160005260206000209050919050565b600081519050919050565b600081549050919050565b6000602082019050919050565b6000600482019050919050565b600082825260208201905092915050565b6000610e5282610ed5565b9150610e5d83610ed5565b925082821015610e7057610e6f610faf565b5b828203905092915050565b600060ff82169050919050565b600060ff82169050919050565b6000819050919050565b600063ffffffff82169050919050565b6000819050610ebd826110a4565b919050565b6000819050610ed0826110b8565b919050565b6000819050919050565b600063ffffffff82169050919050565b6000610efa82610eaf565b9050919050565b6000610f0c82610ec2565b9050919050565b6000610f26610f2183611070565b610e7b565b9050919050565b6000610f40610f3b83611070565b610e95565b9050919050565b6000610f5a610f5583611070565b610e9f565b9050919050565b6000610f74610f6f83611097565b610e88565b9050919050565b6000610f8e610f898361107d565b610e9f565b9050919050565b6000610fa8610fa38361108a565b610e9f565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b60008160001c9050919050565b60008160201c9050919050565b60008160401c9050919050565b60008160081c9050919050565b600281106110b5576110b4610fde565b5b50565b600381106110c9576110c8610fde565b5b50565b600281106110d957600080fd5b50565b600381106110e957600080fd5b50565b6110f581610ed5565b811461110057600080fd5b50565b61110c81610edf565b811461111757600080fd5b5056fea26469706673582212202a5f2dc6cfe14c25d93bb2ec9046af04b62f1305dd6cc6869ae88990355439e964736f6c63430008070033";

    public static final String FUNC_CHANGEPRIORITY = "changePriority";

    public static final String FUNC_DELETESTRATEGY = "deleteStrategy";

    public static final String FUNC_GETLENGHT = "getLenght";

    public static final String FUNC_GETSTREGEGIES = "getStregegies";

    public static final String FUNC_LENGTH = "length";

    public static final String FUNC_MAKESTRATEGY = "makeStrategy";

    public static final String FUNC_STRATEGIES = "strategies";

    public static final String FUNC_UPDATEARRAY = "updateArray";

    public static final Event CHANGESTRATEGY_EVENT = new Event("changeStrategy", 
            Arrays.<TypeReference<?>>asList(new TypeReference<List<Strategy>>() {}));
    ;

    @Deprecated
    protected Storage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Storage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Storage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Storage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ChangeStrategyEventResponse> getChangeStrategyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGESTRATEGY_EVENT, transactionReceipt);
        ArrayList<ChangeStrategyEventResponse> responses = new ArrayList<ChangeStrategyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangeStrategyEventResponse typedResponse = new ChangeStrategyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.strategies = (List<Strategy>) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChangeStrategyEventResponse> changeStrategyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ChangeStrategyEventResponse>() {
            @Override
            public ChangeStrategyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGESTRATEGY_EVENT, log);
                ChangeStrategyEventResponse typedResponse = new ChangeStrategyEventResponse();
                typedResponse.log = log;
                typedResponse.strategies = (List<Strategy>) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChangeStrategyEventResponse> changeStrategyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGESTRATEGY_EVENT));
        return changeStrategyEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> changePriority(BigInteger index, BigInteger priority) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHANGEPRIORITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index), 
                new org.web3j.abi.datatypes.generated.Uint8(priority)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteStrategy(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETESTRATEGY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getLenght() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENGHT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getStregegies() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTREGEGIES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Strategy>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> length() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> makeStrategy(BigInteger x, BigInteger y, BigInteger radius, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MAKESTRATEGY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(x), 
                new org.web3j.abi.datatypes.generated.Uint32(y), 
                new org.web3j.abi.datatypes.generated.Uint32(radius), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate), 
                new org.web3j.abi.datatypes.generated.Uint8(connectionType), 
                new org.web3j.abi.datatypes.generated.Uint8(priority)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<Location, BigInteger, BigInteger, BigInteger, BigInteger>> strategies(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRATEGIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Location>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple5<Location, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple5<Location, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<Location, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<Location, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (Location) results.get(0), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateArray() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEARRAY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Storage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Storage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Storage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Storage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Storage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Storage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Storage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Storage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Storage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Storage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Storage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Storage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Storage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Storage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Storage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Storage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Location extends StaticStruct {
        public BigInteger x;

        public BigInteger y;

        public BigInteger radius;

        public Location(BigInteger x, BigInteger y, BigInteger radius) {
            super(new org.web3j.abi.datatypes.generated.Uint32(x),new org.web3j.abi.datatypes.generated.Uint32(y),new org.web3j.abi.datatypes.generated.Uint32(radius));
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public Location(Uint32 x, Uint32 y, Uint32 radius) {
            super(x,y,radius);
            this.x = x.getValue();
            this.y = y.getValue();
            this.radius = radius.getValue();
        }
    }

    public static class ChangeStrategyEventResponse extends BaseEventResponse {
        public List<Strategy> strategies;
    }
}