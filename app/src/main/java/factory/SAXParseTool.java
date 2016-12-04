package factory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import bean.Book;
import sax.SaxHandler;

public class SAXParseTool extends XMLParseFactory{
	
	private SaxHandler mHandler= new SaxHandler();
	private List<Book> mBookList;
	
	

	@Override
	public void readXML(InputStream mInputStream) {
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(mInputStream, mHandler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (mInputStream!=null) {
				try {
					mInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

	@Override
	public void writeXML(String filePath,List<Book> mBooks) {
		SAXTransformerFactory transFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler mHandler = null;
		try {
			mHandler = transFactory.newTransformerHandler();
			Transformer mTransformer = mHandler.getTransformer();
			mTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			mTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			mTransformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		    
			FileOutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			Result result = new StreamResult(writer);
			mHandler.setResult(result);
			
		    String uri = "";
	        String localName = "";
			mHandler.startDocument();
			mHandler.startElement(uri, localName, Book.BOOKS, null);
			AttributesImpl attr = new AttributesImpl();
			char[] ch =null;
			for(Book book:mBooks){
				attr.clear();
				attr.addAttribute(uri, localName, Book.ID, "String", String.valueOf(book.getId()));
				mHandler.startElement(uri, localName, Book.BOOK, attr);
				
				mHandler.startElement(uri, localName, Book.NAME, null);
				ch = book.getName().toCharArray();
				mHandler.characters(ch, 0, ch.length);
				mHandler.endElement(uri, localName, Book.NAME);
				
				mHandler.startElement(uri, localName, Book.PRICE, null);
				ch = String.valueOf(book.getId()).toCharArray();
				mHandler.characters(ch, 0, ch.length);
				mHandler.endElement(uri, localName, Book.PRICE);
				
				mHandler.endElement(uri, localName, Book.BOOK);
			}
			mHandler.endElement(uri, localName, Book.BOOKS);
			mHandler.endDocument();
		
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void setBookList(List<Book> mBookList) {
		if (this.mBookList!=null && this.mBookList.size()>0) {
			this.mBookList.clear();
		}
        this.mBookList = mBookList;
	}

	@Override
	public List<Book> getBookList() {
		// TODO Auto-generated method stub
		return mHandler.getBookList();
	}

	@Override
	public void writeXML(String filePath) {
		// TODO Auto-generated method stub
		
	}

}
