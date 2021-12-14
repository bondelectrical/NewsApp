package nlt.bondarenko.newsapp.screens.sourceList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.data.SourceListItem;

public class SourceListFragment extends Fragment implements SourceListContract.SourceListView, SourceListAdapter.OnSourceListClickListener {

    private RecyclerView recyclerView;
    private SourceListAdapter sourceListAdapter;
    private SourceListContract.SourceListPresenter sourceListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sourceListPresenter = new SourceListPresenterImpl();
        return inflater.inflate(R.layout.fragment_source_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sourceListPresenter.attach(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_source_news);
        sourceListAdapter = new SourceListAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sourceListAdapter);
        sourceListPresenter.getSourceList();

    }

    @Override
    public void updateSourceList(List<SourceListItem> sourceList) {
        sourceListAdapter.setList(sourceList);
    }

    @Override
    public void showToast(String sourceName) {
        Toast.makeText(getContext(), sourceName + " was clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        sourceListPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void OnSourceListClick(SourceListItem sourceListItem, int position) {
        sourceListPresenter.onClickItemSourceList(sourceListItem, position);
    }
}
