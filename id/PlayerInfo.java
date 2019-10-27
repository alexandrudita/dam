package ro.ase.eu.damapp;

import android.os.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Alex Dita on 11/12/2017.
 */
public class PlayerInfo {

    private String name;
    private Date birthday;
    private String favoriteHand;

    public PlayerInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFavoriteHand() {
        return favoriteHand;
    }

    public void setFavoriteHand(String favoriteHand) {
        this.favoriteHand = favoriteHand;
    }

    PlayerInfo(Parcel source) {

        this.name = source.readString();

        try {
            this.birthday = new SimpleDateFormat(AddPlayerActivity.DATE_FORMAT, Locale.US)
                    .parse(source.readString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.favoriteHand = source.readString();
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", favoriteHand='" + favoriteHand + '\'' +
                '}';
    }
}
