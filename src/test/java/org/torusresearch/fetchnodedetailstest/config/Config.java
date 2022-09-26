package org.torusresearch.fetchnodedetailstest.config;

import com.google.gson.Gson;
import org.torusresearch.fetchnodedetails.types.NodeDetails;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Config {
    public static NodeDetails MAINNET;
    public static NodeDetails TESTNET;
    public static NodeDetails CYAN;

    public static NodeDetails AQUA;

    static {
        try {
            String basePath = "src/test/java/org/torusresearch/fetchnodedetailstest/config/";
            MAINNET = getNodeDetailsFromFile(basePath + "mainnet.json");
            TESTNET = getNodeDetailsFromFile(basePath + "testnet.json");
            CYAN = getNodeDetailsFromFile(basePath + "cyan.json");
            AQUA = getNodeDetailsFromFile(basePath + "aqua.json");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static NodeDetails getNodeDetailsFromFile(String path) {
        try {

            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = new FileReader(new File(path));

            // convert JSON file to map
            NodeDetails details = gson.fromJson(reader, NodeDetails.class);

            System.out.println(details.toString());

            // close reader
            reader.close();

            return details;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
