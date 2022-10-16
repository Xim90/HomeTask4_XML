package by.it_academy.parser;

import by.it_academy.constants.ExceptionMessage;
import by.it_academy.constants.ParserConstants;
import by.it_academy.constants.TagsXML;
import by.it_academy.entity.Articles;
import by.it_academy.entity.Contacts;
import by.it_academy.entity.Hotkeys;
import by.it_academy.entity.Journal;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

import java.util.List;

public class StAXParser {

    public static void main(String[] args) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
        Journal journal = null;
        try {
            reader = factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream(ParserConstants.JOURNAL_XML));
            journal = createJournalByStAXParser(reader);
        } catch (XMLStreamException e) {
            e.printStackTrace();
            System.out.println(ExceptionMessage.XML_STREAM_EXCEPTION_LOG);
        }
        System.out.println(journal);
    }

    private static Journal createJournalByStAXParser(XMLStreamReader reader) throws XMLStreamException {
        String tagContent = null;
        Journal journal = null;
        Contacts contacts = null;
        List<Articles> articlesList = null;
        List<Hotkeys> hotkeysList = null;
        Articles article = null;
        int flag = 0;
        try {
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (reader.getLocalName()) {
                            case TagsXML.JOURNAL -> {
                                journal = new Journal();
                                flag = ParserConstants.JOURNAL_TAG_FLAG;
                            }
                            case TagsXML.CONTACTS -> {
                                contacts = new Contacts();
                                flag = ParserConstants.CONTACTS_TAG_FLAG;
                            }
                            case TagsXML.ARTICLES -> {
                                articlesList = new ArrayList<>();
                                flag = ParserConstants.ARTICLES_TAG_FLAG;
                            }
                            case TagsXML.ARTICLE -> {
                                article = new Articles();
                                article.setId(Integer.parseInt(reader.getAttributeValue(0)));
                            }
                            case TagsXML.HOTKEYS -> {
                                hotkeysList = new ArrayList<>();
                                flag = ParserConstants.ARTICLES_TAG_FLAG;
                            }
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case TagsXML.ADDRESS -> contacts.setAddress(tagContent);
                            case TagsXML.TEL -> contacts.setTel(tagContent);
                            case TagsXML.EMAIL -> contacts.setEmail(tagContent);
                            case TagsXML.URL -> {
                                if (flag == ParserConstants.CONTACTS_TAG_FLAG) {
                                    contacts.setUrl(tagContent);
                                }
                                if (flag == ParserConstants.ARTICLES_TAG_FLAG) {
                                    article.setUrl(tagContent);
                                }
                            }
                            case TagsXML.CONTACTS -> journal.setContacts(contacts);
                            case TagsXML.ARTICLES -> journal.setArticles(articlesList);
                            case TagsXML.ARTICLE -> articlesList.add(article);
                            case TagsXML.TITLE -> {
                                if (flag == ParserConstants.JOURNAL_TAG_FLAG) {
                                    journal.setTitle(tagContent);
                                }
                                if (flag == ParserConstants.ARTICLES_TAG_FLAG) {
                                    article.setTitle(tagContent);
                                }
                            }
                            case TagsXML.AUTHOR -> article.setAuthor(tagContent);
                            case TagsXML.HOTKEYS -> article.setHotkeys(hotkeysList);
                            case TagsXML.HOTKEY -> {
                                Hotkeys hotkey = new Hotkeys(tagContent);
                                hotkeysList.add(hotkey);
                            }
                        }
                        break;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println(ExceptionMessage.NULL_POINTER_EXCEPTION_LOG);
        }
        return journal;
    }
}
