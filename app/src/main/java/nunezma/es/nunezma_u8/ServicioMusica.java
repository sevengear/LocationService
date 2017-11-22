package nunezma.es.nunezma_u8;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Miguel Á. Núñez on 20/11/2017.
 */

public class ServicioMusica extends Service {

    public static final int ID_NOTIFICACION_CREAR = 1;
    MediaPlayer reproductor;

    @Override public void onCreate() {
        Toast.makeText(this,"Servicio creado", Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);
    }
    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
        Notification.Builder notific = new Notification.Builder(this) .setContentTitle("Creando Servicio de Música") .setSmallIcon(R.mipmap.ic_launcher) .setContentText("información adicional");
        PendingIntent intencionPendiente = PendingIntent.getActivity( this, 0, new Intent(this, ServicioMusicaActivity.class), 0);
        notific.setContentIntent(intencionPendiente);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID_NOTIFICACION_CREAR, notific.build());
        Toast.makeText(this,"Servicio arrancado "+ idArranque, Toast.LENGTH_SHORT).show();
        reproductor.start();
        return START_STICKY;
    }
    @Override public void onDestroy() {
        Toast.makeText(this,"Servicio detenido", Toast.LENGTH_SHORT).show();
        reproductor.stop();
        reproductor.release();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICACION_CREAR);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
