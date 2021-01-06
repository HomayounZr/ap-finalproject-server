package models;

import java.io.Serializable;

public class RestMessage implements Serializable {

    private String route;
    private Object body;

    public RestMessage(String route, Object body){
        this.route = route;
        this.body = body;
    }

    public String getRoute() {
        return route;
    }

    public Object getBody() {
        return body;
    }

}
