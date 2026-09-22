package com.nova.vpn;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.net.VpnService;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    Button button; TextView status;
    int dp(float v){return (int)(v*getResources().getDisplayMetrics().density+0.5f);}
    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER); box.setPadding(dp(28),dp(40),dp(28),dp(28));
        box.setBackgroundColor(Color.rgb(247,245,255));
        TextView logo=new TextView(this); logo.setText("✦  NOVA"); logo.setTextSize(28); logo.setTextColor(Color.rgb(75,45,150));
        logo.setGravity(Gravity.CENTER); box.addView(logo,new LinearLayout.LayoutParams(-1,dp(70)));
        TextView title=new TextView(this); title.setText("Nova VPN"); title.setTextSize(38); title.setTextColor(Color.BLACK); title.setGravity(Gravity.CENTER);
        box.addView(title,new LinearLayout.LayoutParams(-1,dp(70)));
        status=new TextView(this); status.setText("Готов к подключению"); status.setTextSize(17); status.setGravity(Gravity.CENTER);
        box.addView(status,new LinearLayout.LayoutParams(-1,dp(55)));
        button=new Button(this); button.setText("Подключить"); button.setTextSize(18);
        LinearLayout.LayoutParams bp=new LinearLayout.LayoutParams(-1,dp(60)); bp.setMargins(0,dp(20),0,dp(20)); box.addView(button,bp);
        TextView note=new TextView(this); note.setText("Бесплатная тестовая версия\nСервер ещё не подключён");
        note.setGravity(Gravity.CENTER); note.setTextColor(Color.DKGRAY); box.addView(note,new LinearLayout.LayoutParams(-1,dp(70)));
        setContentView(box);
        button.setOnClickListener(v -> requestVpn());
    }
    void requestVpn(){
        Intent i=VpnService.prepare(this);
        if(i!=null) startActivityForResult(i,10); else startVpn();
    }
    void startVpn(){ startService(new Intent(this,NovaVpnService.class)); status.setText("Тестовый VPN запущен"); button.setText("Отключить"); }
    @Override protected void onActivityResult(int r,int c,Intent d){ super.onActivityResult(r,c,d); if(r==10 && c==RESULT_OK) startVpn(); }
}