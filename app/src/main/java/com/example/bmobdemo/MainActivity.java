package com.example.bmobdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username_signin;
    private EditText userpassword_signin;
    private Button  sgin_btn;
    private Button  sgup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"3e2b32db389a9c94e6750692066340b6");
        initview();
        setListen();
    }

    private void setListen() {
        sgup_btn.setOnClickListener(this);
        sgin_btn.setOnClickListener(this);
        sgin_btn.setOnClickListener(this);
        sgup_btn.setOnClickListener(this);
    }

    private void initview() {
         sgin_btn=(Button)findViewById(R.id.signin);
         sgup_btn=(Button)findViewById(R.id.signup);
         username_signin=(EditText)findViewById(R.id.signin_username);
         userpassword_signin=(EditText)findViewById(R.id.signin_password);
    }

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
}
