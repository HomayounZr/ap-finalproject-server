package controllers;

import helper.FileHelper;
import models.ScoreItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScoreBoardController {

    private FileHelper fileHelper;

    public ScoreBoardController(){
        this.fileHelper = new FileHelper("scoreboard");
    }

    public ArrayList<ScoreItem> getScores(){
        return fileHelper.getScores();
    }

    public boolean addScore(ScoreItem newItem){
        ArrayList<ScoreItem> items = fileHelper.getScores();
        ScoreItem previousRecord = null;
        for(ScoreItem item: items){
            if(item.getUsername().equalsIgnoreCase(newItem.getUsername())){
                previousRecord = item;
                break;
            }
        }

        if(previousRecord == null){
            items.add(newItem);
        } else {
            previousRecord.setScore(newItem.getScore());
        }
        fileHelper.saveData();

        return true;
    }

    public boolean changeUsername(String username){
        ArrayList<ScoreItem> items = fileHelper.getScores();
        for (ScoreItem item: items){
            if(item.getUsername().equalsIgnoreCase(username)){
                // same username
                return false;
            }
        }

        ScoreItem newItem = new ScoreItem(username,0);
        items.add(newItem);
        fileHelper.saveData();

        return true;
    }
}
