package com.example.framework.core.db.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

/**
 * public class ConnectionUtils {
 *     static Properties properties = new Properties(); 
 *     // ... do load properties 
 *     
 *     // delegate to properties
 * 	   static PropertiesHelper props = new PropertiesHelper(properties);
 *     public static Connection getConnection() {
 *     		// use getRequiredProperty() 
 *     		DriverManager.getConnection(props.getRequiredString("jdbc.url"));
 *     }
 * }
 * new PropertiesHelper(properties,PropertiesHelper.SYSTEM_PROPERTIES_MODE_OVERRIDE)
 * </pre>
 * @author badqiu
 *
 * 
 * 
 * 来自： 来自rapid_framework框架
 */
public class PropertiesHelper {
	/** Never check system properties. */
	public static final int SYSTEM_PROPERTIES_MODE_NEVER = 0;

	/**
	 * Check system properties if not resolvable in the specified properties.
	 * This is the default.
	 */
	public static final int SYSTEM_PROPERTIES_MODE_FALLBACK = 1;

	/**
	 * Check system properties first, before trying the specified properties.
	 * This allows system properties to override any other property source.
	 */
	public static final int SYSTEM_PROPERTIES_MODE_OVERRIDE = 2;
	
	private int systemPropertiesMode = SYSTEM_PROPERTIES_MODE_NEVER;
	private Properties p;

	public PropertiesHelper(Properties p) {
		setProperties(p);
	}
	
	public PropertiesHelper(Properties p,int systemPropertiesMode) {
		setProperties(p);
		if(systemPropertiesMode != SYSTEM_PROPERTIES_MODE_NEVER && systemPropertiesMode != SYSTEM_PROPERTIES_MODE_FALLBACK && systemPropertiesMode != SYSTEM_PROPERTIES_MODE_OVERRIDE) {
			throw new IllegalArgumentException("error systemPropertiesMode mode:"+systemPropertiesMode);
		}
		this.systemPropertiesMode = systemPropertiesMode;
	}
	
	public Properties getProperties() {
		return p;
	}
	
	public void setProperties(Properties props) {
		if(props == null) throw new IllegalArgumentException("properties must be not null");
		this.p = props;
	}
	
	public String getRequiredString(String key) {
		String value = getProperty(key);
		if(isBlankString(value)) {
			throw new IllegalStateException("required property is blank by key="+key);
		}
		return value;
	}
	
	public String getNullIfBlank(String key) {
		String value = getProperty(key);
		if(isBlankString(value)) {
			return null;
		}
		return value;
	}
	
	public String getNullIfEmpty(String key) {
		String value = getProperty(key);
		if(value == null || "".equals(value)) {
			return null;
		}
		return value;
	}
	
	/**
	 * 灏濊瘯浠嶴ystem.getProperty(key)鍙奡ystem.getenv(key)寰楀埌鍊�
	 * @return
	 */
	public String getAndTryFromSystem(String key) {
		String value = getProperty(key);
		if(isBlankString(value)) {
			value = getSystemProperty(key);
		}
		return value;
	}

	private String getSystemProperty(String key) {
		String value;
		value = System.getProperty(key);
		if(isBlankString(value)) {
			value = System.getenv(key);
		}
		return value;
	}
	
	public Integer getInteger(String key) {
		String v = getProperty(key);
		if(v == null){
			return null;
		}
		return Integer.parseInt(v);
	}
	
	public int getInt(String key,int defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Integer.parseInt(getRequiredString(key));
	}
	
	public int getRequiredInt(String key) {
		return Integer.parseInt(getRequiredString(key));
	}
	
	public Long getLong(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Long.parseLong(getRequiredString(key));
	}
	
	public long getLong(String key,long defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Long.parseLong(getRequiredString(key));
	}
	
	public Long getRequiredLong(String key) {
		return Long.parseLong(getRequiredString(key));
	}
	
	public Boolean getBoolean(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Boolean.parseBoolean(getRequiredString(key));
	}
	
	public boolean getBoolean(String key,boolean defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Boolean.parseBoolean(getRequiredString(key));
	}
	
	public boolean getRequiredBoolean(String key) {
		return Boolean.parseBoolean(getRequiredString(key));
	}
	
	public Float getFloat(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Float.parseFloat(getRequiredString(key));
	}
	
	public float getFloat(String key,float defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Float.parseFloat(getRequiredString(key));
	}
	
	public Float getRequiredFloat(String key) {
		return Float.parseFloat(getRequiredString(key));
	}
	
	public Double getDouble(String key) {
		if(getProperty(key) == null) {
			return null;
		}
		return Double.parseDouble(getRequiredString(key));
	}
	
	public double getDouble(String key,double defaultValue) {
		if(getProperty(key) == null) {
			return defaultValue;
		}
		return Double.parseDouble(getRequiredString(key));
	}
	
	public Double getRequiredDouble(String key) {
		return Double.parseDouble(getRequiredString(key));
	}
	
	public URL getURL(String key) throws IllegalArgumentException {
        try {
            return new URL(getProperty(key));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Property " + key + " must be a valid URL (" + getProperty(key) + ")");
        }
    }
    
