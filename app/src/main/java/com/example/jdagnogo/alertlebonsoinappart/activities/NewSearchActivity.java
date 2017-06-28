package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.customlibs.SmoothCheckBox;
import com.example.jdagnogo.alertlebonsoinappart.enums.City;
import com.example.jdagnogo.alertlebonsoinappart.enums.Meuble;
import com.example.jdagnogo.alertlebonsoinappart.enums.SwipeItemEnum;
import com.example.jdagnogo.alertlebonsoinappart.enums.Type;
import com.example.jdagnogo.alertlebonsoinappart.models.*;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.NbRoomSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.RentSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.SurfaceSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateCitiesViewBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateSwipeViewBus;
import com.example.jdagnogo.alertlebonsoinappart.utils.AddCitiesDialog;
import com.example.jdagnogo.alertlebonsoinappart.utils.MinMaxAlertDialog;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NAME_RESEARCH;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;

public class NewSearchActivity extends AppCompatActivity {
    private final String TAG = NewSearchActivity.this.getPackageName();
    @Bind(R.id.rent_min)
    TextView rentMin;
    @Bind(R.id.rent_max)
    TextView rentMax;
    @Bind(R.id.surface_min)
    TextView surfaceMin;
    @Bind(R.id.surface_max)
    TextView surfaceMax;
    @Bind(R.id.nb_room_min)
    TextView nbRoomMin;
    @Bind(R.id.add_cities_text)
    TextView addCitiesText;
    @Bind(R.id.nb_room_max)
    TextView nbRoomMax;
    private NewSearchView newSearchView;
    @Bind(R.id.validate)
    Button validate;
    @Bind(R.id.add_cities_button)
    FloatingActionButton addCitiesButton;
    @Bind(R.id.input_query)
    EditText query;
    @Bind(R.id.input_name)
    EditText name;
    SmoothCheckBox appart, house, meuble, nonMeuble;
    List<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);
        ButterKnife.bind(this);
        newSearchView = new NewSearchView();
        cities = new ArrayList<>();
        GlobalBus.getBus().register(this);
        initLayout();
        initCheckBoxes();

    }

    private void initCheckBoxes() {
        appart = (SmoothCheckBox) findViewById(R.id.appartRealm);
        house = (SmoothCheckBox) findViewById(R.id.house);
        meuble = (SmoothCheckBox) findViewById(R.id.meuble);
        nonMeuble = (SmoothCheckBox) findViewById(R.id.non_meuble);

    }

    @OnClick(R.id.add_cities_button)
    public void setAddCitiesButton() {
        AddCitiesDialog.createDialog(this, cities);
    }


    @OnClick(R.id.validate)
    public void setOnclickValidate() {
        // save
        if (house.isChecked()) {
            if (appart.isChecked()) {
                newSearchView.setType(Type.APPARTEMENT_DEFAULT);
            } else {
                newSearchView.setType(Type.MAISON);
            }
        } else {
            newSearchView.setType(Type.APPARTEMENT);
        }
        if (nonMeuble.isChecked()) {
            if (meuble.isChecked()) {
                newSearchView.setMeuble(Meuble.MEUBLE_DEFAULT);
            } else {
                newSearchView.setMeuble(Meuble.NON_MEUBLE);
            }
        } else {
            newSearchView.setMeuble(Meuble.MEUBLE);
        }
        newSearchView.setName(name.getText().toString());
        newSearchView.setQuery(query.getText().toString());
        Log.d(TAG, "résultat : " + newSearchView);

        Intent intent = new Intent(this, ResultActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(NEW_RESEARCH, createRequestItem());
        args.putString(NAME_RESEARCH,newSearchView.getName());
        intent.putExtras(args);
        startActivity(intent);
    }

    private RequestItems createRequestItem() {
        final RequestItems requestItems = new RequestItems();

        requestItems.setType(newSearchView.getType());
        requestItems.setMeuble(newSearchView.getMeuble());
        requestItems.setCities(newSearchView.getCities());
        Rent rent = new Rent(newSearchView.getRent().getDescriptionMin(), newSearchView.getRent().getDescriptionMax());
        requestItems.setRent(rent);
        NbRoom nbRoom = new NbRoom(newSearchView.getNbRoom().getDescriptionMin(), newSearchView.getNbRoom().getDescriptionMax());
        requestItems.setNbRoom(nbRoom);
        Surface surface = new Surface(String.valueOf(newSearchView.getSurface().getPostitonMin())
                , String.valueOf(newSearchView.getSurface().getPositionMax()));
        requestItems.setSurface(surface);
        requestItems.setKeyWord(newSearchView.getQuery());
        return requestItems;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }

    private void initLayout() {
        LinearLayout rentLayout = (LinearLayout) findViewById(R.id.rent);
        rentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentSwipeItem rentSwipeItem = new RentSwipeItem();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(rentSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.RENT, newSearchView.getRent(),
                        rentSwipeItem.getPostitonMin(), rentSwipeItem.getPositionMax());
                minMaxAlertDialog.createDialog();
            }
        });

        LinearLayout surfaceLayout = (LinearLayout) findViewById(R.id.surface);
        surfaceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurfaceSwipeItem surfaceSwipeItem = new SurfaceSwipeItem();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(surfaceSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.SURFACE, newSearchView.getSurface()
                        , surfaceSwipeItem.getPostitonMin(), surfaceSwipeItem.getPositionMax());
                minMaxAlertDialog.createDialog();
            }
        });

        LinearLayout nbRoomLayout = (LinearLayout) findViewById(R.id.nb_room);
        nbRoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NbRoomSwipeItem nbRoomSwipeItem = new NbRoomSwipeItem();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(nbRoomSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.NB_ROOM, newSearchView.getNbRoom(),
                        nbRoomSwipeItem.getPostitonMin(), nbRoomSwipeItem.getPositionMax());
                minMaxAlertDialog.createDialog();
            }
        });
    }

    @Subscribe
    public void getMessage(UpdateSwipeViewBus updateSwipeViewBus) {
        switch (updateSwipeViewBus.getSwipeItemEnum()) {
            case RENT:
                newSearchView.getRent().setPositionMax(updateSwipeViewBus.getMax());
                newSearchView.getRent().setPostitonMin(updateSwipeViewBus.getMin());
                rentMin.setText(newSearchView.getRent().getDescriptionMinWithUnity());
                rentMax.setText(newSearchView.getRent().getDescriptionMaxWithUnity());

                break;
            case NB_ROOM:
                newSearchView.getNbRoom().setPositionMax(updateSwipeViewBus.getMax());
                newSearchView.getNbRoom().setPostitonMin(updateSwipeViewBus.getMin());
                nbRoomMin.setText(newSearchView.getNbRoom().getDescriptionMinWithUnity());
                nbRoomMax.setText(newSearchView.getNbRoom().getDescriptionMaxWithUnity());
                break;
            default:
                newSearchView.getSurface().setPositionMax(updateSwipeViewBus.getMax());
                newSearchView.getSurface().setPostitonMin(updateSwipeViewBus.getMin());
                surfaceMin.setText(newSearchView.getSurface().getDescriptionMinWithUnity());
                surfaceMax.setText(newSearchView.getSurface().getDescriptionMaxWithUnity());

                break;
        }

    }

    @Subscribe
    public void getCities(UpdateCitiesViewBus updateCitiesViewBus) {
        cities = updateCitiesViewBus.getCities();
        newSearchView.setCities(cities);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cities.size(); i++) {
            sb.append(cities.get(i).getCityName());
            sb.append("\n\n");
        }
        int last = sb.lastIndexOf("\n\n");
        sb.delete(last, sb.length());

        addCitiesText.setText(sb);
    }
}
