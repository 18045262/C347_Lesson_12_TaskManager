package webservices.rp.edu.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    Context context;
    ArrayList<Task> taskLists;
    int resource;
    TextView tvName, tvDesc, tvSecond;

    public TaskAdapter(Context context, int resource, ArrayList<Task> taskLists) {
        super(context, resource, taskLists);
        this.context = context;
        this.taskLists = taskLists;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        tvName = rowView.findViewById(R.id.tvName);
        tvDesc = rowView.findViewById(R.id.tvDesc);

        Task task = taskLists.get(position);

        tvName.setText(task.getId() + " " + task.getName());
        tvDesc.setText(task.getDescription());

        return rowView;
    }

}
