package nlt.bondarenko.newsapp.screens.general;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.data.News;
import nlt.bondarenko.newsapp.util.newsApi.models.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListViewHolder> {

    private final LayoutInflater inflater;
    private List<Article> articleList;
    private Context context;
    private OnClickListenetArticleList onClickListenetArticleList;

    public ArticleListAdapter(Context context) {

        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.item_article_list, parent, false);
        return new ArticleListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ArticleListViewHolder holder, int position) {
        Article articleItem = articleList.get(position);
        ImageView imageViewNews = holder.imageViewNews;
        Glide.with(imageViewNews.getContext()).load(articleItem.getUrlToImage()).into(imageViewNews);
        holder.textViewTitleNews.setText(articleItem.getTitle());
        holder.textViewDescriptionNews.setText(articleItem.getDescription());
        holder.textViewNameSource.setText(articleItem.getName());
        holder.imageViewButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getId();
                Toast.makeText(v.getContext(), " id item" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                //TODO share URL news

            }
        });

        holder.imageViewButtonMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = (News) articleItem;
                onClickListenetArticleList.onClickItemArticle(news);
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
