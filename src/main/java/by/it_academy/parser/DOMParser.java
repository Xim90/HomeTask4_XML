package by.it_academy.parser;

import by.it_academy.constants.ExceptionMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DOMParser {

    public static DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            return factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println(ExceptionMessage.PARSER_CONFIGURATION_LOG);
            return null;
        }
    }

    public static Document parseXMLDocument(String path) {
        DocumentBuilder documentBuilder = createDocumentBuilder();
        Document document = null;
        if (documentBuilder != null) {
            try {
                document = documentBuilder.parse(ClassLoader.getSystemResourceAsStream(path));
            } catch (SAXException e) {
                e.printStackTrace();
                System.out.println(ExceptionMessage.SAX_EXCEPTION_LOG);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(ExceptionMessage.IO_EXCEPTION_LOG);
            }
        }
        return document;
    }

    public static NodeList getNodeList(Document document) {
        return document.getDocumentElement().getChildNodes();
    }

    public static Stream<Node> getNodeListStream(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength()).
                mapToObj(nodeList::item);
    }

}
