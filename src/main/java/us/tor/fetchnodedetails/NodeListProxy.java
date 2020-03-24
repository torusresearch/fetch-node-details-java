package us.tor.fetchnodedetails;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.4.
 */
@SuppressWarnings("rawtypes")
public class NodeListProxy extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50604051610f4a380380610f4a8339818101604052604081101561003357600080fd5b508051602090910151600061004f6001600160e01b036100c116565b600080546001600160a01b0319166001600160a01b0383169081178255604051929350917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a350600155600280546001600160a01b0319166001600160a01b03929092169190911790556100c5565b3390565b610e76806100d46000396000f3fe608060405234801561001057600080fd5b50600436106100d45760003560e01c80637d22c35c11610081578063bafb35811161005b578063bafb3581146102b4578063c7aa8ff714610433578063f2fde38b14610456576100d4565b80637d22c35c146102485780638da5cb5b146102885780638f32d59b146102ac576100d4565b806347de074f116100b257806347de074f146101b9578063715018a614610226578063766718081461022e576100d4565b806308704c0a146100d9578063135022c2146101015780631dd6b9b11461019c575b600080fd5b6100ff600480360360208110156100ef57600080fd5b50356001600160a01b031661047c565b005b61011e6004803603602081101561011757600080fd5b503561058c565b6040518088815260200187815260200186815260200185815260200180602001848152602001838152602001828103825285818151815260200191508051906020019060200280838360005b8381101561018257818101518382015260200161016a565b505050509050019850505050505050505060405180910390f35b6100ff600480360360208110156101b257600080fd5b50356106ff565b6101d6600480360360208110156101cf57600080fd5b5035610798565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156102125781810151838201526020016101fa565b505050509050019250505060405180910390f35b6100ff6108b4565b610236610950565b60408051918252519081900360200190f35b6102746004803603604081101561025e57600080fd5b50803590602001356001600160a01b0316610956565b604080519115158252519081900360200190f35b6102906109e1565b604080516001600160a01b039092168252519081900360200190f35b6102746109f0565b6102da600480360360208110156102ca57600080fd5b50356001600160a01b0316610a14565b6040518080602001878152602001868152602001858152602001806020018060200184810384528a818151815260200191508051906020019080838360005b83811015610331578181015183820152602001610319565b50505050905090810190601f16801561035e5780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b83811015610391578181015183820152602001610379565b50505050905090810190601f1680156103be5780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b838110156103f15781810151838201526020016103d9565b50505050905090810190601f16801561041e5780820380516001836020036101000a031916815260200191505b50995050505050505050505060405180910390f35b6102366004803603604081101561044957600080fd5b5080359060200135610cd6565b6100ff6004803603602081101561046c57600080fd5b50356001600160a01b0316610d29565b6104846109f0565b6104ce576040805162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b604482015290519081900360640190fd5b6001600160a01b038116610529576040805162461bcd60e51b815260206004820152600f60248201527f6e6f207a65726f20616464726573730000000000000000000000000000000000604482015290519081900360640190fd5b600280546001600160a01b038381166001600160a01b0319831681179093556040805191909216808252602082019390935281517fdc1f595b72ed3d34e3bd6fac603c555426330dc53cca030f6dcf09bcf2684816929181900390910190a15050565b6000806000806060600080600260009054906101000a90046001600160a01b03166001600160a01b031663135022c2896040518263ffffffff1660e01b81526004018082815260200191505060006040518083038186803b1580156105f057600080fd5b505afa158015610604573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405260e081101561062d57600080fd5b815160208301516040808501516060860151608087018051935195979496929591949193928201928464010000000082111561066857600080fd5b90830190602082018581111561067d57600080fd5b825186602082028301116401000000008211171561069a57600080fd5b82525081516020918201928201910280838360005b838110156106c75781810151838201526020016106af565b505050509190910160409081526020830151920151989f50969d50949b50929950909750919550929350505050919395979092949650565b6107076109f0565b610751576040805162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b604482015290519081900360640190fd5b6001805490829055604080518281526020810184905281517f528990bbb5369a7f6d5acab41233e32bddb4882673d0208805b59cbad0dc1ec8929181900390910190a15050565b600254604080516347de074f60e01b81526004810184905290516060926001600160a01b0316916347de074f916024808301926000929190829003018186803b1580156107e457600080fd5b505afa1580156107f8573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052602081101561082157600080fd5b810190808051604051939291908464010000000082111561084157600080fd5b90830190602082018581111561085657600080fd5b825186602082028301116401000000008211171561087357600080fd5b82525081516020918201928201910280838360005b838110156108a0578181015183820152602001610888565b505050509050016040525050509050919050565b6108bc6109f0565b610906576040805162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b604482015290519081900360640190fd5b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600080546001600160a01b0319169055565b60015481565b60025460408051631f48b0d760e21b8152600481018590526001600160a01b03848116602483015291516000939290921691637d22c35c91604480820192602092909190829003018186803b1580156109ae57600080fd5b505afa1580156109c2573d6000803e3d6000fd5b505050506040513d60208110156109d857600080fd5b50519392505050565b6000546001600160a01b031690565b600080546001600160a01b0316610a05610d87565b6001600160a01b031614905090565b6002546040805163859da85f60e01b81526001600160a01b03848116600483015291516060936000938493849387938493169163859da85f9160248083019288929190829003018186803b158015610a6b57600080fd5b505afa158015610a7f573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405260c0811015610aa857600080fd5b8101908080516040519392919084640100000000821115610ac857600080fd5b908301906020820185811115610add57600080fd5b8251640100000000811182820188101715610af757600080fd5b82525081516020918201929091019080838360005b83811015610b24578181015183820152602001610b0c565b50505050905090810190601f168015610b515780820380516001836020036101000a031916815260200191505b50604081815260208301519083015160608401516080909401805192969195919284640100000000821115610b8557600080fd5b908301906020820185811115610b9a57600080fd5b8251640100000000811182820188101715610bb457600080fd5b82525081516020918201929091019080838360005b83811015610be1578181015183820152602001610bc9565b50505050905090810190601f168015610c0e5780820380516001836020036101000a031916815260200191505b5060405260200180516040519392919084640100000000821115610c3157600080fd5b908301906020820185811115610c4657600080fd5b8251640100000000811182820188101715610c6057600080fd5b82525081516020918201929091019080838360005b83811015610c8d578181015183820152602001610c75565b50505050905090810190601f168015610cba5780820380516001836020036101000a031916815260200191505b5060405250969e959d50939b5091995097509550909350505050565b6002546040805163c7aa8ff760e01b8152600481018590526024810184905290516000926001600160a01b03169163c7aa8ff7916044808301926020929190829003018186803b1580156109ae57600080fd5b610d316109f0565b610d7b576040805162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b604482015290519081900360640190fd5b610d8481610d8b565b50565b3390565b6001600160a01b038116610de6576040805162461bcd60e51b815260206004820152601860248201527f4f776e61626c653a206e6f207a65726f20616464726573730000000000000000604482015290519081900360640190fd5b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b039290921691909117905556fea265627a7a7231582063f471c91f5092523badf4ee4de5e1f3dc1392dc87d2eeff035b010ca0ab132164736f6c634300050f0032";

    public static final String FUNC_CURRENTEPOCH = "currentEpoch";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_SETCURRENTEPOCH = "setCurrentEpoch";

    public static final String FUNC_SETNODELISTCONTRACT = "setNodeListContract";

    public static final String FUNC_GETNODES = "getNodes";

    public static final String FUNC_GETNODEDETAILS = "getNodeDetails";

    public static final String FUNC_GETPSSSTATUS = "getPssStatus";

    public static final String FUNC_ISWHITELISTED = "isWhitelisted";

    public static final String FUNC_GETEPOCHINFO = "getEpochInfo";

    public static final Event EPOCHCHANGED_EVENT = new Event("EpochChanged",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NODELISTCONTRACTCHANGED_EVENT = new Event("NodeListContractChanged",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("1578914500522", "0x1f5c8F1b6b14044875520A9eC05131eEfeC71112");
        _addresses.put("1577963254622", "0x08D07331C9cce30983929B3043B682c8DA019E02");
        _addresses.put("1577961918044", "0xa977A50Fa0F14E138EC021685408E0Cf07E26300");
        _addresses.put("1577961100099", "0x6320f4F7f2A06dEb8b9Ec635A70E427818421929");
        _addresses.put("1577960620355", "0xfa35b801Ec94B68017Ba6ad1d2898778552dd417");
        _addresses.put("1577961283304", "0x5811C3E88803dBeCeF269b40C1087bcc074D7Cd7");
        _addresses.put("1578913944784", "0x4eE5b52EE4fB80f124480800316Eb464f9978225");
        _addresses.put("1577963812859", "0x885B93D077463209BE9e6B4A15F0BFc7BD494f64");
        _addresses.put("1577963629748", "0xc41bCD899c474c0bD8FBd6009549f33B6D6dA47f");
        _addresses.put("1577966769986", "0x942d5C2cDE0574b29648108733B4A5ac1E13174D");
        _addresses.put("1577963881061", "0x616D136d8DE615d4b8dD67590B72C1238d432ac9");
        _addresses.put("1577964209403", "0x79B5EaF494167Ffe16c478405566b8b8e46af893");
        _addresses.put("1577960360239", "0xFB634d8d5560Ea3dE3D628Dc19F48a67DB502782");
        _addresses.put("1577962310796", "0x56F650571c5Ab0d8326Bc5AFeE14465f8a8D402D");
        _addresses.put("1577964565701", "0x7287D447C91eC608dDa0Eb71Dc18Aaec8a8978ef");
        _addresses.put("1577960486491", "0x33124b8180F4323F890968e83Ec9fccDab599747");
    }

    @Deprecated
    protected NodeListProxy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NodeListProxy(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NodeListProxy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NodeListProxy(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<EpochChangedEventResponse> getEpochChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EPOCHCHANGED_EVENT, transactionReceipt);
        ArrayList<EpochChangedEventResponse> responses = new ArrayList<EpochChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EpochChangedEventResponse typedResponse = new EpochChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldEpoch = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newEpoch = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EpochChangedEventResponse> epochChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EpochChangedEventResponse>() {
            @Override
            public EpochChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EPOCHCHANGED_EVENT, log);
                EpochChangedEventResponse typedResponse = new EpochChangedEventResponse();
                typedResponse.log = log;
                typedResponse.oldEpoch = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newEpoch = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EpochChangedEventResponse> epochChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EPOCHCHANGED_EVENT));
        return epochChangedEventFlowable(filter);
    }

    public List<NodeListContractChangedEventResponse> getNodeListContractChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NODELISTCONTRACTCHANGED_EVENT, transactionReceipt);
        ArrayList<NodeListContractChangedEventResponse> responses = new ArrayList<NodeListContractChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NodeListContractChangedEventResponse typedResponse = new NodeListContractChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldContract = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newContract = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NodeListContractChangedEventResponse> nodeListContractChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NodeListContractChangedEventResponse>() {
            @Override
            public NodeListContractChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NODELISTCONTRACTCHANGED_EVENT, log);
                NodeListContractChangedEventResponse typedResponse = new NodeListContractChangedEventResponse();
                typedResponse.log = log;
                typedResponse.oldContract = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newContract = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NodeListContractChangedEventResponse> nodeListContractChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NODELISTCONTRACTCHANGED_EVENT));
        return nodeListContractChangedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> currentEpoch() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CURRENTEPOCH,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setCurrentEpoch(BigInteger _newEpoch) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETCURRENTEPOCH,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_newEpoch)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setNodeListContract(String nodeListContractAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETNODELISTCONTRACT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(nodeListContractAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getNodes(BigInteger epoch) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNODES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(epoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
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

    public RemoteFunctionCall<Tuple6<String, BigInteger, BigInteger, BigInteger, String, String>> getNodeDetails(String nodeAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNODEDETAILS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(nodeAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple6<String, BigInteger, BigInteger, BigInteger, String, String>>(function,
                new Callable<Tuple6<String, BigInteger, BigInteger, BigInteger, String, String>>() {
                    @Override
                    public Tuple6<String, BigInteger, BigInteger, BigInteger, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, BigInteger, BigInteger, BigInteger, String, String>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (String) results.get(4).getValue(),
                                (String) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getPssStatus(BigInteger oldEpoch, BigInteger newEpoch) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPSSSTATUS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(oldEpoch),
                        new org.web3j.abi.datatypes.generated.Uint256(newEpoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isWhitelisted(BigInteger epoch, String nodeAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISWHITELISTED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(epoch),
                        new org.web3j.abi.datatypes.Address(nodeAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, List<String>, BigInteger, BigInteger>> getEpochInfo(BigInteger epoch) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETEPOCHINFO,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(epoch)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Address>>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, List<String>, BigInteger, BigInteger>>(function,
                new Callable<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, List<String>, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, List<String>, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, List<String>, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                convertToNative((List<Address>) results.get(4).getValue()),
                                (BigInteger) results.get(5).getValue(),
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    @Deprecated
    public static NodeListProxy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NodeListProxy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NodeListProxy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NodeListProxy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NodeListProxy load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NodeListProxy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NodeListProxy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NodeListProxy(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NodeListProxy> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String nodeListContractAddress, BigInteger epoch) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(nodeListContractAddress),
                new org.web3j.abi.datatypes.generated.Uint256(epoch)));
        return deployRemoteCall(NodeListProxy.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<NodeListProxy> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String nodeListContractAddress, BigInteger epoch) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(nodeListContractAddress),
                new org.web3j.abi.datatypes.generated.Uint256(epoch)));
        return deployRemoteCall(NodeListProxy.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NodeListProxy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String nodeListContractAddress, BigInteger epoch) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(nodeListContractAddress),
                new org.web3j.abi.datatypes.generated.Uint256(epoch)));
        return deployRemoteCall(NodeListProxy.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NodeListProxy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String nodeListContractAddress, BigInteger epoch) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(nodeListContractAddress),
                new org.web3j.abi.datatypes.generated.Uint256(epoch)));
        return deployRemoteCall(NodeListProxy.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class EpochChangedEventResponse extends BaseEventResponse {
        public BigInteger oldEpoch;

        public BigInteger newEpoch;
    }

    public static class NodeListContractChangedEventResponse extends BaseEventResponse {
        public String oldContract;

        public String newContract;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
