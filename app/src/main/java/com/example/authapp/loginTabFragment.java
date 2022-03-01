package com.example.authapp;

import static android.content.Context.MODE_PRIVATE;
import static com.example.authapp.login.sharedDB;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class loginTabFragment extends Fragment {
    EditText email,pass;
    Button login;
    float v=0;
    SQLiteDatabase sqLiteDatabaseObj;

    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);
        email=root.findViewById(R.id.login_email);
        pass=root.findViewById(R.id.login_password);
        login=(Button) root.findViewById(R.id.login);
        SharedPreferences prefs=getActivity().getSharedPreferences(sharedDB, MODE_PRIVATE);
        email.setText(prefs.getString("email",""));
        pass.setText(prefs.getString("password",""));

        email.setTranslationX(800);
        pass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name="";
                String Email=email.getText().toString();
                String Password=pass.getText().toString();
                DataBaseHelper mydb=new DataBaseHelper(getContext());
                if(Email.equals("") || Password.equals("")) {
                    TextView textview = new TextView(getContext());
                    if(Email.equals("")){
                        textview.setText("Email Field are Required");
                    }else{
                        textview.setText("Password Field are Required");
                    }

                    textview.setTextColor(Color.WHITE);
                    textview.setPadding(30,30,30,30);

                    Toast toast = new Toast(getContext());
                    toast.setView(textview);
                    toast.setDuration(Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.error_drawable);

                    toast.show();

                }
                else {
// Opening SQLite database write permission.
                    sqLiteDatabaseObj = mydb.getWritableDatabase();

                    // Adding search email query to cursor.
                    cursor = sqLiteDatabaseObj.query(mydb.TABLE_NAME, null, " " + mydb.Table_Column_2_Email + "=?", new String[]{Email}, null, null, null);

                    while (cursor.moveToNext()) {

                        if (cursor.isFirst()) {

                            cursor.moveToFirst();

                            // Storing Password associated with entered email.
                            TempPassword = cursor.getString(cursor.getColumnIndexOrThrow(mydb.Table_Column_3_Password));
                            Name = cursor.getString(cursor.getColumnIndexOrThrow(mydb.Table_Column_1_Name));

                            // Closing cursor.
                            cursor.close();
                        }
                    }

                    if(TempPassword.equalsIgnoreCase(Password))
                    {

                        TextView textview = new TextView(getContext());
                        textview.setText("User Login");
                        textview.setTextColor(Color.WHITE);
                        textview.setPadding(40,40,40,40);

                        Toast toast = new Toast(getContext());
                        toast.setView(textview);
                        toast.setDuration(Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.success_drawable);

                        toast.show();

                        Handler handler=new Handler();

                        String finalName = Name;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(getActivity(), welcome.class);
                                intent.putExtra("Email", finalName);
                                startActivity(intent);
                            }
                        }, 2000);

                    }
                    else {
                        TextView textview = new TextView(getContext());
                        textview.setText("Email or Password is incorrect");
                        textview.setTextColor(Color.WHITE);
                        textview.setPadding(40,40,40,40);

                        Toast toast = new Toast(getContext());
                        toast.setView(textview);
                        toast.setDuration(Toast.LENGTH_SHORT);

                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.error_drawable);

                        toast.show();

                    }
                    TempPassword = "NOT_FOUND" ;



                }
            }
        });
        return root;

    }
}
