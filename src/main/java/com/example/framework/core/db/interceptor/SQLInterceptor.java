package com.example.framework.core.db.interceptor;


import com.example.framework.core.db.util.ReflectHelper;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @Description:运行时SQL输出
 * @author:liaoyuping
 * @version: 
 * @create_date:2015年8月14日
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class SQLInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation
				.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
				.getValueByFieldName(statementHandler, "delegate");
		MappedStatement mappedStatement = (MappedStatement) ReflectHelper
				.getValueByFieldName(delegate, "mappedStatement");
		System.out.println("-------------------------------------------------"
				+ mappedStatement.getId());
		System.out.println(boundSql.getSql());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
