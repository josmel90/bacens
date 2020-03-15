package com.example.dbfarmmedic.MedicsDetail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.dbfarmmedic.DataMedic.MedicsDbHelper;
import com.example.dbfarmmedic.Medics.MedicsActivity;
import com.example.dbfarmmedic.R;

public class MedicsDetailActivity  extends AppCompatActivity {
    String id;
    MedicsDbHelper  myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medics_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra(MedicsActivity.EXTRA_MEDICS_ID);

        MedicsDetailFragmentActivity fragment = (MedicsDetailFragmentActivity)
                getSupportFragmentManager().findFragmentById(R.id.lawyer_detail_cner);
        if (fragment == null) {
            fragment = MedicsDetailFragmentActivity.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.lawyer_detail_cner, fragment)
                    .commit();
        }
        myDB = MedicsDbHelper.GetDBHelper(this);
    }

    // colocamos el Item del Menu  para agregar a favoritos y compartir el medicamento detail

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail_medics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.ic_favorite:
                myDB.updateMedFav(id);
                Toast.makeText(this, "Agregado a Favoritos "+myDB.showMedFav().getIndications(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_share:
                Toast.makeText(this, "compartir", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_like:
                Toast.makeText(this, "Agregado a likes", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
