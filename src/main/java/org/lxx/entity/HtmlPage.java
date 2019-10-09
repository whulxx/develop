package main.java.org.lxx.entity;

public class HtmlPage {
    //网页标题
    private String title;
    //网页网址
    private String url;
    //网页文本内容
    private String htmlContent;

    public String getTitle() {
        return title;
    }

    public HtmlPage(String title, String url, String htmlContent)
    {
        this.title = title;
        this.url = url;
        this.htmlContent = htmlContent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
