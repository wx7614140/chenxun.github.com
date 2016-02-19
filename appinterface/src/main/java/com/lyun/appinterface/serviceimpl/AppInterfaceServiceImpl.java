package com.lyun.appinterface.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lyun.appinterface.mapper.AppInterfaceMapper;
import com.lyun.appinterface.model.AppInterfaceModel;
import com.lyun.appinterface.model.AppInterfaceParamsModel;
import com.lyun.appinterface.query.AppInterfaceQuery;
import com.lyun.appinterface.service.AppInterfaceParamsService;
import com.lyun.appinterface.service.AppInterfaceService;

    
@Service("appInterfaceService")
public class AppInterfaceServiceImpl implements AppInterfaceService{
	@Autowired
	private AppInterfaceMapper appInterfaceMapper;
	@Autowired
	private AppInterfaceParamsService appInterfaceParamsService;
    //查询所有记录 
	public List<AppInterfaceModel> fetchPage(AppInterfaceQuery query){
	
	    		return appInterfaceMapper.fetchPageList(query);
	}
	
	//查询所有记录总数
	public int fetchPageCount(AppInterfaceQuery query){
	    		return appInterfaceMapper.fetchPageCount(query);
	}
	
		
	//根据ID查询指定的数据(不分库)
	public AppInterfaceModel getById(long id){ 
		return appInterfaceMapper.getById(id);
	}
	
		
	//删除 
	public void del(AppInterfaceModel appInterface){
	    		appInterfaceMapper.del(appInterface);
	}
	
	//新增
	public long insert(AppInterfaceModel appInterface){	
		
	    		return appInterfaceMapper.insert(appInterface);
		
	}
	
	//修改
	public long update(AppInterfaceModel appInterface){
	    		return appInterfaceMapper.update(appInterface);
	}
	
	//高级查询 
	@Override
	public List<AppInterfaceModel> fetchPageAdvance(AppInterfaceQuery query) {
	    		return appInterfaceMapper.fetchPageAdvance(query);
	}
	
	//高级查询总记录数
	@Override
	public int fetchPageAdvanceCount(AppInterfaceQuery query) {
	    		return appInterfaceMapper.fetchPageAdvanceCount(query);
	}

	@Override
	public void insertMap(Map<AppInterfaceModel, List<AppInterfaceParamsModel>> map) {
		
		for(Entry<AppInterfaceModel, List<AppInterfaceParamsModel>> e : map.entrySet()){
		   long i=	insert(e.getKey());
		   
		   
		   if(i>0){
			   int max=appInterfaceMapper.getByMaxId();
			   List<AppInterfaceParamsModel> list=e.getValue();
			    for(AppInterfaceParamsModel p:list){
			    	p.setInterfaceId(max);
			    	appInterfaceParamsService.insert(p);
			    }
			  
		   }
		   
			
		}
		
	}
	

}
