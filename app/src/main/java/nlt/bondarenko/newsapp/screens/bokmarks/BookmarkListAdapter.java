package nlt.bondarenko.newsapp.screens.bokmarks;

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
import nlt.bondarenko.newsapp.roomdatabase.entity.ArticleBookMarksEntity;

public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder> {

    private final LayoutInflater inflater;
    private List<ArticleBookMarksEntity> articleList;
    private OnClickListenerMarkList onClickListenerArticleList;


    public BookmarkListAdapter(Context context, OnClickListenerMarkList onClickListenerMarkList) {
        this.inflater = LayoutInflater.from(context);
        this.onClickListenerArticleList = onClickListenerMarkList;
    }


    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        ArticleBookMarksEntity articleItem = articleList.get(position);
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
                onClickListenerArticleList.onClickItemArticle(articleItem);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerArticleList.onClickListenerWebView(articleItem.getUrl());
            }
        });


    }

    public void setArticleList(List<ArticleBookMarksEntity> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_article_list, parent, false);
        ItemArticleListBinding binding = ItemArticleListBinding.bind(view);
        return new BookmarkViewHolder(binding);
    }


    @Override
    public int getItemCount() {
        if (articleList != null) {
            return articleList.size();
        }
        return 0;
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        ItemArticleListBinding binding;

        public BookmarkViewHolder(ItemArticleListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public interface OnClickListenerMarkList {

        void onClickItemArticle(ArticleBookMarksEntity news);

        void onClickListenerArticleShare(ArticleBookMarksEntity news);

        void onClickListenerWebView(String url);

    }
}
