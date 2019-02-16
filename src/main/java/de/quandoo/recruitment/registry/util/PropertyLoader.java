package de.quandoo.recruitment.registry.util;

import de.quandoo.recruitment.registry.ApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This is a utility for reading data from Property file
 */
public class PropertyLoader {
    private static String defaultResourceName = "application.properties";

    /**
     * This method uses the the default property file.
     * @return Properties
     * @throws ApplicationException
     */
    public static Properties getProperties() throws ApplicationException {
        return getProperties(defaultResourceName);
    }

    /**
     *
     * @param filePath is the path of a Property file
     * @return Properties
     * @throws ApplicationException
     */
    public static Properties getProperties(String filePath) throws ApplicationException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(filePath)) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationException(e.getMessage());
        }
        return props;
    }
}

