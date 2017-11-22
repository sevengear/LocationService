package nunezma.es.nunezma_u8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Miguel Á. Núñez on 21/11/2017.
 */

public class ServicioMusicaActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_musica);
        Button arrancar = findViewById(R.id.boton_arrancar);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startService(new Intent(ServicioMusicaActivity.this, ServicioMusica.class));
            }
        });
        Button detener = findViewById(R.id.boton_detener);
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                stopService(new Intent(ServicioMusicaActivity.this, ServicioMusica.class));
            }
        });
        Button mapa = findViewById(R.id.ir_mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServicioMusicaActivity.this, MainActivity.class));
            }
        });
    }
}
