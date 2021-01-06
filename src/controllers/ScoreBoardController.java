package controllers;

import helper.FileHelper;
import models.ScoreItem;

import java.util.ArrayList;

public class ScoreBoardController {

    private FileHelper fileHelper;

    public ScoreBoardController(){
        this.fileHelper = new FileHelper("scoreboard");
    }

    public ArrayList<ScoreItem> getScores(){
        return fileHelper.getScores();
    }

    public boolean addScore(String username, int secureRandom, int score){
        ScoreItem item = checkUsername(new ScoreItem(username, secureRandom, score));

        if(item != null){
            fileHelper.saveData();
            return true;
        }

        return false;
    }

    public boolean checkNewUsername(String username, int secureRandom){
        ArrayList<ScoreItem> scores = fileHelper.getScores();
        for(ScoreItem score: scores){
            if(score.getUsername().equalsIgnoreCase(username)){
                if(score.getSecureRandom() == secureRandom){
                    // ok
                    return true;
                } else {
                    // if exists
                    return false;
                }
            }
        }
        // ok
        return true;
    }

    private ScoreItem checkUsername(ScoreItem newScore){
        ArrayList<ScoreItem> scores = fileHelper.getScores();
        for(ScoreItem score: scores){
            if(score.getUsername().equalsIgnoreCase(newScore.getUsername())){
                if(score.getSecureRandom() == newScore.getSecureRandom()){
                    return score;
                } else {
                    return null;
                }
            }
        }
        // add new item to list
        scores.add(newScore);
        // return new item
        return newScore;
    }
}
