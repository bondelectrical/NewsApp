package nlt.bondarenko.newsapp.screens;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;
import nlt.bondarenko.newsapp.screens.articleList.ArticleListFragment;
import nlt.bondarenko.newsapp.screens.bokmarks.BookmarkFragment;
import nlt.bondarenko.newsapp.screens.sourceList.SourceListFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationViewMain;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewMain = findViewById(R.id.bottom_navigation_main);

        actionBar = getSupportActionBar();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_main, ArticleListFragment.newInstance(), ArticleListFragment.class.getSimpleName())
                .commit();


        bottomNavigationViewMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        replaceFragment(ArticleListFragment.newInstance());
                        break;
                    case R.id.navigation_sources:
                        replaceFragment(SourceListFragment.newInstance());

                        break;
                    case R.id.navigation_bookmarks:
                        replaceFragment(BookmarkFragment.newInstance());
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
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

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof ArticleFragment) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            bottomNavigationViewMain.setVisibility(View.GONE);
        } else {
            bottomNavigationViewMain.setVisibility(View.VISIBLE);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }


    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_main, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment articleListFragment = getSupportFragmentManager().findFragmentByTag(ArticleListFragment.class.getSimpleName());
        Fragment articleFragment = getSupportFragmentManager().findFragmentByTag(ArticleFragment.class.getSimpleName());
        actionBar.setDisplayHomeAsUpEnabled(false);
        if (articleListFragment != null && articleListFragment.isVisible()) {
            bottomNavigationViewMain.setVisibility(View.VISIBLE);
            articleFragment.setHasOptionsMenu(true);
            super.onBackPressed();
        } else if (articleFragment != null && articleFragment.isVisible()) {
            bottomNavigationViewMain.setVisibility(View.VISIBLE);
            super.onBackPressed();
        } else {
            bottomNavigationViewMain.setSelectedItemId(R.id.navigation_home);

        }
    }

}