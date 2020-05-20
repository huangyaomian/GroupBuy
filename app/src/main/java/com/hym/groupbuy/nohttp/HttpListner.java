package com.hym.groupbuy.nohttp;

import com.yanzhenjie.nohttp.rest.Response;

public interface HttpListner<T> {
    void onSucceed(int what, Response<T> response);
    void onFailed(int what, Response<T> response);
}
