package nlt.bondarenko.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private ArticleFragment articleFragment;
    private ArticleListFragment articleListFragment;
    private BookmarkFragment bookmarkFragment;
    private SourceListFragment sourceListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleFragment = new ArticleFragment();
        articleListFragment = new ArticleListFragment();
        bookmarkFragment = new BookmarkFragment();
        sourceListFragment = new SourceListFragment();

        BottomNavigationView bottomNavigationViewMain = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_main, sourceListFragment );
        fragmentTransaction.commit();

        bottomNavigationViewMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, articleListFragment,null).commit();
                        break;
                    case R.id.navigation_sources:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, sourceListFragment, null).commit();
                        break;
                    case R.id.navigation_bookmarks:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, bookmarkFragment,null).commit();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, articleListFragment, null).commit();
                        break;
                }
                return true;
            }
        });

    }
}