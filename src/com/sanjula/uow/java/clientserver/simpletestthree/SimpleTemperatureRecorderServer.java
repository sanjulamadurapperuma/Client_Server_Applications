package com.sanjula.uow.java.clientserver.simpletestthree;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleTemperatureRecorderServer {
    ArrayList temperatures = new ArrayList();
    String fileName = "temperatures.txt";

    public SimpleTemperatureRecorderServer(){
        try{
            loadFromFile();
        } catch(IOException ex){
            Logger.getLogger(SimpleTemperatureRecorderServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Logger.getLogger(SimpleTemperatureRecorderServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isServerConnected(){
        System.out.println("[SERVER] - Testing if the server is connected");
        return true;
    }

    public void addTemperature(double sample){
        temperatures.add(sample);
        System.out.println("[SERVER] - " + temperatures);
        try {
            this.saveToFile();
        } catch (IOException ex) {
            Logger.getLogger(SimpleTemperatureRecorderServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getColdestTemperature(String scale){
        double min = 0.0;
        if(temperatures.size() != 0){
            min = (double) temperatures.get(0);

            for(int index = 0; index < temperatures.size(); index++){
                if(min > (double) temperatures.get(index)){
                    min = (double) temperatures.get(index);
                }
            }
        }
        return min + scale;
    }


    public String getHottestTemperature(String scale){
        double max = 0.0;
        if(temperatures.size() != 0){
            max = (double) temperatures.get(temperatures.size() - 1);

            for(int index = 0; index < temperatures.size(); index++){
                if(max < (double) temperatures.get(index)){
                    max = (double) temperatures.get(index);
                }
            }
        }
        return max + scale;
    }

    public String getAverageTemperature(String scale){
        int sum = 0;
        if(temperatures.size() != 0){
            for(int index = 0; index < temperatures.size(); index++){
                sum += (double) temperatures.get(index);
            }
        }
        return sum / temperatures.size() + scale;
    }

    public double getNumberOfSamples(){
        return temperatures.size();
    }

    private void saveToFile() throws FileNotFoundException, IOException{
        System.out.println("[SERVER] - Saving " + temperatures + " to file " + fileName);

        //Saving of objects to a file
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);

        //Method for serialization of object
        out.writeObject(temperatures);

        out.close();
        file.close();

        System.out.println("Object has been serialized");
    }

    private void loadFromFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        System.out.println("[SERVER] - Loading sample from file " + fileName);

        //Reading the objects from a file
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);

        //Method for deserialization of object
        temperatures = (ArrayList)in.readObject();

        in.close();
        file.close();
    }
}
