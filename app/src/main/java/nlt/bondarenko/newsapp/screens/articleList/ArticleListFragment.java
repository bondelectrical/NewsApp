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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.network.models.Article;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;
import nlt.bondarenko.newsapp.viewmodel.ArticleListViewModel;
import nlt.bondarenko.newsapp.viewmodel.BookmarkListViewModel;

public class ArticleListFragment extends Fragment implements ArticleListAdapter.OnClickListenerArticleList {

    private RecyclerView recyclerViewArticle;
    private ArticleListAdapter articleListAdapterNews;
    private ArticleListViewModel model;
    private Observer<List<Article>> observer;


    public static ArticleListFragment newInstance() {

        Bundle args = new Bundle();

        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_article_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewArticle = view.findViewById(R.id.recycle_view_article_list);
        articleListAdapterNews = new ArticleListAdapter(getContext(), this);
        recyclerViewArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewArticle.setAdapter(articleListAdapterNews);

        model = new ViewModelProvider(this).get(ArticleListViewModel.class);

        observer = new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                articleListAdapterNews.setArticleList(articles);
            }
        };
        model.livedata.observe(getViewLifecycleOwner(), observer);

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
                model.getSearchArticleList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchView.getQuery().length() == 0) model.loadArticleList();
                return false;
            }
        });
        searchView.setOnCloseListener(new androidx.appcompat.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                model.loadArticleList();
                return false;
            }
        });

    }


    @Override
    public void onClickItemArticle(Article news) {
        BookmarkListViewModel model = new ViewModelProvider(getActivity()).get(BookmarkListViewModel.class);
        model.addArticleBookmark(news);
    }

    @Override
    public void onClickListenerArticleShare(Article news) {
        shareArticle(news);
    }

    @Override
    public void onClickListenerWebView(String url) {
        showArticle(url);
    }


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

    public void showArticle(String url) {
        setHasOptionsMenu(false);
        Bundle bundle = new Bundle();
        bundle.putString(ArticleFragment.URL_KEY, url);
        Navigation.findNavController(recyclerViewArticle).navigate(R.id.article_fragment, bundle);
    }
}
