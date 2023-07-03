package org.torusresearch.fetchnodedetails.types;

import java.math.BigInteger;

public class Utils {

    public static NodeDetails NODE_DETAILS_MAINNET = new NodeDetails("19", new String[]{"https://torus-19.torusnode.com/jrpc", "https://torus-node.ens.domains/jrpc", "https://torus-node.matic.network/jrpc", "https://torus.zilliqa.network/jrpc", "https://torus-mainnet.cosmos.network/jrpc", "https://torus2.etherscan.com/jrpc", "https://torus-node-v2.skalelabs.com/jrpc", "https://torus-node.binancex.dev/jrpc", "https://torusnode.ont.io/jrpc"}, new String[]{}, new String[]{}, new String[]{}, new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5"), new BigInteger("6"), new BigInteger("7"), new BigInteger("8"), new BigInteger("9"),}, new TorusNodePub[]{new TorusNodePub("bbe83c64177c3775550e6ba6ac2bc059f6847d644c9e4894e42c60d7974d8c2b", "82b49a7caf70def38cdad2740af45c1e4f969650105c5019a29bb18b21a9acb5"), new TorusNodePub("c208cac4ef9a47d386097a9c915b28e9cb89213abee8d26a17198ee261201b0d", "c7db2fe4631109f40833de9dc78d07e35706549ee48fa557b33e4e75e1047873"), new TorusNodePub("ca1766bb426d4ca5582818a0c5439d560ea64f5baa060793ab29dd3d0ceacfe", "d46c1d08c40e1306e1bca328c2287b8268166b11a1ba4b8442ea2ad0c5e32152"), new TorusNodePub("c3934dd2f6f4b3d2e1e398cc501e143c1e1a381b52feb6d1525af34d16253768", "71f5141a5035799099f5ea3e241e66946bc55dc857ac3bd7d6fcdb8dcd3eeeef"), new TorusNodePub("22e66f1929631d00bf026227581597f085fd94fd952fc0dca9f0833398b5c064", "6088b3912e10a1e9d50355a609c10db7d188f16a2e2fd7357e51bf4f6a74f0a1"), new TorusNodePub("9dc9fa410f3ce9eb70df70cdea00a49f2c4cc7a31c08c0dab5f863ed35ff5139", "627a291cb87a75c61da3f65d6818e1e05e360217179817ed27e8c73bca7ec122"), new TorusNodePub("118b9fc07e97b096d899b9f6658463ce6a8caa64038e37fc969df4e6023dd8c6", "baf9fa4e51770f4796ea165dd03a769b8606681a38954a0a92c4cbffd6609ce9"), new TorusNodePub("8a6d8b925da15a273dec3d8f8395ec35cd6878f274b2b180e4e106999db64043", "96f67f870c157743da0b1eb84d89bf30500d74dc84c11f501ee1cb013acc8c46"), new TorusNodePub("39cecb62e863729f572f7dfc46c24867981bf04bb405fed0df39e33984bfade5", "61c2364434012e68a2be2e9952805037e52629d7762fafc8e10e9fb5bad8f790")}, false);

    public static NodeDetails NODE_DETAILS_TESTNET = new NodeDetails("1", new String[]{"https://sapphire-dev-2-1.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-2.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-3.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-4.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-5.authnetwork.dev/sss/teal/jrpc",},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-2.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-3.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-4.authnetwork.dev/sss/teal/jrpc", "https://sapphire-dev-2-5.authnetwork.dev/sss/teal/jrpc"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/rss/teal", "https://sapphire-dev-2-2.authnetwork.dev/rss/teal", "https://sapphire-dev-2-3.authnetwork.dev/rss/teal", "https://sapphire-dev-2-4.authnetwork.dev/rss/teal", "https://sapphire-dev-2-5.authnetwork.dev/rss/teal"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/tss/teal", "https://sapphire-dev-2-2.authnetwork.dev/tss/teal", "https://sapphire-dev-2-3.authnetwork.dev/tss/teal", "https://sapphire-dev-2-4.authnetwork.dev/tss/teal", "https://sapphire-dev-2-5.authnetwork.dev/tss/teal"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("f74389b0a4c8d10d2a687ae575f69b20f412d41ab7f1fe6b358aa14871327247", "54e3a73098ed9bced3ef8821736e9794f9264a1420c0c7ad15d2fa617ba35ef7"),
                    new TorusNodePub("bc38813a6873e526087918507c78fc3a61624670ee851ecfb4f3bef55d027b5a", "ac4b21229f662a0aefdfdac21cf17c3261a392c74a8790db218b34e3e4c1d56a"),
                    new TorusNodePub("b56541684ea5fa40c8337b7688d502f0e9e092098962ad344c34e94f06d293fb", "759a998cef79d389082f9a75061a29190eec0cac99b8c25ddcf6b58569dad55c"),
                    new TorusNodePub("7bcb058d4c6ffc6ba4bfdfd93d141af35a66338a62c7c27cdad2ae3f8289b767", "336ab1935e41ed4719e162587f0ab55518db4207a1eb36cc72303f1b86689d2b"),
                    new TorusNodePub("bf12a136ef94399ea098f926f04e26a4ec4ac70f69cce274e8893704c4951773", "bdd44828020f52ce510e026338216ada184a6867eb4e19fb4c2d495d4a7e15e4")},
            false);

