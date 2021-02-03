package helper;

import models.BoardItem;
import models.ScoreItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * File Helper for operations on scoreboards file
 */
public class FileHelper {

    private String fileName;
    private File file;
    private ArrayList<BoardItem> items;

    /**
     * constructor,
     * create file if doesn't exist
     * and load previous data
     * @param fileName String
     */
    public FileHelper(String fileName){
        this.fileName = fileName;
        items = new ArrayList<>();
        file = new File("./data/" + fileName + ".txt");
        if(!file.exists()){
            // creating the file if doesn't exists
            try{
                Files.createFile(Paths.get("./data/" + fileName + ".txt"));
            } catch (IOException ex){
                ex.printStackTrace();
            }
        } else {
            // load previous data
            loadData();
        }
    }

    // load previous data
    private void loadData(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){

            while(true){
                BoardItem item = (BoardItem) inputStream.readObject();
//                System.out.println(item.getUsername());
                items.add(item);
            }

        } catch (EOFException ex){
            System.out.println("end of file");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * get all items
     * @return ArrayList BoardItem
     */
    public ArrayList<BoardItem> getScores(){
//        for(BoardItem item: items){
//            System.out.println(item.getUsername());
//        }
        return items;
    }

    /**
     * add a new game result
     * @param newItem BoardItem
     */
    public void addNewItem(BoardItem newItem){
        items.add(newItem);
    }

    /**
     * write changes on file
     */
    public void saveData(){
        if(file.exists())
            file.delete();

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){

            for(BoardItem item: items){
                outputStream.writeObject(item);
            }

            outputStream.flush();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
