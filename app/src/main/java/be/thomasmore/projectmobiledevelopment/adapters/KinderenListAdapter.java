package be.thomasmore.projectmobiledevelopment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class KinderenListAdapter extends ArrayAdapter<Kind> {

    private final Context context;
    private final List<Kind> values;
    private View.OnClickListener editListener = null;
    private View.OnClickListener delListener = null;
    private View.OnClickListener startSessieListener = null;

    public KinderenListAdapter(Context context, List<Kind> values, View.OnClickListener editListener, View.OnClickListener delListener, View.OnClickListener startSessieListener){
        super(context, R.layout.adapter_kinderen_list);
        this.context = context;
        this.values = values;
        this.editListener = editListener;
        this.delListener = delListener;
        this.startSessieListener = startSessieListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_kinderen_list, parent, false);

        final TextView textViewNaam = (TextView) rowView.findViewById(R.id.kindNaam);
        final TextView textGroepNr = (TextView) rowView.findViewById(R.id.kindGroep);
        textViewNaam.setText(values.get(position).getNaam());
        textGroepNr.setText("Groep " + values.get(position).getGroepID());

        final ImageView btnEdit = (ImageView) rowView.findViewById(R.id.editKind);
        final ImageView btnDel = (ImageView) rowView.findViewById(R.id.delKind);
        final RelativeLayout rootView = (RelativeLayout) rowView.findViewById(R.id.rootLayout);

        btnEdit.setOnClickListener(editListener);
        btnDel.setOnClickListener(delListener);
        rootView.setOnClickListener(startSessieListener);

        btnEdit.setTag(getItem(position).getId());
        btnDel.setTag(getItem(position).getId());
        rootView.setTag(getItem(position).getId());

        return rowView;
    }

    @Override
    public int getCount() { return values.size(); }
    @Override
    public Kind getItem(int position) { return values.get(position); }
}
