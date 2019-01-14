package com.bod.juancarlos.basedatossencilla;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bod.juancarlos.basedatossencilla.RecyclerViewStuff.AdapterTareas;
import com.bod.juancarlos.basedatossencilla.SQLiteStuff.TareaOpenHelper2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AgregarTarea extends AppCompatActivity {
    TextView hora,dia;
    Spinner spinner;
    ImageView imagen;
    EditText nombreTarea;
    RatingBar estrellas;
    Calendar c;

    /*Funcion utilizada en los aspectos de creacion de las tareas en la segunda activity, en la cual se verifca*/
    /*la categoria a la podria pertenecer una tarea, asi como la dificultad que posee la misma*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);

        spinner = findViewById(R.id.spinner);
        imagen = findViewById(R.id.imagen);
        estrellas = findViewById(R.id.ratingBar);
        nombreTarea = findViewById(R.id.nombreTarea);
        hora = findViewById(R.id.horaActivity);
        dia = findViewById(R.id.diaActivity);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        imagen.setImageResource(R.drawable.icono_workout);
                        break;
                    case 2:
                        imagen.setImageResource(R.drawable.icono_trabajo);
                        break;
                    case 3:
                        imagen.setImageResource(R.drawable.icono_salud);
                        break;
                    case 4:
                        imagen.setImageResource(R.drawable.icono_estudios);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    /*Se utiliza para registar una tarea en la base de datos en funcion de los elementos que posee la misma*/
    /*como nombre, fecha, hora, categoria, etc.*/
    /*Luego se insertan enla base de datos mediante db.insert*/
    public void registrarTarea(View view) {
        TareaOpenHelper2 conn = new TareaOpenHelper2(this,"bd_tareas",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",nombreTarea.getText().toString());
        contentValues.put("fecha",dia.getText().toString());
        contentValues.put("hora",hora.getText().toString());
        contentValues.put("completado",0);
        contentValues.put("foto", imagen.getDrawable().toString());
        contentValues.put("categoria",spinner.getSelectedItem().toString());
        contentValues.put("dificultad",estrellas.getRating());

        db.insert("tareas2",null,contentValues);

        Toast.makeText(getApplicationContext(),"bien",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(AgregarTarea.this,MainActivity.class);
        startActivity(i);
    }
    /*Se utiliza para borrar los elementos o tareas de la aplicacion*/
    public void borrarbd(View view) {
        TareaOpenHelper2 conn = new TareaOpenHelper2(this,"bd_tareas",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.delete("tareas2",null,null);
        db.close();
    }
    /*Permite seleccionar la hora a la que se desea completar la tarea*/
    public void selectHora(View view) {
        c= Calendar.getInstance();
        int mHora = c.get(Calendar.HOUR_OF_DAY);
        int mMin = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                hora.setText(hourOfDay + ":" + minute);
            }
        },mHora,mMin,false);
        timePickerDialog.show();
    }
    /*Permite seleccionar la fecha en la que se desea completar la tarea*/
    public void selectDia(View view) {
        c= Calendar.getInstance();
        int mDia = c.get(Calendar.HOUR_OF_DAY);
        int mMonth = c.get(Calendar.MINUTE);
        int mYear = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dia.setText(day + "/" + (month+1) +"/"+year);
            }
        },mYear,mMonth,mDia);
           datePickerDialog.show();
    }
}
