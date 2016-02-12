package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cesarmendes.mediasapp.R;
import com.example.cesarmendes.mediasapp.models.Cadeira;

import java.util.ArrayList;

import database.databaseAdapter;
import com.example.cesarmendes.mediasapp.CadeirasAdapterInterface;

/**
 * Created by cesar.mendes on 10/02/2016.
 */
public class CadeirasAdapter extends BaseAdapter{



    private ArrayList<Cadeira> listCadd;
    private Context context;
    private CadeirasAdapterInterface mOnItemClickListener;

    public CadeirasAdapter(Context context, ArrayList<Cadeira> listCadd){
        this.context = context;
        this.listCadd = listCadd;
    }

    public void setOnItemClickListener(CadeirasAdapterInterface onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return listCadd.size();
    }

    @Override
    public Object getItem(int position) {
        return listCadd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Cadeira cadeira = (Cadeira) getItem(position);
        ChairHolder holder = new ChairHolder();
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_cadeira, parent, false);

            holder.textName = (TextView) convertView.findViewById(R.id.nome);
            holder.textCredit = (TextView) convertView.findViewById(R.id.credit);
            holder.textAno = (TextView) convertView.findViewById(R.id.ano);
            holder.textNota = (TextView) convertView.findViewById(R.id.nota);
            holder.del_Button = (Button) convertView.findViewById(R.id.del_button);
            holder.background = (LinearLayout) convertView.findViewById(R.id.row_background);

            convertView.setTag(holder);
        }else {
            holder = (ChairHolder) convertView.getTag();
        }

        holder.textName.setText(cadeira.getNome());
        holder.textAno.setText(cadeira.getAno().toString());
        holder.textCredit.setText(cadeira.getCredito().toString());
        holder.textNota.setText(cadeira.getNota().toString());
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(cadeira,v);
                }
            }
        });
        holder.del_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAdapter db =  new databaseAdapter(context);
                db.openBD();
                db.delete_cadeira(cadeira.getId());
                setItems(db.getCadeiras());
                db.close();
            }
        });

        return convertView;
    }

    private void setItems(ArrayList<Cadeira> cadeiras) {
        listCadd = cadeiras;
        notifyDataSetChanged();
    }

    private class ChairHolder{
        private TextView textName;
        private TextView textCredit;
        private TextView textAno;
        private TextView textNota;
        private Button del_Button;
        private LinearLayout background;
    }
}
