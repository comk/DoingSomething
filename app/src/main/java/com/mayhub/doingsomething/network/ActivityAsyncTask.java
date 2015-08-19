package com.mayhub.doingsomething.network;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;

import com.mayhub.doingsomething.entity.Result;

import java.util.concurrent.TimeoutException;

/**
 * Created by daihai on 2015/8/19.
 */
public abstract class ActivityAsyncTask<T1,T2,T3> extends AsyncTask<Object,Object,Object> {

    private AsyncTaskListener asyncTaskListener;

    public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener){
        this.asyncTaskListener = asyncTaskListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        asyncTaskListener.onAsyncTaskBegin();
    }

    @Override
    protected void onPostExecute(Object o) {
        asyncTaskListener.onAsyncTaskFinish((Result)o);
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        asyncTaskListener.onAsyncTaskCancel();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        asyncTaskListener.onAsyncTaskCancel();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            asyncTaskListener.onAsyncDoBackground();
        }catch (Exception ex){
            AsyncTaskError error = new AsyncTaskError();
            error.setErrorMessage(ex.getMessage());
            if(ex instanceof TimeoutException){
                error.setErrorCode(AsyncTaskError.ErrorCode_TIMEOUT);
            }else if(ex instanceof NetworkErrorException){
                error.setErrorCode(AsyncTaskError.ErrorCode_NetworkError);
            }else if(ex instanceof ){
                error.setErrorCode();
            }else if(ex instanceof NetworkErrorException){
                error.setErrorCode();
            }else if(ex instanceof NetworkErrorException){
                error.setErrorCode();
            }else if(ex instanceof NetworkErrorException){
                error.setErrorCode();
            }
            asyncTaskListener.onAsyncTaskError();
        }
        return null;
    }


    public static interface AsyncTaskListener{
        public void onAsyncTaskBegin();
        public void onAsyncDoBackground();
        public void onAsyncTaskFinish(Result result);
        public void onAsyncTaskCancel();
        public void onAsyncTaskError(AsyncTaskError error);

    }

    public static class AsyncTaskError{

        public static final int ErrorCode_TIMEOUT = 0x101;
        public static final int ErrorCode_NetworkError = 0x102;
        public static final int ErrorCode_TIMEOUT = 0x103;
        public static final int ErrorCode_TIMEOUT = 0x104;
        public static final int ErrorCode_TIMEOUT = 0x105;

        private String errorMessage;
        private int errorCode;

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
    }

}
