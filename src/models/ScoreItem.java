package models;

import java.io.Serializable;
import java.util.Objects;

public class ScoreItem implements Serializable {

    private String username;
//    private int secureRandom;
    private int score;

    public ScoreItem(String username, int score){
        this.username = username;
//        this.secureRandom = secureRandom;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //    public int getSecureRandom() {
//        return secureRandom;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreItem scoreItem = (ScoreItem) o;
//        return secureRandom == scoreItem.secureRandom &&
        return  Objects.equals(username, scoreItem.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, score);
    }
}
