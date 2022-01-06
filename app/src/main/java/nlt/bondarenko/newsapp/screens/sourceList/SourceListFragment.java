package nlt.bondarenko.newsapp.screens.sourceList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.network.models.Source;

public class SourceListFragment extends Fragment implements SourceListContract.SourceListView, SourceListAdapter.OnSourceListClickListener {

    private RecyclerView recyclerView;
    private SourceListAdapter sourceListAdapter;
    private SourceListContract.SourceListPresenter sourceListPresenter;

    public static SourceListFragment newInstance() {

        Bundle args = new Bundle();

        SourceListFragment fragment = new SourceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sourceListPresenter = new SourceListPresenterImpl();
        return inflater.inflate(R.layout.fragment_source_list, null);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sourceListPresenter.attach(this);
        recyclerView = view.findViewById(R.id.recycler_view_source_news);
        sourceListAdapter = new SourceListAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sourceListAdapter);
        sourceListPresenter.getSourceList();

    }

    @Override
    public void updateSourceList(List<Source> sourceList) {
        sourceListAdapter.setList(sourceList);
    }


    @Override
    public void showSourceUrl(String sourceUrl) {
        Uri address = Uri.parse(sourceUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, address);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("NewsApp", "Can't handle intent!");
        }
    }

    @Override
    public void onDestroy() {
        sourceListPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void OnSourceListClick(Source sourceListItem, int position) {
        sourceListPresenter.onClickItemSourceList(sourceListItem, position);
    }
}
