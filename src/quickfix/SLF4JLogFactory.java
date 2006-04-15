/*******************************************************************************
 * Copyright (c) quickfixengine.org  All rights reserved. 
 * 
 * This file is part of the QuickFIX FIX Engine 
 * 
 * This file may be distributed under the terms of the quickfixengine.org 
 * license as defined by quickfixengine.org and appearing in the file 
 * LICENSE included in the packaging of this file. 
 * 
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING 
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE. 
 * 
 * See http://www.quickfixengine.org/LICENSE for licensing information. 
 * 
 * Contact ask@quickfixengine.org if any conditions of this licensing 
 * are not clear to you.
 ******************************************************************************/

package quickfix;

/**
 * Simple Logging Facade for Java (SLF4J) log factory (<a href="http://slfj4.org">slfj.org</a>).
 */
public class SLF4JLogFactory implements LogFactory {
    /**
     * Log category for events.
     */
    public final static String SETTING_EVENT_CATEGORY = "SLF4JLogEventCategory";
    /**
     * Log category for incoming messages.
     */
    public final static String SETTING_INMSG_CATEGORY = "SLF4JLogIncomingMessageCategory";
    /**
     * Log category for outgoing messages.
     */
    public final static String SETTING_OUTMSG_CATEGORY = "SLF4JLogOutgoingMessageCategory";
    /**
     * Flag for prepending session ID to log output
     */
    public final static String SETTING_PREPEND_SESSION_ID = "SLF4JLogPrependSessionID";
    private final SessionSettings settings;

    public SLF4JLogFactory(SessionSettings settings) {
        this.settings = settings;
    }

    public Log create(SessionID sessionID) {
        String eventCategory = null;
        String incomingMsgCategory = null;
        String outgoingMsgCategory = null;
        boolean prependSessionID = true;
        try {
            if (settings.isSetting(sessionID, SETTING_EVENT_CATEGORY)) {
                eventCategory = settings.getString(sessionID, SETTING_EVENT_CATEGORY);
            }
            if (settings.isSetting(sessionID, SETTING_INMSG_CATEGORY)) {
                incomingMsgCategory = settings.getString(sessionID, SETTING_INMSG_CATEGORY);
            }
            if (settings.isSetting(sessionID, SETTING_OUTMSG_CATEGORY)) {
                outgoingMsgCategory = settings.getString(sessionID, SETTING_OUTMSG_CATEGORY);
            }
            if (settings.isSetting(sessionID, SETTING_PREPEND_SESSION_ID)) {
                prependSessionID = settings.getBool(sessionID, SETTING_PREPEND_SESSION_ID);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new SLF4JLog(sessionID, eventCategory, incomingMsgCategory, outgoingMsgCategory,
                prependSessionID);
    }
}
