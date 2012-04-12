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
package javax.portlet.faces;

/**
 * Thrown when the bridge can't resolve the target view from the request and the portlet hasn't set the PortletRequest attribute
 * <code>javax.portlet.faces.defaultViewId</code>.
 */
public class BridgeDefaultViewNotSpecifiedException extends BridgeException {

    private static final long serialVersionUID = 540438273186022660L;

    public BridgeDefaultViewNotSpecifiedException() {
        super();
    }

    /**
     * @param message
     */
    public BridgeDefaultViewNotSpecifiedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public BridgeDefaultViewNotSpecifiedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public BridgeDefaultViewNotSpecifiedException(String message, Throwable cause) {
        super(message, cause);
    }

}
