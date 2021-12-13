package nlt.bondarenko.newsapp.screens.articleList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import nlt.bondarenko.newsapp.R;

public class ArticleListFragment extends Fragment implements ArticleListContract.ArticleListView {

    private ArticleListContract.ArticleListPresenter articleListPresenter = new ArticleListPresenterImpl();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articleListPresenter.attach(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleListPresenter.onClickItemListView();
            }
        });
    }

    @Override
    public void updateMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        articleListPresenter.detach();
        super.onDestroyView();
    }
}
