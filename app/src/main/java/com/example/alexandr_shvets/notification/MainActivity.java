package com.example.alexandr_shvets.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    FrameLayout container;
    FragmentManager myFragmentManager;
    MyFragment1 myFragment1;
    MyFragment2 myFragment2;
    MyFragment3 myFragment3;
    public static Activity activity = null;
    final static String TAG_1 = "FRAGMENT_1";
    final static String TAG_2 = "FRAGMENT_2";
    final static String TAG_3 = "FRAGMENT_3";
    final static String KEY_MSG_1 = "FRAGMENT1_MSG";
    final static String KEY_MSG_2 = "FRAGMENT2_MSG";
    final static String KEY_MSG_3 = "FRAGMENT3_MSG";
    Intent intentFirstFragmentService;
    Intent intentSecondFragmentService;
    Intent intentThirdFragmentService;
    Intent intent;
    public String nameFragment;
    public  static final String FRAGMENT_NAME = null;
    //Класс для первого фрагмента
    @SuppressLint("ValidFragment")
    public class MyFragment1 extends Fragment
    {
        TextView textMsg;
        FragmentManager myFragmentManager;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            myFragmentManager = getFragmentManager();
            intentFirstFragmentService = new Intent(MainActivity.this,First_Fragment_Service.class);
            View view = inflater.inflate(R.layout.fragment, container,false);
            Button Fragment_add = (Button) view.findViewById(R.id.add_fragment2);

            textMsg = (TextView) view.findViewById(R.id.fragment_message);
            textMsg.setText("1");
            Fragment_add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0)
                {
                    MyFragment1 fragment = (MyFragment1) myFragmentManager
                            .findFragmentByTag(TAG_1);

                    if (fragment == null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_1, "1");
                        myFragment1.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment1,
                                TAG_1);
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        fragment.setMsg("1");
                    }
                    MyFragment2 fragment2 = (MyFragment2) myFragmentManager.findFragmentByTag(TAG_2);
                    if (fragment2 == null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_2, "2");
                        myFragment2.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment2,
                                TAG_2);
                        fragmentTransaction.commit();

                    } else
                    {
                        fragment2.setMsg("2");
                    }
                }
            });
            //Exit App and STOP notification 1
            Button Fragment_exit = (Button) view.findViewById(R.id.exit_fragment);
            Fragment_exit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View arg0)
                {
                    stopService(intentFirstFragmentService);
                    closeApplication(arg0);
                }
            });
            //Notification 1
            Button Notification_add = (Button) view.findViewById(R.id.add_notification);
            Notification_add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View arg0)
                {
                    startService(intentFirstFragmentService);
                }
            });
            Bundle bundle = getArguments();
            if (bundle != null)
            {
                String msg = bundle.getString(KEY_MSG_1);
                if (msg != null)
                {
                    textMsg.setText(msg);
                }
            }
            return view;
        }
        public void setMsg(String msg) {
            textMsg.setText(msg);
        }
    }

    //Класс для второго фрагмента
    @SuppressLint("ValidFragment")
    public class MyFragment2 extends Fragment
    {

        TextView textMsg;
        FragmentManager myFragmentManager;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            myFragmentManager = getFragmentManager();
            View view = inflater.inflate(R.layout.fragment2, container, false);
            Button Fragment_add2 = (Button) view.findViewById(R.id.add_fragment2);
            textMsg = (TextView) view.findViewById(R.id.fragment_message2);
            textMsg.setText("2");
            intentSecondFragmentService = new Intent(MainActivity.this,Second_Fragment_Service.class);
            //Переход на третий фрагмент
            Fragment_add2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0)
                {
                    MyFragment2 fragment2 = (MyFragment2) myFragmentManager.findFragmentByTag(TAG_2);
                    if (fragment2 == null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_2, "2");
                        fragment2.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment2,
                                TAG_2);
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        fragment2.setMsg("2");
                    }
                    MyFragment3 fragment3 = (MyFragment3) myFragmentManager.findFragmentByTag(TAG_3);
                    if (fragment3 == null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_3, "3");
                        myFragment3.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment3,
                                TAG_3);
                        fragmentTransaction.commit();
                    }
                    else
                    {
                        fragment3.setMsg("3");
                    }
                }
            });
            //Переход на первый фрагмент
            Button Fragment_del2 = (Button) view.findViewById(R.id.del_fragment2);
            Fragment_del2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0)
                {
                    MyFragment2 fragment2 = (MyFragment2) myFragmentManager.findFragmentByTag(TAG_2);
                    if (fragment2 == null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_2, "2");
                        fragment2.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment2,
                                TAG_2);
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        fragment2.setMsg("2");
                    }
                    MyFragment1 fragment1 = (MyFragment1) myFragmentManager.findFragmentByTag(TAG_1);
                    if (fragment1 == null)
                    {
                        stopService(intentSecondFragmentService);
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_1, "1");
                        myFragment1.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment1,
                                TAG_1);
                        fragmentTransaction.commit();
                    }
                    else
                    {
                        fragment1.setMsg("1");
                    }
                }
            });
            //Notification 2
            Button Notification_add2 = (Button) view.findViewById(R.id.add_notification2);
            Notification_add2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View arg0)
                {
                    startService(intentSecondFragmentService);
                    //startService(new Intent(MainActivity.class, Second_Fragment_Service.class));
                }
            });
            Bundle bundle = getArguments();
            if (bundle != null)
            {
                String msg = bundle.getString(KEY_MSG_2);
                if (msg != null)
                {
                    textMsg.setText(msg);
                }
            }
            return view;
        }
        public void setMsg(String msg)
        {
            textMsg.setText(msg);
        }
    }

    //Класс для третьего фрагмента
    @SuppressLint("ValidFragment")
    public class MyFragment3 extends Fragment
    {

        TextView textMsg;
        FragmentManager myFragmentManager;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            myFragmentManager = getFragmentManager();
            View view = inflater.inflate(R.layout.fragment3, container, false);
            Button Fragment_del3 = (Button) view.findViewById(R.id.del_fragment3);
            textMsg = (TextView) view.findViewById(R.id.fragment_message3);
            intentThirdFragmentService = new Intent(MainActivity.this,Third_Fragment_Service.class);
            textMsg.setText("3");
            //Переход на второй фрагмент
            Fragment_del3.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0)
                {
                    MyFragment3 fragment3 = (MyFragment3) myFragmentManager.findFragmentByTag(TAG_3);
                    if (fragment3 == null)
                    {

                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_3, "3");
                        fragment3.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment3,
                                TAG_3);
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        fragment3.setMsg("3");
                    }
                    MyFragment2 fragment2 = (MyFragment2) myFragmentManager.findFragmentByTag(TAG_2);
                    if (fragment2 == null)
                    {
                        stopService(intentThirdFragmentService);
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_MSG_2, "2");
                        myFragment2.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = myFragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, myFragment2,
                                TAG_2);
                        fragmentTransaction.commit();
                    }
                    else
                    {
                        fragment2.setMsg("2");
                    }
                }
            });
            Button Notification_add3 = (Button) view.findViewById(R.id.add_notification3);
            Notification_add3.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View arg0)
                {
                    startService(intentThirdFragmentService);
                }
            });
            Bundle bundle = getArguments();
            if (bundle != null)
            {
                String msg = bundle.getString(KEY_MSG_3);
                if (msg != null)
                {
                    textMsg.setText(msg);
                }
            }
            return view;
        }
        public void setMsg(String msg)
        {
            textMsg.setText(msg);
        }
    }


    private PowerManager.WakeLock mWakeLock = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);

        myFragmentManager = getFragmentManager();
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Main_WakeLock");
        mWakeLock.acquire();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        activity = this;


        intent = getIntent();
        //Ловим параметр перехода на фрагмент с уведомления
        nameFragment = intent.getStringExtra(FRAGMENT_NAME);
        FragmentTransaction fragmentTransaction;

        //Делаем проверку
        //Если пусто (к примеру при первом запуске), то запускаем по умолчанию 1 фрагмент
        if (nameFragment != null)
        {
            if (nameFragment.equals("FragmentFirst"))
            {
                nameFragment = "fragment_name";
                fragmentTransaction = myFragmentManager.beginTransaction();
                // добавляем в контейнер при помощи метода add()
                fragmentTransaction.add(R.id.container, myFragment1, TAG_1);
                fragmentTransaction.commit();
            }
            else if (nameFragment.equals("FragmentSecond"))
            {
                nameFragment = "fragment_name";
                fragmentTransaction = myFragmentManager.beginTransaction();
                // добавляем в контейнер при помощи метода add()
                fragmentTransaction.add(R.id.container, myFragment2, TAG_2);
                fragmentTransaction.commit();
            }
            else if (nameFragment.equals("FragmentThird"))
            {
                nameFragment = "fragment_name";
                fragmentTransaction = myFragmentManager.beginTransaction();
                // добавляем в контейнер при помощи метода add()
                fragmentTransaction.add(R.id.container, myFragment3, TAG_3);
                fragmentTransaction.commit();
            }
        }
        else
        {
            nameFragment = "fragment_name";
            fragmentTransaction = myFragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, myFragment1, TAG_1);
            fragmentTransaction.commit();
        }
    }
    public static void closeApplication(View view)
    {
        activity.finish();
        System.exit(0);
    }
}
