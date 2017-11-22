package nunezma.es.nunezma_u8;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Miguel Á. Núñez on 21/11/2017.
 */

public class ServicioCirculoPolar extends Service implements LocationListener {

    private static final int ID_NOTIFICACION_LATITUD = 2;
    private LatLng pos;
    private double latitude;
    private double longitude;
    private LocationManager locationManager;
    private static final String[] A = { "n/d", "preciso", "impreciso" };

    @Override
    public void onCreate() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        getLocation(this,this);
        Log.d("VALOR LOCALIZACION", "onCreate -> " + latitude);
    }

    @Override
    public int onStartCommand(Intent intencion, int flags, int idArran) {
        Toast.makeText(this, "Servicio Localizacion arrancado " + idArran, Toast.LENGTH_SHORT).show();
        getLocation(this, this);
        pos = new LatLng(latitude, longitude);
        Log.d("VALOR LOCALIZACION", "onStartCommand -> " + latitude);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICACION_LATITUD);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intencion) {
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d("VALOR LOCALIZACION", "onLocationChanged -> " + location.getLatitude());
        pos = new LatLng(location.getLatitude(), location.getLongitude());
        if (pos.latitude > 66.55) {
            Notification.Builder notific = new Notification.Builder(ServicioCirculoPolar.this)
                    .setContentTitle("Se encuentra dentro del Circulo Polar")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("información adicional");
            PendingIntent intencionPendiente = PendingIntent.getActivity(ServicioCirculoPolar.this, 0,
                    new Intent(ServicioCirculoPolar.this, MainActivity.class), 0);
            notific.setContentIntent(intencionPendiente);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(ID_NOTIFICACION_LATITUD, notific.build());
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @SuppressLint("MissingPermission")
    public void getLocation(Context context, LocationListener listener) {
        Criteria criteria = new Criteria();
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Log.d("VALOR proveedor", bestProvider);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        } else {
            Log.d("VALOR localizacion NULL","Localización desconocida");
            locationManager.requestLocationUpdates(bestProvider, 1000, 0, listener);
        }
    }
}
