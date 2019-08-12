package com.danny.sumws;

import com.danny.sumws.dto.SumRequest;
import com.danny.sumws.dto.SumResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SumWS {

    @WebMethod
    SumResponse calculateSum(SumRequest request);
}
