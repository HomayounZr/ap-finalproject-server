package controllers;

import appStart.AppStart;
import helper.FileHelper;
import models.BoardItem;
import models.ScoreItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ScoreBoard Controller
 */
public class ScoreBoardController {

    private FileHelper fileHelper;

    public ScoreBoardController(){
        this.fileHelper = new FileHelper("scoreboard");
    }

    /**
     * get all scores
     * @return ArrayList BoardItem
     */
    public ArrayList<BoardItem> getScores(){
        return fileHelper.getScores();
    }

    /**
     * add a new game result and update board
     * @param newItem BoardItem
     * @return true
     */
    public boolean addScore(BoardItem newItem){
        BoardItem alreadyExists = findUser(newItem.getUsername());

        if(alreadyExists != null)
            getScores().remove(alreadyExists);

        getScores().add(newItem);
        fileHelper.saveData();

        return true;
    }

    /**
     * when a user wants to change his username
     * @param prevUsername String current username
     * @param newUsername Stirng new username
     * @return false if exists
     */
    public boolean changeUsername(String prevUsername,String newUsername){
        if(prevUsername.equalsIgnoreCase(newUsername))
            return true;

        BoardItem sameUsernameItem = findUser(newUsername);
        if(sameUsernameItem != null)
            return false;

        BoardItem userItem;
        if(!prevUsername.equals("")){
            userItem = findUser(prevUsername);
            userItem.setUsername(newUsername);
        } else {
            userItem = new BoardItem(newUsername,0,0,0,0,0);
            fileHelper.addNewItem(userItem);
            AppStart.savesController.createFileForUser(newUsername);
        }

        fileHelper.saveData();

        return true;
    }

    /**
     * find a user, used for checking same usernames
     * @param username String
     * @return BoardItem
     */
    public BoardItem findUser(String username){
        ArrayList<BoardItem> items = fileHelper.getScores();
        for(BoardItem item: items){
            if(item.getUsername().equalsIgnoreCase(username)){
                return item;
            }
        }
        return null;
    }
}
