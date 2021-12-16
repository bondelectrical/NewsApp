package nlt.bondarenko.newsapp.screens.articleList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.screens.general.ArticleListAdapter;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListFragment extends Fragment implements ArticleListContract.ArticleListView {

    private ArticleListContract.ArticleListPresenter articleListPresenter;
    private RecyclerView recyclerViewArticle;
    private SearchView searchViewNews;
    private ArticleListAdapter articleListAdapterNews;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        articleListPresenter = new ArticleListPresenterImpl();
        return inflater.inflate(R.layout.fragment_article_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleListPresenter.attach(this);
        recyclerViewArticle = view.findViewById(R.id.recycle_view_article_list);
        searchViewNews = view.findViewById(R.id.search_view_news);
        articleListAdapterNews = new ArticleListAdapter(getContext());
        recyclerViewArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewArticle.setAdapter(articleListAdapterNews);
        articleListPresenter.getArticleList();
    }

    @Override
    public void updateArticleList(List<Article> articleList) {
        articleListAdapterNews.setArticleList(articleList);

    }

    @Override
    public void onDestroyView() {
        articleListPresenter.detach();
        super.onDestroyView();
    }
}
