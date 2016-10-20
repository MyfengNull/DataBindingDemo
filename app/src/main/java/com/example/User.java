package com.example;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by yangshirong on 2016/10/20 20:15.
 * 邮箱 ysr200808@163.com
 */
public class User extends BaseObservable {
    private String name;
    private String nickName;
    private String email;

    private boolean vip;
    private int level;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public User() {
    }

    public User(String name, String nickName, String email) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Bindable
    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
        //刷新变量（变量id）
        notifyPropertyChanged(com.example.BR.name);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void clickName(View view){
        Toast.makeText(view.getContext(),"点击了用户名：" + name,Toast.LENGTH_SHORT).show();
    }

    public boolean longClickNickName(View view){
        Toast.makeText(view.getContext(),"长按了昵称:"+name,Toast.LENGTH_SHORT).show();
        return true;
    }

    public void click(View view){
        Toast.makeText(view.getContext(),"已点击",Toast.LENGTH_SHORT).show();
       // setName(getName() + "( 已点击 )");
    }
}
