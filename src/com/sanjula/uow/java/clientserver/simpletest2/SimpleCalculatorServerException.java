package com.sanjula.uow.java.clientserver.simpletest2;

public class SimpleCalculatorServerException extends Exception {
    public SimpleCalculatorServerException(String cannot_divide_a_number_to_zero) {
        super(cannot_divide_a_number_to_zero);
    }
}
