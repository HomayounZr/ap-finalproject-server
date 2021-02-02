package models.servermodels;

import org.json.simple.JSONObject;

import java.io.Serializable;

public class NewSaveMessage extends RestMessageBody implements Serializable {

    private String username;
    private JSONObject save;

    public NewSaveMessage(String username,JSONObject save){
        this.username = username;
        this.save = save;
    }

    public String getUsername() {
        return username;
    }

    public JSONObject getSave() {
        return save;
    }
}
