package com.jdemaagd.esperar.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.jdemaagd.esperar.data.WaitlistContract.WaitlistEntry;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db) {
        if (db == null) {
            return;
        }

        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(WaitlistEntry.COLUMN_GUEST_NAME, "John");
        cv.put(WaitlistEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistEntry.COLUMN_GUEST_NAME, "Tim");
        cv.put(WaitlistEntry.COLUMN_PARTY_SIZE, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistEntry.COLUMN_GUEST_NAME, "Jessica");
        cv.put(WaitlistEntry.COLUMN_PARTY_SIZE, 99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistEntry.COLUMN_GUEST_NAME, "Larry");
        cv.put(WaitlistEntry.COLUMN_PARTY_SIZE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistEntry.COLUMN_GUEST_NAME, "Kim");
        cv.put(WaitlistEntry.COLUMN_PARTY_SIZE, 45);
        list.add(cv);

        // insert all guests in one transaction
        try {
            db.beginTransaction();
            //clear the table first
            db.delete(WaitlistEntry.TABLE_NAME, null, null);
            //go through the list and add one by one
            for (ContentValues c : list) {
                db.insert(WaitlistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            // too bad :(
        } finally {
            db.endTransaction();
        }
    }
}