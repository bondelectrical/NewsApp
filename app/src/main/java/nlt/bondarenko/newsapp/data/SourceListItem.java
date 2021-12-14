package nlt.bondarenko.newsapp.data;

public class SourceListItem {

    private String sourceNews;
    private String sourceNewsDescription;

    public SourceListItem(String sourceNews, String sourceNewsDescription) {
        this.sourceNews = sourceNews;
        this.sourceNewsDescription = sourceNewsDescription;
    }

    public String getSourceNews() {
        return sourceNews;
    }

    public void setSourceNews(String sourceNews) {
        this.sourceNews = sourceNews;
    }

    public String getSourceNewsDescription() {
        return sourceNewsDescription;
    }

    public void setSourceNewsDescription(String sourceNewsDescription) {
        this.sourceNewsDescription = sourceNewsDescription;
    }
}
