package nlt.bondarenko.newsapp.screens.bokmarks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;
import nlt.bondarenko.newsapp.screens.MainActivity;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;

public class BookmarkFragment extends Fragment implements BookmarkContract.BookmarkView, BookmarkListAdapter.OnClickListenerMarkList {


    private BookmarkContract.BookmarkPresenter bookmarkPresenter;
    private RecyclerView recyclerViewMark;
    private BookmarkListAdapter markListAdapterNews;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottom;

    public BookmarkFragment(FragmentManager fragmentManager, BottomNavigationView bottom) {
        this.fragmentManager = fragmentManager;
        this.bottom = bottom;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookmarkPresenter = new BookmarkPresenterImpl(getContext());
        return inflater.inflate(R.layout.fragment_bookmark, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookmarkPresenter.attach(this);
        recyclerViewMark = view.findViewById(R.id.recycle_view_mark_list);
        markListAdapterNews = new BookmarkListAdapter(getContext(), this);
        recyclerViewMark.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMark.setAdapter(markListAdapterNews);
        bookmarkPresenter.getBookmarkList();
    }


    @Override
    public void onDestroyView() {
        bookmarkPresenter.detach();
        super.onDestroyView();
    }

    @Override
    public void updateBookMarksList(List<NewsBookMarksEntity> newsBookMarksEntities) {
        markListAdapterNews.setArticleList(newsBookMarksEntities);
    }

    @Override
    public void showArticleNews(String url) {
        ArticleFragment articleFragment = new ArticleFragment(url, bottom);
        fragmentManager.beginTransaction().add(R.id.frame_layout_main, articleFragment, MainActivity.TAG_ARTICLE_FRAGMENT)
                .addToBackStack(null).commit();

    }

    @Override
    public void shareNewsBookMarks(NewsBookMarksEntity news) {
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
    public void onClickItemArticle(NewsBookMarksEntity news) {
        bookmarkPresenter.deleteBookmarkItem(news);
    }

    @Override
    public void onClickListenerArticleShare(NewsBookMarksEntity news) {
        bookmarkPresenter.shareBookmarkArticle(news);
    }

    @Override
    public void onClickListenerWebView(String url) {
        bookmarkPresenter.showArticleWebView(url);
    }
}
