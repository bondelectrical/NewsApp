package nlt.bondarenko.newsapp.repository;

import javax.inject.Singleton;

import dagger.Component;
import nlt.bondarenko.newsapp.interactor.ArticleListInteractorImpl;
import nlt.bondarenko.newsapp.interactor.BookmarkInreractorImpl;
import nlt.bondarenko.newsapp.interactor.SourceListInteractorImpl;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface RepositoryComponent {
    void inject(ArticleListInteractorImpl articleListInteractor);

    void inject(BookmarkInreractorImpl bookmarkInreractor);

    void inject(SourceListInteractorImpl sourceListInteractor);
}
