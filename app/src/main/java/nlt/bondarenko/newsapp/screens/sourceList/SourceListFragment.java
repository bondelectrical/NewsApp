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
import nlt.bondarenko.newsapp.util.newsApi.models.Source;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;
import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponseApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SourceListFragment extends Fragment implements SourceListContract.SourceListView, SourceListAdapter.OnSourceListClickListener {

    private RecyclerView recyclerView;
    private SourceResponseApi sourceApi;
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
        //sourceListPresenter.getSourceList();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sourceApi = retrofit.create(SourceResponseApi.class);
        sourceApi.getSources().enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                sourceListAdapter.setList(response.body().getSources());
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void updateSourceList(List<SourceListItem> sourceList) {
        // sourceListAdapter.setList(sourceList);
    }

    public void updateSourceListNew(List<Source> sourceList) {
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
    public void OnSourceListClick(Source sourceListItem, int position) {
        // sourceListPresenter.onClickItemSourceList(sourceListItem, position);
    }
}
