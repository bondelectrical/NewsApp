package nlt.bondarenko.newsapp.roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import nlt.bondarenko.newsapp.roomdatabase.dao.NewsBookMarksDao;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;

@Database(entities = {NewsBookMarksEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase appDataBase;

    public static AppDataBase getAppDataBase(Context context) {
        if (appDataBase == null) {
            synchronized (AppDataBase.class) {
                appDataBase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class, "news_database").build();
            }
        }

        return appDataBase;
    }

    public abstract NewsBookMarksDao newsBookMarksDao();

}
