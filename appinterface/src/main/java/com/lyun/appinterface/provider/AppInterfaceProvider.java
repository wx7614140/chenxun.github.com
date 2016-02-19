package com.lyun.appinterface.provider;

import com.lyun.appinterface.query.AppInterfaceQuery;

import com.lyun.appinterface.support.StringUtil;


public class AppInterfaceProvider {

	
	
	
	public String fetchPageAdvance(AppInterfaceQuery query){  
		StringBuffer sql=new StringBuffer("select * from t_app_interface where 1 = 1" );
		   if(query!=null){
		     		         if(query.getId()>0){
						sql.append(" and id = "+query.getId());
					}
			     	      if(!StringUtil.isEmpty(query.getName())){
						sql.append(" and name like'%"+query.getName()+"%'");
					}
		         			 		         			 		         if(query.getStatuS()>0){
						sql.append(" and status = "+query.getStatuS());
					}
			     			 		         if(!StringUtil.isEmpty(query.getMethod())){
						sql.append(" and method ='"+query.getMethod()+"'");
					}
		         			 		         if(!StringUtil.isEmpty(query.getResponse())){
						sql.append(" and response ='"+query.getResponse()+"'");
					}
		         			 		         if(!StringUtil.isEmpty(query.getRemaRk())){
						sql.append(" and remark ='"+query.getRemaRk()+"'");
					}
		         			 		}
		
		

	
		sql.append(" order by ID desc " );
		sql.append(" limit "+query.getStartRow()+","+query.getEndRow() );
		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(AppInterfaceQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from t_app_interface where 1 = 1" );
		   if(query!=null){
		     		         if(query.getId()>0){
						sql.append(" and id = "+query.getId());
					}
			     			 		         if(!StringUtil.isEmpty(query.getName())){
						sql.append(" and name like'%"+query.getName()+"%'");
					}
		         			 		         			 		         if(query.getStatuS()>0){
						sql.append(" and status = "+query.getStatuS());
					}
			     			 		         if(!StringUtil.isEmpty(query.getMethod())){
						sql.append(" and method ='"+query.getMethod()+"'");
					}
		         			 		         if(!StringUtil.isEmpty(query.getResponse())){
						sql.append(" and response ='"+query.getResponse()+"'");
					}
		         			 		         if(!StringUtil.isEmpty(query.getRemaRk())){
						sql.append(" and remark ='"+query.getRemaRk()+"'");
					}
		         			 		}
		
		
		return sql.toString();
	}
	
	
	

}
