package com.example.jdagnogo.alertlebonsoinappart.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.adapter.CityArrayListAdapter;
import com.example.jdagnogo.alertlebonsoinappart.customlibs.SmoothCheckBox;
import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateCitiesViewBus;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class AddCitiesDialog {
    private static AutoCompleteTextView city;
    private static City citySelected;

    public static void createDialog(final Activity activity, List<City> cityAlreadychecked) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_cities_dialog, null);
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

        // auto complete
        city = (AutoCompleteTextView) dialogView.findViewById(R.id.city_autocomplete_view);
        CityArrayListAdapter adapter = new CityArrayListAdapter
                (activity, android.R.layout.simple_list_item_1, getAllCities());
        city.setAdapter(adapter);

        //validation
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidCity(city.getText().toString())) {
                    UpdateCitiesViewBus updateCitiesViewBus = new UpdateCitiesViewBus(citySelected);
                    GlobalBus.getBus().post(updateCitiesViewBus);
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(activity, "La ville choisie n'est pas reconnue",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        ImageView info = (ImageView)dialogView.findViewById(R.id.info) ;
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                dialogBuilder.setTitle("Liste des villes disponible");
                final CharSequence[] cities = getAllCities().toArray(new String[getAllCities().size()]);
                dialogBuilder.setItems(cities, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                    }
                });
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });
        alertDialog.show();
    }

    private static List<String> getAllCities() {
        List<String> data = new ArrayList<>();
        List<City> citiesAsCity =
                new ArrayList<City>(EnumSet.allOf(City.class));
        for (int i = 0; i < citiesAsCity.size(); i++) {
            data.add(String.format("%s( %s ) ", citiesAsCity.get(i).getCode(), citiesAsCity.get(i).getCodePostal()));
        }
        return data;
    }

    private static boolean isValidCity(String cityAsString) {
        String[] parts = cityAsString.split("\\(");
        if (parts.length<2){
            return false;
        }
        String cityName = parts[0];
        cityName = cityName.substring(0, cityName.length() - 1).toUpperCase();
        String postalCode = parts[1].split(" ")[1];
        try {
            citySelected = City.valueOf(String.format("%s_%s",cityName,postalCode));
        } catch(IllegalArgumentException e) {
            System.out.println("Caught an IllegalArgumentException..." + e.getMessage());
            return  false;
        }
        return true;
    }
}
