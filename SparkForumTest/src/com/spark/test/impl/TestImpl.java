package com.spark.test.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.spark.utils.*;;



public class TestImpl {
	
	public void showUser(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from reg;";
		try {
			con = C3p0Utils.getConnection();
			stmt = C3p0Utils.getStatement(con);
			rs = C3p0Utils.executeQuery(stmt, sql);
			while(rs.next()){
			//	String name = rs.getString("username");
			//	String password = rs.getString("pwd");
			//	User user = new User(name,password);				
			//	System.out.println(user.getName()+user.getPassword());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				C3p0Utils.closeStatement(stmt);
			}
			if (rs != null){
				C3p0Utils.closeResultSet(rs);
			}
		}
	}
	public void addUser(String name, String password){
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reg where username = '"+name+"';";
		String sql2 = "insert into reg values('"+password+"','"+name+"')";
		try {
			con = C3p0Utils.getConnection();
			stmt = C3p0Utils.getStatement(con);
			rs = C3p0Utils.executeQuery(stmt, sql);
			if(!rs.next()){
				pstmt = C3p0Utils.getPrepareStatement(con, sql2);
				pstmt.executeUpdate();
				System.out.println("插入成功!");
			} else {
				System.out.println("用户存在!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				C3p0Utils.closeStatement(stmt);
			}
			if (rs != null){
				C3p0Utils.closeResultSet(rs);
			}
			if (pstmt != null){
				C3p0Utils.closePrepareStatement(pstmt);
			}
		}
	}
	
	public void deleteUser(String name){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from reg where username = '"+name+"';";
		String sql2 = "delete from reg where username = '"+name+"';";
		try {
			con = C3p0Utils.getConnection();
			stmt = C3p0Utils.getStatement(con);
			rs = C3p0Utils.executeQuery(stmt, sql);
			if(rs.next()){
				stmt.executeUpdate(sql2);
				System.out.println("成功删除!");
			} else {
				System.out.println("用户不存在!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if(stmt != null){
				C3p0Utils.closeStatement(stmt);
			}
			if(rs != null){
				C3p0Utils.closeResultSet(rs);
			}
		}
	}
	public void updateUser(String name, String password){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "update reg set pwd = '"+password+"' where username = '"+name+"';";
		String sql2 = "select * from reg where username = '"+name+"';";
		try {
			con = C3p0Utils.getConnection();
			stmt = C3p0Utils.getStatement(con);
			rs = C3p0Utils.executeQuery(stmt, sql2);
			if(rs.next()){
				stmt.executeUpdate(sql);
				System.out.println("更改成功!");
			} else {
				System.out.println("不存在用户!");
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {
			if(stmt != null){
				C3p0Utils.closeStatement(stmt);
			}
			if(rs != null){
				C3p0Utils.closeResultSet(rs);
			}
		}
	}
	
	public boolean isUser(String name, String password) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from reg where username  = '"+name+"' and '"+password+"';";
		try {
			con = C3p0Utils.getConnection();
			stmt = C3p0Utils.getStatement(con);
			rs = C3p0Utils.executeQuery(stmt, sql);
			if(rs.next()){
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if(stmt != null){
				C3p0Utils.closeStatement(stmt);
			}
			if(rs != null){
				C3p0Utils.closeResultSet(rs);
			}
		}
		return false;
	}
	public static void main(String[] args) {
		//(new TestDao()).showUser();
		//(new TestDao()).addUser("tianjia", "1234567890");;
		//(new TestDao()).deleteUser("tianjia");
		//(new TestDao()).updateUser("tianji","iloveyou");
	}
	
	
}
