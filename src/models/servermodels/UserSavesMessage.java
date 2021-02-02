package models.servermodels;

import org.json.simple.JSONArray;

import java.io.Serializable;

public class UserSavesMessage extends RestMessageBody implements Serializable {

    private JSONArray saves;

    public UserSavesMessage(JSONArray saves){
        this.saves = saves;
    }

    public JSONArray getSaves() {
        return saves;
    }
}
