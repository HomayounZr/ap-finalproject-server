package helper;

import models.BoardItem;
import models.UserSaves;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SavesFileHelper {

    private final String ROOT = "./data/saves/";

    public void createFile(String filename){
        File file = new File(ROOT + filename);
        try {
            if (!file.exists())
                file.createNewFile();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public FileInputStream getUserFile(String filename){
        File file = new File(ROOT + filename);
        try {
            return new FileInputStream(file);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void saveNewFile(String filename,byte[] buffer,int bytes){
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            outputStream.write(buffer,0,bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

//    private String fileName;
//    private File file;
//    private ArrayList<UserSaves> saves;

//    public SavesFileHelper(String fileName){
//        this.fileName = fileName;
//        file = new File("./data/" + fileName + ".txt");
//        saves = new ArrayList<>();
//        if(!file.exists()){
//            // creating the file if doesn't exists
//            try{
//                Files.createFile(Paths.get("./data/" + fileName + ".txt"));
//            } catch (IOException ex){
//                ex.printStackTrace();
//            }
//        } else {
//            // load previous data
//            loadData();
//        }
//    }

//    private void loadData(){
//        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
//
//            while(true){
//                UserSaves save = (UserSaves) inputStream.readObject();
//                saves.add(save);
//            }
//
//        } catch (EOFException ex){
//            System.out.println("end of file");
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public JSONArray getUserSaves(String username){
//        for(UserSaves save: saves){
//            if(save.getUsername().equalsIgnoreCase(username)){
//                return save.getSaves();
//            }
//        }
//        return null;
//    }
//
//    public void addNewSave(String username,JSONObject newSave){
//        for(UserSaves save: saves){
//            if(save.getUsername().equalsIgnoreCase(username)){
//                save.newSave(newSave);
//            }
//        }
//    }
//
//    public void saveData(){
//        if(file.exists())
//            file.delete();
//
//        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
//
//            for(UserSaves save: saves){
//                outputStream.writeObject(save);
//            }
//
//            outputStream.flush();
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }




}
