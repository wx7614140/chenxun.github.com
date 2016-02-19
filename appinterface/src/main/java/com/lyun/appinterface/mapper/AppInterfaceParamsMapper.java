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

import com.lyun.appinterface.model.AppInterfaceParamsModel;
import com.lyun.appinterface.query.AppInterfaceParamsQuery;

public interface  AppInterfaceParamsMapper{
	

																																																																																																																				
	public String columns="id,interface_id,name,params_type,create_time,status,is_null,remark";
	
	public String insert="interface_id,name,params_type,create_time,status,is_null,remark";
																																																																																																												
	public String property="#{id},#{interfaceid},#{name},#{paramsType},#{createTime},#{status},#{isNull},#{remark}";
	
	public String insertProperty="#{interfaceid},#{name},#{paramsType},#{createTime},#{status},#{isNull},#{remark}";
																																																																																																																				
	public String update="interface_id=#{interfaceid},name=#{name},params_type=#{paramsType},create_time=#{createTime},status=#{status},is_null=#{isNull},remark=#{remark}";
	
	@Select("select "+columns+" FROM t_app_interface_params  limit #{startRow},#{endRow}")
	@ResultMap(value="com.lyun.appinterface.mapper.AppInterfaceParamsMapper.AppInterfaceParamsModelMap")
	public List<AppInterfaceParamsModel> fetchPageList(AppInterfaceParamsQuery query);
	
	@Select("select count(*) from t_app_interface_params where 1 = 1 ")
	public int fetchPageCount(AppInterfaceParamsQuery query);
	
	@Select("select "+columns+" from t_app_interface_params where ID=#{id}")
	@ResultMap(value="com.lyun.appinterface.mapper.AppInterfaceParamsMapper.AppInterfaceParamsModelMap")
	public AppInterfaceParamsModel getById(long id);
	
	@Insert("insert into t_app_interface_params ("+insert+") values ("+insertProperty+")")
	public long insert(AppInterfaceParamsModel appInterfaceParams);

	@Update("update t_app_interface_params set "+update+" where ID=#{id}")
	public long update(AppInterfaceParamsModel appInterfaceParams); 
	
	@Delete("delete from t_app_interface_params where 1 = 1 and ID=#{id} ")
	public void del(AppInterfaceParamsModel appInterfaceParams);

	@SelectProvider(type=com.lyun.appinterface.provider.AppInterfaceParamsProvider.class,method="fetchPageAdvance")
	@ResultMap(value="com.lyun.appinterface.mapper.AppInterfaceParamsMapper.AppInterfaceParamsModelMap")
	public List<AppInterfaceParamsModel> fetchPageAdvance(AppInterfaceParamsQuery query);  
	
	
	@SelectProvider(type=com.lyun.appinterface.provider.AppInterfaceParamsProvider.class,method="fetchPageAdvanceCount")
	public int fetchPageAdvanceCount(AppInterfaceParamsQuery query);
	
	
	
	
}