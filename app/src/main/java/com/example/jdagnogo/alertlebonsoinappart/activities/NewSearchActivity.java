package com.example.jdagnogo.alertlebonsoinappart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.TransitionInflater;
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
import com.example.jdagnogo.alertlebonsoinappart.models.NbRoom;
import com.example.jdagnogo.alertlebonsoinappart.models.NewSearchView;
import com.example.jdagnogo.alertlebonsoinappart.models.Rent;
import com.example.jdagnogo.alertlebonsoinappart.models.RequestItems;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;
import com.example.jdagnogo.alertlebonsoinappart.models.Surface;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.NbRoomSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.RentSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.SurfaceSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateCitiesViewBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.UpdateSwipeViewBus;
import com.example.jdagnogo.alertlebonsoinappart.utils.AddCitiesDialog;
import com.example.jdagnogo.alertlebonsoinappart.utils.MinMaxAlertDialog;
import com.example.jdagnogo.alertlebonsoinappart.utils.TransitionUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NAME_RESEARCH;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;
import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.RESEARCH;

public class NewSearchActivity extends AppCompatActivity {
    private final String TAG = "NewSearchActivity";
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
    SmoothCheckBox appart, house, meuble, nonMeuble;
    List<City> cities;
    private Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionUtils.doTranstion(getWindow());

        setContentView(R.layout.activity_new_search);
        ButterKnife.bind(this);
        GlobalBus.getBus().register(this);

        initLayout();
        initCheckBoxes();
        if (getIntent() != null) {
            search = getIntent().getParcelableExtra(RESEARCH);
            if (null != search) {
                query.setText(search.getRequestItems().getKeyWord());
            }
            // etc ....
        }
        newSearchView = new NewSearchView();
        cities = new ArrayList<>();


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
            if (meuble.isChecked()) {
                newSearchView.setMeuble(Meuble.NON_MEUBLE);
            } else {
                newSearchView.setMeuble(Meuble.MEUBLE_DEFAULT);
            }
        }

        newSearchView.setQuery(query.getText().toString());
        Log.d(TAG, "résultat : " + newSearchView);

        Intent intent = new Intent(this, ResultActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(NEW_RESEARCH, createRequestItem());
        intent.putExtras(args);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle());
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

                int min = newSearchView.getRent().getPostitonMin();
                int max = newSearchView.getRent().getPositionMax();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(rentSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.RENT, newSearchView.getRent(),
                        min, max);
                minMaxAlertDialog.createDialog();
            }
        });

        LinearLayout surfaceLayout = (LinearLayout) findViewById(R.id.surface);
        surfaceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurfaceSwipeItem surfaceSwipeItem = new SurfaceSwipeItem();
                int min = newSearchView.getSurface().getPostitonMin();
                int max = newSearchView.getSurface().getPositionMax();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(surfaceSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.SURFACE, newSearchView.getSurface()
                        , min, max);
                minMaxAlertDialog.createDialog();
            }
        });

        LinearLayout nbRoomLayout = (LinearLayout) findViewById(R.id.nb_room);
        nbRoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NbRoomSwipeItem nbRoomSwipeItem = new NbRoomSwipeItem();
                int min = newSearchView.getNbRoom().getPostitonMin();
                int max = newSearchView.getNbRoom().getPositionMax();
                MinMaxAlertDialog minMaxAlertDialog = new MinMaxAlertDialog(nbRoomSwipeItem.createBeans(), NewSearchActivity.this, SwipeItemEnum.NB_ROOM, newSearchView.getNbRoom(),
                        min, max);
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
        List<City> citys = new ArrayList<>();
        citys.add(updateCitiesViewBus.getcity());
        cities = citys;
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
