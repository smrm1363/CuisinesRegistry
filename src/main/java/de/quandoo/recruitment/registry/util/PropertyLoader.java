package de.quandoo.recruitment.registry.util;

import de.quandoo.recruitment.registry.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    static String defaultResourceName = "application.properties";
    public static Properties getProperties() throws ApplicationException {
        return getProperties(defaultResourceName);
    }
    public static Properties getProperties(String resourceName) throws ApplicationException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(e.getMessage());
        }
        return props;
    }
}

