package com.example.alexandr_shvets.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class Second_Fragment_Service extends Service
{
    NotificationManager notificationManager2;
    public PowerManager powerManagerFragment;
    public PowerManager.WakeLock wakeLockFragment;
    Uri alarmSound;
    boolean StopANDPlay;
    int notifyID = 2;
    Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        StopANDPlay=true;
        powerManagerFragment = (PowerManager)context.getSystemService(context.POWER_SERVICE);
        //Даём разрешения на работу в фоновом режиме
        wakeLockFragment = powerManagerFragment.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "ON_WAKE_LOCK");
        //Запускаем wakeLock, проц будет работать при заблокированном экране
        wakeLockFragment.acquire();
        notificationManager2 = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //Запускаем поток для отображения уведомления через определенное время
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(StopANDPlay==true)
                {
                    Log.e("Thread_2", "RUN");
                    try
                    {
                        Log.e("Second_Fragment_Notif", "ON");
                        SecondFragmentNotifications();
                        Log.e("Second_Fragment_Notif", "Play again after 35 sec");
                        Thread.sleep(25000);
                        Log.e("Second_Fragment_Notif", "Play after 10 sec");
                        Thread.sleep(10000);
                        Log.e("Second_Fragment_Notif", "Play now");
                    }
                    catch (InterruptedException e)
                    {
                        Log.e("Thread_2", "ERROR");
                        e.printStackTrace();
                    }
                    Log.e("Thread_2", "END");
                }
                if(StopANDPlay==false)
                {
                    Log.e("Second_Fragment_Notif", "OFF");
                }
            }
        }).start();
        return Service.START_STICKY;
    }
    void SecondFragmentNotifications()
    {
        //Звук уведомления
        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Куда будет выполнен переход при нажатии, передаем через Intent название фрагмента
        Intent notificationIntent2  = new Intent(this, MainActivity.class);
        notificationIntent2.putExtra(MainActivity.FRAGMENT_NAME, "FragmentSecond");
        //Последний параметр для обновления данных, дайт разрешение на передачу. (без него putExtra не работает, передаёт null)
        PendingIntent pendingIntentFragment2 = PendingIntent.getActivity(this, notifyID, notificationIntent2 , PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder ServiceBuilder2 = new NotificationCompat.Builder(this);

        Notification notificationFragment2 = ServiceBuilder2.setContentIntent(pendingIntentFragment2)
                .setSmallIcon(R.drawable.big_notif_ico).setWhen(System.currentTimeMillis())//Помещаем иконку
                .setContentTitle("You create a notification")//Заглавие
                .setSound(alarmSound)//Звук
                .setLights(Color.WHITE, 3000, 4000)//Подсветка
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000})//Вибрация
                .setPriority(2)//Максимальный приоритет
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//Полностью отображаем информацию на экране
                .setContentText("Notification 2").build();//Сообщение уведомления

        //Ставим флаг, чтобы уведомление пропало после нажатия
        notificationFragment2.flags |= Notification.FLAG_AUTO_CANCEL;
        // отправляем
        notificationManager2.notify(notifyID, notificationFragment2);
    }
    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }
    @Override
    public void onDestroy()
    {
        StopANDPlay=false;
        //Валим сервис и уведомление
        notificationManager2.cancel(notifyID);
        stopSelf();
        Log.e("Service SFragment Done:", " onDestroy");
    }
}