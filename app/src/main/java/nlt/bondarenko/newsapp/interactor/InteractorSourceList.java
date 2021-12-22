package nlt.bondarenko.newsapp.interactor;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public interface InteractorSourceList {

    SourceResponse getSourceListNews() throws IOException;
}
