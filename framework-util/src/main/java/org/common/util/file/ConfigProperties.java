package org.common.util.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.common.util.StringUtil;


/**
 * 加载properties类型的资源文件.<br>
 * @author pj
 *
 */
public class ConfigProperties {

	private static final Logger log = Logger.getLogger(ConfigProperties.class);
	private static String DEFAULT_PROPERTIES_NAME = "conf.properties";
	private static Properties resourceBundle = new Properties();
	
	static{
		try {
			resourceBundle.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_NAME));
		}catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	/**
	 * 加载properties类型的资源文件
	 * @param propertiesName
	 * @throws IOException
	 */
	public static void reload(String propertiesName) throws IOException{
		if(StringUtil.isNotBlank(propertiesName)){
			DEFAULT_PROPERTIES_NAME = propertiesName;
		}
		InputStream inStream = ConfigProperties.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_NAME) ;
        resourceBundle.load(inStream);
	}
	
    /**
     * Returns the string from the plugin's resource bundle,
     * or 'key' if not found.
     */
    public static String getProperty(String key) {
    	return resourceBundle.getProperty(key);
    }
    
    public static void setProperty(String key,String value){
    	resourceBundle.setProperty(key, value);
    }
	
    public static Set<Entry<Object, Object>> entrySet(){
    	 return resourceBundle.entrySet();
    }
}
