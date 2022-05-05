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
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
    public static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611ea7806100606000396000f3fe608060405234801561001057600080fd5b506004361061009e5760003560e01c8063b49a60bb11610066578063b49a60bb14610133578063bed2a42e14610151578063c5d221581461016f578063d574ea3d1461018b578063dcdcf7e3146101c15761009e565b80630bc6c7bb146100a35780631f7b6d32146100bf57806387302037146100dd578063880cdc31146100f9578063893d20e814610115575b600080fd5b6100bd60048036038101906100b89190611432565b6101f1565b005b6100c76104ac565b6040516100d49190611a12565b60405180910390f35b6100f760048036038101906100f29190611534565b6104b2565b005b610113600480360381019061010e9190611405565b61082f565b005b61011d610900565b60405161012a9190611914565b60405180910390f35b61013b610929565b604051610148919061192f565b60405180910390f35b610159610baa565b6040516101669190611a12565b60405180910390f35b61018960048036038101906101849190611561565b610bb4565b005b6101a560048036038101906101a09190611534565b610cb1565b6040516101b89796959493929190611971565b60405180910390f35b6101db60048036038101906101d69190611534565b610e97565b6040516101e891906119f0565b60405180910390f35b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461027f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027690611951565b60405180910390fd5b600060405180606001604052808b60030b81526020018a60030b81526020018963ffffffff16815250905060006040518060e001604052808381526020018981526020018881526020018760018111156102dc576102db611c94565b5b81526020018660028111156102f4576102f3611c94565b5b8152602001858152602001848152509050600181908060018154018082558091505060019003906000526020600020906006020160009091909190915060008201518160000160008201518160000160006101000a81548163ffffffff021916908360030b63ffffffff16021790555060208201518160000160046101000a81548163ffffffff021916908360030b63ffffffff16021790555060408201518160000160086101000a81548163ffffffff021916908363ffffffff1602179055505050602082015181600101556040820151816002015560608201518160030160006101000a81548160ff021916908360018111156103f6576103f5611c94565b5b021790555060808201518160030160016101000a81548160ff0219169083600281111561042657610425611c94565b5b021790555060a0820151816004019080519060200190610447929190611113565b5060c0820151816005019080519060200190610464929190611113565b5050506001805490506002819055507f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a15050505050505050505050565b60025481565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610540576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161053790611951565b60405180910390fd5b60018054905081108015610555575060008110155b61055e57600080fd5b60018080805490506105709190611ae9565b8154811061058157610580611d21565b5b9060005260206000209060060201600182815481106105a3576105a2611d21565b5b906000526020600020906006020160008201816000016000820160009054906101000a900460030b8160000160006101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160049054906101000a900460030b8160000160046101000a81548163ffffffff021916908360030b63ffffffff1602179055506000820160089054906101000a900463ffffffff168160000160086101000a81548163ffffffff021916908363ffffffff160217905550505060018201548160010155600282015481600201556003820160009054906101000a900460ff168160030160006101000a81548160ff021916908360018111156106ac576106ab611c94565b5b02179055506003820160019054906101000a900460ff168160030160016101000a81548160ff021916908360028111156106e9576106e8611c94565b5b0217905550600482018160040190805461070290611c02565b61070d929190611199565b50600582018160050190805461072290611c02565b61072d929190611199565b50905050600180548061074357610742611cf2565b5b60019003818190600052602060002090600602016000808201600080820160006101000a81549063ffffffff02191690556000820160046101000a81549063ffffffff02191690556000820160086101000a81549063ffffffff02191690555050600182016000905560028201600090556003820160006101000a81549060ff02191690556003820160016101000a81549060ff02191690556004820160006107ec9190611226565b6005820160006107fc9190611226565b505090557f7b3bb51985c2a805847f7c97c1fda54cad17c4979c2582993690e4f8a246c91860405160405180910390a150565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108bd576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108b490611951565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60606001805480602002602001604051908101604052809291908181526020016000905b82821015610ba157838290600052602060002090600602016040518060e0016040529081600082016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff166001811115610a1d57610a1c611c94565b5b6001811115610a2f57610a2e611c94565b5b81526020016003820160019054906101000a900460ff166002811115610a5857610a57611c94565b5b6002811115610a6a57610a69611c94565b5b8152602001600482018054610a7e90611c02565b80601f0160208091040260200160405190810160405280929190818152602001828054610aaa90611c02565b8015610af75780601f10610acc57610100808354040283529160200191610af7565b820191906000526020600020905b815481529060010190602001808311610ada57829003601f168201915b50505050508152602001600582018054610b1090611c02565b80601f0160208091040260200160405190810160405280929190818152602001828054610b3c90611c02565b8015610b895780601f10610b5e57610100808354040283529160200191610b89565b820191906000526020600020905b815481529060010190602001808311610b6c57829003601f168201915b5050505050815250508152602001906001019061094d565b50505050905090565b6000600254905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610c42576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c3990611951565b60405180910390fd5b60018054905082108015610c57575060008210155b610c6057600080fd5b8060018381548110610c7557610c74611d21565b5b906000526020600020906006020160030160016101000a81548160ff02191690836002811115610ca857610ca7611c94565b5b02179055505050565b60018181548110610cc157600080fd5b9060005260206000209060060201600091509050806000016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050908060010154908060020154908060030160009054906101000a900460ff16908060030160019054906101000a900460ff1690806004018054610d8690611c02565b80601f0160208091040260200160405190810160405280929190818152602001828054610db290611c02565b8015610dff5780601f10610dd457610100808354040283529160200191610dff565b820191906000526020600020905b815481529060010190602001808311610de257829003601f168201915b505050505090806005018054610e1490611c02565b80601f0160208091040260200160405190810160405280929190818152602001828054610e4090611c02565b8015610e8d5780601f10610e6257610100808354040283529160200191610e8d565b820191906000526020600020905b815481529060010190602001808311610e7057829003601f168201915b5050505050905087565b610e9f611266565b60018054905082108015610eb4575060008210155b610ebd57600080fd5b60018281548110610ed157610ed0611d21565b5b90600052602060002090600602016040518060e0016040529081600082016040518060600160405290816000820160009054906101000a900460030b60030b60030b81526020016000820160049054906101000a900460030b60030b60030b81526020016000820160089054906101000a900463ffffffff1663ffffffff1663ffffffff1681525050815260200160018201548152602001600282015481526020016003820160009054906101000a900460ff166001811115610f9757610f96611c94565b5b6001811115610fa957610fa8611c94565b5b81526020016003820160019054906101000a900460ff166002811115610fd257610fd1611c94565b5b6002811115610fe457610fe3611c94565b5b8152602001600482018054610ff890611c02565b80601f016020809104026020016040519081016040528092919081815260200182805461102490611c02565b80156110715780601f1061104657610100808354040283529160200191611071565b820191906000526020600020905b81548152906001019060200180831161105457829003601f168201915b5050505050815260200160058201805461108a90611c02565b80601f01602080910402602001604051908101604052809291908181526020018280546110b690611c02565b80156111035780601f106110d857610100808354040283529160200191611103565b820191906000526020600020905b8154815290600101906020018083116110e657829003601f168201915b5050505050815250509050919050565b82805461111f90611c02565b90600052602060002090601f0160209004810192826111415760008555611188565b82601f1061115a57805160ff1916838001178555611188565b82800160010185558215611188579182015b8281111561118757825182559160200191906001019061116c565b5b50905061119591906112cd565b5090565b8280546111a590611c02565b90600052602060002090601f0160209004810192826111c75760008555611215565b82601f106111d85780548555611215565b8280016001018555821561121557600052602060002091601f016020900482015b828111156112145782548255916001019190600101906111f9565b5b50905061122291906112cd565b5090565b50805461123290611c02565b6000825580601f106112445750611263565b601f01602090049060005260206000209081019061126291906112cd565b5b50565b6040518060e001604052806112796112ea565b81526020016000815260200160008152602001600060018111156112a05761129f611c94565b5b8152602001600060028111156112b9576112b8611c94565b5b815260200160608152602001606081525090565b5b808211156112e65760008160009055506001016112ce565b5090565b6040518060600160405280600060030b8152602001600060030b8152602001600063ffffffff1681525090565b600061132a61132584611a52565b611a2d565b90508281526020810184848401111561134657611345611d84565b5b611351848285611bc0565b509392505050565b60008135905061136881611df5565b92915050565b60008135905061137d81611e0c565b92915050565b60008135905061139281611e1c565b92915050565b6000813590506113a781611e2c565b92915050565b600082601f8301126113c2576113c1611d7f565b5b81356113d2848260208601611317565b91505092915050565b6000813590506113ea81611e43565b92915050565b6000813590506113ff81611e5a565b92915050565b60006020828403121561141b5761141a611d8e565b5b600061142984828501611359565b91505092915050565b60008060008060008060008060006101208a8c03121561145557611454611d8e565b5b60006114638c828d01611398565b99505060206114748c828d01611398565b98505060406114858c828d016113f0565b97505060606114968c828d016113db565b96505060806114a78c828d016113db565b95505060a06114b88c828d0161136e565b94505060c06114c98c828d01611383565b93505060e08a013567ffffffffffffffff8111156114ea576114e9611d89565b5b6114f68c828d016113ad565b9250506101008a013567ffffffffffffffff81111561151857611517611d89565b5b6115248c828d016113ad565b9150509295985092959850929598565b60006020828403121561154a57611549611d8e565b5b6000611558848285016113db565b91505092915050565b6000806040838503121561157857611577611d8e565b5b6000611586858286016113db565b925050602061159785828601611383565b9150509250929050565b60006115ad838361179d565b905092915050565b6115be81611b1d565b82525050565b60006115cf82611a93565b6115d98185611ab6565b9350836020820285016115eb85611a83565b8060005b85811015611627578484038952815161160885826115a1565b945061161383611aa9565b925060208a019950506001810190506115ef565b50829750879550505050505092915050565b61164281611b9c565b82525050565b61165181611b9c565b82525050565b61166081611bae565b82525050565b61166f81611bae565b82525050565b61167e81611b55565b82525050565b600061168f82611a9e565b6116998185611ac7565b93506116a9818560208601611bcf565b6116b281611d93565b840191505092915050565b60006116c882611a9e565b6116d28185611ad8565b93506116e2818560208601611bcf565b6116eb81611d93565b840191505092915050565b6000611703601383611ad8565b915061170e82611da4565b602082019050919050565b60608201600082015161172f6000850182611675565b5060208201516117426020850182611675565b5060408201516117556040850182611905565b50505050565b6060820160008201516117716000850182611675565b5060208201516117846020850182611675565b5060408201516117976040850182611905565b50505050565b6000610120830160008301516117b66000860182611719565b5060208301516117c960608601826118e7565b5060408301516117dc60808601826118e7565b5060608301516117ef60a0860182611639565b50608083015161180260c0860182611657565b5060a083015184820360e086015261181a8282611684565b91505060c08301518482036101008601526118358282611684565b9150508091505092915050565b60006101208301600083015161185b6000860182611719565b50602083015161186e60608601826118e7565b50604083015161188160808601826118e7565b50606083015161189460a0860182611639565b5060808301516118a760c0860182611657565b5060a083015184820360e08601526118bf8282611684565b91505060c08301518482036101008601526118da8282611684565b9150508091505092915050565b6118f081611b82565b82525050565b6118ff81611b82565b82525050565b61190e81611b8c565b82525050565b600060208201905061192960008301846115b5565b92915050565b6000602082019050818103600083015261194981846115c4565b905092915050565b6000602082019050818103600083015261196a816116f6565b9050919050565b600061012082019050611987600083018a61175b565b61199460608301896118f6565b6119a160808301886118f6565b6119ae60a0830187611648565b6119bb60c0830186611666565b81810360e08301526119cd81856116bd565b90508181036101008301526119e281846116bd565b905098975050505050505050565b60006020820190508181036000830152611a0a8184611842565b905092915050565b6000602082019050611a2760008301846118f6565b92915050565b6000611a37611a48565b9050611a438282611c34565b919050565b6000604051905090565b600067ffffffffffffffff821115611a6d57611a6c611d50565b5b611a7682611d93565b9050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b6000611af482611b82565b9150611aff83611b82565b925082821015611b1257611b11611c65565b5b828203905092915050565b6000611b2882611b62565b9050919050565b6000819050611b3d82611dcd565b919050565b6000819050611b5082611de1565b919050565b60008160030b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b600063ffffffff82169050919050565b6000611ba782611b2f565b9050919050565b6000611bb982611b42565b9050919050565b82818337600083830152505050565b60005b83811015611bed578082015181840152602081019050611bd2565b83811115611bfc576000848401525b50505050565b60006002820490506001821680611c1a57607f821691505b60208210811415611c2e57611c2d611cc3565b5b50919050565b611c3d82611d93565b810181811067ffffffffffffffff82111715611c5c57611c5b611d50565b5b80604052505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f63616c6c6572206973206e6f74206f776e657200000000000000000000000000600082015250565b60028110611dde57611ddd611c94565b5b50565b60038110611df257611df1611c94565b5b50565b611dfe81611b1d565b8114611e0957600080fd5b50565b60028110611e1957600080fd5b50565b60038110611e2957600080fd5b50565b611e3581611b55565b8114611e4057600080fd5b50565b611e4c81611b82565b8114611e5757600080fd5b50565b611e6381611b8c565b8114611e6e57600080fd5b5056fea26469706673582212204c6cc1d0ba1e08977dbe467ea83abd5d0ad3b0bbd01c3b8916dceb8b1e78858a64736f6c63430008070033";

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

    public RemoteFunctionCall<TransactionReceipt> makeStrategy(BigInteger x, BigInteger y, BigInteger radius, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority, String description, String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MAKESTRATEGY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Int32(x), 
                new org.web3j.abi.datatypes.generated.Int32(y), 
                new org.web3j.abi.datatypes.generated.Uint32(radius), 
                new org.web3j.abi.datatypes.generated.Uint256(startDate), 
                new org.web3j.abi.datatypes.generated.Uint256(endDate), 
                new org.web3j.abi.datatypes.generated.Uint8(connectionType), 
                new org.web3j.abi.datatypes.generated.Uint8(priority), 
                new org.web3j.abi.datatypes.Utf8String(description), 
                new org.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, String, String>> strategies(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_STRATEGIES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Location>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, String, String>>(function,
                new Callable<Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, String, String>>() {
                    @Override
                    public Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<Location, BigInteger, BigInteger, BigInteger, BigInteger, String, String>(
                                (Location) results.get(0), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
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

    public static class Strategy extends DynamicStruct {
        public Location location;

        public BigInteger startDate;

        public BigInteger endDate;

        public BigInteger connectionType;

        public BigInteger priority;

        public String description;

        public String name;

        public Strategy(Location location, BigInteger startDate, BigInteger endDate, BigInteger connectionType, BigInteger priority, String description, String name) {
            super(location,new org.web3j.abi.datatypes.generated.Uint256(startDate),new org.web3j.abi.datatypes.generated.Uint256(endDate),new org.web3j.abi.datatypes.generated.Uint8(connectionType),new org.web3j.abi.datatypes.generated.Uint8(priority),new org.web3j.abi.datatypes.Utf8String(description),new org.web3j.abi.datatypes.Utf8String(name));
            this.location = location;
            this.startDate = startDate;
            this.endDate = endDate;
            this.connectionType = connectionType;
            this.priority = priority;
            this.description = description;
            this.name = name;
        }

        public Strategy(Location location, Uint256 startDate, Uint256 endDate, Uint8 connectionType, Uint8 priority, Utf8String description, Utf8String name) {
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
