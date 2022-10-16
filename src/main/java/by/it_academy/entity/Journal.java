package by.it_academy.entity;

import java.util.List;
import java.util.Objects;

public class Journal {
    public static final String PATTERN_BRACKETS = "[\\]\\[]";
    public static final String PATTERN_SPACE = "\s";
    public static final String COMMA = ",";
    private String title;

    private Contacts contacts;

    private List<Articles> articles;

    public Journal() {
    }

    public Journal(String title, Contacts contacts, List<Articles> articles) {
        this.title = title;
        this.contacts = contacts;
        this.articles = articles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal journal)) return false;

        if (!Objects.equals(contacts, journal.contacts)) return false;
        return Objects.equals(articles, journal.articles);
    }

    @Override
    public int hashCode() {
        int result = contacts != null ? contacts.hashCode() : 0;
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Journal:\n" +
                "\tTitle:\n\t\t" + title + "\n" +
                contacts + "\n" +
                articles.toString()
                        .replaceAll(COMMA, "")
                        .replaceAll(PATTERN_BRACKETS, PATTERN_SPACE)
                + "\n";
    }
}
