package com.example.bmobdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText re_username;
    private EditText re_possword;
    private EditText re_fake;
    private EditText re_sex;
    private EditText re_intro;
    private EditText re_inter;
    private Button   re_sgin_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Bmob.initialize(this,"3e2b32db389a9c94e6750692066340b6");
        initview();
        setListen();
    }

    private void setListen() {
        re_sgin_btn.setOnClickListener(this);
    }

    private void initview() {
        re_username=(EditText)findViewById(R.id.re_name);
        re_possword=(EditText)findViewById(R.id.re_password);
        re_fake=(EditText)findViewById(R.id.re_akename);
        re_sex=(EditText)findViewById(R.id.re_sex);
        re_intro=(EditText)findViewById(R.id.re_intro);
        re_inter=(EditText)findViewById(R.id.re_inter);
        re_sgin_btn=(Button)findViewById(R.id.sgin_up_btn);
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
                    Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
