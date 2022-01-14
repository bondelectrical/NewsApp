package nlt.bondarenko.newsapp.screens.sourceList;

import android.os.Handler;
import android.os.Looper;

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


    public SourceListViewModel() {
        sourceList = new MutableLiveData<>();
        livedata = sourceList;
        loadSourceList();
    }

    private void loadSourceList() {
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
