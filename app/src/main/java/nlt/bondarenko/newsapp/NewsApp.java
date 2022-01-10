package nlt.bondarenko.newsapp;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import nlt.bondarenko.newsapp.repository.DaggerRepositoryComponent;
import nlt.bondarenko.newsapp.repository.RepositoryComponent;
import nlt.bondarenko.newsapp.repository.RepositoryModule;
import nlt.bondarenko.newsapp.roomdatabase.database.AppDataBase;

public class NewsApp extends Application {

    private static NewsApp instance;
    private static AppDataBase appDataBase;
    private static RepositoryComponent component;

    public static AppDataBase getAppDataBase() {
        if (appDataBase == null) {
            synchronized (AppDataBase.class) {
                appDataBase = Room.databaseBuilder(getNewsAppContext(),
                        AppDataBase.class, "news_database").build();
            }
        }

        return appDataBase;
    }

    public static RepositoryComponent repositoryComponent() {
        return component;
    }

    private static Context getNewsAppContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeDaggerGraph();
    }

    private void initializeDaggerGraph() {
        component = DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule()).build();

    }

}
