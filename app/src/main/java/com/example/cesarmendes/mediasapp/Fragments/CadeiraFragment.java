package com.example.cesarmendes.mediasapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cesarmendes.mediasapp.R;
import com.example.cesarmendes.mediasapp.models.Cadeira;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.databaseAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CadeiraFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadeiraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadeiraFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CADEIRA = "cadeira";


    // TODO: Rename and change types of parameters
    private Cadeira cadeira;

    private OnFragmentInteractionListener mListener;

    private Spinner spinner;
    private EditText editName, editCredit,editValue;
    private Button saveButton;

    public CadeiraFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CadeiraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadeiraFragment newInstance(Cadeira cadeira) {
        CadeiraFragment fragment = new CadeiraFragment();
        Bundle args = new Bundle();
        args.putSerializable(CADEIRA, cadeira);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cadeira = (Cadeira) getArguments().getSerializable(CADEIRA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadeira, container, false);

        editName = (EditText) view.findViewById(R.id.nome_cadeira);
        editCredit = (EditText) view.findViewById(R.id.creditos_cadeira);
        editValue = (EditText) view.findViewById(R.id.valores);

        saveButton = (Button) view.findViewById(R.id.saveButton);

        spinner = (Spinner) view.findViewById(R.id.spinner_id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.anos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(cadeira != null){
            editName.setText(cadeira.getNome());
            editCredit.setText(cadeira.getCredito().toString());
            editValue.setText(cadeira.getNota().toString());
        }

        saveButton.setOnClickListener(this);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveButton:
                insertOrUpdate(v);
                break;
        }
    }

    private void insertOrUpdate(View v) {
        boolean valid;
        if(cadeira == null) {
            if (valid = validate())
                valid = insertData();
            editCredit.setText("");
            editName.setText("");
            editValue.setText("");
            mListener.onSaveButtonClick(v, valid);
        }else {
            if (valid = validate())
                valid = updateData();
            mListener.onSaveButtonUpdateClick(v, valid);

        }

    }

    private boolean updateData() {
        databaseAdapter dbAdapt = new databaseAdapter(getActivity());
        dbAdapt.openBD();
        cadeira.setNome(editName.getText().toString());
        cadeira.setCredito(Double.parseDouble(editCredit.getText().toString()));
        cadeira.setNota(Integer.parseInt(editValue.getText().toString()));
        cadeira.setAno(Integer.parseInt(spinner.getSelectedItem().toString()));
        boolean valid = dbAdapt.update(cadeira);
        dbAdapt.close();
        return valid;
    }

    private boolean insertData() {
        databaseAdapter dbAdapt = new databaseAdapter(getActivity());
        dbAdapt.openBD();
        String textName = editName.getText().toString();
        Double textCredits = Double.parseDouble(editCredit.getText().toString());
        Integer textValue = Integer.parseInt(editValue.getText().toString());
        Integer textAno = Integer.parseInt(spinner.getSelectedItem().toString());
        boolean valid = dbAdapt.insert(textName, textCredits, textAno, textValue);
        dbAdapt.close();
        return valid;
    }

    private boolean validate() {
        String textName = editName.getText().toString();
        String textCredits = editCredit.getText().toString();
        String textValue = editValue.getText().toString();
        Boolean valid = true;

        if(textName == null || textCredits == null || textValue == null
                || textName.equals("") || textCredits.equals("") || textValue.equals(""))
            return false;

        if(!textValue.matches("^([1][0-9])?$|^20$") || Double.parseDouble(textCredits) > 60.0)
            valid = false;

        if(Double.parseDouble(textCredits)<=0.0)
            valid = false;

        return valid;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onSaveButtonClick(View view ,Boolean bool);
        void onFragmentInteraction(View view);

        void onSaveButtonUpdateClick(View v, boolean valid);
    }
}
