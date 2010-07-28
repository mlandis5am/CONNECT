package gov.hhs.fha.nhinc.nhinclib;

import gov.hhs.fha.nhinc.async.AsyncMessageIdExtractor;
import java.util.UUID;
import javax.xml.ws.WebServiceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

/**
 * This helper class sets up a logging context such that message flow can be
 * traced through process log messages.
 */
public class LoggingContextHelper {

    private Log log = null;

    /**
     * Default Constructor defines the logger
     */
    public LoggingContextHelper() {
        log = createLogger();
    }

    /**
     * Creates the error logger
     * @return The Logger
     */
    protected Log createLogger() {
        if (log == null) {
            log = LogFactory.getLog(getClass());
        }
        return log;
    }

    /**
     * Builds the Nested Diapnostic Context for the current thread and
     * initializes the generated logging context id in it.
     * @param webServiceContext Provides the message context of the request
     *                          being served
     */
    public void setContext(WebServiceContext webServiceContext) {
        String loggingContextId = generateLoggingContextId(webServiceContext);
        NDC.push(loggingContextId);
    }

    /**
     * This method will create a logging context id that retains the message id
     * for the current message as well as the message id of what it may be
     * responding to.  An additional unique identifier will also be appended to
     * make this logging message unique.
     *
     * @param webServiceContext Provides the message context of the request
     *                          being served
     * @return Unique representation of this logging context
     */
    protected String generateLoggingContextId(WebServiceContext webServiceContext) {

        return AsyncMessageIdExtractor.GetAsyncMessageId(webServiceContext) 
                + "." + AsyncMessageIdExtractor.GetAsyncRelatesTo(webServiceContext)
                + "." + UUID.randomUUID().toString();
    }

    /**
     * This method should be used when exiting the processing thread to remove
     * the context.
     */
    public void clearContext() {
        NDC.remove();
    }
}