package com.sanjula.uow.java.clientserver.simpletestfour;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DummyServer implements Serializable {
    ArrayList<TemperatureSample> samples = new ArrayList<TemperatureSample>();
    String fileName = "Sample.ser";

    public DummyServer(){
        try{
            loadFromFile();
        } catch(IOException ex){
            Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ClassNotFoundException ex){
            Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveToFile() throws FileNotFoundException {
        ObjectOutputStream out = null;
        try {
            System.out.println("[SERVER] - Saving " + samples + " to file " + fileName);
            //Saving of objects to a file
            File arrayListFile = new File(fileName);
            arrayListFile.createNewFile(); // if file already exists it will do nothing
            FileOutputStream file = new FileOutputStream(arrayListFile, false);
            out = new ObjectOutputStream(file);
            //Method for serialization of object
            out.writeObject(samples);
            out.close();
            file.close();
            System.out.println("Object has been serialized");
        } catch (IOException ex) {
            System.out.println("Error writing to file. Try again later.");
            Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadFromFile() throws IOException, ClassNotFoundException{
        System.out.println("[SERVER] - Loading sample from file " + fileName);
        //Reading the objects from a file
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            //Method for deserialization of object
            samples = (ArrayList<TemperatureSample>)in.readObject();

            in.close();
            file.close();
        } catch(FileNotFoundException ex){
            System.out.println("File: " + fileName + " was not found");
            File arrayListFile = new File(fileName);
            arrayListFile.createNewFile();
            System.out.println("File was created");
        }

    }

    public void addSample(TemperatureSample s){
        System.out.println("[SERVER] - Adding " + s + " to " + samples);
        samples.add(s);

        System.out.println("[SERVER] - Sample is " + samples);
        try {
            this.saveToFile();
        } catch (IOException ex) {
            Logger.getLogger(DummyServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public double getColdestTemperature(String city){
        double min = 0.0;
        if(samples.size() != 0){
            min = (double) samples.get(0).value;
            for(int index = 0; index < samples.size(); index++){
                if(city.equals(samples.get(index).location)){
                    if(min > (double) samples.get(index).value){
                        min = (double) samples.get(index).value;
                    }
                }
            }
        }
        return min;
    }

    public double getHottestTemperature(String city){
        double max = 0.0;
        if(samples.size() != 0){
            max = (double) samples.get(samples.size() - 1).value;
            for(int index = 0; index < samples.size(); index++){
                if(city.equals(samples.get(index).location)){
                    if(max < (double) samples.get(index).value){
                        max = (double) samples.get(index).value;
                    }
                }
            }
        }
        return max;
    }

    public double getAverageTemperature(String city){
        int sum = 0;
        if(samples.size() != 0){

            for(int index = 0; index < samples.size(); index++){
                if(city.equals(samples.get(index).value)){
                    sum += (double) samples.get(index).value;
                }
            }
        }
        return sum / samples.size();
    }

    public int getNumberOfSamples(String city){
        int numOfSamples = 0;
        for(int index = 0; index < samples.size(); index++){
            if(city.equals(samples.get(index).location)){
                numOfSamples++;
            }
        }
        return numOfSamples;
    }

    public double getColdestTemperatureAfter(Date date){
        double min = 0.0;
        if(samples.size() != 0){
            min = (double) samples.get(0).value;
            for(int index = 0; index < samples.size(); index++){
                if(date.before(samples.get(index).time)){
                    if(min > (double) samples.get(index).value){
                        min = (double) samples.get(index).value;
                    }
                }
            }
        }
        return min;
    }

    public double getHottestTemperatureIn(Date date){
        double max = 0.0;
        if(samples.size() != 0){
            max = (double) samples.get(samples.size() - 1).value;
            for(int index = 0; index < samples.size(); index++){
                if(date.before(samples.get(index).time)){
                    if(max < (double) samples.get(index).value){
                        max = (double) samples.get(index).value;
                    }
                }
            }
        }
        return max;
    }

    public double getAverageTemperature(Date date){
        int sum = 0;
        if(samples.size() != 0){
            for(int index = 0; index < samples.size(); index++){
                if(date.before(samples.get(index).time)){
                    sum += (double) samples.get(index).value;
                }
            }
        }
        return sum / samples.size();
    }

    public int getNumberOfSamples(Date date){
        int numOfSamples = 0;
        for(int index = 0; index < samples.size(); index++){
            if(date.before(samples.get(index).time)){
                numOfSamples++;
            }
        }
        return numOfSamples;
    }


    public boolean isConnected(){
        System.out.println("[SERVER] - Testing if the server is connected...");
        return true;
    }
}
