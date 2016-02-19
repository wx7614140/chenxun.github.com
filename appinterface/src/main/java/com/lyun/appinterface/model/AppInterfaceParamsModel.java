package com.lyun.appinterface.model;

import java.io.Serializable;
import java.util.Date;

public class AppInterfaceParamsModel implements Serializable{
	
	 
	 	 	private int id;
	 	 	private int interfaceid;
	 	 	private String name;
	 	 	private String paramsType;
	 	 	private Date createTime;
	 	 	private int status;
	 	 	private int isNull;
	 	 	private String remark;
	 
			public void setId(int id){
			this.id=id;
		}
	
	
	    public int getId(){
          return id;
	    }
	
	
			public void setInterfaceId(int interfaceid){
			this.interfaceid=interfaceid;
		}
	
	
	    public int getInterfaceId(){
          return interfaceid;
	    }
	
	
			public void setName(String name){
			this.name=name;
		}
	
	
	    public String getName(){
          return name;
	    }
	
	
			public void setParamsType(String paramsType){
			this.paramsType=paramsType;
		}
	
	
	    public String getParamsType(){
          return paramsType;
	    }
	
	
			public void setCreateTime(Date createTime){
			this.createTime=createTime;
		}
	
	
	    public Date getCreateTime(){
          return createTime;
	    }
	
	
			public void setStatuS(int status){
			this.status=status;
		}
	
	
	    public int getStatuS(){
          return status;
	    }
	
	
			public void setIsNull(int isNull){
			this.isNull=isNull;
		}
	
	
	    public int getIsNull(){
          return isNull;
	    }


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		}
	
	
		
	
	
	  
	
	
	
		
}