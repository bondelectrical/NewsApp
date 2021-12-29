package nlt.bondarenko.newsapp.roomdatabase.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

@Dao
public interface ArticleBookMarksDao {

    @Query("SELECT * FROM ArticleBookMarksEntity")
    List<ArticleBookMarksEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArticleBookMarksEntity articleBookMarks);

    @Update
    void update(ArticleBookMarksEntity articleBookMarks);

    @Delete
    void delete(ArticleBookMarksEntity articleBookMarks);

}
