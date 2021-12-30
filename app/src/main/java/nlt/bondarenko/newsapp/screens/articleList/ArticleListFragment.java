package nlt.bondarenko.newsapp.screens.articleList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.network.models.Article;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;

public class ArticleListFragment extends Fragment implements ArticleListContract.ArticleListView, ArticleListAdapter.OnClickListenerArticleList {

    private ArticleListContract.ArticleListPresenter articleListPresenter;
    private RecyclerView recyclerViewArticle;
    private ArticleListAdapter articleListAdapterNews;


    public static ArticleListFragment newInstance() {

        Bundle args = new Bundle();

        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        articleListPresenter = new ArticleListPresenterImpl();
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_article_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleListPresenter.attach(this);
        recyclerViewArticle = view.findViewById(R.id.recycle_view_article_list);
        articleListAdapterNews = new ArticleListAdapter(getContext(), this);
        recyclerViewArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewArticle.setAdapter(articleListAdapterNews);
        articleListPresenter.getArticleList();

    }

    @Override
    public void updateArticleList(List<Article> articleList) {
        articleListAdapterNews.setArticleList(articleList);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                articleListPresenter.getSearchArticleList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchView.getQuery().length() == 0) articleListPresenter.getArticleList();
                return false;
            }
        });
        searchView.setOnCloseListener(new androidx.appcompat.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                articleListPresenter.getArticleList();
                return false;
            }
        });

    }

    @Override
    public void shareArticle(Article news) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, news.getUrl());
        sendIntent.putExtra(Intent.EXTRA_TITLE, news.getTitle());
        sendIntent.setType("text/plain");
        Context context = getContext();
        if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(sendIntent, null));
        } else {
            Toast.makeText(context, getResources().getString(R.string.no_app),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showArticle(String url) {
        setHasOptionsMenu(false);
        ArticleFragment articleFragment = ArticleFragment.newInstance(url);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_main, articleFragment, articleFragment.getClass().getSimpleName())
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
