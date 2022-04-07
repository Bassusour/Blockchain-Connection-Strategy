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
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
    public static final String BINARY = "608060405234801561001057600080fd5b50611503806100206000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063b49a60bb11610066578063b49a60bb146100f8578063bed2a42e14610116578063c5d2215814610134578063d574ea3d14610150578063dcdcf7e31461018457610093565b80631f7b6d321461009857806326181be0146100b65780633a3f45b7146100d257806387302037146100dc575b600080fd5b6100a06101b4565b6040516100ad91906111a1565b60405180910390f35b6100d060048036038101906100cb9190610d8f565b6101ba565b005b6100da610367565b005b6100f660048036038101906100f19190610d22565b610369565b005b6101006105aa565b60405161010d9190611103565b60405180910390f35b61011e610752565b60405161012b91906111a1565b60405180910390f35b61014e60048036038101906101499190610d4f565b61075c565b005b61016a60048036038101906101659190610d22565b6107bc565b60405161017b959493929190611125565b60405180910390f35b61019e60048036038101906101999190610d22565b610913565b6040516101ab919061117f565b60405180910390f35b60015481565b600060405180606001604052808963ffffffff1681526020018863ffffffff1681526020018763ffffffff16815250905060006040518060a001604052808381526020018781526020018681526020018581526020018460ff168152509050600081908060018154018082558091505060019003906000526020600020906005020160009091909190915060008201518160000160008201518160000160006101000a81548163ffffffff021916908363ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908363ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050602082015181600101556040820151816002015560608201518160030190805190602001906102f5929190610a98565b5060808201518160040160006101000a81548160ff021916908360ff16021790555050506000805490506001819055507f1b44d34d4495f8c21572a94fd95de4a318233f8238afb7d6c641c76f68d9a2f381604051610354919061117f565b60405180910390a1505050505050505050565b565b6000805490508110801561037e575060008111155b61038757600080fd5b6000600160008054905061039b9190611278565b815481106103ac576103ab611405565b5b9060005260206000209060050201600082815481106103ce576103cd611405565b5b906000526020600020906005020160008201816000016000820160009054906101000a900463ffffffff168160000160006101000a81548163ffffffff021916908363ffffffff1602179055506000820160049054906101000a900463ffffffff168160000160046101000a81548163ffffffff021916908363ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050600182015481600101556002820154816002015560038201816003019080546104b390611315565b6104be929190610b1e565b506004820160009054906101000a900460ff168160040160006101000a81548160ff021916908360ff1602179055509050506000805480610502576105016113d6565b5b60019003818190600052602060002090600502016000808201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600182016000905560028201600090556003820160006105839190610bab565b6004820160006101000a81549060ff02191690555050905560008054905060018190555050565b60606000805480602002602001604051908101604052809291908181526020016000905b8282101561074957838290600052602060002090600502016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff16815250508152602001600182015481526020016002820154815260200160038201805461069b90611315565b80601f01602080910402602001604051908101604052809291908181526020018280546106c790611315565b80156107145780601f106106e957610100808354040283529160200191610714565b820191906000526020600020905b8154815290600101906020018083116106f757829003601f168201915b505050505081526020016004820160009054906101000a900460ff1660ff1660ff1681525050815260200190600101906105ce565b50505050905090565b6000600154905090565b60008054905082108015610771575060008210155b61077a57600080fd5b806000838154811061078f5761078e611405565b5b906000526020600020906005020160040160006101000a81548160ff021916908360ff1602179055505050565b600081815481106107cc57600080fd5b9060005260206000209060050201600091509050806000016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff16815250509080600101549080600201549080600301805461087d90611315565b80601f01602080910402602001604051908101604052809291908181526020018280546108a990611315565b80156108f65780601f106108cb576101008083540402835291602001916108f6565b820191906000526020600020905b8154815290600101906020018083116108d957829003601f168201915b5050505050908060040160009054906101000a900460ff16905085565b61091b610beb565b6000828154811061092f5761092e611405565b5b90600052602060002090600502016040518060a0016040529081600082016040518060600160405290816000820160009054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160049054906101000a900463ffffffff1663ffffffff1663ffffffff1681526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820180546109f290611315565b80601f0160208091040260200160405190810160405280929190818152602001828054610a1e90611315565b8015610a6b5780601f10610a4057610100808354040283529160200191610a6b565b820191906000526020600020905b815481529060010190602001808311610a4e57829003601f168201915b505050505081526020016004820160009054906101000a900460ff1660ff1660ff16815250509050919050565b828054610aa490611315565b90600052602060002090601f016020900481019282610ac65760008555610b0d565b82601f10610adf57805160ff1916838001178555610b0d565b82800160010185558215610b0d579182015b82811115610b0c578251825591602001919060010190610af1565b5b509050610b1a9190610c23565b5090565b828054610b2a90611315565b90600052602060002090601f016020900481019282610b4c5760008555610b9a565b82601f10610b5d5780548555610b9a565b82800160010185558215610b9a57600052602060002091601f016020900482015b82811115610b99578254825591600101919060010190610b7e565b5b509050610ba79190610c23565b5090565b508054610bb790611315565b6000825580601f10610bc95750610be8565b601f016020900490600052602060002090810190610be79190610c23565b5b50565b6040518060a00160405280610bfe610c40565b8152602001600081526020016000815260200160608152602001600060ff1681525090565b5b80821115610c3c576000816000905550600101610c24565b5090565b6040518060600160405280600063ffffffff168152602001600063ffffffff168152602001600063ffffffff1681525090565b6000610c86610c81846111e1565b6111bc565b905082815260208101848484011115610ca257610ca1611468565b5b610cad8482856112d3565b509392505050565b600082601f830112610cca57610cc9611463565b5b8135610cda848260208601610c73565b91505092915050565b600081359050610cf281611488565b92915050565b600081359050610d078161149f565b92915050565b600081359050610d1c816114b6565b92915050565b600060208284031215610d3857610d37611472565b5b6000610d4684828501610ce3565b91505092915050565b60008060408385031215610d6657610d65611472565b5b6000610d7485828601610ce3565b9250506020610d8585828601610d0d565b9150509250929050565b600080600080600080600060e0888a031215610dae57610dad611472565b5b6000610dbc8a828b01610cf8565b9750506020610dcd8a828b01610cf8565b9650506040610dde8a828b01610cf8565b9550506060610def8a828b01610ce3565b9450506080610e008a828b01610ce3565b93505060a088013567ffffffffffffffff811115610e2157610e2061146d565b5b610e2d8a828b01610cb5565b92505060c0610e3e8a828b01610d0d565b91505092959891949750929550565b6000610e598383610fcc565b905092915050565b6000610e6c82611222565b610e768185611245565b935083602082028501610e8885611212565b8060005b85811015610ec45784840389528151610ea58582610e4d565b9450610eb083611238565b925060208a01995050600181019050610e8c565b50829750879550505050505092915050565b6000610ee18261122d565b610eeb8185611256565b9350610efb8185602086016112e2565b610f0481611477565b840191505092915050565b6000610f1a8261122d565b610f248185611267565b9350610f348185602086016112e2565b610f3d81611477565b840191505092915050565b606082016000820151610f5e60008501826110d6565b506020820151610f7160208501826110d6565b506040820151610f8460408501826110d6565b50505050565b606082016000820151610fa060008501826110d6565b506020820151610fb360208501826110d6565b506040820151610fc660408501826110d6565b50505050565b600060e083016000830151610fe46000860182610f48565b506020830151610ff760608601826110b8565b50604083015161100a60808601826110b8565b50606083015184820360a08601526110228282610ed6565b915050608083015161103760c08601826110e5565b508091505092915050565b600060e08301600083015161105a6000860182610f48565b50602083015161106d60608601826110b8565b50604083015161108060808601826110b8565b50606083015184820360a08601526110988282610ed6565b91505060808301516110ad60c08601826110e5565b508091505092915050565b6110c1816112ac565b82525050565b6110d0816112ac565b82525050565b6110df816112b6565b82525050565b6110ee816112c6565b82525050565b6110fd816112c6565b82525050565b6000602082019050818103600083015261111d8184610e61565b905092915050565b600060e08201905061113a6000830188610f8a565b61114760608301876110c7565b61115460808301866110c7565b81810360a08301526111668185610f0f565b905061117560c08301846110f4565b9695505050505050565b600060208201905081810360008301526111998184611042565b905092915050565b60006020820190506111b660008301846110c7565b92915050565b60006111c66111d7565b90506111d28282611347565b919050565b6000604051905090565b600067ffffffffffffffff8211156111fc576111fb611434565b5b61120582611477565b9050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b6000611283826112ac565b915061128e836112ac565b9250828210156112a1576112a0611378565b5b828203905092915050565b6000819050919050565b600063ffffffff82169050919050565b600060ff82169050919050565b82818337600083830152505050565b60005b838110156113005780820151818401526020810190506112e5565b8381111561130f576000848401525b50505050565b6000600282049050600182168061132d57607f821691505b60208210811415611341576113406113a7565b5b50919050565b61135082611477565b810181811067ffffffffffffffff8211171561136f5761136e611434565b5b80604052505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b611491816112ac565b811461149c57600080fd5b50565b6114a8816112b6565b81146114b357600080fd5b50565b6114bf816112c6565b81146114ca57600080fd5b5056fea2646970667358221220f618e67faef44e2a1c7cf1a5d1267a1a5d71cd837021767d1fbbc087274a65b764736f6c63430008070033";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Strategy>() {}));
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
            typedResponse.strategy = (Strategy) eventValues.getNonIndexedValues().get(0);
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
                typedResponse.strategy = (Strategy) eventValues.getNonIndexedValues().get(0);
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

    public RemoteFunctionCall<TransactionReceipt> makeStrategy(BigInteger x, BigInteger y, BigInteger radius, BigInteger startDate, BigInteger endDate, String connectionType, BigInteger priority) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MAKESTRATEGY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(x), 
                new org.web3j.abi.datatypes.generated.Uint32(y), 
                new org.web3j.abi.datatypes.generated.Uint32(radius), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate), 
                new org.web3j.abi.datatypes.Utf8String(connectionType), 
                new org.web3j.abi.datatypes.generated.Uint8(priority)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<Location, BigInteger, BigInteger, String, BigInteger>> strategies(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRATEGIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Location>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple5<Location, BigInteger, BigInteger, String, BigInteger>>(function,
                new Callable<Tuple5<Location, BigInteger, BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple5<Location, BigInteger, BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<Location, BigInteger, BigInteger, String, BigInteger>(
                                (Location) results.get(0), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
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

    public static class Strategy extends DynamicStruct {
        public Location location;

        public BigInteger startDate;

        public BigInteger endDate;

        public String connectionType;

        public BigInteger priority;

        public Strategy(Location location, BigInteger startDate, BigInteger endDate, String connectionType, BigInteger priority) {
            super(location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.Utf8String(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority));
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
        }

        public Strategy(Location location, Uint256 startDate, Uint256 endDate, Utf8String connectionType, Uint8 priority) {
            super(location,startDate,endDate,connectionType,priority);
            this.location = location;
            this.startDate = startDate.getValue();
            this.endDate = endDate.getValue();
            this.connectionType = connectionType.getValue();
            this.priority = priority.getValue();
        }
    }

    public static class AddStrategyEventResponse extends BaseEventResponse {
        public Strategy strategy;
    }
}
