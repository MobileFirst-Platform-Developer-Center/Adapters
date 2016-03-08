/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.sample;

import java.util.logging.Logger;

import com.ibm.mfp.adapter.api.MFPJAXRSApplication;

public class JavaHTTPApplication extends MFPJAXRSApplication{

	static Logger logger = Logger.getLogger(JavaHTTPApplication.class.getName());

	@Override
	protected void init() throws Exception {
		JavaHTTPResource.init();
		logger.info("Adapter initialized!");
	}

	protected void destroy() throws Exception {
		logger.info("Adapter destroyed!");
	}

	@Override
	protected String getPackageToScan() {
		//The package of this class will be scanned (recursively) to find JAX-RS resources.
		return getClass().getPackage().getName();
	}

}
