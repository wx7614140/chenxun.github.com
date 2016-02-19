package com.lyun.appinterface.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.lyun.appinterface.model.AppInterfaceModel;
import com.lyun.appinterface.query.AppInterfaceQuery;

public interface  AppInterfaceMapper{
	

																																																																																																																				
	public String columns="id,name,create_time,status,method,response,remark";
	
	public String insert="name,create_time,status,method,response,remark";
																																																																																																												
	public String property="#{id},#{name},#{createTime},#{status},#{method},#{response},#{remark}";
	
	public String insertProperty="#{name},#{createTime},#{status},#{method},#{response},#{remark}";
																																																																																																																				
	public String update="name=#{name},create_time=#{createTime},status=#{status},method=#{method},response=#{response},remark=#{remark}";
	
	@Select("select "+columns+" FROM t_app_interface  limit #{startRow},#{endRow}")
	@ResultMap(value="com.lyun.appinterface.mapper.AppInterfaceMapper.AppInterfaceModelMap")
	public List<AppInterfaceModel> fetchPageList(AppInterfaceQuery query);
	
	@Select("select count(*) from t_app_interface where 1 = 1 ")
	public int fetchPageCount(AppInterfaceQuery query);
	
	@Select("select "+columns+" from t_app_interface where ID=#{id}")
	@ResultMap(value="com.lyun.appinterface.mapper.AppInterfaceMapper.AppInterfaceModelMap")
	public AppInterfaceModel getById(long id);
	
	@Insert("insert into t_app_interface ("+insert+") values ("+insertProperty+")")
	public long insert(AppInterfaceModel appInterface);

	@Update("update t_app_interface set "+update+" where ID=#{id}")
	public long update(AppInterfaceModel appInterface); 
	
	@Delete("delete from t_app_interface where 1 = 1 and ID=#{id} ")
	public void del(AppInterfaceModel appInterface);

	@SelectProvider(type=com.lyun.appinterface.provider.AppInterfaceProvider.class,method="fetchPageAdvance")
	@ResultMap(value="com.lyun.appinterface.mapper.AppInterfaceMapper.AppInterfaceModelMap")
	public List<AppInterfaceModel> fetchPageAdvance(AppInterfaceQuery query);  
	
	
	@SelectProvider(type=com.lyun.appinterface.provider.AppInterfaceProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(AppInterfaceQuery query);
	
	@Select("select max(id) from t_app_interface ")
	public int getByMaxId();
	
	
}