package com.lyun.appinterface.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.eclipse.jetty.util.StringUtil;

public class TestVelocity {
	
	
	private static final  String driver = "com.mysql.jdbc.Driver";  
	private static final  String url = "jdbc:mysql://db-dev.lvyun.com:3306/lyun?useUnicode=true&characterEncoding=utf-8";  
	private static final  String user = "dbuser";  
	private static final  String password = "123456";  
	
	
	  public static void main(String[] args) throws ResourceNotFoundException, ParseErrorException, Exception {
		  VelocityEngine ve = new VelocityEngine();
		  ve.setProperty("input.encoding", "UTF-8");
		  ve.setProperty("output.encoding", "UTF-8");
		  ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		  ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());	
		  ve.init();
		  
		//是否分库  true:分库  false:不分库
		  boolean isDatabaseShard = false;
		  
		//通过jdbc连接数据库
		  
		  
		  
		  String talbeName="t_app_interface";    //表名
		   String[]  temp=talbeName.substring(2).toLowerCase().split("_");
		    String classNameLowCase="";
	           String classNameUpCase="";
	           for(String str:temp){
	        	   if(str!=null&&str.trim().length()>0){
	        		   //首字母大写
	        		   classNameUpCase+=str.replace(str.charAt(0), (char)((str.charAt(0)-32)));
	        	   }
	           }		 
	           //userAccount   UserAccount
	        classNameLowCase=  classNameUpCase.replace(classNameUpCase.charAt(0), (char)((classNameUpCase.charAt(0)+32)));
		  List<Fild> filds=getFileds(talbeName);
		  
		  //生成model
		  Template actionTpt = ve.getTemplate("template/model.vm");
		  VelocityContext ctx = new VelocityContext();
		  ctx.put("classNameLowCase", classNameLowCase);
		  ctx.put("classNameUpCase", classNameUpCase);
		  ctx.put("filds",filds );
		  //
		//  String rootPath =  "D:/south_lyun/commons/lyun-model/src/main";  //物理跟目录
		  String  rootPath =  "E:/Users/testInterface/appInterface/src/main";  //物理跟目录
		  //生成文件    模板  内容容器  新文件位置
		  merge(actionTpt,ctx,rootPath+"/java/com/lyun/appinterface/model/"+classNameUpCase+"Model.java");
		  System.out.println("success...model");
		  
		  
		  //生成mapper
		  Template mapperTmp = ve.getTemplate("template/mapper.vm");
		  VelocityContext mapperCtx = new VelocityContext();
		  String columns = getColumns(talbeName);
		  String property = getProperty(filds);
		  String update = getUpdate(columns, filds);
		  mapperCtx.put("classNameUpCase",classNameUpCase );
		  mapperCtx.put("classNameLowCase",classNameLowCase );
		  mapperCtx.put("talbeName",talbeName );
		  mapperCtx.put("columns",columns );
		  mapperCtx.put("insert", columns.substring(3));  //去掉ID,
		  mapperCtx.put("insertProperty",property.substring(6) );  //去掉#{id},

		  mapperCtx.put("property",property );
		  mapperCtx.put("update",update );
		  mapperCtx.put("isDatabaseShard",isDatabaseShard);  
		//  rootPath="D:/south_lyun/commons/lyun-dal/src/main";
		  merge(mapperTmp,mapperCtx,rootPath+"/java/com/lyun/appinterface/mapper/"+classNameUpCase+"Mapper.java");
		  
		  System.out.println("success...mapper");
		  
		  //生成provider
		  Template providerTmp = ve.getTemplate("template/provider.vm");
		  VelocityContext providerCtx = new VelocityContext();
		  providerCtx.put("classNameUpCase",classNameUpCase );
		  providerCtx.put("classNameLowCase",classNameLowCase );
		  providerCtx.put("talbeName",talbeName );
		  providerCtx.put("filds",filds );
		//  rootPath="D:/south_lyun/commons/lyun-dal/src/main";
		  merge(providerTmp,providerCtx,rootPath+"/java/com/lyun/appinterface/provider/"+classNameUpCase+"Provider.java");
		  
		  System.out.println("success...provider");
		  
		  
		  //生成query
		  Template queryTmp = ve.getTemplate("template/query.vm");
		  VelocityContext queryCtx = new VelocityContext();
		  queryCtx.put("classNameUpCase",classNameUpCase );
		  queryCtx.put("classNameLowCase",classNameLowCase );
		  queryCtx.put("filds",filds );
		//  rootPath="D:/south_lyun/commons/lyun-model/src/main";
		  merge(queryTmp,queryCtx,rootPath+"/java/com/lyun/appinterface/query/"+classNameUpCase+"Query.java");
		  
		  System.out.println("success...query");
		  
