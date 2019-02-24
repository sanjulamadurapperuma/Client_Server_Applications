package com.sanjula.uow.java.clientserver.simpletestthree;

public class SimpleClient {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleClient client = new SimpleClient();
        client.executeTest();
    }

    private void executeTest(){
        String scale = " Celcius";
        SimpleTemperatureRecorderServer server = new SimpleTemperatureRecorderServer();
        if(server.isServerConnected()){
            System.out.println("[CLIENT] - The server is connected. The test can proceed");
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));
            server.addTemperature(10);
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));

            server.addTemperature(1);
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));

            server.addTemperature(12);
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));

            server.addTemperature(-0.1);
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));

            server.addTemperature(-18);
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));

            server.addTemperature(-18.0);
            System.out.println("[CLIENT] - The minimum recorded temperature is " + server.getColdestTemperature(scale));
            System.out.println("[CLIENT] - The highest recorded temperature is " + server.getHottestTemperature(scale));

            System.out.println("[CLIENT] - The average recorded temperature is " + server.getAverageTemperature(scale));

        } else{
            System.out.println("[CLIENT] - The server is not connected, terminating test");
        }
    }
}
