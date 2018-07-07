package com.example.administrator.mymapapp;


        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;

public class PanelAdapter extends ArrayAdapter<PanelProduct> {
    ArrayList<PanelProduct> list;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    Context context;

    private static class ViewHolder {
        TextView tvname, tvsub, tvsem, tvph, tvroll, tvcost;
        ImageView imgPanel;
    }

    public PanelAdapter(Context context, ArrayList<PanelProduct> resource) {
        super(context, R.layout.list_item, resource);
        this.context = context;
       list = resource;
 }

 public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.imgPanel = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.tvname = (TextView) convertView.findViewById(R.id.txtTitle);
            viewHolder.tvcost = (TextView) convertView.findViewById(R.id.txtDescription);
            //viewHolder.tvroll = (TextView) convertView.findViewById(R.id.tvroll);
            //viewHolder.tvph = (TextView) convertView.findViewById(R.id.tvph);
            //viewHolder.tvsub = (TextView) convertView.findViewById(R.id.tvsub);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        PanelProduct s1=list.get(position);


        viewHolder.tvcost.setText(s1.getCost());
        viewHolder.tvname.setText(s1.getName());
        String p=s1.getName();

        switch(p)
        {
            case "Thin-Film Solar Panels":
                viewHolder.imgPanel.setImageResource(R.drawable.thin);

                break;
            case "Monocrystalline":

                viewHolder.imgPanel.setImageResource(R.drawable.monocrystalline_solar_panel);
                break;

            case "Polycrystalline":

                viewHolder.imgPanel.setImageResource(R.drawable.polyrycstalline);
                break;

            case "Crystalline Silicon":
                viewHolder.imgPanel.setImageResource(R.drawable.monocrystalline_solar_panel);
                break;

                case "Amorphous Silicon (a-Si)":
                    viewHolder.imgPanel.setImageResource(R.drawable.solar_pv_module);

                    break;
            case "Cadmium Telluride (CdTe)":
                viewHolder.imgPanel.setImageResource(R.drawable.polyrycstalline);
                break;

            case "Building-Integrated Photovoltaics":
                viewHolder.imgPanel.setImageResource(R.drawable.bulding);
                break;
            case "Copper Indium Gallium Selenide (CIS/CIGS)":
                viewHolder.imgPanel.setImageResource(R.drawable.solar_power_panel);
                break;
        }



       // viewHolder.tvsem.setText(s1.getSem());
        //viewHolder.tvcost.setText(""+s1.getMarks());
        //viewHolder.tvroll.setText(s1.getRoll());
        //viewHolder.tvph.setText(s1.getPh());

        // Return the completed view to render on screen
        return convertView;

    }


}
