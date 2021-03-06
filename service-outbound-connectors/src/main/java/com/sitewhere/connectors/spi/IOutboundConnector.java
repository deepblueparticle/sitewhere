/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.connectors.spi;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.IDeviceManagement;
import com.sitewhere.spi.device.event.IDeviceAlert;
import com.sitewhere.spi.device.event.IDeviceCommandInvocation;
import com.sitewhere.spi.device.event.IDeviceCommandResponse;
import com.sitewhere.spi.device.event.IDeviceEventContext;
import com.sitewhere.spi.device.event.IDeviceEventManagement;
import com.sitewhere.spi.device.event.IDeviceLocation;
import com.sitewhere.spi.device.event.IDeviceMeasurements;
import com.sitewhere.spi.device.event.IDeviceStateChange;
import com.sitewhere.spi.server.lifecycle.ITenantEngineLifecycleComponent;

/**
 * Connects processed events to an external entity for further processing.
 * 
 * @author Derek
 */
public interface IOutboundConnector extends ITenantEngineLifecycleComponent {

    /**
     * Get unique id for connector.
     * 
     * @return
     */
    public String getConnectorId();

    /**
     * Get number of threads used for processing events.
     * 
     * @return
     */
    public int getNumProcessingThreads();

    /**
     * Executes processing code for a device measurements event.
     * 
     * @param context
     * @param measurements
     * @throws SiteWhereException
     */
    public void onMeasurements(IDeviceEventContext context, IDeviceMeasurements measurements) throws SiteWhereException;

    /**
     * Executes processing code for a device location event.
     * 
     * @param context
     * @param location
     * @throws SiteWhereException
     */
    public void onLocation(IDeviceEventContext context, IDeviceLocation location) throws SiteWhereException;

    /**
     * Executes processing code for a device alert event.
     * 
     * @param context
     * @param alert
     * @throws SiteWhereException
     */
    public void onAlert(IDeviceEventContext context, IDeviceAlert alert) throws SiteWhereException;

    /**
     * Executes processing code for a device command invocation event.
     * 
     * @param context
     * @param invocation
     * @throws SiteWhereException
     */
    public void onCommandInvocation(IDeviceEventContext context, IDeviceCommandInvocation invocation)
	    throws SiteWhereException;

    /**
     * Executes processing code for a device command response event.
     * 
     * @param context
     * @param response
     * @throws SiteWhereException
     */
    public void onCommandResponse(IDeviceEventContext context, IDeviceCommandResponse response)
	    throws SiteWhereException;

    /**
     * Executes processing code for a device state change event.
     * 
     * @param context
     * @param state
     * @throws SiteWhereException
     */
    public void onStateChange(IDeviceEventContext context, IDeviceStateChange state) throws SiteWhereException;

    /**
     * Get device management API.
     * 
     * @return
     */
    public IDeviceManagement getDeviceManagement();

    /**
     * Get device event management API.
     * 
     * @return
     */
    public IDeviceEventManagement getDeviceEventManagement();
}