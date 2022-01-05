package nlt.bondarenko.newsapp.viewmodel;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nlt.bondarenko.newsapp.interactor.SourceListInteractor;
import nlt.bondarenko.newsapp.interactor.SourceListInteractorImpl;
import nlt.bondarenko.newsapp.network.models.Source;

public class SourceListViewModel extends ViewModel {

    private MutableLiveData<List<Source>> sourceList;
    public LiveData<List<Source>> livedata;
    private SourceListInteractor interactor = new SourceListInteractorImpl();
    private String message;


    public SourceListViewModel(String message) {
        this.message = message;
        sourceList = new MutableLiveData<>();
        livedata = sourceList;
        loadSourceList();
    }

    private void loadSourceList() {
        Log.d("MyTag", message);
        Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(() -> {
            List<Source> sources = interactor.getSourceList().getSources();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    sourceList.setValue(sources);
                }
            });
        });
        thread.start();

    }

}
