package com.mayhub.doingsomething.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.db.TimeSlotDao;
import com.mayhub.doingsomething.util.DateUtils;


/**
 * Created by daihai on 2015/8/21.
 */
public class TimeSlotListAdapter extends CursorAdapter{




    public TimeSlotListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context, R.layout.activity_timeslot_list_item,null);
        ViewHolder holder = new ViewHolder();
        holder.tv_text = (TextView) view.findViewById(R.id.timeslot_text);
        holder.tv_time = (TextView) view.findViewById(R.id.timeslot_time);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tv_text.setText(cursor.getString(TimeSlotDao.Properties.Text.ordinal));
        holder.tv_time.setText(DateUtils.getFormattedDate(cursor.getLong(TimeSlotDao.Properties.Date.ordinal)));
    }

    private static class ViewHolder{
        public TextView tv_text;
        public TextView tv_time;
    }

}
