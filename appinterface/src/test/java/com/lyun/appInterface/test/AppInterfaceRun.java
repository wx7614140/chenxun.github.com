package com.lyun.appInterface.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class AppInterfaceRun {
	 public static void main(String[] args) {
		 Server server = new Server(7777);
		        WebAppContext context = new WebAppContext();
		        context.setContextPath("/ext");
		        String ProPath= System.getProperty("user.dir");
		        context.setDescriptor(ProPath+"/src/main/webapp/WEB-INF/web.xml");
		        context.setResourceBase(ProPath+"/src/main/webapp");
		        context.setParentLoaderPriority(true);
		        server.setHandler(context);
		        try {
		 server.start();
		 server.join();
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 }

}
