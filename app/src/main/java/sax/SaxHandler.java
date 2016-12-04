package sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import bean.Book;

/**
 * @author John
 * Sax�����������ڸ��������
 */
public class SaxHandler extends DefaultHandler{
	
	private List<Book> mBookList;//Book�б�
	private Book book;//bookʵ��
	private String targetElemant;//��ǰ�Ľڵ�����
	
	public List<Book> getBookList(){
		return mBookList;
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	//��ʼ�����ĵ� ��ʼ�� mBookList
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		mBookList = new ArrayList<Book>();
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	    if (qName.equals(Book.BOOK)) {
			book = new Book();
			book.setId(Integer.valueOf(attributes.getValue(Book.ID)));
		}
	    targetElemant = localName;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if (qName.equals(Book.BOOK)) {
			mBookList.add(book);
			
		}
		targetElemant = null;
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if (Book.NAME.equals(targetElemant)) {
			book.setName(new String(ch,start,length));
		}else if(Book.PRICE.equals(targetElemant)){
			book.setPrice(Float.valueOf(new String(ch,start,length)));
		}
	}

}
