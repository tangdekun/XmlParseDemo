package com.view.john.xmlparsedemo;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import adapter.SaxAdapter;
import bean.Book;
import factory.DomParseTool;
import factory.PULLParseTool;
import factory.SAXParseTool;
import factory.XMLParseFactory;


public  class MainActivity extends Activity implements View.OnClickListener {

        ListView mListView;
        SaxAdapter mAdapter;
        ArrayList<Book> mBooks;
        XMLParseFactory factory;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mListView = (ListView) findViewById(R.id.list);
        }

        @Override
        public void onClick(View view) {
            try {
                InputStream mOutputStream = getAssets().open("ids.xml");
                switch (view.getId()) {

                case R.id.sax_parse_bt:
                    factory = new SAXParseTool();
                    factory.readXML(mOutputStream);
                    mBooks = (ArrayList<Book>) factory.getBookList();
                    mAdapter = new SaxAdapter(MainActivity.this, mBooks);
                    mListView.setAdapter(mAdapter);
                    break;
                case R.id.sax_write_bt:
                    factory = new SAXParseTool();
                    factory.readXML(mOutputStream);
                    factory.writeXML(Environment.getExternalStorageDirectory().getPath()+"/id.xml", factory.getBookList());
                    break;
                case R.id.pull_parse_bt:
                    factory = new PULLParseTool();
                    factory.readXML(mOutputStream);
                    mBooks = (ArrayList<Book>) factory.getBookList();
                    mAdapter = new SaxAdapter(MainActivity.this, mBooks);
                    mListView.setAdapter(mAdapter);

                    break;
                case R.id.pull_write_bt:
                    factory = new PULLParseTool();
                    factory.readXML(mOutputStream);
//                    mBooks = (ArrayList<Book>) factory.getBookList();
                    factory.writeXML(Environment.getExternalStorageDirectory().getPath()+"/ids.xml");

                    break;
                case R.id.dom_parse_bt:
                    factory = new DomParseTool();
                    factory.readXML(mOutputStream);
                    mBooks   = (ArrayList<Book>) factory.getBookList();
                    mAdapter = new SaxAdapter(MainActivity.this, mBooks);
                    mListView.setAdapter(mAdapter);
                    break;

                case R.id.dom_write_bt:
                    factory = new DomParseTool();
                    factory.readXML(mOutputStream);
//                    mBooks = (ArrayList<Book>) factory.getBookList();
                    factory.writeXML(Environment.getExternalStorageDirectory().getPath()+"/ids_dom.xml", factory.getBookList());
                    break;

                default:
                    break;
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }
