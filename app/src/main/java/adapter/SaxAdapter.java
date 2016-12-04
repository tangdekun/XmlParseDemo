package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.view.john.xmlparsedemo.R;

import java.util.ArrayList;
import java.util.List;

import bean.Book;

public class SaxAdapter extends BaseAdapter{
	Context context;
	List<Book> mBooks;
	
	public SaxAdapter(Context mContext,ArrayList<Book> mBooks){
		this.context = mContext;
		if (this.mBooks!=null && this.mBooks.size()>0) {
			this.mBooks.clear();
		}
		this.mBooks = mBooks;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBooks.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mBooks.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder mHolder = null;
		Book book = mBooks.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
		    mHolder = new ViewHolder();
		    mHolder.idTv = (TextView) convertView.findViewById(R.id.id_tv1);
		    mHolder.nameTv = (TextView) convertView.findViewById(R.id.name_tv1);
		    mHolder.priceTv = (TextView) convertView.findViewById(R.id.price_tv1);
		    convertView.setTag(mHolder);	
		}else{
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.idTv.setText(book.getId()+"");
		mHolder.nameTv.setText(book.getName());
		mHolder.priceTv.setText(book.getPrice()+"");
		
		return convertView;
	}
    
	class ViewHolder{
		TextView idTv;
		TextView nameTv;
		TextView priceTv;
		
	}
}
