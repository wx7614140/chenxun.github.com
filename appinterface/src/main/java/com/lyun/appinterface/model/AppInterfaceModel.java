package com.lyun.appinterface.model;

import java.io.Serializable;
import java.util.Date;

public class AppInterfaceModel implements Serializable{
	
	 
	 	 	private int id;
	 	 	private String name;
	 	 	private Date createTime;
	 	 	private int status;
	 	 	private String method;
	 	 	private String response;
	 	 	private String remark;
	 
			public void setId(int id){
			this.id=id;
		}
	
	
	    public int getId(){
          return id;
	    }
	
	
			public void setName(String name){
			this.name=name;
		}
	
	
	    public String getName(){
          return name;
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
	
	
			public void setMethod(String method){
			this.method=method;
		}
	
	
	    public String getMethod(){
          return method;
	    }
	
	
			public void setResponse(String response){
			this.response=response;
		}
	
	
	    public String getResponse(){
          return response;
	    }


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		}
	
	
	
	
	
	
		
}