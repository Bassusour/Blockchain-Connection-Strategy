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
    public static final String BINARY = "6080604052600060015534801561001557600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061182d806100656000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063bed2a42e11610066578063bed2a42e14610135578063c5d2215814610153578063d574ea3d1461016f578063d9b72cc3146101a6578063dcdcf7e3146101c25761009e565b80631f7b6d32146100a357806387302037146100c1578063880cdc31146100dd578063893d20e8146100f9578063b49a60bb14610117575b600080fd5b6100ab6101f2565b6040516100b89190610ee2565b60405180910390f35b6100db60048036038101906100d69190610f2e565b6101f8565b005b6100f760048036038101906100f29190610fb9565b610597565b005b610101610668565b60405161010e9190610ff5565b60405180910390f35b61011f610691565b60405161012c91906112c8565b60405180910390f35b61013d61080d565b60405161014a9190610ee2565b60405180910390f35b61016d6004803603810190610168919061130f565b610817565b005b61018960048036038101906101849190610f2e565b610914565b60405161019d9897969594939291906113be565b60405180910390f35b6101c060048036038101906101bb91906114e7565b6109f0565b005b6101dc60048036038101906101d79190610f2e565b610cb0565b6040516101e99190611655565b60405180910390f35b60035481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610286576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027d906116ce565b60405180910390fd5b600081101561029457600080fd5b600080600090505b6002805490508110156102ec5782600282815481106102be576102bd6116ee565b5b906000526020600020906007020160000154036102d9578091505b80806102e49061174c565b91505061029c565b50600260016002805490506103019190611794565b81548110610312576103116116ee565b5b906000526020600020906007020160028281548110610334576103336116ee565b5b90600052602060002090600702016000820154816000015560018201816001016000820160009054906101000a900460030b8160000160006101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160049054906101000a900460030b8160000160046101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060028201548160020155600382015481600301556004820160009054906101000a900460ff168160040160006101000a81548160ff02191690836001811115610447576104466110c8565b5b02179055506004820160019054906101000a900460ff168160040160016101000a81548160ff02191690836002811115610484576104836110c8565b5b0217905550600582015481600501556006820154816006015590505060028054806104b2576104b16117c8565b5b600190038181906000526020600020906007020160008082016000905560018201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600282016000905560038201600090556004820160006101000a81549060ff02191690556004820160016101000a81549060ff021916905560058201600090556006820160009055505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610625576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161061c906116ce565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606002805480602002602001604051908101604052809291908181526020016000905b8282101561080457838290600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff1660018111156107905761078f6110c8565b5b60018111156107a2576107a16110c8565b5b81526020016004820160019054906101000a900460ff1660028111156107cb576107ca6110c8565b5b60028111156107dd576107dc6110c8565b5b815260200160058201548152602001600682015481525050815260200190600101906106b5565b50505050905090565b6000600354905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108a5576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161089c906116ce565b60405180910390fd5b600280549050821080156108ba575060008210155b6108c357600080fd5b80600283815481106108d8576108d76116ee565b5b906000526020600020906007020160040160016101000a81548160ff0219169083600281111561090b5761090a6110c8565b5b02179055505050565b6002818154811061092457600080fd5b9060005260206000209060070201600091509050806000015490806001016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060020154908060030154908060040160009054906101000a900460ff16908060040160019054906101000a900460ff16908060050154908060060154905088565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a7e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a75906116ce565b60405180910390fd5b600060405180606001604052808b60030b81526020018a60030b81526020018963ffffffff16815250905060006040518061010001604052806001548152602001838152602001898152602001888152602001876001811115610ae457610ae36110c8565b5b8152602001866002811115610afc57610afb6110c8565b5b815260200185815260200184815250905060028190806001815401808255809150506001900390600052602060002090600702016000909190919091506000820151816000015560208201518160010160008201518160000160006101000a81548163ffffffff021916908360030b63ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908360030b63ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050604082015181600201556060820151816003015560808201518160040160006101000a81548160ff02191690836001811115610c0857610c076110c8565b5b021790555060a08201518160040160016101000a81548160ff02191690836002811115610c3857610c376110c8565b5b021790555060c0820151816005015560e08201518160060155505060028054905060038190555060016000815480929190610c729061174c565b91905055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050505050505050505050565b610cb8610e27565b60028054905082108015610ccd575060008210155b610cd657600080fd5b60028281548110610cea57610ce96116ee565b5b90600052602060002090600702016040518061010001604052908160008201548152602001600182016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160028201548152602001600382015481526020016004820160009054906101000a900460ff166001811115610dbb57610dba6110c8565b5b6001811115610dcd57610dcc6110c8565b5b81526020016004820160019054906101000a900460ff166002811115610df657610df56110c8565b5b6002811115610e0857610e076110c8565b5b8152602001600582015481526020016006820154815250509050919050565b60405180610100016040528060008152602001610e42610e9c565b8152602001600081526020016000815260200160006001811115610e6957610e686110c8565b5b815260200160006002811115610e8257610e816110c8565b5b815260200160008019168152602001600080191681525090565b6040518060600160405280600060030b8152602001600060030b8152602001600063ffffffff1681525090565b6000819050919050565b610edc81610ec9565b82525050565b6000602082019050610ef76000830184610ed3565b92915050565b600080fd5b610f0b81610ec9565b8114610f1657600080fd5b50565b600081359050610f2881610f02565b92915050565b600060208284031215610f4457610f43610efd565b5b6000610f5284828501610f19565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610f8682610f5b565b9050919050565b610f9681610f7b565b8114610fa157600080fd5b50565b600081359050610fb381610f8d565b92915050565b600060208284031215610fcf57610fce610efd565b5b6000610fdd84828501610fa4565b91505092915050565b610fef81610f7b565b82525050565b600060208201905061100a6000830184610fe6565b92915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b61104581610ec9565b82525050565b60008160030b9050919050565b6110618161104b565b82525050565b600063ffffffff82169050919050565b61108081611067565b82525050565b60608201600082015161109c6000850182611058565b5060208201516110af6020850182611058565b5060408201516110c26040850182611077565b50505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b60028110611108576111076110c8565b5b50565b6000819050611119826110f7565b919050565b60006111298261110b565b9050919050565b6111398161111e565b82525050565b600381106111505761114f6110c8565b5b50565b60008190506111618261113f565b919050565b600061117182611153565b9050919050565b61118181611166565b82525050565b6000819050919050565b61119a81611187565b82525050565b610140820160008201516111b7600085018261103c565b5060208201516111ca6020850182611086565b5060408201516111dd608085018261103c565b5060608201516111f060a085018261103c565b50608082015161120360c0850182611130565b5060a082015161121660e0850182611178565b5060c082015161122a610100850182611191565b5060e082015161123e610120850182611191565b50505050565b600061125083836111a0565b6101408301905092915050565b6000602082019050919050565b600061127582611010565b61127f818561101b565b935061128a8361102c565b8060005b838110156112bb5781516112a28882611244565b97506112ad8361125d565b92505060018101905061128e565b5085935050505092915050565b600060208201905081810360008301526112e2818461126a565b905092915050565b600381106112f757600080fd5b50565b600081359050611309816112ea565b92915050565b6000806040838503121561132657611325610efd565b5b600061133485828601610f19565b9250506020611345858286016112fa565b9150509250929050565b6060820160008201516113656000850182611058565b5060208201516113786020850182611058565b50604082015161138b6040850182611077565b50505050565b61139a8161111e565b82525050565b6113a981611166565b82525050565b6113b881611187565b82525050565b6000610140820190506113d4600083018b610ed3565b6113e1602083018a61134f565b6113ee6080830189610ed3565b6113fb60a0830188610ed3565b61140860c0830187611391565b61141560e08301866113a0565b6114236101008301856113af565b6114316101208301846113af565b9998505050505050505050565b6114478161104b565b811461145257600080fd5b50565b6000813590506114648161143e565b92915050565b61147381611067565b811461147e57600080fd5b50565b6000813590506114908161146a565b92915050565b600281106114a357600080fd5b50565b6000813590506114b581611496565b92915050565b6114c481611187565b81146114cf57600080fd5b50565b6000813590506114e1816114bb565b92915050565b60008060008060008060008060006101208a8c03121561150a57611509610efd565b5b60006115188c828d01611455565b99505060206115298c828d01611455565b985050604061153a8c828d01611481565b975050606061154b8c828d01610f19565b965050608061155c8c828d01610f19565b95505060a061156d8c828d016114a6565b94505060c061157e8c828d016112fa565b93505060e061158f8c828d016114d2565b9250506101006115a18c828d016114d2565b9150509295985092959850929598565b610140820160008201516115c8600085018261103c565b5060208201516115db6020850182611086565b5060408201516115ee608085018261103c565b50606082015161160160a085018261103c565b50608082015161161460c0850182611130565b5060a082015161162760e0850182611178565b5060c082015161163b610100850182611191565b5060e082015161164f610120850182611191565b50505050565b60006101408201905061166b60008301846115b1565b92915050565b600082825260208201905092915050565b7f63616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b60006116b8601383611671565b91506116c382611682565b602082019050919050565b600060208201905081810360008301526116e7816116ab565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061175782610ec9565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82036117895761178861171d565b5b600182019050919050565b600061179f82610ec9565b91506117aa83610ec9565b9250828210156117bd576117bc61171d565b5b828203905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fdfea2646970667358221220096d24716c3f79c6572ad0f1788d2607452b85ee69a7641309956859449b789d64736f6c634300080d0033\n";

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
        List<org.web3j.tx.Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STRATEGYCHANGE_EVENT, transactionReceipt);
        ArrayList<StrategyChangeEventResponse> responses = new ArrayList<StrategyChangeEventResponse>(valueList.size());
        for (org.web3j.tx.Contract.EventValuesWithLog eventValues : valueList) {
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
                org.web3j.tx.Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STRATEGYCHANGE_EVENT, log);
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

        public BigInteger description;

        public BigInteger name;

        public Strategy(BigInteger id, Location location, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority, BigInteger description, BigInteger name) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id),location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.generated.Uint8(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority),new org.web3j.abi.datatypes.generated.Uint256(description),new org.web3j.abi.datatypes.generated.Uint256(name));
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
