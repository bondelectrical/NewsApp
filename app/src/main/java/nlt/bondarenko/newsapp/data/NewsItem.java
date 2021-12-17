package nlt.bondarenko.newsapp.data;

public class NewsItem implements News {

    private String nameNews;
    private String titleNews;
    private String descriptionNews;
    private String urlNews;
    private String urlToImageNews;

    public NewsItem(String nameNews, String titleNews, String descriptionNews, String urlNews, String urlToImageNews) {
        this.nameNews = nameNews;
        this.titleNews = titleNews;
        this.descriptionNews = descriptionNews;
        this.urlNews = urlNews;
        this.urlToImageNews = urlToImageNews;
    }

    @Override
    public String getName() {
        return nameNews;
    }

    @Override
    public String getTitle() {
        return titleNews;
    }

    @Override
    public String getDescription() {
        return descriptionNews;
    }

    @Override
    public String getUrl() {
        return urlNews;
    }

    @Override
    public String getUrlToImage() {
        return urlToImageNews;
    }
}
