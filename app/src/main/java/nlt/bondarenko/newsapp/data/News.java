package nlt.bondarenko.newsapp.data;

public class News {

    private String urlNews;
    private String description;
    private String imgNews;
    private String textNes;

    public News(String urlNews, String description, String imgNews, String textNes) {
        this.urlNews = urlNews;
        this.description = description;
        this.imgNews = imgNews;
        this.textNes = textNes;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public String getDescription() {
        return description;
    }

    public String getImgNews() {
        return imgNews;
    }

    public String getTextNes() {
        return textNes;
    }
}
