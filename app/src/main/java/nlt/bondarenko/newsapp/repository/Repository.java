package nlt.bondarenko.newsapp.repository;

import java.io.IOException;

import nlt.bondarenko.newsapp.util.newsApi.models.response.SourceResponse;

public interface Repository {

    SourceResponse getSourceList() throws IOException;
}
