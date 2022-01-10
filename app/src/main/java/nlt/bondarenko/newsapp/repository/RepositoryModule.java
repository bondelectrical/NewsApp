package nlt.bondarenko.newsapp.repository;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    Repository provideRepository() {
        return new RepositoryImpl();
    }
}
