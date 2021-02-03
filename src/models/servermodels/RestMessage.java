package models.servermodels;

import models.servermodels.RestMessageBody;

import java.io.Serializable;

/**
 * define shape of a message could be
 * sent to server or client through socket
 */
public class RestMessage implements Serializable {

    private String route;
    private RestMessageBody body;

    public RestMessage(String route, RestMessageBody body){
        this.route = route;
        this.body = body;
    }

    public String getRoute() {
        return route;
    }

    public RestMessageBody getBody() {
        return body;
    }

}
