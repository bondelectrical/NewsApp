package nlt.bondarenko.newsapp.data;

public class SourceListItem implements SourceList {

    private String sourceNews;
    private String sourceNewsDescription;
    private String sourceUrl;

    public SourceListItem(String sourceNews, String sourceNewsDescription) {
        this.sourceNews = sourceNews;
        this.sourceNewsDescription = sourceNewsDescription;
    }

    @Override
    public String getSourceNews() {
        return sourceNews;
    }

    @Override
    public String getSourceNewsDescription() {
        return sourceNewsDescription;
    }

    @Override
    public String getUrl() {
        return sourceUrl;
    }


}