		  //生成server
		  Template serviceTmp = ve.getTemplate("template/service.vm");
		  VelocityContext serviceCtx = new VelocityContext();
		  serviceCtx.put("classNameUpCase",classNameUpCase );
		  serviceCtx.put("classNameLowCase",classNameLowCase );
		  serviceCtx.put("isDatabaseShard",isDatabaseShard);  
		//  rootPath="D:/south_lyun/services/lyun-service-api/src/main";
		  merge(serviceTmp,serviceCtx,rootPath+"/java/com/lyun/appinterface/service/"+classNameUpCase+"Service.java");
		  
		  System.out.println("success...server");
		  
		  //生成serverImpl
		  Template serviceImplTmp = ve.getTemplate("template/serviceImpl.vm");
		  VelocityContext serviceImplCtx = new VelocityContext();
		  serviceImplCtx.put("classNameUpCase",classNameUpCase );
		  serviceImplCtx.put("classNameLowCase",classNameLowCase );
		  //是否分库  分库 isDatabaseShard的值为:true  不分库未: false;
		  serviceCtx.put("isDatabaseShard",isDatabaseShard);  
	//	  rootPath="D:/south_lyun/services/lyun-service/src/main";
		  merge(serviceImplTmp,serviceImplCtx,rootPath+"/java/com/lyun/appinterface/serviceimpl/"+classNameUpCase+"ServiceImpl.java");
		  
		  System.out.println("success...serviceImpl");
		  
		  
		  //生成mybatis_xxxxx.xml
		  Template mybatis_xxxxxTmp = ve.getTemplate("template/mybatis_xml.vm");
		  VelocityContext mybatis_xxxxxCtx = new VelocityContext();
		  mybatis_xxxxxCtx.put("classNameUpCase",classNameUpCase );
		  mybatis_xxxxxCtx.put("classNameLowCase",classNameLowCase );
		  mybatis_xxxxxCtx.put("filds",filds );
	//	  rootPath="D:/south_lyun/commons/lyun-dal/src/main";
		  merge(mybatis_xxxxxTmp,mybatis_xxxxxCtx,rootPath+"/resources/META-INF/mybatis/mapper/"+"MYBATIS_"+classNameUpCase+"XmlMapper.xml");
		  
