package com.sanjula.uow.java.clientserver.simpletest2;

public class SimpleCalculatorServer {
    public boolean isServerConnected(){
        System.out.println("[SERVER] - Connection is being tested...");
        return true;
    }

    public double addNumbers(double a, double b){
        return a+b;
    }

    public double divideNumbers(double a, double b) throws SimpleCalculatorServerException{
        if(b == 0){
            throw new SimpleCalculatorServerException("[SERVER] - Cannot divide a number to zero");
        }

        if((a == 0) && (b == 0)){
            throw new SimpleCalculatorServerException("[SERVER] - Zero divided by zero is undetermined");
        }

        System.out.println("[SERVER] - Dividing " + a + " and " + b + "...");
        return a/b;
    }

    public double subtractNumbers(double a, double b){
        System.out.println("[SERVER] - Subtracting " + a + " and " + b + "...");
        return a-b;
    }

    public double multiplyNumbers(double a, double b){
        System.out.println("[SERVER] - Multiplying " + a + " and " + b + "...");
        return a*b;
    }

    public int[] addArrayNumbers(int[] a, int[] b){
        int[] c = new int[a.length];
        for(int i = 0; i < a.length; i++){
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public int[] divideArrayNumbers(int[] a, int[] b){
        int[] c = new int[a.length];
        for(int i = 0; i < a.length; i++){
            c[i] = a[i] / b[i];
        }
        return c;
    }
}
