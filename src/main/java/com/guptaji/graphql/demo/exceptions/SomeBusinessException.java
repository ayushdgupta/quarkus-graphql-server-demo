package com.guptaji.graphql.demo.exceptions;

import io.smallrye.graphql.api.ErrorCode;

@ErrorCode("some-business-error-code")
public class SomeBusinessException extends RuntimeException{

    public SomeBusinessException(String msg){
        super(msg);
    }
}

