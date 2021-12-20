package nlt.bondarenko.newsapp.roomdatabase.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksUrl;

@Dao
public interface NewsBookMarksDao {

    @Query("SELECT * FROM NewsBookMarksEntity")
    List<NewsBookMarksEntity> getAll();

    @Query("SELECT * FROM NewsBookMarksEntity WHERE id = :id")
    NewsBookMarksEntity getById(long id);

    @Query("SELECT url FROM NewsBookMarksEntity WHERE id = :id")
    NewsBookMarksUrl getUrl(long id);

    @Insert
    void insert(NewsBookMarksEntity newsBookMarks);

    @Update
    void update(NewsBookMarksEntity newsBookMarks);

    @Delete
    void delete(NewsBookMarksEntity newsBookMarks);

}
