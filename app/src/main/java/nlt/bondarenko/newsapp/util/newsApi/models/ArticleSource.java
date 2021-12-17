package nlt.bondarenko.newsapp.util.newsApi.models;

import com.google.gson.annotations.SerializedName;

public class ArticleSource {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
