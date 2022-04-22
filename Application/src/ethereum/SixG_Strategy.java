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
public class SixG_Strategy extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610ede806100206000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063bed2a42e11610066578063bed2a42e146100fa578063c5d2215814610118578063d4a7a77f14610134578063d574ea3d14610150578063dcdcf7e31461018457610093565b80631f7b6d32146100985780633a3f45b7146100b657806387302037146100c0578063b49a60bb146100dc575b600080fd5b6100a06101b4565b6040516100ad9190610d22565b60405180910390f35b6100be6101ba565b005b6100da60048036038101906100d5919061096e565b6101bc565b005b6100e46103df565b6040516100f19190610c92565b60405180910390f35b6101026104ff565b60405161010f9190610d22565b60405180910390f35b610132600480360381019061012d919061099b565b610509565b005b61014e600480360381019061014991906109db565b610569565b005b61016a6004803603810190610165919061096e565b6106f8565b60405161017b959493929190610cb4565b60405180910390f35b61019e6004803603810190610199919061096e565b6107c7565b6040516101ab9190610d07565b60405180910390f35b60015481565b565b600080549050811080156101d1575060008110155b6101da57600080fd5b600060016000805490506101ee9190610d76565b815481106101ff576101fe610e2f565b5b90600052602060002090600502016000828154811061022157610220610e2f565b5b906000526020600020906005020160008201816000016000820160009054906101000a900463ffffffff168160000160006101000a81548163ffffffff021916908363ffffffff1602179055506000820160049054906101000a900463ffffffff168160000160046101000a81548163ffffffff021916908363ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff16021790555050506001820154816001015560028201548160020155600382015481600301556004820160009054906101000a900460ff168160040160006101000a81548160ff021916908360ff160217905550905050600080548061033f5761033e610e00565b5b60019003818190600052602060002090600502016000808201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff021916905550506001820160009055600282016000905560038201600090556004820160006101000a81549060ff02191690555050905560008054905060018190555050565b60606000805480602002602001604051908101604052809291908181526020016000905b828210156104f657838290600052602060002090600502016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff168152505081526020016001820154815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff1660ff1660ff168152505081526020019060010190610403565b50505050905090565b6000600154905090565b6000805490508210801561051e575060008210155b61052757600080fd5b806000838154811061053c5761053b610e2f565b5b906000526020600020906005020160040160006101000a81548160ff021916908360ff1602179055505050565b600060405180606001604052808963ffffffff1681526020018863ffffffff1681526020018763ffffffff16815250905060006040518060a001604052808381526020018781526020018681526020018581526020018460ff168152509050600081908060018154018082558091505060019003906000526020600020906005020160009091909190915060008201518160000160008201518160000160006101000a81548163ffffffff021916908363ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908363ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060208201518160010155604082015181600201556060820151816003015560808201518160040160006101000a81548160ff021916908360ff16021790555050506000805490506001819055507fc6ebd58eac6977b43d1e511fff877b3860343fe7759a0fd675f9454765edfe0960405160405180910390a1505050505050505050565b6000818154811061070857600080fd5b9060005260206000209060050201600091509050806000016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060010154908060020154908060030154908060040160009054906101000a900460ff16905085565b6107cf6108c4565b600082815481106107e3576107e2610e2f565b5b90600052602060002090600502016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff168152505081526020016001820154815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff1660ff1660ff16815250509050919050565b6040518060a001604052806108d76108fc565b8152602001600081526020016000815260200160008152602001600060ff1681525090565b6040518060600160405280600063ffffffff168152602001600063ffffffff168152602001600063ffffffff1681525090565b60008135905061093e81610e63565b92915050565b60008135905061095381610e7a565b92915050565b60008135905061096881610e91565b92915050565b60006020828403121561098457610983610e5e565b5b60006109928482850161092f565b91505092915050565b600080604083850312156109b2576109b1610e5e565b5b60006109c08582860161092f565b92505060206109d185828601610959565b9150509250929050565b600080600080600080600060e0888a0312156109fa576109f9610e5e565b5b6000610a088a828b01610944565b9750506020610a198a828b01610944565b9650506040610a2a8a828b01610944565b9550506060610a3b8a828b0161092f565b9450506080610a4c8a828b0161092f565b93505060a0610a5d8a828b0161092f565b92505060c0610a6e8a828b01610959565b91505092959891949750929550565b6000610a898383610b77565b60e08301905092915050565b6000610aa082610d4d565b610aaa8185610d65565b9350610ab583610d3d565b8060005b83811015610ae6578151610acd8882610a7d565b9750610ad883610d58565b925050600181019050610ab9565b5085935050505092915050565b606082016000820151610b096000850182610c65565b506020820151610b1c6020850182610c65565b506040820151610b2f6040850182610c65565b50505050565b606082016000820151610b4b6000850182610c65565b506020820151610b5e6020850182610c65565b506040820151610b716040850182610c65565b50505050565b60e082016000820151610b8d6000850182610af3565b506020820151610ba06060850182610c47565b506040820151610bb36080850182610c47565b506060820151610bc660a0850182610c47565b506080820151610bd960c0850182610c74565b50505050565b60e082016000820151610bf56000850182610af3565b506020820151610c086060850182610c47565b506040820151610c1b6080850182610c47565b506060820151610c2e60a0850182610c47565b506080820151610c4160c0850182610c74565b50505050565b610c5081610daa565b82525050565b610c5f81610daa565b82525050565b610c6e81610db4565b82525050565b610c7d81610dc4565b82525050565b610c8c81610dc4565b82525050565b60006020820190508181036000830152610cac8184610a95565b905092915050565b600060e082019050610cc96000830188610b35565b610cd66060830187610c56565b610ce36080830186610c56565b610cf060a0830185610c56565b610cfd60c0830184610c83565b9695505050505050565b600060e082019050610d1c6000830184610bdf565b92915050565b6000602082019050610d376000830184610c56565b92915050565b6000819050602082019050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b6000610d8182610daa565b9150610d8c83610daa565b925082821015610d9f57610d9e610dd1565b5b828203905092915050565b6000819050919050565b600063ffffffff82169050919050565b600060ff82169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b610e6c81610daa565b8114610e7757600080fd5b50565b610e8381610db4565b8114610e8e57600080fd5b50565b610e9a81610dc4565b8114610ea557600080fd5b5056fea26469706673582212204f1eb2ccfeef1a999d54738a789e103a380764812a7202c907365dc2deabf4ea64736f6c63430008070033";

    public static final String FUNC_CHANGEPRIORITY = "changePriority";

    public static final String FUNC_DELETESTRATEGY = "deleteStrategy";

    public static final String FUNC_GETLENGHT = "getLenght";

    public static final String FUNC_GETSTRATEGIES = "getStrategies";

    public static final String FUNC_GETSTRATEGYFROMINDEX = "getStrategyFromIndex";

    public static final String FUNC_LENGTH = "length";

    public static final String FUNC_MAKESTRATEGY = "makeStrategy";

    public static final String FUNC_STRATEGIES = "strategies";

    public static final String FUNC_UPDATEARRAY = "updateArray";

    public static final Event ADDSTRATEGY_EVENT = new Event("addStrategy", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected SixG_Strategy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SixG_Strategy(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SixG_Strategy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SixG_Strategy(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AddStrategyEventResponse> getAddStrategyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDSTRATEGY_EVENT, transactionReceipt);
        ArrayList<AddStrategyEventResponse> responses = new ArrayList<AddStrategyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddStrategyEventResponse typedResponse = new AddStrategyEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AddStrategyEventResponse> addStrategyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AddStrategyEventResponse>() {
            @Override
            public AddStrategyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADDSTRATEGY_EVENT, log);
                AddStrategyEventResponse typedResponse = new AddStrategyEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<AddStrategyEventResponse> addStrategyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADDSTRATEGY_EVENT));
        return addStrategyEventFlowable(filter);
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

    public RemoteFunctionCall<List> getStrategies() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTRATEGIES, 
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

    public RemoteFunctionCall<Strategy> getStrategyFromIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTRATEGYFROMINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Strategy>() {}));
        return executeRemoteCallSingleValueReturn(function, Strategy.class);
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
                new org.web3j.abi.datatypes.generated.Uint256(connectionType), 
                new org.web3j.abi.datatypes.generated.Uint8(priority)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<Location, BigInteger, BigInteger, BigInteger, BigInteger>> strategies(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRATEGIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Location>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
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
    public static SixG_Strategy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SixG_Strategy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SixG_Strategy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SixG_Strategy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SixG_Strategy load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SixG_Strategy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SixG_Strategy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SixG_Strategy(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SixG_Strategy> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SixG_Strategy.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SixG_Strategy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SixG_Strategy.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SixG_Strategy> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SixG_Strategy.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SixG_Strategy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SixG_Strategy.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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

    public static class Strategy extends StaticStruct {
        public Location location;

        public BigInteger startDate;

        public BigInteger endDate;

        public BigInteger connectionType;

        public BigInteger priority;

        public Strategy(Location location, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority) {
            super(location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.generated.Uint256(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority));
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
        }

        public Strategy(Location location, Uint256 startDate, Uint256 endDate, Uint256 connectionType, Uint8 priority) {
            super(location,startDate,endDate,connectionType,priority);
            this.location = location;
            this.startDate = startDate.getValue();
            this.endDate = endDate.getValue();
            this.connectionType = connectionType.getValue();
            this.priority = priority.getValue();
        }
    }

    public static class AddStrategyEventResponse extends BaseEventResponse {
    }
}
