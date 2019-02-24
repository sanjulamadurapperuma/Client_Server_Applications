package com.sanjula.uow.java.clientserver.simpletestfour;

import java.io.Serializable;
import java.util.Date;

public class DummyClient implements Serializable {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DummyClient client = new DummyClient();
        client.executeTest();
    }

    private void executeTest(){
        DummyServer server = new DummyServer();
        if(server.isConnected()){
            System.out.println("[CLIENT] - The server is connected, the test can proceed...");

            TemperatureSample s1 = new TemperatureSample();
            s1.value = 10.3;
            s1.location = "London";
            s1.time = new Date();
            System.out.println("[CLIENT] - The sample is: " + s1);
            server.addSample(s1);

            TemperatureSample s2 = new TemperatureSample();
            s2.value = 9.1;
            s2.location = "London";
            s2.time = new Date();
            System.out.println("[CLIENT] - The sample is: " + s2);
            server.addSample(s2);

            TemperatureSample s3 = new TemperatureSample();
            s3.value = 5.1;
            s3.location = "London";
            s3.time = new Date();
            System.out.println("[CLIENT] - The sample is: " + s3);
            server.addSample(s3);

            //Temperature by city
            System.out.println("[CLIENT] - The minimum recorded temperature is " +
                    server.getColdestTemperature("London"));
            System.out.println("[CLIENT] - The maximum recorded temperature is " +
                    server.getHottestTemperature("London"));
            System.out.println("[CLIENT] - The average recorded temperature is " +
                    server.getAverageTemperature("London"));
            System.out.println("[CLIENT] - The number of samples recorded are " +
                    server.getNumberOfSamples("London"));

            //Temperature before specified date
            System.out.println("[CLIENT] - The minimum recorded temperature before " + s1.time + " is " +
                    server.getColdestTemperatureAfter(s1.time));
            System.out.println("[CLIENT] - The maximum recorded temperature before " + s2.time + " is " +
                    server.getHottestTemperatureIn(s2.time));
            System.out.println("[CLIENT] - The average recorded temperature before " + s1.time + " is " +
                    server.getAverageTemperature(s1.time));
            System.out.println("[CLIENT] - The number of samples recorded before " + s1.time + " is " +
                    server.getNumberOfSamples(s1.time));

            System.out.println("[CLIENT] - The test has finished");
        } else{
            System.out.println("[CLIENT] - The server is not connected. The test has failed");
        }
    }
}
