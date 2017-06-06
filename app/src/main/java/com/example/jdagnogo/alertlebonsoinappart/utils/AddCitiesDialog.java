package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.customlibs.SmoothCheckBox;
import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateCitiesViewBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateSwipeViewBus;

import java.util.ArrayList;
import java.util.List;

public class AddCitiesDialog {
    private Activity activity;
    static SmoothCheckBox bdxCentre, talence, merignac, pessac;

    public static void createDialog(final Activity activity, List<City> citiesAlreadychecked) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_cities_dialog, null);
        initCheckBoxes(dialogView);
        checkCitiesAlreadySelected(citiesAlreadychecked);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        Button cancel = (Button) dialogView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        Button ok = (Button) dialogView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bdxCentre.isChecked() && !talence.isChecked() && !pessac.isChecked() && !merignac.isChecked()) {
                    Toast.makeText(activity, "Veuillez choisir au moins une ville",
                            Toast.LENGTH_LONG).show();
                } else {
                    List<City> cities = new ArrayList<City>();
                    if (bdxCentre.isChecked()) {
                        cities.add(City.BORDEAUX_CENTRE);
                    }
                    if (talence.isChecked()) {
                        cities.add(City.TALENCE);
                    }
                    if (pessac.isChecked()) {
                        cities.add(City.PESSAC);
                    }
                    if (merignac.isChecked()) {
                        cities.add(City.MERIGNAC);
                    }
                    UpdateCitiesViewBus updateCitiesViewBus = new UpdateCitiesViewBus(cities);
                    GlobalBus.getBus().post(updateCitiesViewBus);
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }

    private static void checkCitiesAlreadySelected(List<City> citiesAlreadychecked) {
        for (int i = 0; i < citiesAlreadychecked.size(); i++) {
            switch (citiesAlreadychecked.get(i)) {
                case BORDEAUX_CENTRE:
                    bdxCentre.setChecked(true);
                    break;
                case TALENCE:
                    talence.setChecked(true);
                    break;
                case PESSAC:
                    pessac.setChecked(true);
                    break;
                case MERIGNAC:
                    merignac.setChecked(true);
                    break;
            }
        }
    }

    private static void initCheckBoxes(View view) {
        bdxCentre = (SmoothCheckBox) view.findViewById(R.id.bdx_centre);
        talence = (SmoothCheckBox) view.findViewById(R.id.talence);
        merignac = (SmoothCheckBox) view.findViewById(R.id.merignac);
        pessac = (SmoothCheckBox) view.findViewById(R.id.pessac);

    }
}
