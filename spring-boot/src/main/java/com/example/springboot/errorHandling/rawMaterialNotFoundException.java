package com.example.springboot.errorHandling;

public class rawMaterialNotFoundException extends Exception{
    public rawMaterialNotFoundException(){
        super();
    }

    public rawMaterialNotFoundException(String message){
        super(message);
    }

    public rawMaterialNotFoundException(String message , Throwable cause){
        super(message, cause);
    }

    public rawMaterialNotFoundException(Throwable cause){
        super(cause);
    }
}
