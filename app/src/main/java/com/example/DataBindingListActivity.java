package com.example;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.databinding.ActivityDataBindingListBinding;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class DataBindingListActivity extends AppCompatActivity {
    private List<User> list;
    private ActivityDataBindingListBinding binding;
    private Context context;
    private CommonAdapter<User> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, com.example.R.layout.activity_data_binding_list);
        list = new ArrayList<>();
        getData();

    }

    private void getData() {
        RequestParams params = new RequestParams("http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-10.html");
        // 默认缓存存活时间, 单位:毫秒.(如果服务没有返回有效的max-age或Expires)
        params.setCacheMaxAge(1000 * 60);
        Callback.Cancelable cancelable
                // 使用CacheCallback, xUtils将为该请求缓存数据.
                = x.http().get(params, new Callback.CacheCallback<String>() {

            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {
                this.result = result;
                return false; // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
            }

            @Override
            public void onSuccess(String result) {
                if (null!=result) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();

                } else { // 其他错误

                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    // 成功获取数据
                    list = new ArrayList<>();
                    JSONObject obj = JSONObject.parseObject(result);
                    JSONArray body = obj.getJSONArray("V9LG4B3A0");
                    for (int i = 0; i < body.size(); i++) {
                        JSONObject obj4 = body.getJSONObject(i);
                        User user = new User();
                        user.setName(obj4.getString("title"));
                        user.setIcon(obj4.getString("cover"));
                        list.add(user);
                    }
                    adapter = new CommonAdapter<>(
                            context, list, com.example.R.layout.list_item, com.example.BR.user);
                    binding.setAdapter(adapter);
                   // Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
