package nlt.bondarenko.newsapp.roomdatabase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import nlt.bondarenko.newsapp.roomdatabase.dao.ArticleBookMarksDao;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

@Database(entities = {ArticleBookMarksEntity.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ArticleBookMarksDao articleBookMarksDao();

}
