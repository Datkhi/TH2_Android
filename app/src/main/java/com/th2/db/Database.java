package com.th2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.th2.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bookDB";
    private static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE books(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "author TEXT," +
                "publishDate TEXT," +
                "publisher TEXT," +
                "price REAL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("publishDate", book.getPublishDate());
        values.put("publisher", book.getPublisher());
        values.put("price", book.getPrice());
        long result = sqLiteDatabase.insert("books", null, values);
        sqLiteDatabase.close();
        return result;
    }

    public List<Book> getAllBook(){
        List<Book> listBook = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase =getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("books", null, null,
                null, null, null, null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String author = cursor.getString(2);
            String publishDate = cursor.getString(3);
            String publisher = cursor.getString(4);
            String price = cursor.getFloat(5)+"";
            Book book = new Book(id,name,author,publishDate,publisher,price);
            listBook.add(book);
        }
        return listBook;
    }
    public long updateBook(Book book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put("author", book.getAuthor());
        contentValues.put("publishDate", book.getPublishDate());
        contentValues.put("publisher", book.getPublisher());
        contentValues.put("price",Float.parseFloat(book.getPrice()));
        long result = sqLiteDatabase.update("books",
                contentValues,"_id=?",
                new String[]{book.getId()+""});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteBook(int bookId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("books", "_id=?",
                new String[]{bookId+""});
        sqLiteDatabase.close();
        return result;
    }
}
