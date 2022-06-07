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
    public static final String BINARY = "6080604052600060015534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611823806100656000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063b49a60bb11610066578063b49a60bb14610135578063c5d2215814610153578063d574ea3d1461016f578063d9b72cc3146101a6578063dcdcf7e3146101c25761009e565b80631f7b6d32146100a357806387302037146100c1578063880cdc31146100dd578063893d20e8146100f9578063ab9dbd0714610117575b600080fd5b6100ab6101f2565b6040516100b89190611439565b60405180910390f35b6100db60048036038101906100d69190611048565b6101f8565b005b6100f760048036038101906100f29190610f51565b610598565b005b610101610669565b60405161010e91906113c0565b60405180910390f35b61011f610692565b60405161012c9190611439565b60405180910390f35b61013d61069c565b60405161014a91906113db565b60405180910390f35b61016d60048036038101906101689190611075565b610818565b005b61018960048036038101906101849190611048565b610915565b60405161019d989796959493929190611454565b60405180910390f35b6101c060048036038101906101bb9190610f7e565b6109f1565b005b6101dc60048036038101906101d79190611048565b610ca5565b6040516101e9919061141d565b60405180910390f35b60035481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610286576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027d906113fd565b60405180910390fd5b600081101561029457600080fd5b600080600090505b6002805490508110156102ed5782600282815481106102be576102bd6116d5565b5b90600052602060002090600702016000015414156102da578091505b80806102e5906115ff565b91505061029c565b5060026001600280549050610302919061151e565b81548110610313576103126116d5565b5b906000526020600020906007020160028281548110610335576103346116d5565b5b90600052602060002090600702016000820154816000015560018201816001016000820160009054906101000a900460030b8160000160006101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160049054906101000a900460030b8160000160046101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060028201548160020155600382015481600301556004820160009054906101000a900460ff168160040160006101000a81548160ff0219169083600181111561044857610447611677565b5b02179055506004820160019054906101000a900460ff168160040160016101000a81548160ff0219169083600281111561048557610484611677565b5b0217905550600582015481600501556006820154816006015590505060028054806104b3576104b26116a6565b5b600190038181906000526020600020906007020160008082016000905560018201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600282016000905560038201600090556004820160006101000a81549060ff02191690556004820160016101000a81549060ff021916905560058201600090556006820160009055505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610626576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161061d906113fd565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6000600154905090565b60606002805480602002602001604051908101604052809291908181526020016000905b8282101561080f57838290600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff16600181111561079b5761079a611677565b5b60018111156107ad576107ac611677565b5b81526020016004820160019054906101000a900460ff1660028111156107d6576107d5611677565b5b60028111156107e8576107e7611677565b5b815260200160058201548152602001600682015481525050815260200190600101906106c0565b50505050905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108a6576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161089d906113fd565b60405180910390fd5b600280549050821080156108bb575060008210155b6108c457600080fd5b80600283815481106108d9576108d86116d5565b5b906000526020600020906007020160040160016101000a81548160ff0219169083600281111561090c5761090b611677565b5b02179055505050565b6002818154811061092557600080fd5b9060005260206000209060070201600091509050806000015490806001016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060020154908060030154908060040160009054906101000a900460ff16908060040160019054906101000a900460ff16908060050154908060060154905088565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a7f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a76906113fd565b60405180910390fd5b600060405180606001604052808b60030b81526020018a60030b81526020018963ffffffff16815250905060006040518061010001604052806001548152602001838152602001898152602001888152602001876001811115610ae557610ae4611677565b5b8152602001866002811115610afd57610afc611677565b5b815260200185815260200184815250905060028190806001815401808255809150506001900390600052602060002090600702016000909190919091506000820151816000015560208201518160010160008201518160000160006101000a81548163ffffffff021916908360030b63ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908360030b63ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050604082015181600201556060820151816003015560808201518160040160006101000a81548160ff02191690836001811115610c0957610c08611677565b5b021790555060a08201518160040160016101000a81548160ff02191690836002811115610c3957610c38611677565b5b021790555060c0820151816005015560e08201518160060155505060016000815480929190610c67906115ff565b91905055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050505050505050505050565b610cad610e1c565b60028054905082108015610cc2575060008210155b610ccb57600080fd5b60028281548110610cdf57610cde6116d5565b5b90600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff166001811115610db057610daf611677565b5b6001811115610dc257610dc1611677565b5b81526020016004820160019054906101000a900460ff166002811115610deb57610dea611677565b5b6002811115610dfd57610dfc611677565b5b8152602001600582015481526020016006820154815250509050919050565b60405180610100016040528060008152602001610e37610e91565b8152602001600081526020016000815260200160006001811115610e5e57610e5d611677565b5b815260200160006002811115610e7757610e76611677565b5b815260200160008019168152602001600080191681525090565b6040518060600160405280600060030b8152602001600060030b8152602001600063ffffffff1681525090565b600081359050610ecd8161175a565b92915050565b600081359050610ee281611771565b92915050565b600081359050610ef781611788565b92915050565b600081359050610f0c81611798565b92915050565b600081359050610f21816117a8565b92915050565b600081359050610f36816117bf565b92915050565b600081359050610f4b816117d6565b92915050565b600060208284031215610f6757610f66611704565b5b6000610f7584828501610ebe565b91505092915050565b60008060008060008060008060006101208a8c031215610fa157610fa0611704565b5b6000610faf8c828d01610f12565b9950506020610fc08c828d01610f12565b9850506040610fd18c828d01610f3c565b9750506060610fe28c828d01610f27565b9650506080610ff38c828d01610f27565b95505060a06110048c828d01610ee8565b94505060c06110158c828d01610efd565b93505060e06110268c828d01610ed3565b9250506101006110388c828d01610ed3565b9150509295985092959850929598565b60006020828403121561105e5761105d611704565b5b600061106c84828501610f27565b91505092915050565b6000806040838503121561108c5761108b611704565b5b600061109a85828601610f27565b92505060206110ab85828601610efd565b9150509250929050565b60006110c1838361124b565b6101408301905092915050565b6110d781611552565b82525050565b60006110e8826114e4565b6110f281856114fc565b93506110fd836114d4565b8060005b8381101561112e57815161111588826110b5565b9750611120836114ef565b925050600181019050611101565b5085935050505092915050565b61114481611564565b82525050565b61115381611564565b82525050565b611162816115db565b82525050565b611171816115db565b82525050565b611180816115ed565b82525050565b61118f816115ed565b82525050565b61119e81611594565b82525050565b60006111b160138361150d565b91506111bc82611709565b602082019050919050565b6060820160008201516111dd6000850182611195565b5060208201516111f06020850182611195565b50604082015161120360408501826113b1565b50505050565b60608201600082015161121f6000850182611195565b5060208201516112326020850182611195565b50604082015161124560408501826113b1565b50505050565b610140820160008201516112626000850182611393565b50602082015161127560208501826111c7565b5060408201516112886080850182611393565b50606082015161129b60a0850182611393565b5060808201516112ae60c0850182611159565b5060a08201516112c160e0850182611177565b5060c08201516112d561010085018261113b565b5060e08201516112e961012085018261113b565b50505050565b610140820160008201516113066000850182611393565b50602082015161131960208501826111c7565b50604082015161132c6080850182611393565b50606082015161133f60a0850182611393565b50608082015161135260c0850182611159565b5060a082015161136560e0850182611177565b5060c082015161137961010085018261113b565b5060e082015161138d61012085018261113b565b50505050565b61139c816115c1565b82525050565b6113ab816115c1565b82525050565b6113ba816115cb565b82525050565b60006020820190506113d560008301846110ce565b92915050565b600060208201905081810360008301526113f581846110dd565b905092915050565b60006020820190508181036000830152611416816111a4565b9050919050565b60006101408201905061143360008301846112ef565b92915050565b600060208201905061144e60008301846113a2565b92915050565b60006101408201905061146a600083018b6113a2565b611477602083018a611209565b61148460808301896113a2565b61149160a08301886113a2565b61149e60c0830187611168565b6114ab60e0830186611186565b6114b961010083018561114a565b6114c761012083018461114a565b9998505050505050505050565b6000819050602082019050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b6000611529826115c1565b9150611534836115c1565b92508282101561154757611546611648565b5b828203905092915050565b600061155d826115a1565b9050919050565b6000819050919050565b600081905061157c82611732565b919050565b600081905061158f82611746565b919050565b60008160030b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600063ffffffff82169050919050565b60006115e68261156e565b9050919050565b60006115f882611581565b9050919050565b600061160a826115c1565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82141561163d5761163c611648565b5b600182019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b7f63616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b6002811061174357611742611677565b5b50565b6003811061175757611756611677565b5b50565b61176381611552565b811461176e57600080fd5b50565b61177a81611564565b811461178557600080fd5b50565b6002811061179557600080fd5b50565b600381106117a557600080fd5b50565b6117b181611594565b81146117bc57600080fd5b50565b6117c8816115c1565b81146117d357600080fd5b50565b6117df816115cb565b81146117ea57600080fd5b5056fea2646970667358221220ba22ef01caae9242c18f409cf2cdac80f4fe12ec0625f66044a3ea9bc71b645b64736f6c63430008070033";

    public static final String FUNC_CHANGEPRIORITY = "changePriority";

    public static final String FUNC_DELETESTRATEGY = "deleteStrategy";

    public static final String FUNC_GETID = "getID";

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

    public RemoteFunctionCall<BigInteger> getID() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETID, 
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
