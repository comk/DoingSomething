package com.mayhub.doingsomething.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.mayhub.doingsomething.entity.TimeSlot;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by daihai on 2015/8/20.
 */
public class TimeSlotDao extends AbstractDao<TimeSlot,Long> {

    public static final String TABLENAME = "TimeSlot";

    /**
     * Properties of entity TimeSlot.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Date = new Property(1, long.class, "date", false, "DATE");
        public final static Property UserId = new Property(2, String.class, "UserId", false, "USER_ID");
        public final static Property SlotType = new Property(3, int.class, "SlotType", false, "SLOT_TYPE");
        public final static Property Text = new Property(4, String.class, "text", false, "TEXT");
        public final static Property LastModifyTime = new Property(5, long.class, "LastModifyTime", false, "LAST_MODIFY_TIME");
        public final static Property ReferenceObject = new Property(6, String.class, "ReferenceObject", false, "REFERENCE_OBJECT");
        public final static Property LocationLatLng = new Property(7, String.class, "LocationLatLng", false, "LOCATION_LATLNG");
        public final static Property LocationString = new Property(8, String.class, "LocationString", false, "LOCATION_STRING");
        public final static Property Weather = new Property(9, String.class, "Weather", false, "WEATHER");
        public final static Property ImageUrl = new Property(10, String.class, "ImageUrl", false, "IMAGE_URL");
        public final static Property AudioUrl = new Property(11, String.class, "AudioUrl", false, "AUDIO_URL");
        public final static Property VideoUrl = new Property(12, String.class, "VideoUrl", false, "VIDEO_URL");
        public final static Property Level = new Property(13, String.class, "Level", false, "LEVEL");
    };

    public TimeSlotDao(DaoConfig config) {
        super(config);
    }

    public TimeSlotDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'"+TABLENAME+"' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT," + // 0: id
                "'DATE' NUMERIC(18) NOT NULL ," + // 1: date
                "'USER_ID' VARCHAR NOT NULL ," + // 2: UserId
                "'SLOT_TYPE' INT NOT NULL ," + // 3: SlotType
                "'TEXT' VARCHAR," + // 4: Text
                "'LAST_MODIFY_TIME' NUMERIC(18) NOT NULL," + // 5: LastModifyTime
                "'REFERENCE_OBJECT' VARCHAR," + // 6: ReferenceObject
                "'LOCATION_LATLNG' VARCHAR," + // 7: LocationLatLng
                "'LOCATION_STRING' VARCHAR," + // 8: LocationString
                "'WEATHER' VARCHAR," + // 9: Weather
                "'IMAGE_URL' VARCHAR," + // 10: ImageUrl
                "'AUDIO_URL' VARCHAR," + // 11: AudioUrl
                "'VIDEO_URL' VARCHAR," + //  12: VideoUrl
                "'LEVEL' INT NOT NULL);"); // 13: Level
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'"+TABLENAME+"'";
        db.execSQL(sql);
    }

    @Override
    protected TimeSlot readEntity(Cursor cursor, int offset) {
        return new TimeSlot(
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // date
            cursor.getString(offset + 2), // userid
            cursor.getInt(offset + 3), // slotType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Text
            cursor.getLong(offset + 5), // LastModifyTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // ReferenceObject
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // LocationLatLng
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // LocationString
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // Weather
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // ImageUrl
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // AudioUrl
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // VideoUrl
            cursor.getInt(offset + 13)//level
        );
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, TimeSlot entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setDate(cursor.getLong(offset + 1));
        entity.setUserId(cursor.getString(offset + 2));
        entity.setSlotType(cursor.getInt(offset + 3));
        entity.setText(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLastModifyTime(cursor.getLong(offset + 5));
        entity.setReferenceObject(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLocationLatLng(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLocationString(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setWeather(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setImageUrl(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAudioUrl(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setVideoUrl(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setLevel(cursor.getInt(offset + 13));
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, TimeSlot entity) {
        stmt.clearBindings();

        stmt.bindLong(2, entity.getDate());

        stmt.bindString(3, entity.getUserId());

        stmt.bindLong(4,entity.getSlotType());

        String text = entity.getText();
        if (text != null) {
            stmt.bindString(5, text);
        }

        stmt.bindLong(6, entity.getLastModifyTime());

        String referenceObj = entity.getText();
        if (referenceObj != null) {
            stmt.bindString(7, referenceObj);
        }
        String location = entity.getText();
        if (location != null) {
            stmt.bindString(8, location);
        }
        String locationString = entity.getText();
        if (locationString != null) {
            stmt.bindString(9, locationString);
        }
        String weather = entity.getText();
        if (weather != null) {
            stmt.bindString(10, weather);
        }
        String imageUrl = entity.getText();
        if (imageUrl != null) {
            stmt.bindString(11, imageUrl);
        }
        String audioUrl = entity.getText();
        if (audioUrl != null) {
            stmt.bindString(12, audioUrl);
        }
        String videoUrl = entity.getText();
        if (videoUrl != null) {
            stmt.bindString(13, videoUrl);
        }
        stmt.bindLong(14,entity.getLevel());
    }

    @Override
    protected Long updateKeyAfterInsert(TimeSlot entity, long rowId) {
        return null;
    }

    @Override
    protected Long getKey(TimeSlot entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
}
