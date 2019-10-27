package ro.ase.eu.damapp;

import java.util.List;

public class HttpResponse{

    private List<Item> goalkeeper;
    private List<Item> inter;
    private List<Item> center;
    private List<Item> winger;


    public HttpResponse() {
    }

    public List<Item> getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(List<Item> goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public List<Item> getInter() {
        return inter;
    }

    public void setInter(List<Item> inter) {
        this.inter = inter;
    }

    public List<Item> getCenter() {
        return center;
    }

    public void setCenter(List<Item> center) {
        this.center = center;
    }

    public List<Item> getWinger() {
        return winger;
    }

    public void setWinger(List<Item> winger) {
        this.winger = winger;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "goalkeeper=" + goalkeeper +
                ", inter=" + inter +
                ", center=" + center +
                ", winger=" + winger +
                '}';
    }
}
