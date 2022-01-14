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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;

public class BookmarkFragment extends Fragment implements BookmarkListAdapter.OnClickListenerMarkList {

    private RecyclerView recyclerViewMark;
    private BookmarkListAdapter bookmarkListAdapter;
    private BookmarkListViewModel model;

    public static BookmarkFragment newInstance() {

        Bundle args = new Bundle();

        BookmarkFragment fragment = new BookmarkFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewMark = view.findViewById(R.id.recycle_view_mark_list);
        bookmarkListAdapter = new BookmarkListAdapter(getContext(), this);
        recyclerViewMark.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMark.setAdapter(bookmarkListAdapter);

        model = new ViewModelProvider(getActivity()).get(BookmarkListViewModel.class);

        model.livedata.observe(getViewLifecycleOwner(), new Observer<List<ArticleBookMarksEntity>>() {
            @Override
            public void onChanged(List<ArticleBookMarksEntity> articleBookMarksEntities) {
                bookmarkListAdapter.setArticleList(articleBookMarksEntities);
            }
        });

    }

    @Override
    public void onClickItemArticle(ArticleBookMarksEntity news) {
        model.deleteArticleBookmark(news);
    }

    @Override
    public void onClickListenerArticleShare(ArticleBookMarksEntity news) {
        shareArticleBookMarks(news);
    }

    @Override
    public void onClickListenerWebView(String url) {
        showArticle(url);
    }


    public void showArticle(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(ArticleFragment.URL_KEY, url);
        Navigation.findNavController(recyclerViewMark).navigate(R.id.article_fragment, bundle);
    }

    public void shareArticleBookMarks(ArticleBookMarksEntity news) {
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
}
