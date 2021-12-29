package nlt.bondarenko.newsapp;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import nlt.bondarenko.newsapp.roomdatabase.database.AppDataBase;

public class NewsApp extends Application {

    private static NewsApp instance;
    private static AppDataBase appDataBase;

    public static AppDataBase getAppDataBase() {
        if (appDataBase == null) {
            synchronized (AppDataBase.class) {
                appDataBase = Room.databaseBuilder(getNewsAppContext(),
                        AppDataBase.class, "news_database").build();
            }
        }

        return appDataBase;
    }

    private static Context getNewsAppContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
