package nlt.bondarenko.newsapp.screens;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import nlt.bondarenko.newsapp.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationViewMain;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewMain = findViewById(R.id.bottom_navigation_main);

        actionBar = getSupportActionBar();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationViewMain, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.article_fragment) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    bottomNavigationViewMain.setVisibility(View.GONE);
                } else {
                    bottomNavigationViewMain.setVisibility(View.VISIBLE);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}