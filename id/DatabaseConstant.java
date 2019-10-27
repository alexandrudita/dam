package ro.ase.eu.damapp;

/**
 * Created by Alex Dita on 26.11.2017.
 */

public interface DatabaseConstant {

    String DATABASE_NAME = "scmdam.db";
    Integer DATABASE_VERSION = 1;

    String TABLE_NAME_PLAYER = "Player";

    String COLUMN_PLAYER_ID = "_id";
    String COLUMN_PLAYER_BIRTHDAY = "birthday";
    String COLUMN_PLAYER_NAME = "name";
    String COLUMN_PLAYER_NUMBER = "number";
    String COLUMN_PLAYER_FAVORITE_HAND = "favorite_hand";
    String COLUMN_PLAYER_POSITION = "position";

    String CREATE_TABLE_PLAYER = "CREATE TABLE " + TABLE_NAME_PLAYER + " ( " +
            COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PLAYER_BIRTHDAY + " TEXT, " +
            COLUMN_PLAYER_NAME + " TEXT, " +
            COLUMN_PLAYER_NUMBER + " INTEGER, " +
            COLUMN_PLAYER_FAVORITE_HAND + " TEXT, " +
            COLUMN_PLAYER_POSITION + " TEXT);";

    String DROP_TABLE_PLAYER = "DROP TABLE IF EXISTS "+TABLE_NAME_PLAYER;
}
