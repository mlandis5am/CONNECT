/**
* Copyright (c) 2012, United States Government, as represented by the Secretary of Health and Human Services.
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above
*       copyright notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the documentation
*       and/or other materials provided with the distribution.
*     * Neither the name of the United States Government nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE UNITED STATES GOVERNMENT BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package gov.hhs.fha.nhinc.docsubmission.entity.deferred.request;

import gov.hhs.fha.nhinc.docsubmission.nhin.deferred.request.proxy20.NhinDocSubmissionDeferredRequestProxy;
import gov.hhs.fha.nhinc.docsubmission.nhin.deferred.request.proxy20.NhinDocSubmissionDeferredRequestProxyObjectFactory;
import gov.hhs.fha.nhinc.nhinclib.NhincConstants;
import gov.hhs.fha.nhinc.orchestration.Orchestratable;
import gov.hhs.fha.nhinc.orchestration.OrchestrationStrategy;
import gov.hhs.healthit.nhin.XDRAcknowledgementType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author akong
 */
public class OutboundDocSubmissionDeferredRequestStrategyImpl_g1 implements OrchestrationStrategy {

    private static Log log = LogFactory.getLog(OutboundDocSubmissionDeferredRequestStrategyImpl_g1.class);

    public OutboundDocSubmissionDeferredRequestStrategyImpl_g1() {
    }

    protected Log getLogger() {
        return log;
    }

    @Override
    public void execute(Orchestratable message) {
        if (message instanceof OutboundDocSubmissionDeferredRequestOrchestratable) {
            execute((OutboundDocSubmissionDeferredRequestOrchestratable) message);
        } else {
            getLogger().error("Not an OutboundDocSubmissionDeferredRequestOrchestratable.");
        }
    }

    public void execute(OutboundDocSubmissionDeferredRequestOrchestratable message) {
        getLogger().debug("Begin OutboundDocSubmissionOrchestratableImpl_g1.process");
        if (message == null) {
            getLogger().debug("OutboundDocSubmissionOrchestratable was null");
            return;
        }

        if (message instanceof OutboundDocSubmissionDeferredRequestOrchestratable) {

            NhinDocSubmissionDeferredRequestProxy nhincDocSubmission = new NhinDocSubmissionDeferredRequestProxyObjectFactory().getNhinDocSubmissionDeferredRequestProxy();
            XDRAcknowledgementType response = new XDRAcknowledgementType();
            response.setMessage(nhincDocSubmission.provideAndRegisterDocumentSetBRequest20(message.getRequest(),
                    message.getAssertion(), message.getTarget()));
            message.setResponse(response);

        } else {
            getLogger().error("OutboundDocSubmissionDeferredRequestStrategyImpl_g1 AdapterDelegateImpl_a0.process received a message " +
                    "which was not of type OutboundDocSubmissionOrchestratable.");
        }
        getLogger().debug("End OutboundDocSubmissionDeferredRequestStrategyImpl_g1.process");
    }

}
