package com.lyun.appinterface.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lyun.appinterface.mapper.AppInterfaceParamsMapper;
import com.lyun.appinterface.model.AppInterfaceParamsModel;

import com.lyun.appinterface.query.AppInterfaceParamsQuery;
import com.lyun.appinterface.service.AppInterfaceParamsService;

    
@Service("appInterfaceParamsService")
public class AppInterfaceParamsServiceImpl implements AppInterfaceParamsService{
	@Autowired
	private AppInterfaceParamsMapper appInterfaceParamsMapper;

    //查询所有记录 
	public List<AppInterfaceParamsModel> fetchPage(AppInterfaceParamsQuery query){
	
	    		return appInterfaceParamsMapper.fetchPageList(query);
	}
	
	//查询所有记录总数
	public int fetchPageCount(AppInterfaceParamsQuery query){
	    		return appInterfaceParamsMapper.fetchPageCount(query);
	}
	
		
	//根据ID查询指定的数据(不分库)
	public AppInterfaceParamsModel getById(long id){ 
		return appInterfaceParamsMapper.getById(id);
	}
	
		
	//删除 
	public void del(AppInterfaceParamsModel appInterfaceParams){
	    		appInterfaceParamsMapper.del(appInterfaceParams);
	}
	
	//新增
	public long insert(AppInterfaceParamsModel appInterfaceParams){	
	    		return appInterfaceParamsMapper.insert(appInterfaceParams);
		
	}
	
	//修改
	public long update(AppInterfaceParamsModel appInterfaceParams){
	    		return appInterfaceParamsMapper.update(appInterfaceParams);
	}
	
	//高级查询 
	@Override
	public List<AppInterfaceParamsModel> fetchPageAdvance(AppInterfaceParamsQuery query) {
	    		return appInterfaceParamsMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(AppInterfaceParamsQuery query) {
	    		return appInterfaceParamsMapper.fetchPageAdvanceCount(query);
	}

	
	

}
