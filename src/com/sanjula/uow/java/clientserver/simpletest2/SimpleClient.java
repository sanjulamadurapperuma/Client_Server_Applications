package com.sanjula.uow.java.clientserver.simpletest2;

import java.util.Arrays;

public class SimpleClient {
    SimpleCalculatorServer server = new SimpleCalculatorServer();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SimpleClient simpleClient = new SimpleClient();
        simpleClient.executeTest();
        // TODO code application logic here
    }

    private void executeTest() {
        System.out.println("[CLIENT] - Testing if the server is connected...");
        if(server.isServerConnected()){
            System.out.println("[CLIENT] - The client is connected, the test can proceed...\n");

            //Addition
            double a = 2;
            double b = 5;
            System.out.println("[CLIENT] - Asking the server what is the sum of " + a + " and " + b + "...");
            double sum = server.addNumbers(a, b);
            System.out.println("[CLIENT] - The server has replied that the sum of " + a + " and " + b + " is " + sum);
            if(sum == a+b){
                System.out.println("[CLIENT] - The result returned from the server is correct\n");
            } else{
                System.out.println("[CLIENT] - The result returned from the server is NOT correct\n");
            }

            //Division
            a = 4;
            b = 0;
            System.out.println("[CLIENT] - Asking the server what is the ratio of " + a + " and " + b + "...");
            double div = 0;
            try {
                div = server.divideNumbers(a, b);
            }
            catch (SimpleCalculatorServerException ex) {
                System.out.println(ex.getMessage());
            }
            try{
                System.out.println("[CLIENT] - The server has replied that the ratio of " + a + " and " + b + " is " + div);
                if(div == a/b){
                    System.out.println("[CLIENT] - The result returned from the server is correct.\n");
                } else{
                    System.out.println("[CLIENT] - The result returned from the server is NOT correct.\n");
                }
            } catch(ArithmeticException ex){
                System.out.println(ex.getMessage());
            }


            //Subtraction
            a = 10;
            b = 8;
            System.out.println("[CLIENT] - Asking the server what is the subtraction of " + a + " and " + b + "...");
            double subtraction = server.subtractNumbers(a, b);
            System.out.println("[CLIENT] - The server has replied that the subtraction of " + a + " and " + b + " is " + subtraction);
            if(subtraction == a-b){
                System.out.println("[CLIENT] - The result returned from the server is correct\n");
            } else{
                System.out.println("[CLIENT] - The result returned from the server is NOT correct\n");
            }

            //Multiplication
            a = 6;
            b = 6;
            System.out.println("[CLIENT] - Asking the server what is the multiplication of " + a + " and " + b + "...");
            double multiply = server.multiplyNumbers(a, b);
            System.out.println("[CLIENT] - The server has replied that the multiplication of " + a + " and " + b + " is " + multiply);
            if(multiply == a*b){
                System.out.println("[CLIENT] - The result returned from the server is correct\n");
            } else{
                System.out.println("[CLIENT] - The result returned from the server is NOT correct\n");
            }

            //For Arrays
            //Addition
            int[] arrayA = {1,2,3,};
            int[] arrayB = {3,4,5};
            System.out.println("[CLIENT] - Asking the server what is the sum of " +
                    Arrays.toString(arrayA) + " and " + Arrays.toString(arrayB) + "...");
            try{
                int[] arraySum = server.addArrayNumbers(arrayA, arrayB);
                System.out.println("[CLIENT] - The server has replied that the sum of " +
                        Arrays.toString(arrayA) + " and " + Arrays.toString(arrayB) +
                        " is " + Arrays.toString(arraySum) + "\n");
            } catch(ArrayIndexOutOfBoundsException arrayIndexErr){
                System.out.println("[SERVER] - The length of the two arrays do not match. "
                        + "Operation not possible\n");
            }


            //For Division of Arrays
            int[] arrayC = {1,2,3,0};
            int[] arrayD = {3,0,4,0};
            System.out.println("[CLIENT] - Asking the server what is the division of " +
                    Arrays.toString(arrayC) + " and " + Arrays.toString(arrayD) + "...");
            try{
                int[] arrayDiv = server.divideArrayNumbers(arrayC, arrayD);
                System.out.println("[CLIENT] - The server has replied that the division of " +
                        Arrays.toString(arrayC) + " and " + Arrays.toString(arrayD) +
                        " is " + Arrays.toString(arrayDiv));
            } catch(ArrayIndexOutOfBoundsException arrayIndexErr){
                System.out.println("[SERVER] - The length of the two arrays do not match. "
                        + "Operation not possible");
            } catch(ArithmeticException e){
                System.out.println("[SERVER] - Cannot divide values with 0. "
                        + "Operation not possible");
            }


        } else{
            System.out.println("[CLIENT] - The client is NOT connected, the test has failed...");
        }


    }
}
