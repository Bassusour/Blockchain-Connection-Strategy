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
    public static final String BINARY = "6080604052600060015534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061182f806100656000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063bed2a42e11610066578063bed2a42e14610135578063c5d2215814610153578063d574ea3d1461016f578063d9b72cc3146101a6578063dcdcf7e3146101c25761009e565b80631f7b6d32146100a357806387302037146100c1578063880cdc31146100dd578063893d20e8146100f9578063b49a60bb14610117575b600080fd5b6100ab6101f2565b6040516100b89190611445565b60405180910390f35b6100db60048036038101906100d69190611054565b6101f8565b005b6100f760048036038101906100f29190610f5d565b610598565b005b610101610669565b60405161010e91906113cc565b60405180910390f35b61011f610692565b60405161012c91906113e7565b60405180910390f35b61013d61080e565b60405161014a9190611445565b60405180910390f35b61016d60048036038101906101689190611081565b610818565b005b61018960048036038101906101849190611054565b610915565b60405161019d989796959493929190611460565b60405180910390f35b6101c060048036038101906101bb9190610f8a565b6109f1565b005b6101dc60048036038101906101d79190611054565b610cb1565b6040516101e99190611429565b60405180910390f35b60035481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610286576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027d90611409565b60405180910390fd5b600081101561029457600080fd5b600080600090505b6002805490508110156102ed5782600282815481106102be576102bd6116e1565b5b90600052602060002090600702016000015414156102da578091505b80806102e59061160b565b91505061029c565b5060026001600280549050610302919061152a565b81548110610313576103126116e1565b5b906000526020600020906007020160028281548110610335576103346116e1565b5b90600052602060002090600702016000820154816000015560018201816001016000820160009054906101000a900460030b8160000160006101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160049054906101000a900460030b8160000160046101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060028201548160020155600382015481600301556004820160009054906101000a900460ff168160040160006101000a81548160ff0219169083600181111561044857610447611683565b5b02179055506004820160019054906101000a900460ff168160040160016101000a81548160ff0219169083600281111561048557610484611683565b5b0217905550600582015481600501556006820154816006015590505060028054806104b3576104b26116b2565b5b600190038181906000526020600020906007020160008082016000905560018201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600282016000905560038201600090556004820160006101000a81549060ff02191690556004820160016101000a81549060ff021916905560058201600090556006820160009055505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610626576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161061d90611409565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606002805480602002602001604051908101604052809291908181526020016000905b8282101561080557838290600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff16600181111561079157610790611683565b5b60018111156107a3576107a2611683565b5b81526020016004820160019054906101000a900460ff1660028111156107cc576107cb611683565b5b60028111156107de576107dd611683565b5b815260200160058201548152602001600682015481525050815260200190600101906106b6565b50505050905090565b6000600354905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108a6576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161089d90611409565b60405180910390fd5b600280549050821080156108bb575060008210155b6108c457600080fd5b80600283815481106108d9576108d86116e1565b5b906000526020600020906007020160040160016101000a81548160ff0219169083600281111561090c5761090b611683565b5b02179055505050565b6002818154811061092557600080fd5b9060005260206000209060070201600091509050806000015490806001016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060020154908060030154908060040160009054906101000a900460ff16908060040160019054906101000a900460ff16908060050154908060060154905088565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a7f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a7690611409565b60405180910390fd5b600060405180606001604052808b60030b81526020018a60030b81526020018963ffffffff16815250905060006040518061010001604052806001548152602001838152602001898152602001888152602001876001811115610ae557610ae4611683565b5b8152602001866002811115610afd57610afc611683565b5b815260200185815260200184815250905060028190806001815401808255809150506001900390600052602060002090600702016000909190919091506000820151816000015560208201518160010160008201518160000160006101000a81548163ffffffff021916908360030b63ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908360030b63ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050604082015181600201556060820151816003015560808201518160040160006101000a81548160ff02191690836001811115610c0957610c08611683565b5b021790555060a08201518160040160016101000a81548160ff02191690836002811115610c3957610c38611683565b5b021790555060c0820151816005015560e08201518160060155505060028054905060038190555060016000815480929190610c739061160b565b91905055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050505050505050505050565b610cb9610e28565b60028054905082108015610cce575060008210155b610cd757600080fd5b60028281548110610ceb57610cea6116e1565b5b90600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff166001811115610dbc57610dbb611683565b5b6001811115610dce57610dcd611683565b5b81526020016004820160019054906101000a900460ff166002811115610df757610df6611683565b5b6002811115610e0957610e08611683565b5b8152602001600582015481526020016006820154815250509050919050565b60405180610100016040528060008152602001610e43610e9d565b8152602001600081526020016000815260200160006001811115610e6a57610e69611683565b5b815260200160006002811115610e8357610e82611683565b5b815260200160008019168152602001600080191681525090565b6040518060600160405280600060030b8152602001600060030b8152602001600063ffffffff1681525090565b600081359050610ed981611766565b92915050565b600081359050610eee8161177d565b92915050565b600081359050610f0381611794565b92915050565b600081359050610f18816117a4565b92915050565b600081359050610f2d816117b4565b92915050565b600081359050610f42816117cb565b92915050565b600081359050610f57816117e2565b92915050565b600060208284031215610f7357610f72611710565b5b6000610f8184828501610eca565b91505092915050565b60008060008060008060008060006101208a8c031215610fad57610fac611710565b5b6000610fbb8c828d01610f1e565b9950506020610fcc8c828d01610f1e565b9850506040610fdd8c828d01610f48565b9750506060610fee8c828d01610f33565b9650506080610fff8c828d01610f33565b95505060a06110108c828d01610ef4565b94505060c06110218c828d01610f09565b93505060e06110328c828d01610edf565b9250506101006110448c828d01610edf565b9150509295985092959850929598565b60006020828403121561106a57611069611710565b5b600061107884828501610f33565b91505092915050565b6000806040838503121561109857611097611710565b5b60006110a685828601610f33565b92505060206110b785828601610f09565b9150509250929050565b60006110cd8383611257565b6101408301905092915050565b6110e38161155e565b82525050565b60006110f4826114f0565b6110fe8185611508565b9350611109836114e0565b8060005b8381101561113a57815161112188826110c1565b975061112c836114fb565b92505060018101905061110d565b5085935050505092915050565b61115081611570565b82525050565b61115f81611570565b82525050565b61116e816115e7565b82525050565b61117d816115e7565b82525050565b61118c816115f9565b82525050565b61119b816115f9565b82525050565b6111aa816115a0565b82525050565b60006111bd601383611519565b91506111c882611715565b602082019050919050565b6060820160008201516111e960008501826111a1565b5060208201516111fc60208501826111a1565b50604082015161120f60408501826113bd565b50505050565b60608201600082015161122b60008501826111a1565b50602082015161123e60208501826111a1565b50604082015161125160408501826113bd565b50505050565b6101408201600082015161126e600085018261139f565b50602082015161128160208501826111d3565b506040820151611294608085018261139f565b5060608201516112a760a085018261139f565b5060808201516112ba60c0850182611165565b5060a08201516112cd60e0850182611183565b5060c08201516112e1610100850182611147565b5060e08201516112f5610120850182611147565b50505050565b61014082016000820151611312600085018261139f565b50602082015161132560208501826111d3565b506040820151611338608085018261139f565b50606082015161134b60a085018261139f565b50608082015161135e60c0850182611165565b5060a082015161137160e0850182611183565b5060c0820151611385610100850182611147565b5060e0820151611399610120850182611147565b50505050565b6113a8816115cd565b82525050565b6113b7816115cd565b82525050565b6113c6816115d7565b82525050565b60006020820190506113e160008301846110da565b92915050565b6000602082019050818103600083015261140181846110e9565b905092915050565b60006020820190508181036000830152611422816111b0565b9050919050565b60006101408201905061143f60008301846112fb565b92915050565b600060208201905061145a60008301846113ae565b92915050565b600061014082019050611476600083018b6113ae565b611483602083018a611215565b61149060808301896113ae565b61149d60a08301886113ae565b6114aa60c0830187611174565b6114b760e0830186611192565b6114c5610100830185611156565b6114d3610120830184611156565b9998505050505050505050565b6000819050602082019050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b6000611535826115cd565b9150611540836115cd565b92508282101561155357611552611654565b5b828203905092915050565b6000611569826115ad565b9050919050565b6000819050919050565b60008190506115888261173e565b919050565b600081905061159b82611752565b919050565b60008160030b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600063ffffffff82169050919050565b60006115f28261157a565b9050919050565b60006116048261158d565b9050919050565b6000611616826115cd565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561164957611648611654565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b7f63616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b6002811061174f5761174e611683565b5b50565b6003811061176357611762611683565b5b50565b61176f8161155e565b811461177a57600080fd5b50565b61178681611570565b811461179157600080fd5b50565b600281106117a157600080fd5b50565b600381106117b157600080fd5b50565b6117bd816115a0565b81146117c857600080fd5b50565b6117d4816115cd565b81146117df57600080fd5b50565b6117eb816115d7565b81146117f657600080fd5b5056fea2646970667358221220fbe0edd4a3e5c03b7c89e90f52a68fb8719677a5915000b1a8a0fc43d7af5c6a64736f6c63430008070033";

    public static final String FUNC_CHANGEPRIORITY = "changePriority";

    public static final String FUNC_DELETESTRATEGY = "deleteStrategy";

    public static final String FUNC_GETLENGHT = "getLenght";

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

    public RemoteFunctionCall<BigInteger> getLenght() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENGHT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

        public byte[] description;

        public byte[] name;

        public Strategy(BigInteger id, Location location, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority, byte[] description, byte[] name) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id),location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.generated.Uint8(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority),new org.web3j.abi.datatypes.generated.Bytes32(description),new org.web3j.abi.datatypes.generated.Bytes32(name));
            this.id = id;
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
            this.description = description;
            this.name = name;
        }

        public Strategy(Uint256 id, Location location, Uint256 startDate, Uint256 endDate, Uint8 connectionType, Uint8 priority, Bytes32 description, Bytes32 name) {
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