    public Object getClassInstance(String key) throws IllegalArgumentException {
        String s = (String) getProperty(key);
        if (s == null || "".equals(s.trim())) {
            throw new IllegalArgumentException("Property " + key + " must be a valid classname  : " + key);
        }
        try {
            return Class.forName(s).newInstance();
        } catch (ClassNotFoundException nfe) {
            throw new IllegalArgumentException(s + ": invalid class name for key " + key, nfe);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(s + ": class could not be reflected " + s, e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(s + ": class could not be reflected " + s, e);
        }
    }

    public Object getClassInstance(String key, Object defaultinstance)
            throws IllegalArgumentException {
        return (containsKey(key) ? getClassInstance(key) : defaultinstance);
    }
    
	/** setProperty(String key,int value) ... start */
	
	public Object setProperty(String key,int value) {
		return setProperty(key, String.valueOf(value));
	}
	
	public Object setProperty(String key,long value) {
		return setProperty(key, String.valueOf(value));
	}
	
	public Object setProperty(String key,float value) {
		return setProperty(key, String.valueOf(value));
	}
	
	public Object setProperty(String key,double value) {
		return setProperty(key, String.valueOf(value));
	}
	
	public Object setProperty(String key,boolean value) {
		return setProperty(key, String.valueOf(value));
	}

	public String[] getStringArray(String key) {
		String v = getProperty(key);
		if(v == null) {
			return new String[0];
		}else {
		    return tokenizeToStringArray(v, ", \t\n\r\f");
		}
	}

	public int[] getIntArray(String key) {
		return toIntArray(getStringArray(key));
	}

	public Properties getStartsWithProperties(String prefix) {
		if(prefix == null) throw new IllegalArgumentException("'prefix' must be not null");
		
		Properties props = getProperties();
		Properties result = new Properties();
		for(Entry<Object, Object> entry : props.entrySet()) {
			String key = (String)entry.getKey();
			if(key != null && key.startsWith(prefix)) {
				result.put(key.substring(prefix.length()), entry.getValue());
			}
		}
		return result;
	}
	
	/** delegate method start */
	
	public String getProperty(String key, String defaultValue) {
	    String value = getProperty(key);
	    if(isBlankString(value)) {
	        return defaultValue;
	    }
		return value;
	}

	public String getProperty(String key) {
		String propVal = null;
		if (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_OVERRIDE) {
			propVal = getSystemProperty(key);
		}
		if (propVal == null) {
			propVal = p.getProperty(key);
		}
		if (propVal == null && systemPropertiesMode == SYSTEM_PROPERTIES_MODE_FALLBACK) {
			propVal = getSystemProperty(key);
		}
		return propVal;
	}

	public Object setProperty(String key,String value) {
		return p.setProperty(key, value);
	}

	public void clear() {
		p.clear();
	}

	public Set<Entry<Object, Object>> entrySet() {
		return p.entrySet();
	}

	public Enumeration<?> propertyNames() {
		return p.propertyNames();
	}

	public boolean contains(Object value) {
		return p.contains(value);
	}

	public boolean containsKey(Object key) {
		return p.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return p.containsValue(value);
	}

	public Enumeration<Object> elements() {
		return p.elements();
	}

	public Object get(Object key) {
		return p.get(key);
	}

	public boolean isEmpty() {
		return p.isEmpty();
	}

	public Enumeration<Object> keys() {
		return p.keys();
	}

	public Set<Object> keySet() {
		return p.keySet();
	}

	public void list(PrintStream out) {
		p.list(out);
	}

	public void list(PrintWriter out) {
		p.list(out);
	}

	public void load(InputStream inStream) throws IOException {
		p.load(inStream);
	}

	public void loadFromXML(InputStream in) throws IOException,
			InvalidPropertiesFormatException {
		p.loadFromXML(in);
	}

	public Object put(Object key, Object value) {
		return p.put(key, value);
	}

	public void putAll(Map<? extends Object, ? extends Object> t) {
		p.putAll(t);
	}

	public Object remove(Object key) {
		return p.remove(key);
	}

	/** @deprecated */
	public void save(OutputStream out, String comments) {
		p.save(out, comments);
	}

	public int size() {
		return p.size();
	}

	public void store(OutputStream out, String comments) throws IOException {
		p.store(out, comments);
	}

	public void storeToXML(OutputStream os, String comment, String encoding)
			throws IOException {
		p.storeToXML(os, comment, encoding);
	}

	public void storeToXML(OutputStream os, String comment) throws IOException {
		p.storeToXML(os, comment);
	}

	public Collection<Object> values() {
		return p.values();
	}
	
	public String toString() {
		return p.toString();
	}
	
	public static Properties restoreFromString(String str) {
		if(str == null) return new Properties();
		Properties p = new Properties();
		try {
			p.load(new ByteArrayInputStream(str.getBytes()));
		}catch(IOException e) {
			throw new IllegalStateException("restore properties from String occer error. str:"+str,e);
		}
		return p;
	}

	private static boolean isBlankString(String value) {
		return value == null || "".equals(value.trim());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static String[] tokenizeToStringArray(String str,String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str,seperators);
		List result = new ArrayList();
		
		while(tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[])result.toArray(new String[result.size()]);
	}
	
	private static int[] toIntArray(String[] array) {
		int[] result = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			result[i] = Integer.parseInt(array[i]);
		}
		return result;
	}
}
