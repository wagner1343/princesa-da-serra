package princesadaserra.java.util.config;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Config {
    private Map<String, String> configMap;

    public Config(){
        configMap = new TreeMap<>();
    }

    public Config(List<Pair<String,String>> configPairs){
        configMap = new TreeMap<>();
        load(configPairs);
    }

    public String getValue(String key){
        return configMap.get(key);
    }

    public String getValue(ConfigKeys key){
        return configMap.get(key.toString());
    }

    public String setValue(ConfigKeys key, String value){
        return configMap.put(key.toString(), value);
    }

    public String setValue(String key, String value){
        return configMap.put(key, value);
    }

    public void load(List<Pair<String, String>> configPairs){
        for(Pair<String, String> pair : configPairs){
            configMap.put(pair.getKey(), pair.getValue());
        }
    }
}
