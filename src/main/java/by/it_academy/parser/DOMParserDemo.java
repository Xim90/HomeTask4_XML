package by.it_academy.parser;

import by.it_academy.constants.ParserConstants;
import by.it_academy.constants.TagsXML;
import by.it_academy.entity.Articles;
import by.it_academy.entity.Contacts;
import by.it_academy.entity.Hotkeys;
import by.it_academy.entity.Journal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DOMParserDemo {
    private static void setJournalWithXMLNodeValues(Journal journal, NodeList nodeList) {
        DOMParser.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                setJournalWithXMLChildNodeValues(journal, node);
            }
        });
    }

    private static void setJournalWithXMLChildNodeValues(Journal journal, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        NodeList childNodes = node.getChildNodes();
        switch (node.getNodeName()) {
            case TagsXML.TITLE -> journal.setTitle(content);
            case TagsXML.CONTACTS -> {
                Contacts contact = new Contacts();
                journal.setContacts(setContactWithXMLNodeValues(contact, childNodes));
            }
            case TagsXML.ARTICLES -> {
                List<Articles> articlesList = new ArrayList<>();
                journal.setArticles(setArticlesWithNodeValues(articlesList, childNodes));
            }
        }
    }

    private static Contacts setContactWithXMLNodeValues(Contacts contact, NodeList nodeList) {
        DOMParser.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                setContactWithXMLChildNodeValues(contact, node);
            }
        });
        return contact;
    }

    private static void setContactWithXMLChildNodeValues(Contacts contact, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case TagsXML.ADDRESS -> contact.setAddress(content);
            case TagsXML.TEL -> contact.setTel(content);
            case TagsXML.EMAIL -> contact.setEmail(content);
            case TagsXML.URL -> contact.setUrl(content);
        }
    }

    private static List<Articles> setArticlesWithNodeValues(List<Articles> articlesList, NodeList nodeList) {
        DOMParser.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                Articles article = new Articles();
                article.setId(Integer.parseInt(node.getAttributes().getNamedItem(TagsXML.ID).getNodeValue()));
                NodeList childNodes = node.getChildNodes();
                DOMParser.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setArticlesWithChildNodeValues(article, childNode);
                    }
                });
                articlesList.add(article);
            }
        });
        return articlesList;
    }

    private static void setArticlesWithChildNodeValues(Articles article, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case TagsXML.TITLE -> article.setTitle(content);
            case TagsXML.AUTHOR -> article.setAuthor(content);
            case TagsXML.URL -> article.setUrl(content);
            case TagsXML.HOTKEYS -> {
                List<Hotkeys> hotkeysList = new ArrayList<>();
                article.setHotkeys(setHotkeysWithXMLNodeValues(hotkeysList, node.getChildNodes()));
            }
        }
    }

    private static List<Hotkeys> setHotkeysWithXMLNodeValues(List<Hotkeys> hotkeysList, NodeList nodeList) {
        DOMParser.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                Hotkeys hotkey = new Hotkeys();
                setHotkeysWithXMLChildNodeValues(hotkey, node);
                hotkeysList.add(hotkey);
            }
        });
        return hotkeysList;
    }

    private static void setHotkeysWithXMLChildNodeValues(Hotkeys hotkey, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        if (node.getNodeName().equals(TagsXML.HOTKEY)) {
            hotkey.setHotkey(content);
        }
    }

    public static void main(String[] args) {
        Journal journal = new Journal();
        Document document = DOMParser.parseXMLDocument(ParserConstants.JOURNAL_XML);
        NodeList nodeList = DOMParser.getNodeList(document);
        setJournalWithXMLNodeValues(journal, nodeList);
        System.out.println(journal);

    }
}
