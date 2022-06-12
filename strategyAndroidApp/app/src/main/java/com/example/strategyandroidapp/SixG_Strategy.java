package com.example.strategyandroidapp;

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
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int32;
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
import org.web3j.tuples.generated.Tuple8;
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
    public static final String BINARY = "6080604052600060015534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506117f0806100656000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063b49a60bb11610066578063b49a60bb1461010c578063c5d221581461012a578063d574ea3d14610146578063d9b72cc31461017d578063dcdcf7e31461019957610093565b80631f7b6d321461009857806387302037146100b6578063880cdc31146100d2578063893d20e8146100ee575b600080fd5b6100a06101c9565b6040516100ad9190611406565b60405180910390f35b6100d060048036038101906100cb9190611015565b6101cf565b005b6100ec60048036038101906100e79190610f1e565b61056f565b005b6100f6610640565b604051610103919061138d565b60405180910390f35b610114610669565b60405161012191906113a8565b60405180910390f35b610144600480360381019061013f9190611042565b6107e5565b005b610160600480360381019061015b9190611015565b6108e2565b604051610174989796959493929190611421565b60405180910390f35b61019760048036038101906101929190610f4b565b6109be565b005b6101b360048036038101906101ae9190611015565b610c72565b6040516101c091906113ea565b60405180910390f35b60035481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461025d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610254906113ca565b60405180910390fd5b600081101561026b57600080fd5b600080600090505b6002805490508110156102c4578260028281548110610295576102946116a2565b5b90600052602060002090600702016000015414156102b1578091505b80806102bc906115cc565b915050610273565b50600260016002805490506102d991906114eb565b815481106102ea576102e96116a2565b5b90600052602060002090600702016002828154811061030c5761030b6116a2565b5b90600052602060002090600702016000820154816000015560018201816001016000820160009054906101000a900460030b8160000160006101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160049054906101000a900460030b8160000160046101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060028201548160020155600382015481600301556004820160009054906101000a900460ff168160040160006101000a81548160ff0219169083600181111561041f5761041e611644565b5b02179055506004820160019054906101000a900460ff168160040160016101000a81548160ff0219169083600281111561045c5761045b611644565b5b02179055506005820154816005015560068201548160060155905050600280548061048a57610489611673565b5b600190038181906000526020600020906007020160008082016000905560018201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600282016000905560038201600090556004820160006101000a81549060ff02191690556004820160016101000a81549060ff021916905560058201600090556006820160009055505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146105fd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105f4906113ca565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606002805480602002602001604051908101604052809291908181526020016000905b828210156107dc57838290600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff16600181111561076857610767611644565b5b600181111561077a57610779611644565b5b81526020016004820160019054906101000a900460ff1660028111156107a3576107a2611644565b5b60028111156107b5576107b4611644565b5b8152602001600582015481526020016006820154815250508152602001906001019061068d565b50505050905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610873576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161086a906113ca565b60405180910390fd5b60028054905082108015610888575060008210155b61089157600080fd5b80600283815481106108a6576108a56116a2565b5b906000526020600020906007020160040160016101000a81548160ff021916908360028111156108d9576108d8611644565b5b02179055505050565b600281815481106108f257600080fd5b9060005260206000209060070201600091509050806000015490806001016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060020154908060030154908060040160009054906101000a900460ff16908060040160019054906101000a900460ff16908060050154908060060154905088565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a4c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a43906113ca565b60405180910390fd5b600060405180606001604052808b60030b81526020018a60030b81526020018963ffffffff16815250905060006040518061010001604052806001548152602001838152602001898152602001888152602001876001811115610ab257610ab1611644565b5b8152602001866002811115610aca57610ac9611644565b5b815260200185815260200184815250905060028190806001815401808255809150506001900390600052602060002090600702016000909190919091506000820151816000015560208201518160010160008201518160000160006101000a81548163ffffffff021916908360030b63ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908360030b63ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050604082015181600201556060820151816003015560808201518160040160006101000a81548160ff02191690836001811115610bd657610bd5611644565b5b021790555060a08201518160040160016101000a81548160ff02191690836002811115610c0657610c05611644565b5b021790555060c0820151816005015560e08201518160060155505060016000815480929190610c34906115cc565b91905055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050505050505050505050565b610c7a610de9565b60028054905082108015610c8f575060008210155b610c9857600080fd5b60028281548110610cac57610cab6116a2565b5b90600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff166001811115610d7d57610d7c611644565b5b6001811115610d8f57610d8e611644565b5b81526020016004820160019054906101000a900460ff166002811115610db857610db7611644565b5b6002811115610dca57610dc9611644565b5b8152602001600582015481526020016006820154815250509050919050565b60405180610100016040528060008152602001610e04610e5e565b8152602001600081526020016000815260200160006001811115610e2b57610e2a611644565b5b815260200160006002811115610e4457610e43611644565b5b815260200160008019168152602001600080191681525090565b6040518060600160405280600060030b8152602001600060030b8152602001600063ffffffff1681525090565b600081359050610e9a81611727565b92915050565b600081359050610eaf8161173e565b92915050565b600081359050610ec481611755565b92915050565b600081359050610ed981611765565b92915050565b600081359050610eee81611775565b92915050565b600081359050610f038161178c565b92915050565b600081359050610f18816117a3565b92915050565b600060208284031215610f3457610f336116d1565b5b6000610f4284828501610e8b565b91505092915050565b60008060008060008060008060006101208a8c031215610f6e57610f6d6116d1565b5b6000610f7c8c828d01610edf565b9950506020610f8d8c828d01610edf565b9850506040610f9e8c828d01610f09565b9750506060610faf8c828d01610ef4565b9650506080610fc08c828d01610ef4565b95505060a0610fd18c828d01610eb5565b94505060c0610fe28c828d01610eca565b93505060e0610ff38c828d01610ea0565b9250506101006110058c828d01610ea0565b9150509295985092959850929598565b60006020828403121561102b5761102a6116d1565b5b600061103984828501610ef4565b91505092915050565b60008060408385031215611059576110586116d1565b5b600061106785828601610ef4565b925050602061107885828601610eca565b9150509250929050565b600061108e8383611218565b6101408301905092915050565b6110a48161151f565b82525050565b60006110b5826114b1565b6110bf81856114c9565b93506110ca836114a1565b8060005b838110156110fb5781516110e28882611082565b97506110ed836114bc565b9250506001810190506110ce565b5085935050505092915050565b61111181611531565b82525050565b61112081611531565b82525050565b61112f816115a8565b82525050565b61113e816115a8565b82525050565b61114d816115ba565b82525050565b61115c816115ba565b82525050565b61116b81611561565b82525050565b600061117e6013836114da565b9150611189826116d6565b602082019050919050565b6060820160008201516111aa6000850182611162565b5060208201516111bd6020850182611162565b5060408201516111d0604085018261137e565b50505050565b6060820160008201516111ec6000850182611162565b5060208201516111ff6020850182611162565b506040820151611212604085018261137e565b50505050565b6101408201600082015161122f6000850182611360565b5060208201516112426020850182611194565b5060408201516112556080850182611360565b50606082015161126860a0850182611360565b50608082015161127b60c0850182611126565b5060a082015161128e60e0850182611144565b5060c08201516112a2610100850182611108565b5060e08201516112b6610120850182611108565b50505050565b610140820160008201516112d36000850182611360565b5060208201516112e66020850182611194565b5060408201516112f96080850182611360565b50606082015161130c60a0850182611360565b50608082015161131f60c0850182611126565b5060a082015161133260e0850182611144565b5060c0820151611346610100850182611108565b5060e082015161135a610120850182611108565b50505050565b6113698161158e565b82525050565b6113788161158e565b82525050565b61138781611598565b82525050565b60006020820190506113a2600083018461109b565b92915050565b600060208201905081810360008301526113c281846110aa565b905092915050565b600060208201905081810360008301526113e381611171565b9050919050565b60006101408201905061140060008301846112bc565b92915050565b600060208201905061141b600083018461136f565b92915050565b600061014082019050611437600083018b61136f565b611444602083018a6111d6565b611451608083018961136f565b61145e60a083018861136f565b61146b60c0830187611135565b61147860e0830186611153565b611486610100830185611117565b611494610120830184611117565b9998505050505050505050565b6000819050602082019050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b60006114f68261158e565b91506115018361158e565b92508282101561151457611513611615565b5b828203905092915050565b600061152a8261156e565b9050919050565b6000819050919050565b6000819050611549826116ff565b919050565b600081905061155c82611713565b919050565b60008160030b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600063ffffffff82169050919050565b60006115b38261153b565b9050919050565b60006115c58261154e565b9050919050565b60006115d78261158e565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561160a57611609611615565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b7f63616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b600281106117105761170f611644565b5b50565b6003811061172457611723611644565b5b50565b6117308161151f565b811461173b57600080fd5b50565b61174781611531565b811461175257600080fd5b50565b6002811061176257600080fd5b50565b6003811061177257600080fd5b50565b61177e81611561565b811461178957600080fd5b50565b6117958161158e565b81146117a057600080fd5b50565b6117ac81611598565b81146117b757600080fd5b5056fea264697066735822122077fa3dd1b3ba41a10dffffd4b1b037c3662c64235e55ff308c322ed33bb0adc764736f6c63430008070033";

    public static final String FUNC_CHANGEPRIORITY = "changePriority";

    public static final String FUNC_DELETESTRATEGY = "deleteStrategy";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETSTRATEGIES = "getStrategies";

    public static final String FUNC_GETSTRATEGYFROMINDEX = "getStrategyFromIndex";

    public static final String FUNC_LENGTH = "length";

    public static final String FUNC_MAKESTRATEGY = "makeStrategy";

    public static final String FUNC_STRATEGIES = "strategies";

    public static final String FUNC_UPDATEOWNER = "updateOwner";

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

    public RemoteFunctionCall<TransactionReceipt> deleteStrategy(BigInteger id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETESTRATEGY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteFunctionCall<TransactionReceipt> makeStrategy(BigInteger x, BigInteger y, BigInteger radius, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority, byte[] description, byte[] name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MAKESTRATEGY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int32(x), 
                new org.web3j.abi.datatypes.generated.Int32(y), 
                new org.web3j.abi.datatypes.generated.Uint32(radius), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate), 
                new org.web3j.abi.datatypes.generated.Uint8(connectionType), 
                new org.web3j.abi.datatypes.generated.Uint8(priority), 
                new org.web3j.abi.datatypes.generated.Bytes32(description), 
                new org.web3j.abi.datatypes.generated.Bytes32(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple8<BigInteger, Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>> strategies(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRATEGIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Location>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple8<BigInteger, Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>>(function,
                new Callable<Tuple8<BigInteger, Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>>() {
                    @Override
                    public Tuple8<BigInteger, Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<BigInteger, Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (Location) results.get(1), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (byte[]) results.get(6).getValue(), 
                                (byte[]) results.get(7).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateOwner(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
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

    public static RemoteCall<SixG_Strategy> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SixG_Strategy.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SixG_Strategy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SixG_Strategy.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
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
            super(new org.web3j.abi.datatypes.generated.Int32(x),new org.web3j.abi.datatypes.generated.Int32(y),new org.web3j.abi.datatypes.generated.Uint32(radius));
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public Location(Int32 x, Int32 y, Uint32 radius) {
            super(x,y,radius);
            this.x = x.getValue();
            this.y = y.getValue();
            this.radius = radius.getValue();
        }
    }

    public static class Strategy extends StaticStruct {
        public BigInteger id;

        public Location location;

        public BigInteger startDate;

        public BigInteger endDate;

        public BigInteger connectionType;

        public BigInteger priority;

        public BigInteger description;

        public BigInteger name;

        public Strategy(BigInteger id, Location location, BigInteger startDate, BigInteger endDate,
                        BigInteger connectionType, BigInteger priority, BigInteger description, BigInteger name) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id),location,new org.web3j.abi.datatypes.generated.Uint256(startDate),
                    new org.web3j.abi.datatypes.generated.Uint256(endDate),
                    new org.web3j.abi.datatypes.generated.Uint8(connectionType),
                    new org.web3j.abi.datatypes.generated.Uint8(priority),
                    new org.web3j.abi.datatypes.generated.Uint256(description),
                    new org.web3j.abi.datatypes.generated.Uint256(name));
            this.id = id;
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
            this.description = description;
            this.name = name;
        }

        public Strategy(Uint256 id, Location location, Uint256 startDate, Uint256 endDate, Uint8 connectionType, Uint8 priority, Uint256 description, Uint256 name) {
            super(id,location,startDate,endDate,connectionType,priority,description,name);
            this.id = id.getValue();
            this.location = location;
            this.startDate = startDate.getValue();
            this.endDate = endDate.getValue();
            this.connectionType = connectionType.getValue();
            this.priority = priority.getValue();
            this.description = description.getValue();
            this.name = name.getValue();
        }
    }

    public static class StrategyChangeEventResponse extends BaseEventResponse {
    }
}
