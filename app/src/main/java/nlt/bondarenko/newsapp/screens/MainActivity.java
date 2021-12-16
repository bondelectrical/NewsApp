package nlt.bondarenko.newsapp.screens;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.screens.article.ArticleFragment;
import nlt.bondarenko.newsapp.screens.articleList.ArticleListFragment;
import nlt.bondarenko.newsapp.screens.bokmarks.BookmarkFragment;
import nlt.bondarenko.newsapp.screens.sourceList.SourceListFragment;

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


        BottomNavigationView bottomNavigationViewMain = findViewById(R.id.bottom_navigation_main);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_main, sourceListFragment);
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
                }
                return true;
            }
        });

    }
}