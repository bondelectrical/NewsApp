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

import nlt.bondarenko.newsapp.R;

public class ArticleFragment extends Fragment {

    private WebView webView;

    private String url;

    public static String URL_KEY = "Url";

    public static ArticleFragment newInstance() {

        ArticleFragment fragment = new ArticleFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            webView = view.findViewById(R.id.web_view_news);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(getArguments().getString(URL_KEY));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}