    public static NodeDetails NODE_DETAILS_SAPPHIRE_MAINNET = new NodeDetails("1", new String[]{"https://sapphire-1.auth.network/sss/jrpc", "https://sapphire-2.auth.network/sss/jrpc", "https://sapphire-3.auth.network/sss/jrpc", "https://sapphire-4.auth.network/sss/jrpc", "https://sapphire-5.auth.network/sss/jrpc"},
            new String[]{"https://sapphire-1.auth.network/sss/jrpc", "https://sapphire-2.auth.network/sss/jrpc", "https://sapphire-3.auth.network/sss/jrpc", "https://sapphire-4.auth.network/sss/jrpc", "https://sapphire-5.auth.network/sss/jrpc"},
            new String[]{"https://sapphire-1.auth.network/rss", "https://sapphire-2.auth.network/rss", "https://sapphire-3.auth.network/rss", "https://sapphire-4.auth.network/rss", "https://sapphire-5.auth.network/rss"},
            new String[]{"https://sapphire-1.auth.network/tss", "https://sapphire-2.auth.network/tss", "https://sapphire-3.auth.network/tss", "https://sapphire-4.auth.network/tss", "https://sapphire-5.auth.network/tss"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("e0925898fee0e9e941fdca7ee88deec99939ae9407e923535c4d4a3a3ff8b052", "54b9fea924e3f3e40791f9987f4234ae4222412d65b74068032fa5d8b63375c1"),
                    new TorusNodePub("9124cf1e280aab32ba50dffd2de81cecabc13d82d2c1fe9de82f3b3523f9b637", "fca939a1ceb42ce745c55b21ef094f543b457630cb63a94ef4f1afeee2b1f107"),
                    new TorusNodePub("555f681a63d469cc6c3a58a97e29ebd277425f0e6159708e7c7bf05f18f89476", "606f2bcc0884fa5b64366fc3e8362e4939841b56acd60d5f4553cf36b891ac4e"),
                    new TorusNodePub("2b5f58d8e340f1ab922e89b3a69a68930edfe51364644a456335e179bc130128", "4b4daa05939426e3cbe7d08f0e773d2bf36f64c00d04620ee6df2a7af4d2247"),
                    new TorusNodePub("3ecbb6a68afe72cf34ec6c0a12b5cb78a0d2e83ba402983b6adbc5f36219861a", "dc1031c5cc8f0472bd521a62a64ebca9e163902c247bf05937daf4ae835091e4")},
            true);

