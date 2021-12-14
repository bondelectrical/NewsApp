package nlt.bondarenko.newsapp.util.newsApi.models.response;

import java.util.List;

import nlt.bondarenko.newsapp.util.newsApi.models.Source;

public class SourceResponse {
    private String status;
    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
