package nlt.bondarenko.newsapp.screens.general;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListViewHolder> {

    private final LayoutInflater inflater;
    private List<Article> articleList;

    public ArticleListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_article_list, parent, false);
        return new ArticleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListViewHolder holder, int position) {
        Article articleItem = articleList.get(position);
        ImageView imageViewNews = holder.imageViewNews;
        Picasso.get().load(articleItem.getUrlToImage()).into(imageViewNews);
        holder.textViewTitleNews.setText(articleItem.getTitle());
        holder.textViewDescriptionNews.setText(articleItem.getDescription());
//        holder.textViewNameSource.setText(articleItem.getArticleSource().getName());
        holder.imageViewButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO share URL news

            }
        });

        holder.imageViewButtonMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add/delete Marks

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO open is WebView
                articleItem.getUrl();
            }
        });


    }

    @Override
    public int getItemCount() {
        if (articleList != null) {
            return articleList.size();
        }
        return 0;
    }

}
