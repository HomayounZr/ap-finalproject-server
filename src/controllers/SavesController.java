package controllers;

import helper.SavesFileHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileInputStream;

/**
 *
 */
public class SavesController {

    private SavesFileHelper fileHelper;

    public SavesController(){
        fileHelper = new SavesFileHelper();
    }

//    public JSONArray getUserSaves(String username){
//        return fileHelper.getUserSaves(username);
//    }
//
//    public void addNewSave(String username, JSONObject save){
//        fileHelper.addNewSave(username,save);
//        fileHelper.saveData();
//    }

    public void createFileForUser(String username){
        fileHelper.createFile(username + ".txt");
    }

    public FileInputStream getUserSaves(String username){
        return fileHelper.getUserFile(username + ".txt");
    }

    public void saveNewFile(String username,byte[] buffer,int bytes){
        fileHelper.saveNewFile(username + ".txt",buffer,bytes);
    }

}
