package com.example.pizza_ordering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pizza_ordering.ui.home.getset;
import com.example.pizza_ordering.ui.orders.orders_getset;

public class DatabaseHelper extends SQLiteOpenHelper {


//     databasename, table name and column names.

        private static final String DATABASE_NAME = "Pizza_app";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "items";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_PRICE = "price";

    private static final String ORDERS_TABLE_NAME = "orders";
    private static final String ID = "id";
    private static final String ORDER_ID = "order_id";
    private static final String ORDER_ITEMS = "item_name";
    private static final String ORDER_PRICE = "o_price";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            // The query to create our table


            String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                    "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT item_pk PRIMARY KEY AUTOINCREMENT,\n" +
                    "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
                    "    " + COLUMN_PRICE + " double NOT NULL \n" +
                    ");";

            String sql1 = "CREATE TABLE " + ORDERS_TABLE_NAME + " (\n" +
                    "    " + ID + " INTEGER NOT NULL CONSTRAINT orders_pk PRIMARY KEY AUTOINCREMENT,\n" +
                    "    " + ORDER_ID + " INTEGER NOT NULL,\n" +
                    "    " + ORDER_ITEMS + " varchar(200) NOT NULL,\n" +
                    "    " + ORDER_PRICE + " double NOT NULL \n" +
                    ");";
            /*
             * Executing the string to create the table
             * */
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.execSQL(sql1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           // dropping and creating the table

            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
            String sql1 = "DROP TABLE IF EXISTS " + ORDERS_TABLE_NAME + ";";
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.execSQL(sql1);
            onCreate(sqLiteDatabase);
        }


          //we required contentValues object with all the values required
//        call the method getWritableDatabase() and it will return us the SQLiteDatabase object and we can write on the database using it.


          //insert() will return the inserted row id, if there is some error inserting the row


        public boolean addItems(String name, double price) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME, name);
            contentValues.put(COLUMN_PRICE, price);

            SQLiteDatabase db = getWritableDatabase();
            return db.insert(TABLE_NAME, null, contentValues) != -1;
        }

    public boolean addOrders(int order_id,String name,double price) {
        //CRUD , adding Products
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_ID,order_id);
        values.put(ORDER_ITEMS, name);
        values.put(ORDER_PRICE,price);

        return dbb.insert(ORDERS_TABLE_NAME, null, values) != -1;
    }
    //READ OPERATION

        public Cursor getAllCart_items() {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        }

    public Cursor getAllOrders() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + ORDERS_TABLE_NAME + " GROUP BY " + ORDER_ID, null);
    }
    public Cursor getOrder_id() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT MAX(order_id) FROM " + ORDERS_TABLE_NAME , null);
    }



      // DELETE OPERATION

        public boolean deleteCart_items(int id) {
            SQLiteDatabase db = getWritableDatabase();
            return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
        }

    public boolean deleteOrder_items(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ORDERS_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }
    }

