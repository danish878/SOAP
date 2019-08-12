package com.danny;

import com.danny.generated.SumRequest;
import com.danny.generated.SumResponse;
import com.danny.generated.SumWS;
import com.danny.generated.SumWSService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final String KEYSTORE_PROPS_FILE = "etc/clientKeyStore.properties";
    private static final String WSDL_URL = "http://localhost:8080/Sum_SOAP_WS_war/services/sumService?wsdl";

    @Test
    public void calculateSumShouldReturnAValidResult() throws MalformedURLException {
        SumWSService service = new SumWSService(new URL(WSDL_URL));
        SumWS port = service.getSumWSPort();

        Client client = ClientProxy.getClient(port);
        Endpoint endpoint = client.getEndpoint();

        HashMap<String, Object> outProps = new HashMap<>();

        outProps.put(WSHandlerConstants.ACTION, "UsernameToken Timestamp Signature Encrypt"); //add "ENCRYPT" action for encryption
        outProps.put(WSHandlerConstants.USER, "danny");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());

        outProps.put(WSHandlerConstants.ENCRYPTION_USER, "myservicekey"); // Encrypt using Server PUBLIC key
        outProps.put(WSHandlerConstants.ENC_PROP_FILE, KEYSTORE_PROPS_FILE); // etc -> Editable Text Configuration
        outProps.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Element}{http://www.w3.org/2000/09/xmldsig#}Signature;{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");

        outProps.put(WSHandlerConstants.SIGNATURE_USER, "myclientkey"); // Sign using Client PRIVATE key
        outProps.put(WSHandlerConstants.SIG_PROP_FILE, KEYSTORE_PROPS_FILE);
        outProps.put(WSHandlerConstants.SIGNATURE_PARTS, "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");

        outProps.put(WSHandlerConstants.TTL_TIMESTAMP, "30");

        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
        endpoint.getOutInterceptors().add(wssOut);

        HashMap<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, "Encrypt Signature Timestamp"); //add "ENCRYPT" action for encryption
        inProps.put(WSHandlerConstants.DEC_PROP_FILE, KEYSTORE_PROPS_FILE); // etc -> Editable Text Configuration
        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());
        inProps.put(WSHandlerConstants.SIG_PROP_FILE, KEYSTORE_PROPS_FILE);


        WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
        endpoint.getInInterceptors().add(wssIn);


        SumRequest request = new SumRequest();
        request.setNum1(3);
        request.setNum2(7);

        SumResponse response = port.calculateSum(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(response.getResult(), 10);

    }
}
