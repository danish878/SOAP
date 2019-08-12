package com.danny.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.danny.soap.dto.PaymentProcessorRequest;
import com.danny.soap.dto.PaymentProcessorResponse;
import com.danny.soap.exception.ServiceException;

@WebService
public interface PaymentProcessor {
    // All the below Annotations are OPTIONAL because JAX-WS puts these implicitly
    @WebMethod
    public @WebResult(name = "result")
    PaymentProcessorResponse processPayment(
            @WebParam(name = "paymentProcessorRequest") PaymentProcessorRequest paymentProcessorRequest)
            throws ServiceException;
}
