package edu.gatech.courseManagement.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Generic utility class
 */
public class CourseManagementUtil {
    private static Map<String, String> _properties = new HashMap<String,String>();

    private static void init()
    {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            properties.load(classLoader.getResourceAsStream("CourseManagement.properties"));

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                _properties.put( key, value );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * read the property file
     * @param key
     * @return
     */
    public static String getProperty( String key )
    {
        if( _properties.isEmpty() )
        {
            init();
        }
        if( _properties.containsKey( key ) )
            return _properties.get(key);
        return null;
    }

    /**
     * create object from class mentioned as parameter which is in string
     * reflection
     * @param clazzName
     * @return
     */
    public static Object createRuntimeObject( String clazzName )
    {
        try {
            return Class.forName( clazzName ).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
