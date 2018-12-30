package be.thomasmore.projectmobiledevelopment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.projectmobiledevelopment.R;
import be.thomasmore.projectmobiledevelopment.models.Woord;

public class OverzichtOefeningAdapter extends ArrayAdapter<Woord> {
    private final Context context;
    private final List<Woord> values;
    private final List<String> scoreValues;

    public OverzichtOefeningAdapter(Context context, List<Woord> values, List<String> scoreValues){
        super(context, R.layout.adapter_kinderen_list);
        this.context = context;
        this.values = values;
        this.scoreValues = scoreValues;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_overzicht_oefening, parent, false);

        TextView textViewWoord = (TextView) rowView.findViewById(R.id.adapter_overzicht_oef_woord);
        TextView textViewScore = (TextView) rowView.findViewById(R.id.adapter_overzicht_oef_score);
        textViewWoord.setText(values.get(position).getWoord());
        textViewScore.setText(scoreValues.get(position));

        return rowView;
    }

    @Override
    public int getCount() { return values.size(); }
    @Override
    public Woord getItem(int position) { return values.get(position); }
}
