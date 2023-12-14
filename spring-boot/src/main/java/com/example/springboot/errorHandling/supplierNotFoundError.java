package com.example.springboot.errorHandling;

public class supplierNotFoundError extends Exception {
    public supplierNotFoundError(){
        super();
    }

    public supplierNotFoundError(String message){
        super(message);
    }

    public supplierNotFoundError(String message , Throwable cause){
        super(message, cause);
    }

    public supplierNotFoundError(Throwable cause){
        super(cause);
    }

   
}
