package nlt.bondarenko.newsapp.interector;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public interface InteractorSourceList {

    SourceResponse getSourceListNews() throws IOException;
}
