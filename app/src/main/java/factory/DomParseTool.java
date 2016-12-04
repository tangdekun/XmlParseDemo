package factory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import bean.Book;

/**
 * Created by John on 2016/12/4.
 */

public class DomParseTool extends XMLParseFactory {

    List<Book> bookList;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;


    public DomParseTool() {
        bookList = new ArrayList<Book>();
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void readXML(InputStream mInputStream) {
        try {
            Document document = builder.parse(mInputStream);
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getElementsByTagName(Book.BOOK);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element ele = (Element) nodeList.item(i);
                Book book = new Book();
                book.setId(Integer.valueOf(ele.getAttribute(Book.ID)));
                NodeList childNodeList = ele.getChildNodes();
                for (int j = 0; j < childNodeList.getLength(); j++) {
                    Node childNode = childNodeList.item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        if (Book.NAME.equals(childNode.getNodeName())) {
                            book.setName(childNode.getFirstChild().getNodeValue());
                        } else if (Book.PRICE.equals(childNode.getNodeName())) {
                            book.setPrice(Float.valueOf(childNode.getFirstChild().getNodeValue()));
                        }
                    }
                }
                bookList.add(book);
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public void writeXML(String filePath) {

    }

    @Override
    public void writeXML(String filePath,List<Book> mBooks) {
        String str = filePath;
        Document doc = builder.newDocument();
        Element rootele = doc.createElement(Book.BOOKS);
        doc.appendChild(rootele);
        for (int i = 0; i <  mBooks.size(); i++) {
            Book book =  mBooks.get(i);
            Element bookEle = doc.createElement(Book.BOOK);
            bookEle.setAttribute(Book.ID, String.valueOf(book.getId()));
            Element nameEle = doc.createElement(Book.NAME);
            nameEle.appendChild(doc.createTextNode(book.getName()));
            bookEle.appendChild(nameEle);
            Element priceEle = doc.createElement(Book.PRICE);
            priceEle.appendChild(doc.createTextNode(String.valueOf(book.getPrice())));
            bookEle.appendChild(priceEle);
            rootele.appendChild(bookEle);
        }

        TransformerFactory mTransformerf = TransformerFactory.newInstance();
        try {
            Transformer mTransformer = mTransformerf.newTransformer();
            DOMSource resource = new DOMSource(doc);
            mTransformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            mTransformer.setOutputProperty(OutputKeys.INDENT, "no");
            try {
                FileOutputStream fileOs = new FileOutputStream(filePath);
                PrintWriter pw = new PrintWriter(fileOs);
                StreamResult sr = new StreamResult(pw);
                mTransformer.transform(resource,sr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setBookList(List<Book> mBookList) {
        this.bookList = mBookList;

    }

    @Override
    public List<Book> getBookList() {
        return bookList;
    }
}
