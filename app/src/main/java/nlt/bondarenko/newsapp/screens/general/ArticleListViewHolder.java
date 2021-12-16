package nlt.bondarenko.newsapp.screens.general;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nlt.bondarenko.newsapp.R;

public class ArticleListViewHolder extends RecyclerView.ViewHolder {

    ImageView imageViewNews;
    TextView textViewTitleNews;
    TextView textViewDescriptionNews;
    TextView textViewNameSource;
    ImageView imageViewButtonShare;
    ImageView imageViewButtonMark;


    public ArticleListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewNews = itemView.findViewById(R.id.image_view_news);
        textViewTitleNews = itemView.findViewById(R.id.text_view_news);
        textViewDescriptionNews = itemView.findViewById(R.id.text_view_description_news);
        textViewNameSource = itemView.findViewById(R.id.text_view_source);
        imageViewButtonShare = itemView.findViewById(R.id.image_button_share);
        imageViewButtonMark = itemView.findViewById(R.id.image_button_bookmark);

    }
}
