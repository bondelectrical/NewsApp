package nlt.bondarenko.newsapp.screens.bokmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.roomdatabase.entity.NewsBookMarksEntity;

public class BookmarkFragment extends Fragment implements BookmarkContract.BookmarkView, BookmarkListAdapter.OnClickListenerMarkList {


    private BookmarkContract.BookmarkPresenter bookmarkPresenter;
    private RecyclerView recyclerViewMark;
    private BookmarkListAdapter markListAdapterNews;

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
    public void onClickItemArticle(NewsBookMarksEntity news) {
        bookmarkPresenter.deleteBookmarkItem(news);
    }

    @Override
    public void onClickListenerArticleShare(NewsBookMarksEntity news) {
        bookmarkPresenter.shareBookmarkArticle(news);
    }
}
