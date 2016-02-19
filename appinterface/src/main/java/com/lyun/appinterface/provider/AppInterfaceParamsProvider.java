package com.lyun.appinterface.provider;

import com.lyun.appinterface.query.AppInterfaceParamsQuery;

import com.lyun.appinterface.support.StringUtil;


public class AppInterfaceParamsProvider {

	
	
	
	public String fetchPageAdvance(AppInterfaceParamsQuery query){  
		StringBuffer sql=new StringBuffer("select * from t_app_interface_params where 1 = 1" );
		   if(query!=null){
		     		         if(query.getId()>0){
						sql.append(" and id = "+query.getId());
					}
			     			 		         if(query.getInterfaceId()>0){
						sql.append(" and interface_id = "+query.getInterfaceId());
					}
			     			 		         if(!StringUtil.isEmpty(query.getName())){
						sql.append(" and name ='"+query.getName()+"'");
					}
		         			 		         if(!StringUtil.isEmpty(query.getParamsType())){
						sql.append(" and params_type ='"+query.getParamsType()+"'");
					}
		         			 		         			 		         if(query.getStatuS()>0){
						sql.append(" and status = "+query.getStatuS());
					}
			     			 		         if(query.getIsNull()>0){
						sql.append(" and is_null = "+query.getIsNull());
					}
			     			 		         if(!StringUtil.isEmpty(query.getRemaRk())){
						sql.append(" and remark ='"+query.getRemaRk()+"'");
					}
		         			 		}
		
		

	
		sql.append(" order by ID desc " );
		sql.append(" limit "+query.getStartRow()+","+query.getEndRow() );
		
		
		return sql.toString();
	}
	
	public String fetchPageAdvanceCount(AppInterfaceParamsQuery query){
		StringBuffer sql=new StringBuffer("select count(*) from t_app_interface_params where 1 = 1" );
		   if(query!=null){
		     		         if(query.getId()>0){
						sql.append(" and id = "+query.getId());
					}
			     			 		         if(query.getInterfaceId()>0){
						sql.append(" and interface_id = "+query.getInterfaceId());
					}
			     			 		         if(!StringUtil.isEmpty(query.getName())){
						sql.append(" and name ='"+query.getName()+"'");
					}
		         			 		         if(!StringUtil.isEmpty(query.getParamsType())){
						sql.append(" and params_type ='"+query.getParamsType()+"'");
					}
		         			 		         			 		         if(query.getStatuS()>0){
						sql.append(" and status = "+query.getStatuS());
					}
			     			 		         if(query.getIsNull()>0){
						sql.append(" and is_null = "+query.getIsNull());
					}
			     			 		         if(!StringUtil.isEmpty(query.getRemaRk())){
						sql.append(" and remark ='"+query.getRemaRk()+"'");
					}
		         			 		}
		
		
		return sql.toString();
	}
	
	
	

}
