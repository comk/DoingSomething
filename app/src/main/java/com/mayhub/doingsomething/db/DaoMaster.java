package com.mayhub.doingsomething.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.mayhub.doingsomething.entity.TimeSlot;

import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

/**
 * Created by daihai on 2015/8/20.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1000;

    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            TimeSlotDao.createTable(db,false);
        }
    }

    public static class TimeSlotOpenHelper extends OpenHelper{

        public TimeSlotOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           TimeSlotDao.dropTable(db,true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db){
        super(db,SCHEMA_VERSION);
        registerDaoClass(TimeSlotDao.class);
    }

    public DaoMaster(SQLiteDatabase db, int schemaVersion) {
        super(db, schemaVersion);
        registerDaoClass(TimeSlotDao.class);
    }

    @Override
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    @Override
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
}
