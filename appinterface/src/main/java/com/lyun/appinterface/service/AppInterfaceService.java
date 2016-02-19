package com.lyun.appinterface.service;

import com.lyun.appinterface.model.AppInterfaceModel;
import com.lyun.appinterface.model.AppInterfaceParamsModel;
import com.lyun.appinterface.query.AppInterfaceQuery;

import java.util.List;
import java.util.Map;


public interface AppInterfaceService{

    //查询所有记录
	public List<AppInterfaceModel> fetchPage(AppInterfaceQuery query);
	
	//查询所有记录总数
	public int fetchPageCount(AppInterfaceQuery query);
	
		
	//根据ID查询指定的数据(不分库)
	public AppInterfaceModel getById(long id);
	
		
	
	//删除
	public void del(AppInterfaceModel appInterface);
	
	//新增
	public long insert(AppInterfaceModel appInterface);
	
	//修改
	public long update(AppInterfaceModel appInterface);
	
	//高级查询
	public List<AppInterfaceModel> fetchPageAdvance(AppInterfaceQuery query);
	
	//高级查询总记录数
	public int fetchPageAdvanceCount(AppInterfaceQuery query);
	
	public void insertMap(Map<AppInterfaceModel, List<AppInterfaceParamsModel>> map) ;

}
