package com.example.framework.core.db.dialect;

/**
 * @Description:来自rapid_framework框架
 */
public class Dialect {
	public boolean supportsLimit(){
    	return false;
    }

    public boolean supportsLimitOffset() {
    	return supportsLimit();
    }
    
    /**
     * getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit))
     */
    public String getLimitString(String sql, int offset, int limit) {
    	return getLimitString(sql,offset,Integer.toString(offset),limit,Integer.toString(limit));
    }
    
    /**
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 灏嗚繑鍥�
     * select * from user limit :offset,:limit
     * </pre>
     * @return sql
     */
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit,String limitPlaceholder) {
    	throw new UnsupportedOperationException("paged queries not supported");
    }

}
