package com.example.authapp;

import static com.example.authapp.login.sharedDB;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class SignupTabFragment extends Fragment {

    EditText email,pass,name,cpass;
    Button sign;
    float v=0;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email=root.findViewById(R.id.email);
        name=root.findViewById(R.id.name);
        pass=root.findViewById(R.id.password);
        cpass=root.findViewById(R.id.confirm_password);
        sign=(Button) root.findViewById(R.id.signup);

        email.setTranslationX(800);
        name.setTranslationX(800);
        cpass.setTranslationX(800);
        pass.setTranslationX(800);
        sign.setTranslationX(800);

        email.setAlpha(v);
        name.setAlpha(v);
        pass.setAlpha(v);
        cpass.setAlpha(v);
        sign.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        cpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        sign.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        DataBaseHelper mydb=new DataBaseHelper(getContext());

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email=email.getText().toString();
                String Name=name.getText().toString();
                String password=pass.getText().toString();
                String confirm_password=cpass.getText().toString();

                if(Email.equals("") || Name.equals("") || password.equals("") || confirm_password.equals("")){
                    TextView textview = new TextView(getContext());
                    if(Email.equals("")){
                        textview.setText("Email Field Are Required");
                    }else if(Name.equals("")){
                        textview.setText("Name Field Are Required");
                    }else if(password.equals("")){
                        textview.setText("Password Field Are Required");
                    }else{
                        textview.setText("Confirm Password Field Are Required");
                    }

                    textview.setTextColor(Color.WHITE);
                    textview.setPadding(40,40,40,40);

                    Toast toast = new Toast(getContext());
                    toast.setView(textview);
                    toast.setDuration(Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.error_drawable);

                    toast.show();
                }else {
                    if (password.equals(confirm_password)) {

                        sqLiteDatabaseObj=mydb.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("name",Name);
                        values.put("email",Email);
                        values.put("password", password);
                        Log.d("values", String.valueOf(values));
                        sqLiteDatabaseObj.insert(mydb.TABLE_NAME,null,values);

                        sqLiteDatabaseObj.close();

                        SharedPreferences.Editor editor=getActivity().getSharedPreferences(sharedDB, Context.MODE_PRIVATE).edit();
                        editor.putString("email",Email);
                        editor.putString("password",password);
                        editor.apply();

                        TextView textview = new TextView(getContext());
                        textview.setText("User Registered");
                        textview.setTextColor(Color.WHITE);
                        textview.setPadding(40,40,40,40);

                        Toast toast = new Toast(getContext());
                        toast.setView(textview);
                        toast.setDuration(Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.success_drawable);

                        toast.show();

                        Handler handler=new Handler();

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(getActivity(), welcome.class);
                                intent.putExtra("Email", Name);
                                startActivity(intent);
                            }
                        }, 2000);

                    }else{

                        TextView textview = new TextView(getContext());
                        textview.setText("Password Not Matched");
                        textview.setTextColor(Color.WHITE);
                        textview.setPadding(40,40,40,40);

                        Toast toast = new Toast(getContext());
                        toast.setView(textview);
                        toast.setDuration(Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.success_drawable);
                        toast.show();
                    }
                }
            }
        });
        return root;
    }
}
