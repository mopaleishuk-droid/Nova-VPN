package com.nova.vpn;

import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

public class NovaVpnService extends VpnService {
    private ParcelFileDescriptor iface;
    @Override public int onStartCommand(android.content.Intent intent,int flags,int startId){
        try {
            Builder b=new Builder();
            b.setSession("Nova VPN");
            b.addAddress("10.8.0.2",32);
            b.addRoute("10.8.0.0",24);
            iface=b.establish();
        } catch(Exception e){}
        return START_STICKY;
    }
    @Override public void onDestroy(){ try{if(iface!=null)iface.close();}catch(IOException e){} super.onDestroy(); }
}