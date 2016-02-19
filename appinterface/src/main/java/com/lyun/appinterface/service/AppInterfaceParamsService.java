package com.lyun.appinterface.service;

import com.lyun.appinterface.model.AppInterfaceParamsModel;
import com.lyun.appinterface.query.AppInterfaceParamsQuery;

import java.util.List;


public interface AppInterfaceParamsService{

    //查询所有记录
	public List<AppInterfaceParamsModel> fetchPage(AppInterfaceParamsQuery query);
	
	//查询所有记录总数
	public int fetchPageCount(AppInterfaceParamsQuery query);
	
		
	//根据ID查询指定的数据(不分库)
	public AppInterfaceParamsModel getById(long id);
	
		
	
	//删除
	public void del(AppInterfaceParamsModel appInterfaceParams);
	
	//新增
	public long insert(AppInterfaceParamsModel appInterfaceParams);
	
	//修改
	public long update(AppInterfaceParamsModel appInterfaceParams);
	
	//高级查询
	public List<AppInterfaceParamsModel> fetchPageAdvance(AppInterfaceParamsQuery query);
	
	//高级查询总记录数
	public int fetchPageAdvanceCount(AppInterfaceParamsQuery query);
	
	

}
