package com.bod.juancarlos.basedatossencilla;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import com.bod.juancarlos.basedatossencilla.RecyclerViewStuff.AdapterTareas;
import com.bod.juancarlos.basedatossencilla.SQLiteStuff.TareaOpenHelper2;
import java.util.ArrayList;

/*Elementos tales como el nombre de las tareas, las estrellas de la dificultad, */
/*el array con las tareas, el recycler view, la barra de progreso, entre otras*/
public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ImageView imagen;
    EditText nombreTarea;
    RatingBar estrellas;
    ArrayList<Tarea> listaTareas;
    RecyclerView mRecycler;
    Uri imageUri;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinner);
        imagen = findViewById(R.id.profile_image);
        estrellas = findViewById(R.id.ratingBar);
        nombreTarea = findViewById(R.id.nombreTarea);
        progressBar = findViewById(R.id.circularProgressbar);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        llenarRecycler();


        progressBar.setProgress(verProgreso());

    }
    /**/
    private int verProgreso() {
        TareaOpenHelper2 conn = new TareaOpenHelper2(this,"bd_tareas",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tareas2 WHERE completado = 1",null);
        int progreso = 0;
        while (cursor.moveToNext()) {
            int aux = cursor.getInt(6);
            progreso= progreso+aux;
        }
        return progreso;
        }

    /*Se utiliza para llenar la base de datos con los elementos que componen las tareas como el nombre, la fecha, la hora,*/
    /*la categoria, dificultad y se le da una posicion dentro del array de tareas que se mostrara en el recycler view*/

    private void llenarRecycler() {
        TareaOpenHelper2 conn = new TareaOpenHelper2(this,"bd_tareas",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();

        Tarea tarea = null;
        listaTareas = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM tareas2 WHERE completado = 0",null);

        while (cursor.moveToNext()){
            tarea = new Tarea();
            tarea.setNombre(cursor.getString(1));
            tarea.setFecha(cursor.getString(2));
            tarea.setHora(cursor.getString(3));
            tarea.setCategoria(cursor.getString(4));
            switch (tarea.getCategoria()){
                case "Ejercicio": tarea.setFoto(R.drawable.icono_workout_small); break;
                case "Estudio": tarea.setFoto(R.drawable.icono_estudios_small); break;
                case "Salud": tarea.setFoto(R.drawable.icono_salud_small); break;
                case "Trabajo": tarea.setFoto(R.drawable.icono_trabajo_small); break;
            }
            tarea.setDificultad(cursor.getInt(6));
            listaTareas.add(tarea);
        }

        AdapterTareas adapter = new AdapterTareas(listaTareas);
        mRecycler.setAdapter(adapter);

    }

    /*Se utiliza para agregar la tarea a la activity principal*/
    public void agregarTarea(View view) {
        Intent i = new Intent(MainActivity.this,AgregarTarea.class);
        startActivity(i);
    }

    /*Se utiliza para añadir una foto al estilo de redes sociales a la interfaz principal de la aplicacion*/
    public void selectFoto(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i,100);

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 100 && resultCode == RESULT_OK){
            imageUri = data.getData();
            imagen.setImageURI(imageUri);
        }
    }

}
