package com.mayhub.doingsomething.db;

import android.database.sqlite.SQLiteDatabase;

import com.mayhub.doingsomething.entity.TimeSlot;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by daihai on 2015/8/20.
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig timeSlotConfig;

    private final TimeSlotDao timeSlotDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        timeSlotConfig = daoConfigMap.get(TimeSlot.class).clone();
        timeSlotConfig.initIdentityScope(type);

        timeSlotDao = new TimeSlotDao(timeSlotConfig,this);

        registerDao(TimeSlot.class,timeSlotDao);

    }


    public void clear(){
        timeSlotConfig.getIdentityScope().clear();
    }

    public TimeSlotDao getTimeSlotDao(){
        return timeSlotDao;
    }

}
