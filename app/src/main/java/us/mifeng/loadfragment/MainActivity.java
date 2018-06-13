package us.mifeng.loadfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import us.mifeng.loadfragment.fra.LoadFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new VAdapter(getSupportFragmentManager()));
    }
    class VAdapter extends FragmentPagerAdapter{

        public VAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LoadFragment.getFragmentInstance("Fragment"+position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
