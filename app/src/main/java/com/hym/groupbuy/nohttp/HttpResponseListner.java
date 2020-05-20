package com.hym.groupbuy.nohttp;

import android.content.Context;
import android.content.DialogInterface;

import com.hym.groupbuy.widget.WaitDialog;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class HttpResponseListner<T> implements OnResponseListener<T> {

    private HttpListner<T> mListner;

    private WaitDialog mWaitDialog;

    private boolean isLoading;

    private Request<T> mRequest;

    public HttpResponseListner(Context context,HttpListner<T> mListner,  Request<T> mRequest, boolean isLoading, boolean canCancle) {
        this.isLoading = isLoading;
        this.mRequest = mRequest;
        this.mListner = mListner;
        if (context != null) {
            mWaitDialog = new WaitDialog(context);
            mWaitDialog.setCancelable(canCancle);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mWaitDialog.cancel();
                }
            });
        }
    }

    @Override
    public void onStart(int what) {
        if (isLoading && mWaitDialog != null && !mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        if (mListner != null) {
            mListner.onSucceed(what, response);
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        if (mListner != null) {
            mListner.onFailed(what, response);
        }
    }

    @Override
    public void onFinish(int what) {
        if (isLoading && mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.cancel();
        }
    }
}
