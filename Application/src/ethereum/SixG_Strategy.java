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
    public static final String BINARY = "608060405234801561001057600080fd5b50611136806100206000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063bed2a42e1161005b578063bed2a42e14610101578063c5d221581461011f578063d574ea3d1461013b578063dcdcf7e31461016f57610088565b80631f7b6d321461008d57806387302037146100ab578063a020f772146100c7578063b49a60bb146100e3575b600080fd5b61009561019f565b6040516100a29190610edd565b60405180910390f35b6100c560048036038101906100c09190610b0b565b6101a5565b005b6100e160048036038101906100dc9190610b78565b610436565b005b6100eb61061b565b6040516100f89190610e4d565b60405180910390f35b61010961078a565b6040516101169190610edd565b60405180910390f35b61013960048036038101906101349190610b38565b610794565b005b61015560048036038101906101509190610b0b565b610803565b604051610166959493929190610e6f565b60405180910390f35b61018960048036038101906101849190610b0b565b6108df565b6040516101969190610ec2565b60405180910390f35b60015481565b600080549050811080156101ba575060008110155b6101c357600080fd5b600060016000805490506101d79190610f31565b815481106101e8576101e7611056565b5b90600052602060002090600402016000828154811061020a57610209611056565b5b906000526020600020906004020160008201816000016000820160009054906101000a900463ffffffff168160000160006101000a81548163ffffffff021916908363ffffffff1602179055506000820160049054906101000a900463ffffffff168160000160046101000a81548163ffffffff021916908363ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060018201548160010155600282015481600201556003820160009054906101000a900460ff168160030160006101000a81548160ff0219169083600181111561031357610312610ff8565b5b02179055506003820160019054906101000a900460ff168160030160016101000a81548160ff021916908360028111156103505761034f610ff8565b5b0217905550905050600080548061036a57610369611027565b5b60019003818190600052602060002090600402016000808201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600182016000905560028201600090556003820160006101000a81549060ff02191690556003820160016101000a81549060ff0219169055505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a150565b600060405180606001604052808963ffffffff1681526020018863ffffffff1681526020018763ffffffff16815250905060006040518060a0016040528083815260200187815260200186815260200185600181111561049957610498610ff8565b5b81526020018460028111156104b1576104b0610ff8565b5b8152509050600081908060018154018082558091505060019003906000526020600020906004020160009091909190915060008201518160000160008201518160000160006101000a81548163ffffffff021916908363ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908363ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050602082015181600101556040820151816002015560608201518160030160006101000a81548160ff021916908360018111156105a1576105a0610ff8565b5b021790555060808201518160030160016101000a81548160ff021916908360028111156105d1576105d0610ff8565b5b021790555050506000805490506001819055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a1505050505050505050565b60606000805480602002602001604051908101604052809291908181526020016000905b8282101561078157838290600052602060002090600402016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff16600181111561072157610720610ff8565b5b600181111561073357610732610ff8565b5b81526020016003820160019054906101000a900460ff16600281111561075c5761075b610ff8565b5b600281111561076e5761076d610ff8565b5b815250508152602001906001019061063f565b50505050905090565b6000600154905090565b600080549050821080156107a9575060008210155b6107b257600080fd5b80600083815481106107c7576107c6611056565b5b906000526020600020906004020160030160016101000a81548160ff021916908360028111156107fa576107f9610ff8565b5b02179055505050565b6000818154811061081357600080fd5b9060005260206000209060040201600091509050806000016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060010154908060020154908060030160009054906101000a900460ff16908060030160019054906101000a900460ff16905085565b6108e7610a2b565b600082815481106108fb576108fa611056565b5b90600052602060002090600402016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff1660018111156109d3576109d2610ff8565b5b60018111156109e5576109e4610ff8565b5b81526020016003820160019054906101000a900460ff166002811115610a0e57610a0d610ff8565b5b6002811115610a2057610a1f610ff8565b5b815250509050919050565b6040518060a00160405280610a3e610a84565b8152602001600081526020016000815260200160006001811115610a6557610a64610ff8565b5b815260200160006002811115610a7e57610a7d610ff8565b5b81525090565b6040518060600160405280600063ffffffff168152602001600063ffffffff168152602001600063ffffffff1681525090565b600081359050610ac6816110b2565b92915050565b600081359050610adb816110c2565b92915050565b600081359050610af0816110d2565b92915050565b600081359050610b05816110e9565b92915050565b600060208284031215610b2157610b20611085565b5b6000610b2f84828501610ae1565b91505092915050565b60008060408385031215610b4f57610b4e611085565b5b6000610b5d85828601610ae1565b9250506020610b6e85828601610acc565b9150509250929050565b600080600080600080600060e0888a031215610b9757610b96611085565b5b6000610ba58a828b01610af6565b9750506020610bb68a828b01610af6565b9650506040610bc78a828b01610af6565b9550506060610bd88a828b01610ae1565b9450506080610be98a828b01610ae1565b93505060a0610bfa8a828b01610ab7565b92505060c0610c0b8a828b01610acc565b91505092959891949750929550565b6000610c268383610d50565b60e08301905092915050565b6000610c3d82610f08565b610c478185610f20565b9350610c5283610ef8565b8060005b83811015610c83578151610c6a8882610c1a565b9750610c7583610f13565b925050600181019050610c56565b5085935050505092915050565b610c9981610fa5565b82525050565b610ca881610fa5565b82525050565b610cb781610fb7565b82525050565b610cc681610fb7565b82525050565b606082016000820151610ce26000850182610e3e565b506020820151610cf56020850182610e3e565b506040820151610d086040850182610e3e565b50505050565b606082016000820151610d246000850182610e3e565b506020820151610d376020850182610e3e565b506040820151610d4a6040850182610e3e565b50505050565b60e082016000820151610d666000850182610ccc565b506020820151610d796060850182610e20565b506040820151610d8c6080850182610e20565b506060820151610d9f60a0850182610c90565b506080820151610db260c0850182610cae565b50505050565b60e082016000820151610dce6000850182610ccc565b506020820151610de16060850182610e20565b506040820151610df46080850182610e20565b506060820151610e0760a0850182610c90565b506080820151610e1a60c0850182610cae565b50505050565b610e2981610f8b565b82525050565b610e3881610f8b565b82525050565b610e4781610f95565b82525050565b60006020820190508181036000830152610e678184610c32565b905092915050565b600060e082019050610e846000830188610d0e565b610e916060830187610e2f565b610e9e6080830186610e2f565b610eab60a0830185610c9f565b610eb860c0830184610cbd565b9695505050505050565b600060e082019050610ed76000830184610db8565b92915050565b6000602082019050610ef26000830184610e2f565b92915050565b6000819050602082019050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b6000610f3c82610f8b565b9150610f4783610f8b565b925082821015610f5a57610f59610fc9565b5b828203905092915050565b6000819050610f738261108a565b919050565b6000819050610f868261109e565b919050565b6000819050919050565b600063ffffffff82169050919050565b6000610fb082610f65565b9050919050565b6000610fc282610f78565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b6002811061109b5761109a610ff8565b5b50565b600381106110af576110ae610ff8565b5b50565b600281106110bf57600080fd5b50565b600381106110cf57600080fd5b50565b6110db81610f8b565b81146110e657600080fd5b50565b6110f281610f95565b81146110fd57600080fd5b5056fea2646970667358221220a01dbe34e0ab73cf8cdda4b34082a3a73ffdf3388958957225f4848a97819acc64736f6c63430008070033";

    public static final String FUNC_CHANGEPRIORITY = "changePriority";

    public static final String FUNC_DELETESTRATEGY = "deleteStrategy";

    public static final String FUNC_GETLENGHT = "getLenght";

    public static final String FUNC_GETSTRATEGIES = "getStrategies";

    public static final String FUNC_GETSTRATEGYFROMINDEX = "getStrategyFromIndex";

    public static final String FUNC_LENGTH = "length";

    public static final String FUNC_MAKESTRATEGY = "makeStrategy";

    public static final String FUNC_STRATEGIES = "strategies";

    public static final Event STRATEGYCHANGE_EVENT = new Event("strategyChange", 
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

    public List<StrategyChangeEventResponse> getStrategyChangeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STRATEGYCHANGE_EVENT, transactionReceipt);
        ArrayList<StrategyChangeEventResponse> responses = new ArrayList<StrategyChangeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StrategyChangeEventResponse typedResponse = new StrategyChangeEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<StrategyChangeEventResponse> strategyChangeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, StrategyChangeEventResponse>() {
            @Override
            public StrategyChangeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STRATEGYCHANGE_EVENT, log);
                StrategyChangeEventResponse typedResponse = new StrategyChangeEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<StrategyChangeEventResponse> strategyChangeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STRATEGYCHANGE_EVENT));
        return strategyChangeEventFlowable(filter);
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
            super(location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.generated.Uint8(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority));
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
        }

        public Strategy(Location location, Uint256 startDate, Uint256 endDate, Uint8 connectionType, Uint8 priority) {
            super(location,startDate,endDate,connectionType,priority);
            this.location = location;
            this.startDate = startDate.getValue();
            this.endDate = endDate.getValue();
            this.connectionType = connectionType.getValue();
            this.priority = priority.getValue();
        }
    }

    public static class StrategyChangeEventResponse extends BaseEventResponse {
    }
}
