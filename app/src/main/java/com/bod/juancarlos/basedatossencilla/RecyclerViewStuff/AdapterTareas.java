package com.bod.juancarlos.basedatossencilla.RecyclerViewStuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bod.juancarlos.basedatossencilla.AgregarTarea;
import com.bod.juancarlos.basedatossencilla.MainActivity;
import com.bod.juancarlos.basedatossencilla.R;
import com.bod.juancarlos.basedatossencilla.SQLiteStuff.TareaOpenHelper2;
import com.bod.juancarlos.basedatossencilla.Tarea;

import java.util.ArrayList;



public class AdapterTareas extends RecyclerView.Adapter<AdapterTareas.ViewHolderTareas>{

    ArrayList<Tarea> listaTareas;

    /*Permite llenar el arreglo de tareas con los elementos que componen la misma*/
    public AdapterTareas(ArrayList<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public ViewHolderTareas onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_list,null,false);

        return new ViewHolderTareas(view);
    }
    /*Permite relacionar los elementos del layout con los objetos de las tarea en*/
    /*cuanto a las lista de cosas por hacer*/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolderTareas holder, int position) {
        holder.nombreTareaHolder.setText(listaTareas.get(position).getNombre().toString());
        holder.fecha.setText(listaTareas.get(position).getFecha().toString());
        holder.hora.setText(listaTareas.get(position).getHora().toString());
        holder.fotoHolder.setImageResource(listaTareas.get(position).getFoto());
        holder.dificultad.setRating(listaTareas.get(position).getDificultad());
        holder.setOnClickListeners();

    }


    @Override
    public int getItemCount() { return listaTareas.size(); }
    /*Permite cambiar el estado de completado de 0 a 1 para los que posteriormente se */
    /*mostrara un mensaje de felicitaciones*/

    public class ViewHolderTareas extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fecha,nombreTareaHolder,hora;
        ImageButton completado;
        ImageView fotoHolder;
        RatingBar dificultad;
        Context context;

        public ViewHolderTareas(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            fecha = itemView.findViewById(R.id.fechaCreacion);
            nombreTareaHolder = itemView.findViewById(R.id.titulo);
            hora = itemView.findViewById(R.id.horaHolder);
            fotoHolder = itemView.findViewById(R.id.foto);
            completado = itemView.findViewById(R.id.listo);
            dificultad = itemView.findViewById(R.id.dificultad);
          }

          void setOnClickListeners(){
            completado.setOnClickListener(this);
          }

        /*Verifica si una tarea se a marcado como completado*/
        /*Donde en caso de se halla completado una dira un mensaje*/
        /*de "completado" y en uncion de la dificultad que se le*/
        /*halla colocado a la tarea se sumara a la barra de progreso el*/
        /*avance y se mostrara un mensaje de felicitaciones*/
        @Override
        public void onClick(View view) {
            TareaOpenHelper2 conn = new TareaOpenHelper2(context,"bd_tareas",null,1);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("completado",1);

            db.update("tareas2",values,"nombre =?", new String[]{nombreTareaHolder.getText().toString()});
            Toast.makeText(context,"Felicidades! Has ganado "+dificultad.getRating()+" puntos",Toast.LENGTH_LONG).show();
            removeAt(getAdapterPosition());


        }
        /*Se utiliza para remover la tarea una vez se halla completado*/
        /*la misma.*/
        public void removeAt(int position) {
            listaTareas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, listaTareas.size());
        }
      }
    }

