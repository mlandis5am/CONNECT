/*
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
package gov.hhs.fha.nhinc.docquery._20.entity.deferred.request;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.common.nhinccommon.NhinTargetCommunitiesType;
import gov.hhs.fha.nhinc.common.nhinccommonentity.RespondingGatewayCrossGatewayQuerySecuredRequestType;
import gov.hhs.fha.nhinc.docquery._20.entity.deferred.request.EntityDocQueryDeferredReqSecured;
import gov.hhs.fha.nhinc.docquery.entity.deferred.request.EntityDocQueryDeferredReqOrchImpl;
import gov.hhs.healthit.nhin.DocQueryAcknowledgementType;
import javax.xml.ws.WebServiceContext;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mark Goldman
 */
public class EntityDocQueryDeferredReqSecuredTest {

  Mockery mockery;

  @Before
  public void setup() {
    mockery = new Mockery() {

      {
        setImposteriser(ClassImposteriser.INSTANCE);
      }
    };
  }

  /**
   * Test of respondingGatewayCrossGatewayQuery method, of class EntityDocQueryDeferredReqSecured.
   */
  @Test
  @Ignore
  public void testRespondingGatewayCrossGatewayQuery_rem() {
    final EntityDocQueryDeferredReqOrchImpl mockOrchImpl = mockery.mock(EntityDocQueryDeferredReqOrchImpl.class);

    final RespondingGatewayCrossGatewayQuerySecuredRequestType mockReq = mockery.mock(RespondingGatewayCrossGatewayQuerySecuredRequestType.class);
    final DocQueryAcknowledgementType expected = new DocQueryAcknowledgementType();

    EntityDocQueryDeferredReqSecured testSubject = new EntityDocQueryDeferredReqSecured() {

      @Override
      protected EntityDocQueryDeferredReqOrchImpl getOrchImpl() {
        return mockOrchImpl;
      }

      @Override
      protected AssertionType extractAssertion(WebServiceContext context) {
        return new AssertionType();
      }
    };

    mockery.checking(new Expectations() {

      {
        one(mockReq).getAdhocQueryRequest();
        one(mockReq).getNhinTargetCommunities();
        one(mockOrchImpl).respondingGatewayCrossGatewayQuery(
                with(any(AdhocQueryRequest.class)),
                with(any(AssertionType.class)),
                with(any(NhinTargetCommunitiesType.class)));
        will(returnValue(expected));

      }
    });

    DocQueryAcknowledgementType result = testSubject.respondingGatewayCrossGatewayQuery(new RespondingGatewayCrossGatewayQuerySecuredRequestType());
    assertSame(result, expected);

    mockery.assertIsSatisfied();
  }

   /**
   * Test of respondingGatewayCrossGatewayQuery method, of class EntityDocQueryDeferredReqSecured.
   */
  @Test
  public void testRespondingGatewayCrossGatewayQuery() {
    final DocQueryAcknowledgementType expected = new DocQueryAcknowledgementType();

    EntityDocQueryDeferredReqSecured testSubject = new EntityDocQueryDeferredReqSecured() {

      @Override
      protected DocQueryAcknowledgementType respondingGatewayCrossGatewayQuery(
          final RespondingGatewayCrossGatewayQuerySecuredRequestType body, final WebServiceContext context) {
        return expected;
      }
    };

    DocQueryAcknowledgementType result = testSubject.respondingGatewayCrossGatewayQuery(new RespondingGatewayCrossGatewayQuerySecuredRequestType());
    assertSame(result, expected);
  }
}