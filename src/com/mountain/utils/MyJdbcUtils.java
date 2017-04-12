package com.mountain.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC的第三个工具类（使用C3P0的连接池）
 * @author 
 */
public class MyJdbcUtils {
	
	// C3P0的连接池
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 获取C3P0的连接池
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/**
	 * 获取链接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConn() throws Exception{
		// 获取连接，从连接池中
		return dataSource.getConnection();
	}
	
	
	/**
	 * 释放资源（释放查询）
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
	
	/**
	 * 释放资源（增删改）
	 * @param stmt
	 * @param conn
	 */
	public static void release(Statement stmt,Connection conn){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		if(conn!=null){
			try {
				// 现在close是归还连接的方法，不是销毁连接
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}

}
