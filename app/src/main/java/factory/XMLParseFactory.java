package factory;

import java.io.InputStream;
import java.util.List;

import bean.Book;

/**
 * Created by John on 2016/12/4.
 */

public abstract class XMLParseFactory {
    /**
     * 读取指定的XML文件
     * @param mInputStream XML文件输入流
     */
    public abstract void readXML(InputStream mInputStream);
    /**
     * 保存XML到指定的文件
     * @param filePath 文件的绝对路径
     */
    public abstract void writeXML(String filePath);

    /**
     * 设置book列表
     * @param mBookList
     */
    public abstract void setBookList(List<Book> mBookList);

    /**
     * @return 返回Book列表
     */
    public abstract List<Book> getBookList();
    public void writeXML(String filePath, List<Book> mBooks) {
        // TODO Auto-generated method stub

    }
}
