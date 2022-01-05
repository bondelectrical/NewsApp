package nlt.bondarenko.newsapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SourceListModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final String message;

    public SourceListModelFactory(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == SourceListViewModel.class) {
            return (T) new SourceListViewModel(message);
        }
        return null;
    }
}
