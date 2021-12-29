package nlt.bondarenko.newsapp.network.models;

import java.util.List;

public class SourceResponse {

    private String status;

    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public List<Source> getSources() {
        return sources;
    }
}
