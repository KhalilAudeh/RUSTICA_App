package com.example.srourcompu.sample_app.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.srourcompu.sample_app.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srourcompu on 4/24/2018.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "rusticaDB.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] select = {"FoodName", "FoodID", "Price", "Quantity"};
        String table = "DETAIL";

        qb.setTables(table);
        Cursor cursor = qb.query(db, select, null, null, null, null, null);

        final List<Order> out = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                out.add(new Order(cursor.getString(cursor.getColumnIndex("FoodName")),
                        cursor.getString(cursor.getColumnIndex("FoodID")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Quantity"))
                ));
            }
        }
        return out;
    }

    public void addToCart(Order o) {

        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("INSERT INTO DETAIL (FoodName, FoodID, Quantity, Price) VALUES ('%s', '%s', '%s', '%s');",
        o.getFoodName(), o.getFoodID(), o.getPrice(), o.getQuantity());

        db.execSQL(query);
    }

    public void clean() {

        SQLiteDatabase db = getReadableDatabase();

        String query = String.format("DELETE FROM DETAIL");

        db.execSQL(query);
    }

}
