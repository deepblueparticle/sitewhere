/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.user.initializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sitewhere.SiteWhere;
import com.sitewhere.rest.model.user.request.UserManagementRequestBuilder;
import com.sitewhere.server.ModelInitializer;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.user.IUserManagement;
import com.sitewhere.user.spi.initializer.IUserModelInitializer;

import groovy.lang.Binding;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

/**
 * Implementation of {@link IUserModelInitializer} that delegates creation logic
 * to a Groovy script.
 * 
 * @author Derek
 */
public class GroovyUserModelInitializer extends ModelInitializer implements IUserModelInitializer {

    /** Static logger instance */
    private static Logger LOGGER = LogManager.getLogger();

    /** Relative path to Groovy script */
    private String scriptPath;

    /*
     * (non-Javadoc)
     * 
     * @see com.sitewhere.spi.server.user.IUserModelInitializer#initialize(com.
     * sitewhere.spi. user.IUserManagement)
     */
    @Override
    public void initialize(IUserManagement userManagement) throws SiteWhereException {
	// Skip if not enabled.
	if (!isEnabled()) {
	    return;
	}

	Binding binding = new Binding();
	binding.setVariable("logger", LOGGER);
	binding.setVariable("userBuilder", new UserManagementRequestBuilder(userManagement));

	try {
	    SiteWhere.getServer().getGroovyConfiguration().getGroovyScriptEngine().run(getScriptPath(), binding);
	} catch (ResourceException e) {
	    throw new SiteWhereException("Unable to access Groovy script. " + e.getMessage(), e);
	} catch (ScriptException e) {
	    throw new SiteWhereException("Unable to run Groovy script.", e);
	}
    }

    public String getScriptPath() {
	return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
	this.scriptPath = scriptPath;
    }
}