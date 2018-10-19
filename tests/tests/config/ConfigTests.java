package tests.config;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import princesadaserra.java.util.config.Config;

public class ConfigTests {
    static princesadaserra.java.util.config.Config config = new princesadaserra.java.util.config.Config();

    public void TestGetValueAndSetValueIntegrity(){

        config.setValue("TEST", "TEST");
        Assert.assertEquals(config.getValue("TEST"), "TEST");
    }
}
