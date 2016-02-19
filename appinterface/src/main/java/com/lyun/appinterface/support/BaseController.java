package com.lyun.appinterface.support;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;







import org.springframework.web.multipart.MultipartFile;




public class BaseController {

		
	
	  	@Resource
	    protected HttpServletRequest request;

		@InitBinder
		protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
				throws Exception {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 可以設定任意的日期格式
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat2.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// 格式化时间
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat2, true));
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));// 去掉空格
		}
		

		
		public String getLanguage(){
			//set land
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					if("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE".equals(cookie.getName())){
						 return cookie.getValue();
					}
				}
			}
			return "en_us";
		}
		
		public String redirectRefer(HttpServletRequest request){
				return "redirect:"+request.getHeader("referer");
		}
		
	    
	    @ExceptionHandler(Throwable.class) 
	    @ResponseBody
	    public void restError(Throwable error,ModelMap model) {
	    	
	
	    }
	    
	    protected String enCodingFileName(String filename){
	    	
	    	
	    	   if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
	               try {
	   				filename = URLEncoder.encode(filename, "UTF-8");
	   			} catch (UnsupportedEncodingException e) {
	   				
	   			}  
	           } else {  
	               try {
	   				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
	   			} catch (UnsupportedEncodingException e) {
	   				
	   			}  
	           }  
	    	   return filename;
	    	
	    }
	    
	    /**
	     * 
	     * @param fromFile
	     * @param rootPath    //  d:doc/
	     * @param business     //  othercompany/13231      othercompany
	     * @throws IllegalStateException
	     * @throws IOException
	     */
	    protected String  saveFile(MultipartFile fromFile,String rootPath,String business) throws IllegalStateException, IOException {
			if(fromFile!=null){
				String   suffix=fromFile.getOriginalFilename().substring(fromFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
				Random r=new Random();	
				String temp=business+"/"+System.currentTimeMillis()+r.nextInt(10000)+suffix;
				File toFile=new File(rootPath+temp);		
				File parentFile=new File(rootPath+business);
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
				fromFile.transferTo(toFile);
				return temp;
			}
			
			return null;
	    	
	    	
		}
	    
		
}
