package nlt.bondarenko.newsapp.network.models;

import com.google.gson.annotations.SerializedName;

import nlt.bondarenko.newsapp.data.SourceList;

public class Source implements SourceList {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("category")
    private String category;

    @SerializedName("language")
    private String language;

    @SerializedName("country")
    private String country;

    public String getId() {
        return id;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String getSourceNews() {
        return name;
    }

    @Override
    public String getSourceNewsDescription() {
        return description;
    }
}
