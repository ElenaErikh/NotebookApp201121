package com.example.notebookapp201121;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.notebookapp201121.ui.AboutFragment;
import com.example.notebookapp201121.ui.MyDialogFragment;
import com.example.notebookapp201121.ui.MyNotificationResult;
import com.example.notebookapp201121.data.Note;
import com.example.notebookapp201121.ui.NoteContentChildFragment;
import com.example.notebookapp201121.ui.TitlesFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements MyNotificationResult {

    private Navigation navigation;
    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = new Navigation(getSupportFragmentManager());

        initToolbar();

        getNavigation().addFragment(R.id.fragment_container, new TitlesFragment(), false);

        if (savedInstanceState == null) {
            openFragment(new TitlesFragment());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_all_notes:
                    openFragment(new TitlesFragment());
                    drawer.closeDrawers();
                    return true;
                case R.id.action_about:
                    openFragment(new AboutFragment());
                    drawer.closeDrawers();
                    return true;
                case R.id.action_add:
                    openFragment(NoteContentChildFragment.newInstance(new Note()));
                    drawer.closeDrawers();
                    return true;
                case R.id.action_exit:
                    showAlertDialogFragment();
                    return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_all_notes:
                openFragment(new TitlesFragment());
                return true;
            case R.id.action_about:
                openFragment(new AboutFragment());
                return true;
            case R.id.action_add:
               openFragment(NoteContentChildFragment.newInstance(new Note()));
                return true;
            case R.id.action_exit:
                showAlertDialogFragment();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void showAlertDialogFragment() {
        new MyDialogFragment().show(getSupportFragmentManager(), MyDialogFragment.TAG);
    }

    @Override
    public void onSnackBarResult(String text) {
        Snackbar.make(findViewById(R.id.fragment_container), text, Snackbar.LENGTH_SHORT).show();
    }
}