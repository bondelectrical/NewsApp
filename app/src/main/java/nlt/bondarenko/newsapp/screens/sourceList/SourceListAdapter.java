package nlt.bondarenko.newsapp.screens.sourceList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.data.SourceListItem;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.SourceListViewHolder> {

    private final LayoutInflater inflater;
    private final OnSourceListClickListener sourceListClickListener;
    private List<SourceListItem> sourceList;

    public SourceListAdapter(Context context, OnSourceListClickListener sourceListClickListener) {
        this.sourceListClickListener = sourceListClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<SourceListItem> sources) {
        sourceList = sources;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SourceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_source_list, parent, false);
        return new SourceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceListViewHolder holder, int position) {
        SourceListItem sourceListItem = sourceList.get(position);
        holder.textViewSourceNews.setText(sourceListItem.getSourceNews());
        holder.textViewDescriptionNews.setText(sourceListItem.getSourceNewsDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceListClickListener.OnSourceListClick(sourceListItem, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public interface OnSourceListClickListener {
        void OnSourceListClick(SourceListItem sourceListItem, int position);
    }

    public class SourceListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSourceNews;
        TextView textViewDescriptionNews;

        public SourceListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescriptionNews = (TextView) itemView.findViewById(R.id.text_view_description_source_name);
            textViewSourceNews = (TextView) itemView.findViewById(R.id.text_view_source_news);
        }
    }
}