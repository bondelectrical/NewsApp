package nlt.bondarenko.newsapp.interector;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public interface Interactor {

    SourceResponse getSourceListNews() throws IOException;
}
