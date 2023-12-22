
package com.example.springboot.errorHandling;

public class facilityNotFoundError extends Exception{
    public facilityNotFoundError(){
        super();
    }

    public facilityNotFoundError(String message){
        super(message);
    }

    public facilityNotFoundError(String message , Throwable cause){
        super(message, cause);
    }

    public facilityNotFoundError(Throwable cause){
        super(cause);
    }
    
}
