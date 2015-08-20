package com.mayhub.doingsomething.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

/**
 * Created by daihai on 2015/8/20.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1000;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        TimeSlotDao.createTable(db, ifNotExists);
    }

    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, true);
        }
    }


    public DaoMaster(SQLiteDatabase db, int schemaVersion) {
        super(db, schemaVersion);
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
