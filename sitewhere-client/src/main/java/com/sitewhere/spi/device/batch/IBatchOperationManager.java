/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.device.batch;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.server.lifecycle.ILifecycleComponent;

/**
 * Interface for interacting with a batch operation manager.
 * 
 * @author Derek
 */
public interface IBatchOperationManager extends ILifecycleComponent {

	/**
	 * Processes an {@link IBatchOperation}. The batch operation is processed in the
	 * calling thread.
	 * 
	 * @param operation
	 * @throws SiteWhereException
	 */
	public void process(IBatchOperation operation) throws SiteWhereException;
}