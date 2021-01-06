package helper;

import models.ScoreItem;

import java.io.*;
import java.util.ArrayList;

public class FileHelper {

    private String fileName;
    private File file;
    private ArrayList<ScoreItem> scores;

    public FileHelper(String fileName){
        this.fileName = fileName;
        scores = new ArrayList<>();
        file = new File("./data/" + fileName + ".txt");
        if(!file.exists()){
            // creating the file if doesn't exists
            try{
                file.createNewFile();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        } else {
            // load previous data
            loadData();
        }
    }

    private void loadData(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){

            while(true){
                ScoreItem scoreItem = (ScoreItem) inputStream.readObject();
                scores.add(scoreItem);
            }

        } catch (EOFException ex){
            System.out.println("end of file");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public ArrayList<ScoreItem> getScores(){
        return scores;
    }

    public void saveData(){
        if(file.exists())
            file.delete();

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){

            for(ScoreItem score: scores){
                outputStream.writeObject(score);
            }

            outputStream.flush();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
