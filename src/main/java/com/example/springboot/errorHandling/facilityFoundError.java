package com.example.springboot.errorHandling;

public class facilityFoundError extends Exception{
    public facilityFoundError(){
        super();
    }

    public facilityFoundError(String message){
        super(message);
    }

    public facilityFoundError(String message , Throwable cause){
        super(message, cause);
    }

    public facilityFoundError(Throwable cause){
        super(cause);
    }
    
}
