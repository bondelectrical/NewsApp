package nlt.bondarenko.newsapp.interactor;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.Article;
import nlt.bondarenko.newsapp.util.newsApi.models.response.ArticleResponse;

public interface ArticleListInteractor {

    ArticleResponse getArticleListNews() throws IOException;

    void setNewsBookMarks(Context context, Article article);

    void shareArticleNews(Context context, Article article);

    void showArticleNews(FragmentManager fragmentManager, String url, BottomNavigationView bottom);

    ArticleResponse getArticleListSearchNews(String search) throws IOException;
}
