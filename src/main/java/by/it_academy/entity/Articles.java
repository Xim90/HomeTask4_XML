package by.it_academy.entity;

import java.util.List;
import java.util.Objects;

public class Articles {
    private int id;
    private String title;
    private String author;
    private String url;
    private List<Hotkeys> hotkeys;

    public Articles() {
    }

    public Articles(int id, String title, String author, String url, List<Hotkeys> hotkeys) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.hotkeys = hotkeys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Hotkeys> getHotkeys() {
        return hotkeys;
    }

    public void setHotkeys(List<Hotkeys> hotkeys) {
        this.hotkeys = hotkeys;
    }

    @Override
    public String toString() {
        return "\tArticle (id = "+id+"):\n" +
                "\t\ttitle: " + title + "\n" +
                "\t\tauthor: " + author + "\n" +
                "\t\turl: " + url + "\n" +
                "\t\thotkeys:\n" + hotkeys.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Articles articles)) return false;

        if (id != articles.id) return false;
        if (!Objects.equals(title, articles.title)) return false;
        if (!Objects.equals(author, articles.author)) return false;
        if (!Objects.equals(url, articles.url)) return false;
        return Objects.equals(hotkeys, articles.hotkeys);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (hotkeys != null ? hotkeys.hashCode() : 0);
        return result;
    }
}
