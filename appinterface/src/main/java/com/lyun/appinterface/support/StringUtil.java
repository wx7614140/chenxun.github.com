package com.lyun.appinterface.support;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class StringUtil
{

	public static boolean isEmpty(String str)
	{
		if(str==null || "".equals(str)||"null".equals(str))
		{
			return true;
		}
		else
		{
			if(!"".equals(str.trim()))
			{
			  return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	public static boolean isEmail(String email)
	{
		if(email==null){
			return false;
		}
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
		Pattern regex = Pattern.compile(check);  
		Matcher matcher = regex.matcher(email);  
		return matcher.matches(); 
	}
	
	public static String subString(String str,int length){
		if(!StringUtil.isEmpty(str)){
			if(str.length()>length){
				return str.substring(0, length-1)+"...";
			}else{
				return str;
			}
		}else{
			return "";
		}
	}
	
	
	
	
	/**
	 * 
	* @Title: isEmptyCollection
	* @Description: TODO(判断集合是否为空集合)
	* @param @param obj (List or Map)
	* @return boolean    (如果该集合为null或者该集合的size==0返回ture，否则返回fasle)
	* @author ChenXun
	* @date 2015年4月26日
	* @throws
	 */
	public static boolean  isEmptyCollection(Object obj){
		if(obj instanceof Map){
			return obj==null||((Map)obj).size()==0? true:false;
		}
		else if(obj instanceof List){
			return obj==null||((List)obj).size()==0? true:false;
		}
		else
			
		return true;
		
	}
	
	/**
	 * 把一个字符串的一个空格或多个空格替换成一个空格，并切割成多个字符串，返回一个字符串数组
	 * @param str 
	 * @return String[] 数组  
	 */ 
	public static String[] splitStr(String str){
		 if(str==null||StringUtil.isEmpty(str))
			return null;
		
		
		String values = str.replaceAll(" {2,}", " "); 
		values = str.replaceAll(" ", "-"); 
		String[] array = str.split("-");
		return array;	
	}
	
	/**
	 * 获取QQaccess_token
	 * @param str
	 * @return
	 */
	public static String getAccess_token(String str){
		return  str.substring(str.indexOf("=")+1,str.indexOf("&"));
	}
	
	
	/**
	 * 获取微信的sccess_token
	 * @return
	 */
	public static String getWeixinAccess_token(String str){
		// {"access_token":"OezXcEiiBSKSxW0eoylIeIGVIJJtlQ82_aP8OqwEn2mCNXQGQ03Q0XsN20MrP-2u2RhNyIVq_EJwWKXLM-5E2TQYblw0v5y6a-HVYlB7vDRMYcIDf1X1tgi3TmfPlx0bYA4R4O1EkQBVjg8ClUwYxQ","expires_in":7200,"refresh_token":"OezXcEiiBSKSxW0eoylIeIGVIJJtlQ82_aP8OqwEn2mCNXQGQ03Q0XsN20MrP-2u5os3ls6H55ABLkiFrzs-YBuw1ym0aVWfl9qddkc5AZTaCPsZK1O68dMcYHJKuka0DJvUaikCggNAjah9ZrjAGQ","openid":"ot3mQs9QMWV5o5XSTZ-YgiYFeoXo","scope":"snsapi_login","unionid":"o-SeIuProDf6_kAGoRt1mCITK7oA"}
		String result = str.substring(str.indexOf("scope")-31,str.indexOf("scope")-3);
		return result;
		
	}
	
	/**
	 * 返回一个当前日期加上一个八位随机数的State
	 * @return
	 */
	public static String getWeixinState(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		dateStr = dateStr+getRandom();
		
		return dateStr+getRandom();
	}
	
	public static String getRandom(){
		String str = "";
		//产生随机数
		Random random = new Random();


		for(int i=0;i<8;i++){

			str+=random.nextInt(10);

		}
		return str;
	}
	
	
	/**
	 * 传入一个11位数的手机号码，返回一个130******01的字符串
	 * @param str
	 * @return
	 */
	public static String getMaskPhone(String str){
		if(isEmpty(str)||str.trim().length()!=11)
			return "";
	    String rsu = str.substring(str.length()-8, str.length()-2);
		String result = str.replace(rsu, "******");
		
		return result;
		
	}
	
	public static String getMaskPhone2(String str){   //130*****001
		if(isEmpty(str)||str.trim().length()!=11)
			return "";
	    String rsu = str.substring(str.length()-8, str.length()-3);
		String result = str.replace(rsu, "*");
		
		return result;
		
	}
	
	
	/**
	 * 获取微博返回的uid
	 * @param str
	 * @return
	 */
	public static String getWeiBoUid(String str){
		String result = null;
		//{"access_token":"2.002TxrJG014gfi330934cb3b0dFSPE","remind_in":"126898","expires_in":126898,"uid":"5642644015"}
		result = str.substring(str.lastIndexOf(":")+2, str.length()-2);
		return result;
	}
	
	
	public static String getNowDate(){
		Date d2 =  new Date();
        //获得年份 （注意年份要加上1900，这样才是日期对象d2所代表的年份）
        int year = d2.getYear() + 1900;
        //获得月份  （注意月份要加1，这样才是日期对象d2所代表的月份）
        int month = d2.getMonth() + 1;
        //获得日期
        int date = d2.getDate();
        //获得小时
        int hour = d2.getHours();
        //获得分钟
        int minute = d2.getMinutes();
        //获得秒
        int second = d2.getSeconds();
		return year+"年"+month+"月"+date+"日"+hour+"点"+minute+"分"+second+"秒";
	}
	
	public static boolean checkSameDay(Date tempDate){
		boolean flag = false;
		//传入的日期
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdfTemp.format(tempDate);
		
		//当前日期
		SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = sdfNow.format(new Date());
		
		if(date1.equals(date2)){
			flag = true;
		}
		return flag;
	}
	
	public static boolean checkArrayContainStr(String[] arry,String temp){
		boolean flag = false;
		String tager = "";
		if(arry!=null||arry.length>0){
			for(int i=0;i<arry.length;i++){
				tager = arry[i];
				if(tager.indexOf(temp)>0){
					flag = true;
				}
			}
		}
		
		return flag;
		
	}
	
	public static boolean checkSetContainStr(Set<String> setList,String temp){
		boolean flag = false;
		Iterator<String> it = setList.iterator();  
		while (it.hasNext()) {  
		  String str = it.next();  
		  if(temp.equals(str)){
			  flag = true;
		  }
		} 
		
		return flag;
		
	}
	
	public static String getAddress(String address){
	   if(StringUtil.isEmpty(address))
		   return "";
	   String temp=address.replaceAll("_地级市", "");
	   temp=temp.replaceAll("_市、县、区", "");
	   return temp;
		
		
	}
	
	
	
	public static String parseString(String str,int length){
		if(StringUtil.isEmpty(str)){
			return "";
		}
		
		return str.length()>(length+1)?str.substring(0,length)+"...":str;
		 
		
	};
	
	//判断输入的字符是否是整数
    public static boolean isInteger(String str) {    
		    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		    return pattern.matcher(str).matches();    
		  }  
    
    
    
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z" };

    //生成短8位的UUID
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
		    String str = uuid.substring(i * 4, i * 4 + 4);
		    int x = Integer.parseInt(str, 16);
		    shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	
	}
	
	
	
	public static boolean isMobileNo(String mobiles){
		//Pattern p = Pattern.compile("^((1[3-4][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Pattern p=Pattern.compile("^1[34578]\\d{9}$");  
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
		
	}
	
	public String encodeing(String str){
		
		String temStr = "";
		try {
			temStr = new String(str.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temStr;
	}
	
	
	/**
	 * amountConversion:(这里用一句话描述这个方法的作用). 金额转换<br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author shijunqiang
	 * @param str
	 * @return
	 */
	public String amountConversion(String str){
		DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");
		if(null==str || "".equals(str.trim()) || "null".equals(str) || "0".equals(str.trim())){
			return "0.00";
		}
		return df.format(new Double(str));
	}
	
	public static String formatDate(String str)
	{
		if(str!=null && !"".equals(str) && str.indexOf("-")>0){
			String [] date = str.split("-");
			String result = date[0] + "-" + (date[1].length() == 1 ? ("0" + date[1]) : date[1]) + "-"  + (date[2].length() == 1 ? ("0" + date[2]) : date[2]);  
			return result;
		}
		return "暂无日期";
	}	
	
	/**
	 * formatDate:(这里用一句话描述这个方法的作用).日期区间格式化  <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author shijunqiang
	 * @param str 开始日期-结束日期 
	 * @param c 开始日期-结束日期  '-'
	 * @return
	 */
	public static String formatDate(String str, String c)
	{
		if(str!=null && !"".equals(str) && str.indexOf(c)>0 && !"".equals(c) && c!=null){
			String[] arrDate = str.split(c);
			return formatDate(arrDate[0]) + c + formatDate(arrDate[1]);
		}
	
		return str;
	}	
	
	
	
	/**
	 * 去除字符串中的"- - 空格"
	 * @param userName
	 * @return
	 */
    public static String replaceUserNameStr(String userName){
    	String str = userName.replace("-", "").replace("-", "").trim();
    	str = str.trim().replace(" ", "");
    	return str;
    }

    
    public static void main(String args[]){

        String str="<p><font size=\"2\"><span style=\"2322\">3213123123</p>";  
        String str_text=Html2Text(str);  
        System.out.println(str_text);  
        String slice=abbreviate(str_text,60,"...");  
        System.out.println(slice);   
    	
    	
    	
    	
    }
    /**
     * @param str:
     *             source string
     * @param width:
     *             string's byte width
     * @param ellipsis:
     *             a string added to abbreviate string bottom
     * @return String Object
     * @deprecated 这个函数是用来对输入的字符串进行截取的功能
     */  
   public static String abbreviate(String str, int width, String ellipsis) {  
       if (str == null || "".equals(str)) {  
           return "";  
        }  
 
       int d = 0; // byte length  
       int n = 0; // char length  
       for (; n < str.length(); n++) {  
            d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;  
           if (d > width) {  
               break;  
            }  
        }  
 
       if (d > width) {  
            n = n - ellipsis.length() / 2;  
           return str.substring(0, n > 0 ? n : 0) + ellipsis;  
        }  
 
       return str = str.substring(0, n);  
    }  
   /**
     * @param str:
     *             source string
     * @param width:
     *             string's byte width
     * @param ellipsis:
     *             a string added to abbreviate string bottom
     * @return String Object
     * @deprecated 这个函数是用来对输入字符的HTML代码进行过滤
     */  
   public static String Html2Text(String inputString) {   
          String htmlStr = inputString; //含html标签的字符串   
          String textStr ="";   
          java.util.regex.Pattern p_script;   
          java.util.regex.Matcher m_script;   
          java.util.regex.Pattern p_style;   
          java.util.regex.Matcher m_style;   
          java.util.regex.Pattern p_html;   
          java.util.regex.Matcher m_html;   
           
          java.util.regex.Pattern p_html1;   
          java.util.regex.Matcher m_html1;   
        
         try {   
           String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }   
           String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }   
              String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式   
              String regEx_html1 = "<[^>]+";   
              p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);   
              m_script = p_script.matcher(htmlStr);   
              htmlStr = m_script.replaceAll(""); //过滤script标签   
 
              p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);   
              m_style = p_style.matcher(htmlStr);   
              htmlStr = m_style.replaceAll(""); //过滤style标签   
           
              p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);   
              m_html = p_html.matcher(htmlStr);   
              htmlStr = m_html.replaceAll(""); //过滤html标签   
               
              p_html1 = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);   
              m_html1 = p_html1.matcher(htmlStr);   
              htmlStr = m_html1.replaceAll(""); //过滤html标签   
           
               
           textStr = htmlStr;   
           
          }catch(Exception e) {   
                   System.err.println("Html2Text: " + e.getMessage());   
          }   
        
         return textStr;//返回文本字符串   
           }      
   
   
   /**
    * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
    * @return
    *      以yyyyMMddHHmmss为格式的当前系统时间
    */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}

		public  static String toDateStr(Date date){
			
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		}
	    
    
	   /**
	    * 
	    * @param jsonStr
	    * @return
	    */
	    public static String formatJson(String jsonStr) {
	        if (null == jsonStr || "".equals(jsonStr)) return "";
	        StringBuilder sb = new StringBuilder();
	        char last = '\0';
	        char current = '\0';
	        int indent = 0;
	        for (int i = 0; i < jsonStr.length(); i++) {
	            last = current;
	            current = jsonStr.charAt(i);
	            switch (current) {
	                case '{':
	                case '[':
	                    sb.append(current);
	                    sb.append("&#10");
	                    
	                    indent++;
	                //    addIndentBlank(sb, indent);
	                    break;
	                case '}':
	                case ']':
	                    sb.append("&#10");
	                    indent--;
	                //    addIndentBlank(sb, indent);
	                    sb.append(current);
	                    break;
	                case ',':
	                    sb.append(current);
	                    if (last != '\\') {
	                        sb.append("&#10");
	                //        addIndentBlank(sb, indent);
	                    }
	                    break;
	                default:
	                    sb.append(current);
	            }
	        }
	 
	        return sb.toString();
	    }
	 
	   /**
	    * 
	    * @param sb
	    * @param indent
	    */
	    private static void addIndentBlank(StringBuilder sb, int indent) {
	        for (int i = 0; i < indent; i++) {
	            sb.append('\t');
	        }
	    }
	
     

    
    


	    /**
		    * 
		    * @param jsonStr
		    * @return
		    */
		    public static String formatJson2(String jsonStr) {
		        if (null == jsonStr || "".equals(jsonStr)) return "";
		        StringBuilder sb = new StringBuilder();
		        char last = '\0';
		        char current = '\0';
		        int indent = 0;
		        for (int i = 0; i < jsonStr.length(); i++) {
		            last = current;
		            current = jsonStr.charAt(i);
		            switch (current) {
		                case '{':
		                case '[':
		                    sb.append(current);
		                    sb.append("\n");
		                    
		                    indent++;
		                    //addIndentBlank(sb, indent);
		                    break;
		                case '}':
		                case ']':
		                    sb.append("\n");
		                    indent--;
		                 //   addIndentBlank(sb, indent);
		                    sb.append(current);
		                    break;
		                case ',':
		                    sb.append(current);
		                    if (last != '\\') {
		                        sb.append("\n");
		                     //   addIndentBlank(sb, indent);
		                    }
		                    break;
		                default:
		                    sb.append(current);
		            }
		        }
		 
		        return sb.toString();
		    }
    
	
	
	
	
	
	
	
	
	
	
	
	
}

