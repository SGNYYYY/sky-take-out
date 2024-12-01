package com.sky.utils;

import com.alibaba.fastjson.JSONObject;
import com.sky.exception.OrderBusinessException;
import com.sky.properties.BaiduProperties;
import com.sky.properties.ShopProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class BaiduUtil {

    @Autowired
    private BaiduProperties baiduProperties;

    @Autowired
    private ShopProperties shopProperties;

    public static final String GEOCODING_URL = "https://api.map.baidu.com/geocoding/v3?";

    public static final String direction_URL = "https://api.map.baidu.com/directionlite/v1/riding?";

    public String geoCoding(String address) {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("address", address);
        paramMap.put("output", "json");
        paramMap.put("ak", baiduProperties.getAK());
//        paramMap.put("callback", "showLocation");
        String result = HttpClientUtil.doGet(GEOCODING_URL, paramMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(!jsonObject.getString("status").equals("0")){
            throw new OrderBusinessException("地址解析失败");
        }
        JSONObject jsonLocation =  jsonObject.getJSONObject("result").getJSONObject("location");
        return String.format("%.6f", jsonLocation.getDouble("lat")) + "," + String.format("%.6f", jsonLocation.getDouble("lng"));
    }

    public String getDistance(String address) {
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("origin", geoCoding(shopProperties.getAddress()));
        paramMap.put("destination", geoCoding(address));
        paramMap.put("ak", baiduProperties.getAK());
        String result = HttpClientUtil.doGet(direction_URL, paramMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(!jsonObject.getString("status").equals("0")){
            throw new OrderBusinessException("配送路线规划失败");
        }
        JSONObject jsonRoutes = jsonObject.getJSONObject("result").getJSONArray("routes").getJSONObject(0);
        return jsonRoutes.getString("distance");
    }
}
