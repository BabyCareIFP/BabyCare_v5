package com.example.babycarev1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class VerCuidadoresActivity extends AppCompatActivity {

    //TODO estrellas valoracion
    //TODO PerfilCuidadorActivity, haciendolo clickable con listview, con un atributo que desconozca o con una imagen clicable(true)
    //TODO posiciones de elementos, y meter formula de la edad?

    protected ScrollView scroll;
    protected LinearLayout layout;
    protected DataBaseSQL db;
    private ArrayList<Cuidador> listaCuidadores = new ArrayList<Cuidador>();
    private String nombre, apellidos, telefono, fechaNacimiento, sexo, experiencia, disponibilidad, puntuacion, resenias;
    private String[] datosCuidador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cuidadores);

        db = new DataBaseSQL(this);
        scroll = findViewById(R.id.scroll1_test);
        layout = findViewById(R.id.layout1_test);

        for (String cuidadorTexto :
                db.consultarCuidadores()) {
            datosCuidador = cuidadorTexto.split(".-");

            nombre = datosCuidador[0];
            sexo = datosCuidador[1];
            experiencia = datosCuidador[2];
            disponibilidad = datosCuidador[3];
            puntuacion = datosCuidador[4];

            listaCuidadores.add(new Cuidador(nombre, apellidos, telefono, fechaNacimiento, sexo, experiencia, disponibilidad, puntuacion, resenias));
        }

        for (Cuidador cuidador :  listaCuidadores)
        {
            View previewCuidador = LayoutInflater.from(this).inflate(R.layout.item_cuidador, scroll, false);

            // Obtener referencias a los elementos de la vista
            ImageView imagen1 = (ImageView) previewCuidador.findViewById(R.id.image1_cuidador);
            TextView label1 = previewCuidador.findViewById(R.id.label1_cuidador);
            TextView label2 = previewCuidador.findViewById(R.id.label2_cuidador);
            TextView label3 = previewCuidador.findViewById(R.id.label3_cuidador);
            LinearLayout layoutEstrellas = previewCuidador.findViewById(R.id.layout3_cuidador);

            int numEstrellas = Integer.parseInt(cuidador.getPuntuacion());
            for (int i=0; i<numEstrellas; i++)
            {
                ImageView imagenEstrella = new ImageView(this);
                imagenEstrella.setImageResource(R.drawable.android_star);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(64, 64);
                imagenEstrella.setLayoutParams(layoutParams);

                layoutEstrellas.addView(imagenEstrella);
            }

            imagen1.setImageResource(R.drawable.default_avatar_icon);
            label1.setText(cuidador.getNombre());
            label2.setText(cuidador.getExperiencia());
            label3.setText(cuidador.getDisponibilidad());

            layout.addView(previewCuidador);

        }
    }
}