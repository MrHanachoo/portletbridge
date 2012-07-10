/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.portletbridge.context;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletContext;
import javax.portlet.faces.Bridge;

/**
 * @author asmirnov
 *
 */
public class EventRequestExternalContextImpl extends PortletExternalContextImpl {

    /**
     * @param context
     * @param request
     * @param response
     */
    public EventRequestExternalContextImpl(PortletContext context, EventRequest request, EventResponse response) {
        super(context, request, response);
    }

    @Override
    protected String createActionUrl(PortalActionURL url, boolean escape) {
        return ACTION_URL_DO_NOTHITG;
    }

    @Override
    protected String createRenderUrl(PortalActionURL portalUrl, boolean escape, Map<String, List<String>> parameters) {
        return ACTION_URL_DO_NOTHITG;
    }

    @Override
    protected String createResourceUrl(PortalActionURL portalUrl, boolean escape) {
        return RESOURCE_URL_DO_NOTHITG;
    }

    @Override
    public void redirect(String url) throws IOException {
        if (null == url) {
            throw new NullPointerException("Path to redirect is null");
        }
        PortalActionURL actionURL = new PortalActionURL(url);

        Map<String, String[]> urlParams = null;
        if (null != encodedActionUrlParameters) {
            urlParams = encodedActionUrlParameters.get(url);
        }

        if (null != urlParams) {
            PortalUrlQueryString queryString = new PortalUrlQueryString(null);
            queryString.setParameters(urlParams);

            Map<String, String[]> publicParamMap = getRequest().getPublicParameterMap();
            if (null != publicParamMap && !publicParamMap.isEmpty()) {
                for (Map.Entry<String, String[]> entry : publicParamMap.entrySet()) {
                    String key = entry.getKey();

                    if (!queryString.hasParameter(key)) {
                        for (String param : entry.getValue()) {
                            queryString.addParameter(key, param);
                        }
                    }
                }
            }

            bridgeContext.setRenderRedirectQueryString(queryString.toString());
        } else if (url.startsWith("#") || (!actionURL.isInContext(getRequestContextPath()))
                || "true".equalsIgnoreCase(actionURL.getParameter(Bridge.DIRECT_LINK))) {
            // Do Nothing
        } else {
            redirect(encodeActionURL(url));
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    @Override
    protected String createPartialActionUrl(PortalActionURL portalUrl) {
        return RESOURCE_URL_DO_NOTHITG;
    }

    @Override
    public boolean isResponseCommitted() {
        return true;
    }

    @Override
    public void addResponseHeader(String name, String value) {
        getResponse().addProperty(name, value);
    }

    @Override
    public void setResponseHeader(String name, String value) {
        getResponse().setProperty(name, value);
    }

    @Override
    public void responseSendError(int statusCode, String message) throws IOException {

    }

    @Override
    public String getRequestCharacterEncoding() {
        return null;
    }

    @Override
    public int getRequestContentLength() {
        return 0;
    }

    @Override
    public int getResponseBufferSize() {
        return 0;
    }

    @Override
    public OutputStream getResponseOutputStream() throws IOException {
        return null;
    }

    @Override
    public Writer getResponseOutputWriter() throws IOException {
        return null;
    }

    @Override
    public void responseFlushBuffer() throws IOException {

    }

    @Override
    public void responseReset() {

    }

    @Override
    public void setRequestCharacterEncoding(String encoding) throws UnsupportedEncodingException {

    }

    @Override
    public void setResponseBufferSize(int size) {

    }

    @Override
    public void setResponseContentLength(int length) {

    }

    @Override
    public void setResponseContentType(String contentType) {

    }

}
