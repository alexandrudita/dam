package ro.ase.eu.damapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex Dita on 26.11.2017.
 */

public class DatabaseController extends SQLiteOpenHelper implements DatabaseConstant {

    private static DatabaseController controller;

    public static DatabaseController getInstance(Context context) {
        if (controller == null) {
            synchronized (DatabaseController.class) {
                if (controller == null) {
                    controller = new DatabaseController(context);
                }
            }
        }
        return controller;
    }

    private DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_PLAYER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE_PLAYER);
            onCreate(db);
        } catch (Exception e) {
        }
    }
}
