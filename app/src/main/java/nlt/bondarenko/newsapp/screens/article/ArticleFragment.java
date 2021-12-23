package nlt.bondarenko.newsapp.screens.article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import nlt.bondarenko.newsapp.R;

public class ArticleFragment extends Fragment {

    private WebView webView;
    private String url;
    private BottomNavigationView bottomNavigationViewMain;

    public ArticleFragment(String url, BottomNavigationView bottomNavigationViewMain) {
        this.url = url;
        this.bottomNavigationViewMain = bottomNavigationViewMain;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.web_view_news);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        bottomNavigationViewMain.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bottomNavigationViewMain.setVisibility(View.VISIBLE);
    }
}
