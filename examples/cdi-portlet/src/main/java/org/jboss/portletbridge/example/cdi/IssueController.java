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
package org.jboss.portletbridge.example.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author <a href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
@Named
@ConversationScoped
public class IssueController implements Serializable {

    private static final long serialVersionUID = 3184963917964851211L;

    @Inject
    private IssueManager mgr;

    @Inject
    private Conversation conversation;

    private String message;

    @PostConstruct
    public void init() {
        message = "Hello and welcome to your Issues";
    }

    public void deleteIssue(Issue issue) {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        mgr.deleteIssue(issue);
        message = "You just deleted Issue #" + issue.getId() + " with a title of '" + issue.getTitle() + "'";
    }

    public String getMessage() {
        return message;
    }
}
