package nlt.bondarenko.newsapp.screens.articleList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.screens.MainActivity;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListFragment extends Fragment implements ArticleListContract.ArticleListView, ArticleListAdapter.OnClickListenerArticleList {

    private ArticleListContract.ArticleListPresenter articleListPresenter;
    private RecyclerView recyclerViewArticle;
    private SearchView searchViewNews;
    private ArticleListAdapter articleListAdapterNews;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottom;

    public ArticleListFragment(FragmentManager fragmentManager, BottomNavigationView bottom) {
        this.fragmentManager = fragmentManager;
        this.bottom = bottom;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        articleListPresenter = new ArticleListPresenterImpl(getContext());
        return inflater.inflate(R.layout.fragment_article_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleListPresenter.attach(this);
        bottom.setVisibility(View.VISIBLE);
        recyclerViewArticle = view.findViewById(R.id.recycle_view_article_list);
        searchViewNews = view.findViewById(R.id.search_view_news);
        articleListAdapterNews = new ArticleListAdapter(getContext(), this);
        recyclerViewArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewArticle.setAdapter(articleListAdapterNews);
        articleListPresenter.getArticleList();

        searchViewNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                articleListPresenter.getArticleListSearch(query);
                Toast.makeText(getContext(), searchViewNews.getQuery().toString(), Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void updateArticleList(List<Article> articleList) {
        articleListAdapterNews.setArticleList(articleList);

    }

    @Override
    public void shareArticleNews(Article news) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, news.getUrl());
        sendIntent.putExtra(Intent.EXTRA_TITLE, news.getTitle());
        sendIntent.setType("text/plain");
        Context context = getContext();
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(sendIntent, null));
        } else {
            Toast.makeText(context, "No app to send email. Please install at least one",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showArticleNews(String url) {
        ArticleFragment articleFragment = new ArticleFragment(url, bottom);
        fragmentManager.beginTransaction().add(R.id.frame_layout_main, articleFragment, MainActivity.TAG_ARTICLE_FRAGMENT)
                .addToBackStack(null).commit();
    }

    @Override
    public void onDestroyView() {
        articleListPresenter.detach();
        super.onDestroyView();
    }

    @Override
    public void onClickItemArticle(Article news) {
        articleListPresenter.setArticleDataBase(news);
    }

    @Override
    public void onClickListenerArticleShare(Article news) {
        articleListPresenter.shareArticleNews(news);
    }

    @Override
    public void onClickListenerWebView(String url) {
        articleListPresenter.showArticleWebView(url);
    }
}
