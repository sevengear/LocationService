package nunezma.es.nunezma_u8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Miguel Á. Núñez on 21/11/2017.
 */

public class ReceptorArranque extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ServicioCirculoPolar.class));
    }


}
