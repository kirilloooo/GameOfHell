package org.eu.chiminori.GoH;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager2.*;
import com.robinhood.ticker.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import meow.bottomnavigation.*;
import org.json.*;

public class GameActivity extends AppCompatActivity {
	
	private double clicks = 0;
	
	private LinearLayout linear1;
	private ViewPager viewpager1;
	private MeowBottomNavigation btmNav;
	
	private Intent intent = new Intent();
	private FaFragmentAdapter fa;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.game);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		viewpager1 = findViewById(R.id.viewpager1);
		btmNav = findViewById(R.id.btmNav);
		fa = new FaFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				btmNav.show((int)_position,true);
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
	}
	
	private void initializeLogic() {
		fa.setTabCount(2);
		viewpager1.setAdapter(fa);
		viewpager1.setCurrentItem((int)0);
		btmNav.add(new MeowBottomNavigation.Model((int)0, R.drawable.ic_home));
		btmNav.add(new MeowBottomNavigation.Model((int)1, R.drawable.ic_explore));
		btmNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
			@Override
			public void onShowItem(MeowBottomNavigation.Model item) {
				String name;
				switch (item.getId()) {
					//this must be added after adding tabs
					case 0:
					name = "HOME";
					break;
					case 1:
					name = "SHOP";
					break;                    
					default:
					name = "";
				}
			}});
		btmNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
			@Override
			public void onClickItem(MeowBottomNavigation.Model item) {
				viewpager1.setCurrentItem((int)item.getId());
			}});
		btmNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
			@Override
			public void onReselectItem(MeowBottomNavigation.Model item) {
				 
			}});
		btmNav.show((int)0,true);
		btmNav.setCountTypeface(Typeface.create(Typeface.createFromAsset(getAssets(),"fonts/droidsansmono.ttf"), Typeface.BOLD));//my telegram channel sketchware95
		btmNav.setCountTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));//my telegram channel sketchware95
	}
	
	public class FaFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public FaFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new PlayFragmentActivity();
			}
			if (_position == 1) {
				return new ShopFragmentActivity();
			}
			return null;
		}
	}
	
	@Override
	public void onBackPressed() {
		intent.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}