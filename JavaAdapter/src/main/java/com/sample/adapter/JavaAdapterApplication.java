/*
 *    Licensed Materials - Property of IBM
 *    5725-I43 (C) Copyright IBM Corp. 2015. All Rights Reserved.
 *    US Government Users Restricted Rights - Use, duplication or
 *    disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
*/

package com.mycompany;

import java.util.logging.Logger;
import javax.ws.rs.core.Application;
import java.util.*;

public class JavaAdapterApplication extends Application{

	static Logger logger = Logger.getLogger(JavaAdapterApplication.class.getName());
	

	protected void init() throws Exception {
		logger.info("Adapter initialized!");
	}
	

	protected void destroy() throws Exception {
		logger.info("Adapter destroyed!");
	}
	

	protected String getPackageToScan() {
		//The package of this class will be scanned (recursively) to find JAX-RS resources. 
		//It is also possible to override "getPackagesToScan" method in order to return more than one package for scanning
		return getClass().getPackage().getName();
	}

		public Set<Class<?>> getClasses() {
			Set<Class<?>> classes = new HashSet<Class<?>>();
			classes.add(JavaAdapterResource.class);
			return classes;
		}
}
