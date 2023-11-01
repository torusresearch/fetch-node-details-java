package org.torusresearch.fetchnodedetails.types;

import java.math.BigInteger;
import java.util.HashMap;

public class Utils {

    public static HashMap<String, LegacyNetworkMigrationInfo> LEGACY_NETWORKS_ROUTE_MAP = new HashMap<String, LegacyNetworkMigrationInfo>() {
        {
            put(TorusNetwork.AQUA.toString(), new LegacyNetworkMigrationInfo(true, "aqua", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.CELESTE.toString(), new LegacyNetworkMigrationInfo(false, "celeste", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.CYAN.toString(), new LegacyNetworkMigrationInfo(true, "cyan", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.MAINNET.toString(), new LegacyNetworkMigrationInfo(true, "mainnet", TorusNetwork.SAPPHIRE_MAINNET));
            put(TorusNetwork.TESTNET.toString(), new LegacyNetworkMigrationInfo(true, "teal", TorusNetwork.SAPPHIRE_DEVNET));
        }
    };

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

    public static NodeDetails NODE_DETAILS_MAINNET = new NodeDetails("1", new String[]{"https://sapphire-1.auth.network/sss/mainnet/jrpc", "https://sapphire-2.auth.network/sss/mainnet/jrpc", "https://sapphire-3.auth.network/sss/mainnet/jrpc", "https://sapphire-4.auth.network/sss/mainnet/jrpc", "https://sapphire-5.auth.network/sss/mainnet/jrpc"},
            new String[]{"https://sapphire-1.auth.network/sss/mainnet/jrpc", "https://sapphire-2.auth.network/sss/mainnet/jrpc", "https://sapphire-3.auth.network/sss/mainnet/jrpc", "https://sapphire-4.auth.network/sss/mainnet/jrpc", "https://sapphire-5.auth.network/sss/mainnet/jrpc"},
            new String[]{"https://sapphire-1.auth.network/rss/mainnet", "https://sapphire-2.auth.network/rss/mainnet", "https://sapphire-3.auth.network/rss/mainnet", "https://sapphire-4.auth.network/rss/mainnet", "https://sapphire-5.auth.network/rss/mainnet"},
            new String[]{"https://sapphire-1.auth.network/tss/mainnet", "https://sapphire-2.auth.network/tss/mainnet", "https://sapphire-3.auth.network/tss/mainnet", "https://sapphire-4.auth.network/tss/mainnet", "https://sapphire-5.auth.network/tss/mainnet"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("e0925898fee0e9e941fdca7ee88deec99939ae9407e923535c4d4a3a3ff8b052", "54b9fea924e3f3e40791f9987f4234ae4222412d65b74068032fa5d8b63375c1"),
                    new TorusNodePub("9124cf1e280aab32ba50dffd2de81cecabc13d82d2c1fe9de82f3b3523f9b637", "fca939a1ceb42ce745c55b21ef094f543b457630cb63a94ef4f1afeee2b1f107"),
                    new TorusNodePub("555f681a63d469cc6c3a58a97e29ebd277425f0e6159708e7c7bf05f18f89476", "606f2bcc0884fa5b64366fc3e8362e4939841b56acd60d5f4553cf36b891ac4e"),
                    new TorusNodePub("2b5f58d8e340f1ab922e89b3a69a68930edfe51364644a456335e179bc130128", "4b4daa05939426e3cbe7d08f0e773d2bf36f64c00d04620ee6df2a7af4d2247"),
                    new TorusNodePub("3ecbb6a68afe72cf34ec6c0a12b5cb78a0d2e83ba402983b6adbc5f36219861a", "dc1031c5cc8f0472bd521a62a64ebca9e163902c247bf05937daf4ae835091e4")},
            false);

    public static NodeDetails NODE_DETAILS_CYAN = new NodeDetails("1", new String[]{"https://sapphire-1.auth.network/sss/cyan/jrpc", "https://sapphire-2.auth.network/sss/cyan/jrpc", "https://sapphire-3.auth.network/sss/cyan/jrpc", "https://sapphire-4.auth.network/sss/cyan/jrpc", "https://sapphire-5.auth.network/sss/cyan/jrpc"},
            new String[]{"https://sapphire-1.auth.network/sss/cyan/jrpc", "https://sapphire-2.auth.network/sss/cyan/jrpc", "https://sapphire-3.auth.network/sss/cyan/jrpc", "https://sapphire-4.auth.network/sss/cyan/jrpc", "https://sapphire-5.auth.network/sss/cyan/jrpc"},
            new String[]{"https://sapphire-1.auth.network/rss/cyan", "https://sapphire-2.auth.network/rss/cyan", "https://sapphire-3.auth.network/rss/cyan", "https://sapphire-4.auth.network/rss/cyan", "https://sapphire-5.auth.network/rss/cyan"},
            new String[]{"https://sapphire-1.auth.network/tss/cyan", "https://sapphire-2.auth.network/tss/cyan", "https://sapphire-3.auth.network/tss/cyan", "https://sapphire-4.auth.network/tss/cyan", "https://sapphire-5.auth.network/tss/cyan"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("e0925898fee0e9e941fdca7ee88deec99939ae9407e923535c4d4a3a3ff8b052", "54b9fea924e3f3e40791f9987f4234ae4222412d65b74068032fa5d8b63375c1"),
                    new TorusNodePub("9124cf1e280aab32ba50dffd2de81cecabc13d82d2c1fe9de82f3b3523f9b637", "fca939a1ceb42ce745c55b21ef094f543b457630cb63a94ef4f1afeee2b1f107"),
                    new TorusNodePub("555f681a63d469cc6c3a58a97e29ebd277425f0e6159708e7c7bf05f18f89476", "606f2bcc0884fa5b64366fc3e8362e4939841b56acd60d5f4553cf36b891ac4e"),
                    new TorusNodePub("2b5f58d8e340f1ab922e89b3a69a68930edfe51364644a456335e179bc130128", "4b4daa05939426e3cbe7d08f0e773d2bf36f64c00d04620ee6df2a7af4d2247"),
                    new TorusNodePub("3ecbb6a68afe72cf34ec6c0a12b5cb78a0d2e83ba402983b6adbc5f36219861a", "dc1031c5cc8f0472bd521a62a64ebca9e163902c247bf05937daf4ae835091e4")},
            false);

    public static NodeDetails NODE_DETAILS_AQUA = new NodeDetails("1", new String[]{"https://sapphire-1.auth.network/sss/aqua/jrpc", "https://sapphire-2.auth.network/sss/aqua/jrpc", "https://sapphire-3.auth.network/sss/aqua/jrpc", "https://sapphire-4.auth.network/sss/aqua/jrpc", "https://sapphire-5.auth.network/sss/aqua/jrpc"},
            new String[]{"https://sapphire-1.auth.network/sss/aqua/jrpc", "https://sapphire-2.auth.network/sss/aqua/jrpc", "https://sapphire-3.auth.network/sss/aqua/jrpc", "https://sapphire-4.auth.network/sss/aqua/jrpc", "https://sapphire-5.auth.network/sss/aqua/jrpc"},
            new String[]{"https://sapphire-1.auth.network/rss/aqua", "https://sapphire-2.auth.network/rss/aqua", "https://sapphire-3.auth.network/rss/aqua", "https://sapphire-4.auth.network/rss/aqua", "https://sapphire-5.auth.network/rss/aqua"},
            new String[]{"https://sapphire-1.auth.network/tss/aqua", "https://sapphire-2.auth.network/tss/aqua", "https://sapphire-3.auth.network/tss/aqua", "https://sapphire-4.auth.network/tss/aqua", "https://sapphire-5.auth.network/tss/aqua"},
            new BigInteger[]{new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"), new BigInteger("5")},
            new TorusNodePub[]{new TorusNodePub("e0925898fee0e9e941fdca7ee88deec99939ae9407e923535c4d4a3a3ff8b052", "54b9fea924e3f3e40791f9987f4234ae4222412d65b74068032fa5d8b63375c1"),
                    new TorusNodePub("9124cf1e280aab32ba50dffd2de81cecabc13d82d2c1fe9de82f3b3523f9b637", "fca939a1ceb42ce745c55b21ef094f543b457630cb63a94ef4f1afeee2b1f107"),
                    new TorusNodePub("555f681a63d469cc6c3a58a97e29ebd277425f0e6159708e7c7bf05f18f89476", "606f2bcc0884fa5b64366fc3e8362e4939841b56acd60d5f4553cf36b891ac4e"),
                    new TorusNodePub("2b5f58d8e340f1ab922e89b3a69a68930edfe51364644a456335e179bc130128", "4b4daa05939426e3cbe7d08f0e773d2bf36f64c00d04620ee6df2a7af4d2247"),
                    new TorusNodePub("3ecbb6a68afe72cf34ec6c0a12b5cb78a0d2e83ba402983b6adbc5f36219861a", "dc1031c5cc8f0472bd521a62a64ebca9e163902c247bf05937daf4ae835091e4")},
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
            false);

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
            false);

    public static NodeDetails fetchLocalConfig(TorusNetwork network) {
        NodeDetails nodeDetails = null;
        switch (network) {
            case SAPPHIRE_DEVNET:
                nodeDetails = NODE_DETAILS_SAPPHIRE_DEVNET;
            case SAPPHIRE_MAINNET:
                nodeDetails = NODE_DETAILS_SAPPHIRE_MAINNET;
            case MAINNET:
                nodeDetails = NODE_DETAILS_MAINNET;
            case AQUA:
                nodeDetails = NODE_DETAILS_AQUA;
            case CYAN:
                nodeDetails = NODE_DETAILS_CYAN;
            case TESTNET:
                nodeDetails = NODE_DETAILS_TESTNET;
        }
        return nodeDetails;
    }
}
