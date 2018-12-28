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
import be.thomasmore.projectmobiledevelopment.dataservices.SessieDataService;
import be.thomasmore.projectmobiledevelopment.models.Kind;

public class OverzichtKinderenListAdapter extends ArrayAdapter<Kind>{

    private final Context context;
    private final List<Kind> values;
    private View.OnClickListener toonSessieListener = null;
    private View.OnClickListener geenSessieListener = null;
    private SessieDataService sessieDataService = new SessieDataService();

    public OverzichtKinderenListAdapter(Context context, List<Kind> values, View.OnClickListener toonSessieListener, View.OnClickListener geenSessieListener){
        super(context, R.layout.adapter_kinderen_list);
        this.context = context;
        this.values = values;
        this.toonSessieListener = toonSessieListener;
        this.geenSessieListener = geenSessieListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_overzicht_kinderen_list, parent, false);

        final TextView textViewNaam = (TextView) rowView.findViewById(R.id.kindNaam);
        final TextView textGroepNr = (TextView) rowView.findViewById(R.id.kindGroep);
        final TextView textAantalSessies = (TextView) rowView.findViewById(R.id.kindAantalSessies);

        long aantalSessies = sessieDataService.countSessieWhereKindId(getItem(position).getId());

        textViewNaam.setText(values.get(position).getNaam());
        textGroepNr.setText("Groep " + values.get(position).getGroepID());
        textAantalSessies.setText("Sessies: " + aantalSessies);

        final RelativeLayout rootView = (RelativeLayout) rowView.findViewById(R.id.rootLayout);
        if(aantalSessies > 0){
            rootView.setOnClickListener(toonSessieListener);
        } else{
            rootView.setOnClickListener(geenSessieListener);
        }


        rootView.setTag(getItem(position).getId());
        return rowView;
    }

    @Override
    public int getCount() { return values.size(); }
    @Override
    public Kind getItem(int position) { return values.get(position); }

}
