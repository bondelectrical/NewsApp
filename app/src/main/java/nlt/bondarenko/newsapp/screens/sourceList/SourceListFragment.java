package nlt.bondarenko.newsapp.screens.sourceList;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.network.models.Source;
import nlt.bondarenko.newsapp.viewmodel.SourceListViewModel;

public class SourceListFragment extends Fragment implements SourceListAdapter.OnSourceListClickListener {

    private RecyclerView recyclerView;
    private SourceListAdapter sourceListAdapter;
    private SourceListViewModel model;

    public static SourceListFragment newInstance() {

        Bundle args = new Bundle();

        SourceListFragment fragment = new SourceListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_source_list, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_source_news);
        sourceListAdapter = new SourceListAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sourceListAdapter);

//        model = new ViewModelProvider(this).get(SourceListViewModel.class);
        model = new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()).create(SourceListViewModel.class);
        model.getSourceList().observe(this, new Observer<List<Source>>() {
            @Override
            public void onChanged(List<Source> sources) {
                sourceListAdapter.setList(sources);
            }
        });


    }


    @Override
    public void OnSourceListClick(Source sourceListItem, int position) {
        model.select(sourceListItem);
    }
}
