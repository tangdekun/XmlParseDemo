package factory;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import bean.Book;


public class PULLParseTool extends XMLParseFactory {
	private List<Book> mBooksList;
	private Book book;

	@Override
	public void readXML(InputStream mInputStream) {
     XmlPullParser mXmlPullParser = Xml.newPullParser();
     try {
		mXmlPullParser.setInput(mInputStream, "UTF-8");
		int parserType = mXmlPullParser.getEventType();
		while (parserType!=XmlPullParser.END_DOCUMENT) {
			String tagName = mXmlPullParser.getName();
			switch (parserType) {
			case XmlPullParser.START_DOCUMENT:
				mBooksList = new ArrayList<Book>();
				break;
			case XmlPullParser.START_TAG:
				if (Book.BOOK.equals(tagName)) {
					book = new Book();
					book.setId(Integer.valueOf(mXmlPullParser.getAttributeValue("", Book.ID)));
				}
				else if(Book.NAME.equals(tagName)){
					book.setName(mXmlPullParser.nextText());
				}else if(Book.PRICE.equals(tagName)){
					book.setPrice(Float.valueOf(mXmlPullParser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if (Book.BOOK.equals(tagName)) {
					mBooksList.add(book);
				}
				
			}
				parserType = mXmlPullParser.next();
		}
	} catch (XmlPullParserException e) {
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
	public void writeXML(String filePath) {
		// TODO Auto-generated method stub
		XmlSerializer mSerializer = Xml.newSerializer();
		FileOutputStream fos = null;
		try {
			 fos =new FileOutputStream(filePath);
			mSerializer.setOutput(fos, "utf-8");
			mSerializer.startDocument("utf-8", true);
			mSerializer.startTag("",Book.BOOKS);
			for (Book book: getBookList()) {
				mSerializer.startTag("", Book.BOOK);
				mSerializer.attribute("", Book.ID,String.valueOf(book.getId()));
			   
				mSerializer.startTag("", Book.NAME);
				mSerializer.text(book.getName());
				mSerializer.endTag("",Book.NAME);
				
				mSerializer.startTag("",Book.PRICE);
				mSerializer.text(String.valueOf(book.getPrice()));
				mSerializer.endTag("", Book.PRICE);
				
				mSerializer.endTag("", Book.BOOK);
			}
			mSerializer.endTag("", Book.BOOKS);
			mSerializer.endDocument();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void setBookList(List<Book> mBookList) {
		if (this.mBooksList!=null&& this.mBooksList.size()>0) {
			this.mBooksList.clear();
		}
		this.mBooksList = mBookList;
	}

	@Override
	public List<Book> getBookList() {
		// TODO Auto-generated method stub
		return mBooksList;
	}

}
