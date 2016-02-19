package com.lyun.appinterface.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.velocity.tools.generic.EscapeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyun.appinterface.model.AppInterfaceModel;
import com.lyun.appinterface.model.AppInterfaceParamsModel;
import com.lyun.appinterface.query.AppInterfaceParamsQuery;
import com.lyun.appinterface.query.AppInterfaceQuery;
import com.lyun.appinterface.service.AppInterfaceParamsService;
import com.lyun.appinterface.service.AppInterfaceService;
import com.lyun.appinterface.support.BaseController;
import com.lyun.appinterface.support.HttpExcuteUtil;
import com.lyun.appinterface.support.StringUtil;

@Controller
public class AppInterfaceController extends BaseController {
	
	@Autowired
	private AppInterfaceService appInterfaceService;
	@Autowired
	private AppInterfaceParamsService appInterfaceParamsService;
	
	@RequestMapping("index")
	public String index(ModelMap model,AppInterfaceQuery query){
		
		query.setPageSize(10);
		query.setTotalItem(appInterfaceService.fetchPageAdvanceCount(query));
		model.put("list", appInterfaceService.fetchPageAdvance(query));
		model.put("pageInfo", query);
		return "index";
	}
	@RequestMapping("details")
	public String index(ModelMap model,Integer id){
		
		model.put("i", appInterfaceService.getById(id));
		AppInterfaceParamsQuery query=new AppInterfaceParamsQuery();
		query.setInterfaceId(id);
		query.setTotalItem(appInterfaceParamsService.fetchPageAdvanceCount(query));
		query.setPageSize(query.getTotalItem());
		model.put("pList", appInterfaceParamsService.fetchPageAdvance(query));
		
		
		return "details";
	}
	
	
	@RequestMapping("load")
	public String load(){
		
		File f=new File("E:/apptest.xls");
		// 定义要返回的list

	     Map<AppInterfaceModel, List<AppInterfaceParamsModel>>  map=new HashMap<AppInterfaceModel, List<AppInterfaceParamsModel>>();
	
		EscapeTool et=new EscapeTool();
		

		try {

			// 根据Excel数据源创建WorkBook
			Workbook wb = Workbook.getWorkbook(f);
			// 获取工作表
			Sheet sheet = wb.getSheet("接口列表");

			// 获取工作表的有效行数
			int realRows = 0;
			for (int i = 0; i < sheet.getRows(); i++) {

				int nullCols = 0;
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell currentCell = sheet.getCell(j, i);
					if (currentCell == null
							|| "".equals(currentCell.getContents().toString())) {
						nullCols++;
					}
				}

				if (nullCols == sheet.getColumns()) {
					break;
				} else {
					realRows++;
				}
			}

			// 如果Excel中没有数据则提示错误
			if (realRows <= 1) {
				throw new RuntimeException("Excel文件中没有任何数据");
			}

           Date now=new Date();
		

			// 将sheet转换为list
			for (int i = 1; i < realRows; i++) {
				// 新建要转换的对象
				AppInterfaceModel entity=new AppInterfaceModel();

				entity.setCreateTime(now);
				entity.setName( sheet.getCell(0, i).getContents().toString().trim());
				
				// 获取工作表
				Sheet s = wb.getSheet(entity.getName());
				if(s==null){
					continue;
				}
				// 获取工作表的有效行数
				int realRows1 = 0;
				for (int k = 0;  k< sheet.getRows(); k++) {

					int nullCols = 0;
					for (int j = 0; j < s.getColumns(); j++) {
						Cell currentCell = s.getCell(j, k);
						if (currentCell == null
								|| "".equals(currentCell.getContents().toString())) {
							nullCols++;
						}
					}

					if (nullCols == s.getColumns()) {
						break;
					} else {
						realRows1++;
					}
				}
				// 如果Excel中没有数据则提示错误
				if (realRows1 <= 1) {
					throw new RuntimeException("Excel文件中没有任何数据");
				}
				entity.setMethod("get/post");
				entity.setRemark(et.html( s.getCell(0,0 ).getContents().toString().trim()));
				Range[] ranges = s.getMergedCells();
				List<AppInterfaceParamsModel> l=new ArrayList<AppInterfaceParamsModel>();
			    for (Range range : ranges) { 
			    	 int startRow = range.getTopLeft().getRow();
			    	 int startCol = range.getTopLeft().getColumn();
			    	 int endRow = range.getBottomRight().getRow();
			    	 int endCol = range.getBottomRight().getColumn();
			    	String c=s.getCell(startCol, startRow).getContents().toString().trim();
			        if("请求参数".equals(c)){
			        
			        	for(int o=startRow+1;o<=endRow;o++){
			        		AppInterfaceParamsModel p=new AppInterfaceParamsModel();
			        		p.setName(s.getCell(endCol+1, o).getContents().toString().trim());
			        		p.setParamsType(s.getCell(endCol+2, o).getContents().toString().trim());
			        		p.setIsNull("Y".equals(s.getCell(endCol+3, o).getContents().toString().trim())?1:0);
			        		String desc=s.getCell(endCol+4, o).getContents().toString().trim();
			        	  
			        		p.setRemark(desc);
			        		p.setStatuS(1);
			        		p.setCreateTime(now);
			        		
			        		if(!StringUtil.isEmpty(p.getParamsType())&&p.getParamsType().length()>10){
			        			p.setParamsType(p.getParamsType().substring(0,10));
			        		}
			        		
			        		if(!StringUtil.isEmpty(p.getName())){
			        			l.add(p);
			        		}
			        		
			        	}
			        	
			        }
			        else if("响应结果".equals(c)){
			        	entity.setResponse(s.getCell(endCol+1,startRow ).getContents().toString().trim());
			        	
			        	if(!StringUtil.isEmpty(entity.getResponse())&&entity.getResponse().length()>3000){
			        		entity.setResponse(entity.getResponse().substring(0, 3000));
			        		System.out.println(entity.getName());
			        	}
			        	entity.setStatuS(1);
			        }
			       
			    }
			
			     map.put(entity, l);
			    
			    
			    
			}
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		appInterfaceService.insertMap(map);
		
		
		
		return "index";
	}

	
	@RequestMapping("test")
	public String index(ModelMap model,String url,String data ) throws IOException{
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		JSONObject json=JSONObject.fromObject(data);
		NameValuePair n=new BasicNameValuePair("data",json.toString());
		list.add(n);
		String result= HttpExcuteUtil.getContent(url,new BasicHttpParams(),list , false);
		
		model.put("result", StringUtil.formatJson2(result));
		return null;
	}
	
}
