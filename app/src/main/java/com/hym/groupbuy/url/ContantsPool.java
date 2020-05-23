package com.hym.groupbuy.url;

/**
 * Created by Android on 2016/6/22.
 * 常量池
 */
public interface ContantsPool {

   //请求头中所必须要有的数据
/*   String appId ="X-Bmob-Application-Id";
   String appIdKey ="596a1dc9e9617aedee7505a214dc30b9";
   String APIId ="X-Bmob-REST-API-Key";
   String APIIdKey ="ebea31aa16b2c1bb1045d9eb967e4494";
   String contentType ="Content-Type";
   String contentTypeKey ="application/json";*/

   String[] title = new String[]{"首页", "周边", "我的", "更多"};

   String baseUrl = "https://api.bmob.cn/1/classes";

   /**猜你喜欢**/
   String spRecommendURL = baseUrl + "/goodlist/70dfdc12cc";

   /**電影**/
   String spFilmURL = baseUrl + "/film/e987ff03a5";


}
