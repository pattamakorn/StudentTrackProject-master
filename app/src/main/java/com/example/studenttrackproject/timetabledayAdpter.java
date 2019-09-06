package com.example.studenttrackproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class timetabledayAdpter extends RecyclerView.Adapter<timetabledayAdpter.MyViewHolder> {
    private View itemView;
    private Context context;
    private List<timetableday> Listtimetable;

    public timetabledayAdpter(Context context,List<timetableday> listtimetable){
        this.context = context;
        Listtimetable = listtimetable;
    }
    @NonNull
    @Override
    public timetabledayAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timetable_item,parent,false);
        context = parent.getContext();
        return new timetabledayAdpter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final timetableday td = Listtimetable.get(position);
        holder.idsubject.setText(td.getIdsubject());
        holder.subjectname.setText(td.getSubjectname());
        holder.teachsubject.setText(td.getTeachsubject());
        holder.classroom1.setText(td.getClassroom());
        holder.timestart.setText(td.getStarttime());
        holder.timestop.setText(td.getStoptime());

    }

    @Override
    public int getItemCount() {return Listtimetable.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView idsubject,subjectname,teachsubject,
                classroom1,timestart,timestop;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                idsubject = itemView.findViewById(R.id.idsubject);
                subjectname = itemView.findViewById(R.id.namesubject);
                teachsubject = itemView.findViewById(R.id.teachsubject);
                classroom1 = itemView.findViewById(R.id.classroomre);
                timestart = itemView.findViewById(R.id.timeStart1);
                timestop = itemView.findViewById(R.id.timeStop1);
                //new TimeTable();
            }
    }
}
