# 借助bmob实现简单的登陆注册

![在这里插入图片描述](https://github.com/kurumi2501314/Bmobdemo/blob/master/ezgif.com-video-to-gif.gif?raw=true)




欢迎下载
https://github.com/kurumi2501314/Bmobdemo/blob/master/demo.apk
# 实现步骤
详细可以去我的github
https://github.com/kurumi2501314/Bmobdemo
只是将官方文档稍作了修改，详细更改还是去官方文档。
## 1注册bmob
去bmob官网注册，地址
https://www.bmob.cn/
然后在我的控制台创建应用，之后
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190410210713150.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMzE4NDAw,size_7,color_FFFFFF,t_70)
我的这里是demo，点击进入你新进的那个应用，例如我进入demo
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190410210915862.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMzE4NDAw,size_16,color_FFFFFF,t_70)
设置里的第一个Application ID就是之后需要的。数据里是你建的表，默认有一个user表，如果需要扩充，如性别，简介，兴趣爱好这些需要一个子类继承bmobuser类进行扩展。


## 2配置Bomb相关
**1配置SDK**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190410211630887.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMzE4NDAw,size_16,color_FFFFFF,t_70)
源于官网文档
**2配置权限和ContentProvider**
打开AndroidManifest.xml，添加如下权限：
```javascript
<!--允许联网 --> 
<uses-permission android:name="android.permission.INTERNET" /> 
<!--获取GSM（2g）、WCDMA（联通3g）等网络状态的信息  --> 
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> 
<!--获取wifi网络状态的信息 --> 
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /> 
<!--保持CPU 运转，屏幕和键盘灯有可能是关闭的,用于文件上传和下载 -->
<uses-permission android:name="android.permission.WAKE_LOCK" /> 
<!--获取sd卡写的权限，用于文件上传和下载-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!--允许读取手机状态 用于创建BmobInstallation--> 
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```
然后配置ContentProvider
```java
<application>
···
<provider
    android:name="cn.bmob.v3.util.BmobContentProvider"
    android:authorities="你的应用包名.BmobContentProvider">
</provider>
···
</application>
```

这两个都是官网文档的，这里直接引用。


**3初始化bmob**
在活动的oncreate方法里初始化
```java
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"这里填写你自己的id，就是第一步注册后看到的那个");
       ````
    }
```


## 3扩展自己的用户列表
BmobUser默认里没有昵称，性别这些，所以如果用的话需要继承然后扩展，新建Myuser类继承BmobUser。
```javascript
package com.example.bmobdemo;

import cn.bmob.v3.BmobUser;

public class MyUser  extends BmobUser {

    //补充其他属性
    private  String fakename;//昵称
    private  String introd;//简介
    private  String sex; //性别
    private  String interesting; //兴趣爱好

    public String getFakename()
    {
        return  fakename;
    }
    public void  setFakename(String fakename)
    {
        this.fakename=fakename;
    }
    public String getIntrod()
    {
        return  introd;
    }
    public  void  setIntrod(String introd)
    {
        this.introd=introd;
    }
    public  String getSex()
    {
        return  sex;
    }
    public  void  setSex(String sex)
    {
        this.sex=sex;
    }
    public  String getInteresting()
    {
        return interesting;
    }
    public  void  setInteresting(String interesting)
    {
        this.interesting=interesting;
    }
}
```




## 4实现注册
默认在登陆页，当点击注册按钮后进入注册界面，填写相关信息。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190410212842106.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQxMzE4NDAw,size_16,color_FFFFFF,t_70)
布局就是线性布局然后向里面填充，然后密码栏选择了password属性的Edittext所以是不可见的，布局不再赘述，详细可以去我git看全部，这里主要是活动里。
活动也很简单，先初始化布局控件，然后判断点击，再添加点击事件。关于注册方法bmob给封装了，在官方文档是这样的：
```java
private void signUp(final View view) {
    final User user = new User();
    user.setUsername("" + System.currentTimeMillis());
    user.setPassword("" + System.currentTimeMillis());
    user.setAge(18);
    user.setGender(0);
    user.signUp(new SaveListener<User>() {
        @Override
        public void done(User user, BmobException e) {
            if (e == null) {
                Snackbar.make(view, "注册成功", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(view, "尚未失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }
    });
}
```
这里稍作修改，关键部分如下：
```java
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Bmob.initialize(this,"我的应用id");
        initview(); //初始化布局
        setListen();//设置监听
    }

    private void setListen() {
        re_sgin_btn.setOnClickListener(this);
    }

    private void initview() {
         //省略
    }

    @Override
    public void onClick(View v) {
           if(v.getId()==R.id.sgin_up_btn)
           {
               signup();
           }
    }

    private void signup() {
        final  MyUser myUser=new MyUser();
        myUser.setUsername(re_username.getText().toString().trim());
        myUser.setPassword(re_possword.getText().toString().trim());
        myUser.setFakename(re_fake.getText().toString().trim());
        myUser.setSex(re_sex.getText().toString().trim());
        myUser.setIntrod(re_intro.getText().toString().trim());
        myUser.setInteresting(re_inter.getText().toString().trim());
        myUser.signUp(new SaveListener<MyUser>() {
            @Override
            public  void  done(MyUser user, BmobException e)
            {
                if (e==null)
                {
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignupActivity.this,MainActivity.class);    //注册成功就去登陆页，其实finish（）也行。
                    startActivity(intent);
                }
            }
        });
    }
   ```
## 5实现登陆
布局就是开始效果图那样，不再赘述
登陆活动的关键部分如下
```java
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.signin:
                     login();
                     break;
            case  R.id.signup:
                Intent intent1=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent1);
        }

    }

    private void login() {
        final  MyUser user=new MyUser();
        final  String username=username_signin.getText().toString().trim();
        final  String password=userpassword_signin.getText().toString().trim();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<MyUser>() {
            @Override
            public  void  done(MyUser myUser, BmobException e)
            {
                if(e==null)
                {
                    MyUser user=MyUser.getCurrentUser(MyUser.class);
                    Intent intent =new Intent(MainActivity.this,nextActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
```
 



详细可以去我github去看完整
https://github.com/kurumi2501314/Bmobdemo


欢迎下载
https://github.com/kurumi2501314/Bmobdemo/blob/master/demo.apk

### 
