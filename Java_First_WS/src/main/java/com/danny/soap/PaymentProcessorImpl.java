package com.danny.soap;

import com.danny.soap.dto.PaymentProcessorRequest;
import com.danny.soap.dto.PaymentProcessorResponse;
import com.danny.soap.exception.ServiceException;

public class PaymentProcessorImpl implements PaymentProcessor {

    @Override
    public PaymentProcessorResponse processPayment(PaymentProcessorRequest paymentProcessorRequest)
            throws ServiceException {

        PaymentProcessorResponse paymentProcessorResponse = new PaymentProcessorResponse();

        String cardNumber = paymentProcessorRequest.getCreditCardInfo().getCardNumber();
        if (cardNumber == null || cardNumber.length() == 0)
            throw new ServiceException("Invalid Card Number");

        paymentProcessorResponse.setResult(true);
        return paymentProcessorResponse;
    }

}
