package org.torusresearch.fetchnodedetails;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class TorusLookup extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5060405161145938038061145983398101604081905261002f91610095565b600080546001600160a01b031916339081178255604051909182917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908290a350600180546001600160a01b0319166001600160a01b03929092169190911790556100c5565b6000602082840312156100a757600080fd5b81516001600160a01b03811681146100be57600080fd5b9392505050565b611385806100d46000396000f3fe608060405234801561001057600080fd5b50600436106100a35760003560e01c8063a9487fd911610076578063decde7661161005b578063decde76614610144578063f2fde38b14610157578063f4f59bed1461016a57600080fd5b8063a9487fd91461010d578063d59936e91461012057600080fd5b80633f3787d2146100a8578063715018a6146100bd5780638da5cb5b146100c55780638f32d59b146100ef575b600080fd5b6100bb6100b6366004610ce0565b61017d565b005b6100bb6101f7565b6000546001600160a01b03165b6040516001600160a01b0390911681526020015b60405180910390f35b6000546001600160a01b0316331460405190151581526020016100e6565b6100bb61011b366004610ded565b610294565b61013361012e366004610eb1565b6104af565b6040516100e6959493929190610f89565b6100d2610152366004610eb1565b6109a7565b6100bb610165366004610ce0565b6109ee565b6100bb610178366004610ded565b610a4d565b6000546001600160a01b031633146101d55760405162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b60448201526064015b60405180910390fd5b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000546001600160a01b0316331461024a5760405162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b60448201526064016101cc565b600080546040516001600160a01b03909116907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0908390a3600080546001600160a01b0319169055565b6000546001600160a01b031633146102e75760405162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b60448201526064016101cc565b60015460405163ac205deb60e01b81526000916001600160a01b03169063ac205deb90610318908690600401611030565b600060405180830381865afa158015610335573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261035d9190810190611090565b9350505050806103af5760405162461bcd60e51b815260206004820152601c60248201527f506c65617365206372656174652076657269666965722066697273740000000060448201526064016101cc565b60006002846040516103c1919061111f565b908152604080519182900360209081018320805480830285018301909352828452919083018282801561041d57602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116103ff575b505050505090506000815111156104765760405162461bcd60e51b815260206004820152601f60248201527f566572696669657220616c7265616479206861732061206e6f6465207365740060448201526064016101cc565b82600285604051610487919061111f565b908152602001604051809103902090805190602001906104a8929190610c51565b5050505050565b600060608060608060006002886040516104c9919061111f565b908152604080519182900360209081018320805480830285018301909352828452919083018282801561052557602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610507575b505050505090508051600014156105b05760405166191959985d5b1d60ca1b815260029060070190815260408051918290036020908101832080548083028501830190935282845291908301828280156105a857602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161058a575b505050505090505b80516105fe5760405162461bcd60e51b815260206004820152601f60248201527f536574204e6f6465536574202d3e2076657269666965722f64656661756c740060448201526064016101cc565b805160009061060d908961113b565b905060008282815181106106235761062361115d565b6020026020010151905060008190506000816001600160a01b031663766718086040518163ffffffff1660e01b8152600401602060405180830381865afa158015610672573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106969190611173565b6040516309a8116160e11b8152600481018290529091506000906001600160a01b0384169063135022c290602401600060405180830381865afa1580156106e1573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610709919081019061118c565b50509450505050506000815167ffffffffffffffff81111561072d5761072d610d04565b604051908082528060200260200182016040528015610756578160200160208202803683370190505b5090506000825167ffffffffffffffff81111561077557610775610d04565b6040519080825280602002602001820160405280156107a857816020015b60608152602001906001900390816107935790505b5090506000835167ffffffffffffffff8111156107c7576107c7610d04565b6040519080825280602002602001820160405280156107f0578160200160208202803683370190505b5090506000845167ffffffffffffffff81111561080f5761080f610d04565b604051908082528060200260200182016040528015610838578160200160208202803683370190505b50905060005b855181101561098957610852816001611278565b8582815181106108645761086461115d565b60200260200101818152505060008060008a6001600160a01b031663bafb35818a86815181106108965761089661115d565b60200260200101516040518263ffffffff1660e01b81526004016108c991906001600160a01b0391909116815260200190565b600060405180830381865afa1580156108e6573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261090e9190810190611290565b5050935093505092508287858151811061092a5761092a61115d565b6020026020010181905250818685815181106109485761094861115d565b602002602001018181525050808585815181106109675761096761115d565b602002602001018181525050505050808061098190611334565b91505061083e565b50949d50909b50995091975090955050505050509295509295909350565b815160208184018101805160028252928201918501919091209190528054829081106109d257600080fd5b6000918252602090912001546001600160a01b03169150829050565b6000546001600160a01b03163314610a415760405162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b60448201526064016101cc565b610a4a81610ba0565b50565b6000546001600160a01b03163314610aa05760405162461bcd60e51b815260206004820152601660248201527527bbb730b136329d103737ba103a34329037bbb732b960511b60448201526064016101cc565b60015460405163ac205deb60e01b81526000916001600160a01b03169063ac205deb90610ad1908690600401611030565b600060405180830381865afa158015610aee573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052610b169190810190611090565b935050505080610b685760405162461bcd60e51b815260206004820152601c60248201527f506c65617365206372656174652076657269666965722066697273740000000060448201526064016101cc565b81600284604051610b79919061111f565b90815260200160405180910390209080519060200190610b9a929190610c51565b50505050565b6001600160a01b038116610bf65760405162461bcd60e51b815260206004820152601860248201527f4f776e61626c653a206e6f207a65726f2061646472657373000000000000000060448201526064016101cc565b600080546040516001600160a01b03808516939216917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e091a3600080546001600160a01b0319166001600160a01b0392909216919091179055565b828054828255906000526020600020908101928215610ca6579160200282015b82811115610ca657825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610c71565b50610cb2929150610cb6565b5090565b5b80821115610cb25760008155600101610cb7565b6001600160a01b0381168114610a4a57600080fd5b600060208284031215610cf257600080fd5b8135610cfd81610ccb565b9392505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610d4357610d43610d04565b604052919050565b600067ffffffffffffffff821115610d6557610d65610d04565b50601f01601f191660200190565b600082601f830112610d8457600080fd5b8135610d97610d9282610d4b565b610d1a565b818152846020838601011115610dac57600080fd5b816020850160208301376000918101602001919091529392505050565b600067ffffffffffffffff821115610de357610de3610d04565b5060051b60200190565b60008060408385031215610e0057600080fd5b823567ffffffffffffffff80821115610e1857600080fd5b610e2486838701610d73565b9350602091508185013581811115610e3b57600080fd5b85019050601f81018613610e4e57600080fd5b8035610e5c610d9282610dc9565b81815260059190911b82018301908381019088831115610e7b57600080fd5b928401925b82841015610ea2578335610e9381610ccb565b82529284019290840190610e80565b80955050505050509250929050565b60008060408385031215610ec457600080fd5b823567ffffffffffffffff811115610edb57600080fd5b610ee785828601610d73565b95602094909401359450505050565b60005b83811015610f11578181015183820152602001610ef9565b83811115610b9a5750506000910152565b60008151808452610f3a816020860160208601610ef6565b601f01601f19169290920160200192915050565b600081518084526020808501945080840160005b83811015610f7e57815187529582019590820190600101610f62565b509495945050505050565b600060a08201878352602060a08185015281885180845260c08601915060c08160051b8701019350828a0160005b82811015610fe55760bf19888703018452610fd3868351610f22565b95509284019290840190600101610fb7565b50505050508281036040840152610ffc8187610f4e565b905082810360608401526110108186610f4e565b905082810360808401526110248185610f4e565b98975050505050505050565b602081526000610cfd6020830184610f22565b600082601f83011261105457600080fd5b8151611062610d9282610d4b565b81815284602083860101111561107757600080fd5b611088826020830160208701610ef6565b949350505050565b600080600080608085870312156110a657600080fd5b84516110b181610ccb565b602086015190945067ffffffffffffffff808211156110cf57600080fd5b6110db88838901611043565b945060408701519150808211156110f157600080fd5b506110fe87828801611043565b9250506060850151801515811461111457600080fd5b939692955090935050565b60008251611131818460208701610ef6565b9190910192915050565b60008261115857634e487b7160e01b600052601260045260246000fd5b500690565b634e487b7160e01b600052603260045260246000fd5b60006020828403121561118557600080fd5b5051919050565b600080600080600080600060e0888a0312156111a757600080fd5b8751965060208089015196506040890151955060608901519450608089015167ffffffffffffffff8111156111db57600080fd5b8901601f81018b136111ec57600080fd5b80516111fa610d9282610dc9565b81815260059190911b8201830190838101908d83111561121957600080fd5b928401925b8284101561124057835161123181610ccb565b8252928401929084019061121e565b809750505050505060a0880151915060c0880151905092959891949750929550565b634e487b7160e01b600052601160045260246000fd5b6000821982111561128b5761128b611262565b500190565b60008060008060008060c087890312156112a957600080fd5b865167ffffffffffffffff808211156112c157600080fd5b6112cd8a838b01611043565b975060208901519650604089015195506060890151945060808901519150808211156112f857600080fd5b6113048a838b01611043565b935060a089015191508082111561131a57600080fd5b5061132789828a01611043565b9150509295509295509295565b600060001982141561134857611348611262565b506001019056fea2646970667358221220ff4c563c9b5bbc18744cd81e5b7c83da3779a5a6e7fbab8be085b388cee415ec64736f6c634300080b0033";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VERIFIERNODESETMAP = "verifierNodeSetMap";

    public static final String FUNC_SETVERIFIERROUTER = "setVerifierRouter";

    public static final String FUNC_SETNODESET = "setNodeSet";

    public static final String FUNC_SETNODESETDANGEROUSLY = "setNodeSetDangerously";

    public static final String FUNC_GETNODESET = "getNodeSet";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected TorusLookup(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TorusLookup(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TorusLookup(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TorusLookup(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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

    public RemoteFunctionCall<String> verifierNodeSetMap(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VERIFIERNODESETMAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setVerifierRouter(String _verifierRouterContract) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETVERIFIERROUTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_verifierRouterContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setNodeSet(String _verifier, List<String> _nodeSetProxyAddresses) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETNODESET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_verifier), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_nodeSetProxyAddresses, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setNodeSetDangerously(String _verifier, List<String> _nodeSetProxyAddresses) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETNODESETDANGEROUSLY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_verifier), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(_nodeSetProxyAddresses, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<BigInteger, List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> getNodeSet(String _verifier, byte[] hashedVerifierId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNODESET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_verifier), 
                new org.web3j.abi.datatypes.generated.Bytes32(hashedVerifierId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteFunctionCall<Tuple5<BigInteger, List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(function,
                new Callable<Tuple5<BigInteger, List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple5<BigInteger, List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);

                        return new Tuple5<BigInteger, List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                (BigInteger) results.get(0).getValue(), 
                                convertToNative((List<Utf8String>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()), 
                                convertToNative((List<Uint256>) results.get(4).getValue()));
                    }
                });
    }

    @Deprecated
    public static TorusLookup load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TorusLookup(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TorusLookup load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TorusLookup(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TorusLookup load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TorusLookup(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TorusLookup load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TorusLookup(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TorusLookup> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _verifierRouterContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_verifierRouterContract)));
        return deployRemoteCall(TorusLookup.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TorusLookup> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _verifierRouterContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_verifierRouterContract)));
        return deployRemoteCall(TorusLookup.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TorusLookup> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _verifierRouterContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_verifierRouterContract)));
        return deployRemoteCall(TorusLookup.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TorusLookup> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _verifierRouterContract) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_verifierRouterContract)));
        return deployRemoteCall(TorusLookup.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}