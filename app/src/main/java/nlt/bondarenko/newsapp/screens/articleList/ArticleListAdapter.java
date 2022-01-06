package nlt.bondarenko.newsapp.screens.articleList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.databinding.ItemArticleListBinding;
import nlt.bondarenko.newsapp.network.models.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder> {

    private final LayoutInflater inflater;
    private List<Article> articleList;
    private OnClickListenerArticleList onClickListenerArticleList;

    public ArticleListAdapter(Context context, OnClickListenerArticleList onClickListenerArticleList) {
        this.inflater = LayoutInflater.from(context);
        this.onClickListenerArticleList = onClickListenerArticleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (articleList != null) {
            return articleList.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_article_list, parent, false);
        ItemArticleListBinding binding = ItemArticleListBinding.bind(view);

        return new ArticleListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListViewHolder holder, int position) {

        Article articleItem = articleList.get(position);
        ImageView imageViewNews = holder.binding.imageViewNews;
        Glide.with(imageViewNews.getContext()).load(articleItem.getUrlToImage()).into(imageViewNews);
        holder.binding.textViewNews.setText(articleItem.getTitle());
        holder.binding.textViewDescriptionNews.setText(articleItem.getDescription());
        holder.binding.textViewSource.setText(articleItem.getName());

        holder.binding.imageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerArticleList.onClickListenerArticleShare(articleItem);

            }
        });

        holder.binding.imageButtonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articleItem != null) {
                    onClickListenerArticleList.onClickItemArticle(articleItem);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerArticleList.onClickListenerWebView(articleItem.getUrl());
            }
        });
    }

    public class ArticleListViewHolder extends RecyclerView.ViewHolder {
        ItemArticleListBinding binding;

        public ArticleListViewHolder(ItemArticleListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickListenerArticleList {

        void onClickItemArticle(Article news);

        void onClickListenerArticleShare(Article news);

        void onClickListenerWebView(String url);

    }
}
