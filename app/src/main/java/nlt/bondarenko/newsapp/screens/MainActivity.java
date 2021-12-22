package nlt.bondarenko.newsapp.screens;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.screens.articleList.ArticleListFragment;
import nlt.bondarenko.newsapp.screens.bokmarks.BookmarkFragment;
import nlt.bondarenko.newsapp.screens.sourceList.SourceListFragment;

public class MainActivity extends AppCompatActivity {

    private ArticleListFragment articleListFragment;
    private BookmarkFragment bookmarkFragment;
    private SourceListFragment sourceListFragment;
    public static String TAG_ARTICLE_FRAGMENT = "ArticleListFragment";
    public static String TAG_BOOKMARK_FRAGMENT = "BookmarkFragment";
    public static String TAG_SOURCE_LIST_FRAGMENT = "SourceListFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationViewMain = findViewById(R.id.bottom_navigation_main);

        articleListFragment = new ArticleListFragment(getSupportFragmentManager());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_main, articleListFragment, null)
                .addToBackStack(null).commit();


        bottomNavigationViewMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        articleListFragment = new ArticleListFragment(getSupportFragmentManager());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, articleListFragment, null)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.navigation_sources:
                        sourceListFragment = new SourceListFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, sourceListFragment, null)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.navigation_bookmarks:
                        bookmarkFragment = new BookmarkFragment(getSupportFragmentManager());
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, bookmarkFragment, null)
                                .addToBackStack(null).commit();
                        break;
                }
                return true;
            }
        });

    }

}