    public static NodeDetails NODE_DETAILS_SAPPHIRE_TESTNET = new NodeDetails("1", new String[]{"https://sapphire-dev-2-1.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-2.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-3.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-4.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-5.authnetwork.dev/sss/jrpc",},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-2.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-3.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-4.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-5.authnetwork.dev/sss/jrpc"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/rss", "https://sapphire-dev-2-2.authnetwork.dev/rss", "https://sapphire-dev-2-3.authnetwork.dev/rss", "https://sapphire-dev-2-4.authnetwork.dev/rss", "https://sapphire-dev-2-5.authnetwork.dev/rss"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/tss", "https://sapphire-dev-2-2.authnetwork.dev/tss", "https://sapphire-dev-2-3.authnetwork.dev/tss", "https://sapphire-dev-2-4.authnetwork.dev/tss", "https://sapphire-dev-2-5.authnetwork.dev/tss"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("f74389b0a4c8d10d2a687ae575f69b20f412d41ab7f1fe6b358aa14871327247", "54e3a73098ed9bced3ef8821736e9794f9264a1420c0c7ad15d2fa617ba35ef7"),
                    new TorusNodePub("bc38813a6873e526087918507c78fc3a61624670ee851ecfb4f3bef55d027b5a", "ac4b21229f662a0aefdfdac21cf17c3261a392c74a8790db218b34e3e4c1d56a"),
                    new TorusNodePub("b56541684ea5fa40c8337b7688d502f0e9e092098962ad344c34e94f06d293fb", "759a998cef79d389082f9a75061a29190eec0cac99b8c25ddcf6b58569dad55c"),
                    new TorusNodePub("7bcb058d4c6ffc6ba4bfdfd93d141af35a66338a62c7c27cdad2ae3f8289b767", "336ab1935e41ed4719e162587f0ab55518db4207a1eb36cc72303f1b86689d2b"),
                    new TorusNodePub("bf12a136ef94399ea098f926f04e26a4ec4ac70f69cce274e8893704c4951773", "bdd44828020f52ce510e026338216ada184a6867eb4e19fb4c2d495d4a7e15e4")},
            true);

    public static NodeDetails NODE_DETAILS_SAPPHIRE_DEVNET = new NodeDetails("1", new String[]{"https://sapphire-dev-2-1.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-2.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-3.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-4.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-5.authnetwork.dev/sss/jrpc"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-2.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-3.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-4.authnetwork.dev/sss/jrpc", "https://sapphire-dev-2-5.authnetwork.dev/sss/jrpc"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/rss", "https://sapphire-dev-2-2.authnetwork.dev/rss", "https://sapphire-dev-2-3.authnetwork.dev/rss", "https://sapphire-dev-2-4.authnetwork.dev/rss", "https://sapphire-dev-2-5.authnetwork.dev/rss"},
            new String[]{"https://sapphire-dev-2-1.authnetwork.dev/tss", "https://sapphire-dev-2-2.authnetwork.dev/tss", "https://sapphire-dev-2-3.authnetwork.dev/tss", "https://sapphire-dev-2-4.authnetwork.dev/tss", "https://sapphire-dev-2-5.authnetwork.dev/tss"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("f74389b0a4c8d10d2a687ae575f69b20f412d41ab7f1fe6b358aa14871327247", "54e3a73098ed9bced3ef8821736e9794f9264a1420c0c7ad15d2fa617ba35ef7"),
                    new TorusNodePub("bc38813a6873e526087918507c78fc3a61624670ee851ecfb4f3bef55d027b5a", "ac4b21229f662a0aefdfdac21cf17c3261a392c74a8790db218b34e3e4c1d56a"),
                    new TorusNodePub("b56541684ea5fa40c8337b7688d502f0e9e092098962ad344c34e94f06d293fb", "759a998cef79d389082f9a75061a29190eec0cac99b8c25ddcf6b58569dad55c"),
                    new TorusNodePub("7bcb058d4c6ffc6ba4bfdfd93d141af35a66338a62c7c27cdad2ae3f8289b767", "336ab1935e41ed4719e162587f0ab55518db4207a1eb36cc72303f1b86689d2b"),
                    new TorusNodePub("bf12a136ef94399ea098f926f04e26a4ec4ac70f69cce274e8893704c4951773", "bdd44828020f52ce510e026338216ada184a6867eb4e19fb4c2d495d4a7e15e4")},
            true);

    public static NodeDetails fetchLocalConfig(TorusNetwork network) {
        NodeDetails nodeDetails = null;
        switch (network) {
            case SAPPHIRE_DEVNET:
                nodeDetails = NODE_DETAILS_SAPPHIRE_DEVNET;
            case SAPPHIRE_TESTNET:
                nodeDetails = NODE_DETAILS_SAPPHIRE_TESTNET;
            case SAPPHIRE_MAINNET:
                nodeDetails = NODE_DETAILS_SAPPHIRE_MAINNET;
            case LEGACY_MAINNET:
                nodeDetails = NODE_DETAILS_MAINNET;
            case LEGACY_TESTNET:
                nodeDetails = NODE_DETAILS_TESTNET;
        }
        return nodeDetails;
    }
}
