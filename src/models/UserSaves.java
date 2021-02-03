package models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * for saving save as json file
 * ** NOT WORKING*
 */
public class UserSaves {

    private String username;
    private JSONArray saves;

    public UserSaves(String username){
        this.username = username;
        this.saves = new JSONArray();
    }

    public String getUsername() {
        return username;
    }

    public JSONArray getSaves() {
        return saves;
    }

    public void newSave(JSONObject save){
        saves.add(save);
    }
}