		  System.out.println("success...mybatis_xxxxx.xml");
		  
		  
		  }

		  private static void merge(Template template, VelocityContext ctx, String path) {
		  PrintWriter writer = null;
		  try {
		  writer = new PrintWriter(path);
		  template.setEncoding("UTF-8");
		  template.merge(ctx, writer);
		  writer.flush();
		  } catch (FileNotFoundException e) {
		  e.printStackTrace();
		  } catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		  writer.close();
		  }
	}
		  
		 public static  List<Fild>  getFileds(String table) throws ClassNotFoundException, SQLException{
			 List<Fild> list=new ArrayList<Fild>();
			 
		  
		        Class.forName(driver);  
		        Connection conn = DriverManager.getConnection(url, user, password);  
		        if (!conn.isClosed())  
		            System.out.println("Succeeded connecting to the Database!");  
		        else  
		            System.err.println("connect filed");  
		        // 获取所有表名  
		        Statement statement = conn.createStatement();  
		  
		      //  getTables(conn);  
		  
		        ResultSet resultSet = statement  
		                .executeQuery("select * from "+table);  
		        // 获取列名  
		        ResultSetMetaData metaData = resultSet.getMetaData();  
		        for (int i = 0; i < metaData.getColumnCount(); i++) {  
		            // resultSet数据下标从1开始  
		            String columnName = metaData.getColumnName(i + 1);  
		            int type = metaData.getColumnType(i + 1);  
		           String[]  temp=columnName.toLowerCase().split("_");
		           String fild="";
		           String property="";
		           for(String str:temp){
		        	   if(str!=null&&str.trim().length()>0){
		        		   //首字母大写
		        		   property+=str.replaceFirst(str.charAt(0)+"", (char)((str.charAt(0)-32))+"");
		        	   }
		        	   
		           }
		           //首字母小写
		           fild=property.replace(property.charAt(0), (char)((property.charAt(0)+32)));
		            switch (type) {
					case Types.INTEGER:
						 list.add(new Fild("int", fild, property,columnName));
						break;
					case Types.VARCHAR:
						 list.add(new Fild("String", fild, property,columnName));
						 break;
					case Types.DATE:
					case Types.TIMESTAMP:
					case Types.TIME:
						list.add(new Fild("Date",fild,property,columnName));
						break;
					default:
						break;
					}
		          
		            System.out.print(columnName + "\t");  
		        }  
		        System.out.println();  
		        statement.close();  
		        conn.close();
				return list;  
		    }  
		 
		 /**
		  * 获取表所有的列名 
		  * @param table
		  * @return    ID,USER_NAME,CONTACT_USER_NAME
		  * @throws ClassNotFoundException
		  * @throws SQLException
		  */
		 public static  String  getColumns(String table) throws ClassNotFoundException, SQLException{
			 StringBuffer str = new StringBuffer();
		
		  
		        Class.forName(driver);  
		        Connection conn = DriverManager.getConnection(url, user, password);  
		        if (!conn.isClosed())  
		            System.out.println("Succeeded connecting to the Database!");  
		        else  
		            System.err.println("connect filed");  
		        // 获取所有表名  
		        Statement statement = conn.createStatement();  
		  
		  
		        ResultSet resultSet = statement  
		                .executeQuery("select * from "+table);  
		        // 获取列名  
		        ResultSetMetaData metaData = resultSet.getMetaData();  
		        for (int i = 0; i < metaData.getColumnCount(); i++) {  
		            // resultSet数据下标从1开始  
		            String columnName = metaData.getColumnName(i + 1);  
		            str.append(columnName+",");  
		        }  
		        String strTem = str.substring(0, str.length() - 1).toString();
		        statement.close();  
		        conn.close();
				return strTem;  
		    }  
		 
		 /**
		  * 获取表所有的列名  ${userName}
		  * @param table
		  * @return    ${userName}
		  * @throws ClassNotFoundException
		  * @throws SQLException
		  */
		 public static  String  getProperty(List<Fild> list){
			 StringBuffer str = new StringBuffer();
			 if(list!=null && list.size()>0){
				 for(int i=0; i<list.size(); i++){
					 str.append("#{"+list.get(i).getFild()+"},");
				 }
			 }
			 
			String strTem = str.substring(0, str.length() - 1 );

				return strTem;  
		    }  
		 
		 
		 /**
		  * 获取表所有的列名  ${userName}
		  * @param table
		  * @return    ${userName}
		  * @throws ClassNotFoundException
		  * @throws SQLException
		  */
		 public static  String  getUpdate(String columns,List<Fild> filds){
			 StringBuffer str = new StringBuffer();
			 if(!(StringUtil.nonNull(columns).trim().equals("")) && filds!=null && filds.size()>0){
				 String[] columnArray = columns.split(",");
				 
				 for(int i=0; i <columnArray.length; i++){
					 for(int j=0; j<filds.size(); j++){
						 if(i==j && i!=0 && j!=0){
							 str.append(columnArray[i]+"=#{"+filds.get(j).getFild()+"},");
						 }
					 }
				 }
				 
			 }

			 
			String strTem = str.substring(0, str.length() - 1 );

				return strTem;  
		    }  

		  
	
		 
		    public static String convertDatabaseCharsetType(String in, String type) {  
		        String dbUser;  
		        if (in != null) {  
		            if (type.equals("oracle")) {  
		                dbUser = in.toUpperCase();  
		            } else if (type.equals("postgresql")) {  
		                dbUser = "public";  
		            } else if (type.equals("mysql")) {  
		                dbUser = null;  
		            } else if (type.equals("mssqlserver")) {  
		                dbUser = null;  
		            } else if (type.equals("db2")) {  
		                dbUser = in.toUpperCase();  
		            } else {  
		                dbUser = in;  
		            }  
		        } else {  
		            dbUser = "public";  
		        }  
		        return dbUser;  
		    }  
		  
		    private static void getTables(Connection conn) throws SQLException {  
		        DatabaseMetaData dbMetData = conn.getMetaData();  
		        // mysql convertDatabaseCharsetType null  
		        ResultSet rs = dbMetData.getTables(null,  
		                convertDatabaseCharsetType("root", "mysql"), null,  
		                new String[] { "TABLE", "VIEW" });  
		  
		        while (rs.next()) {  
		            if (rs.getString(4) != null  
		                    && (rs.getString(4).equalsIgnoreCase("TABLE") || rs  
		                            .getString(4).equalsIgnoreCase("VIEW"))) {  
		                String tableName = rs.getString(3).toLowerCase();  
		                System.out.print(tableName + "\t");  
		                // 根据表名提前表里面信息：  
		                ResultSet colRet = dbMetData.getColumns(null, "%", tableName,  
		                        "%");  
		                while (colRet.next()) {  
		                    String columnName = colRet.getString("COLUMN_NAME");  
		                    String columnType = colRet.getString("TYPE_NAME");  
		                    int datasize = colRet.getInt("COLUMN_SIZE");  
		                    int digits = colRet.getInt("DECIMAL_DIGITS");  
		                    int nullable = colRet.getInt("NULLABLE");  
		                    // System.out.println(columnName + " " + columnType + " "+  
		                    // datasize + " " + digits + " " + nullable);  
		                }  
		  
		            }  
		        }  
		        System.out.println();  
		  
		        // resultSet数据下标从1开始 ResultSet tableRet =  
		        //conn.getMetaData().getTables(null, null, "%", new String[] { "TABLE" });  
		        //while (tableRet.next()) {  
		        //  System.out.print(tableRet.getString(3) + "\t");  
		        //}  
		        //System.out.println();  
		  
		    }  
			 
		

}
