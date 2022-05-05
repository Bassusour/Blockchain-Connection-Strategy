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
import org.web3j.tuples.generated.Tuple7;
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
    public static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611700806100606000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063bed2a42e11610066578063bed2a42e14610135578063c5d2215814610153578063d574ea3d1461016f578063d9b72cc3146101a5578063dcdcf7e3146101c15761009e565b80631f7b6d32146100a357806387302037146100c1578063880cdc31146100dd578063893d20e8146100f9578063b49a60bb14610117575b600080fd5b6100ab6101f1565b6040516100b891906113df565b60405180910390f35b6100db60048036038101906100d69190610fa5565b6101f7565b005b6100f760048036038101906100f29190610eae565b610538565b005b610101610609565b60405161010e91906112f5565b60405180910390f35b61011f610632565b60405161012c9190611310565b60405180910390f35b61013d6107a3565b60405161014a91906113df565b60405180910390f35b61016d60048036038101906101689190610fd2565b6107ad565b005b61018960048036038101906101849190610fa5565b6108aa565b60405161019c9796959493929190611352565b60405180910390f35b6101bf60048036038101906101ba9190610edb565b610980565b005b6101db60048036038101906101d69190610fa5565b610c15565b6040516101e891906113c3565b60405180910390f35b60025481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610285576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027c90611332565b60405180910390fd5b6001805490508110801561029a575060008110155b6102a357600080fd5b60018080805490506102b59190611444565b815481106102c6576102c56115b2565b5b9060005260206000209060060201600182815481106102e8576102e76115b2565b5b906000526020600020906006020160008201816000016000820160009054906101000a900460030b8160000160006101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160049054906101000a900460030b8160000160046101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060018201548160010155600282015481600201556003820160009054906101000a900460ff168160030160006101000a81548160ff021916908360018111156103f1576103f0611554565b5b02179055506003820160019054906101000a900460ff168160030160016101000a81548160ff0219169083600281111561042e5761042d611554565b5b02179055506004820154816004015560058201548160050155905050600180548061045c5761045b611583565b5b60019003818190600052602060002090600602016000808201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600182016000905560028201600090556003820160006101000a81549060ff02191690556003820160016101000a81549060ff021916905560048201600090556005820160009055505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a150565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146105c6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105bd90611332565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606001805480602002602001604051908101604052809291908181526020016000905b8282101561079a57838290600052602060002090600602016040518060e0016040529081600082016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff16600181111561072657610725611554565b5b600181111561073857610737611554565b5b81526020016003820160019054906101000a900460ff16600281111561076157610760611554565b5b600281111561077357610772611554565b5b81526020016004820154815260200160058201548152505081526020019060010190610656565b50505050905090565b6000600254905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461083b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161083290611332565b60405180910390fd5b60018054905082108015610850575060008210155b61085957600080fd5b806001838154811061086e5761086d6115b2565b5b906000526020600020906006020160030160016101000a81548160ff021916908360028111156108a1576108a0611554565b5b02179055505050565b600181815481106108ba57600080fd5b9060005260206000209060060201600091509050806000016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060010154908060020154908060030160009054906101000a900460ff16908060030160019054906101000a900460ff16908060040154908060050154905087565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a0e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a0590611332565b60405180910390fd5b600060405180606001604052808b60030b81526020018a60030b81526020018963ffffffff16815250905060006040518060e00160405280838152602001898152602001888152602001876001811115610a6b57610a6a611554565b5b8152602001866002811115610a8357610a82611554565b5b8152602001858152602001848152509050600181908060018154018082558091505060019003906000526020600020906006020160009091909190915060008201518160000160008201518160000160006101000a81548163ffffffff021916908360030b63ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908360030b63ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050602082015181600101556040820151816002015560608201518160030160006101000a81548160ff02191690836001811115610b8557610b84611554565b5b021790555060808201518160030160016101000a81548160ff02191690836002811115610bb557610bb4611554565b5b021790555060a0820151816004015560c0820151816005015550506001805490506002819055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050505050505050505050565b610c1d610d81565b60018054905082108015610c32575060008210155b610c3b57600080fd5b60018281548110610c4f57610c4e6115b2565b5b90600052602060002090600602016040518060e0016040529081600082016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff166001811115610d1557610d14611554565b5b6001811115610d2757610d26611554565b5b81526020016003820160019054906101000a900460ff166002811115610d5057610d4f611554565b5b6002811115610d6257610d61611554565b5b8152602001600482015481526020016005820154815250509050919050565b6040518060e00160405280610d94610dee565b8152602001600081526020016000815260200160006001811115610dbb57610dba611554565b5b815260200160006002811115610dd457610dd3611554565b5b815260200160008019168152602001600080191681525090565b6040518060600160405280600060030b8152602001600060030b8152602001600063ffffffff1681525090565b600081359050610e2a81611637565b92915050565b600081359050610e3f8161164e565b92915050565b600081359050610e5481611665565b92915050565b600081359050610e6981611675565b92915050565b600081359050610e7e81611685565b92915050565b600081359050610e938161169c565b92915050565b600081359050610ea8816116b3565b92915050565b600060208284031215610ec457610ec36115e1565b5b6000610ed284828501610e1b565b91505092915050565b60008060008060008060008060006101208a8c031215610efe57610efd6115e1565b5b6000610f0c8c828d01610e6f565b9950506020610f1d8c828d01610e6f565b9850506040610f2e8c828d01610e99565b9750506060610f3f8c828d01610e84565b9650506080610f508c828d01610e84565b95505060a0610f618c828d01610e45565b94505060c0610f728c828d01610e5a565b93505060e0610f838c828d01610e30565b925050610100610f958c828d01610e30565b9150509295985092959850929598565b600060208284031215610fbb57610fba6115e1565b5b6000610fc984828501610e84565b91505092915050565b60008060408385031215610fe957610fe86115e1565b5b6000610ff785828601610e84565b925050602061100885828601610e5a565b9150509250929050565b600061101e83836111a8565b6101208301905092915050565b61103481611478565b82525050565b60006110458261140a565b61104f8185611422565b935061105a836113fa565b8060005b8381101561108b5781516110728882611012565b975061107d83611415565b92505060018101905061105e565b5085935050505092915050565b6110a18161148a565b82525050565b6110b08161148a565b82525050565b6110bf81611501565b82525050565b6110ce81611501565b82525050565b6110dd81611513565b82525050565b6110ec81611513565b82525050565b6110fb816114ba565b82525050565b600061110e601383611433565b9150611119826115e6565b602082019050919050565b60608201600082015161113a60008501826110f2565b50602082015161114d60208501826110f2565b50604082015161116060408501826112e6565b50505050565b60608201600082015161117c60008501826110f2565b50602082015161118f60208501826110f2565b5060408201516111a260408501826112e6565b50505050565b610120820160008201516111bf6000850182611124565b5060208201516111d260608501826112c8565b5060408201516111e560808501826112c8565b5060608201516111f860a08501826110b6565b50608082015161120b60c08501826110d4565b5060a082015161121e60e0850182611098565b5060c0820151611232610100850182611098565b50505050565b6101208201600082015161124f6000850182611124565b50602082015161126260608501826112c8565b50604082015161127560808501826112c8565b50606082015161128860a08501826110b6565b50608082015161129b60c08501826110d4565b5060a08201516112ae60e0850182611098565b5060c08201516112c2610100850182611098565b50505050565b6112d1816114e7565b82525050565b6112e0816114e7565b82525050565b6112ef816114f1565b82525050565b600060208201905061130a600083018461102b565b92915050565b6000602082019050818103600083015261132a818461103a565b905092915050565b6000602082019050818103600083015261134b81611101565b9050919050565b600061012082019050611368600083018a611166565b61137560608301896112d7565b61138260808301886112d7565b61138f60a08301876110c5565b61139c60c08301866110e3565b6113a960e08301856110a7565b6113b76101008301846110a7565b98975050505050505050565b6000610120820190506113d96000830184611238565b92915050565b60006020820190506113f460008301846112d7565b92915050565b6000819050602082019050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600061144f826114e7565b915061145a836114e7565b92508282101561146d5761146c611525565b5b828203905092915050565b6000611483826114c7565b9050919050565b6000819050919050565b60008190506114a28261160f565b919050565b60008190506114b582611623565b919050565b60008160030b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600063ffffffff82169050919050565b600061150c82611494565b9050919050565b600061151e826114a7565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b600080fd5b7f63616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b600281106116205761161f611554565b5b50565b6003811061163457611633611554565b5b50565b61164081611478565b811461164b57600080fd5b50565b6116578161148a565b811461166257600080fd5b50565b6002811061167257600080fd5b50565b6003811061168257600080fd5b50565b61168e816114ba565b811461169957600080fd5b50565b6116a5816114e7565b81146116b057600080fd5b50565b6116bc816114f1565b81146116c757600080fd5b5056fea2646970667358221220ea3a71a1cd8782c8dbc9258b550da85d50e22d08f6be0df1c64d4f3e2205a8a164736f6c63430008070033";

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

    public RemoteFunctionCall<Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>> strategies(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRATEGIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Location>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>>(function,
                new Callable<Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>>() {
                    @Override
                    public Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, byte[], byte[]>(
                                (Location) results.get(0), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (byte[]) results.get(5).getValue(), 
                                (byte[]) results.get(6).getValue());
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
        public Location location;

        public BigInteger startDate;

        public BigInteger endDate;

        public BigInteger connectionType;

        public BigInteger priority;

        public byte[] description;

        public byte[] name;

        public Strategy(Location location, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority, byte[] description, byte[] name) {
            super(location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.generated.Uint8(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority),new org.web3j.abi.datatypes.generated.Bytes32(description),new org.web3j.abi.datatypes.generated.Bytes32(name));
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
            this.description = description;
            this.name = name;
        }

        public Strategy(Location location, Uint256 startDate, Uint256 endDate, Uint8 connectionType, Uint8 priority, Bytes32 description, Bytes32 name) {
            super(location,startDate,endDate,connectionType,priority,description,name);
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