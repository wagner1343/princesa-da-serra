package princesadaserra.java.util.config;

import java.util.Map;
import java.util.TreeMap;

public class Config {
    private Map<String, String> configMap;

    public Config(){
        configMap = new TreeMap<>();
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
}
