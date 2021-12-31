package nlt.bondarenko.newsapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.SourceListInteractor;
import nlt.bondarenko.newsapp.interactor.SourceListInteractorImpl;
import nlt.bondarenko.newsapp.network.models.Source;

public class SourceListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Source>> sourceList;
    private SourceListInteractor interactor = new SourceListInteractorImpl();

    public SourceListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Source>> getSourceList() {
        if (sourceList == null) {
            sourceList = new MutableLiveData<>();
            loadSourceList();
        }

        return sourceList;
    }

    private void loadSourceList() {
        Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(() -> {
            List<Source> sources = interactor.getSourceList().getSources();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    sourceList.postValue(sources);
                }
            });
        });
        thread.start();
    }

    public void select(Source source) {
        String addressString = source.getUrl();
        Uri address = Uri.parse(addressString);
        Intent intent = new Intent(Intent.ACTION_VIEW, address);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplication();
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Log.d("NewsApp", "Can't handle intent!");
        }
    }
}
