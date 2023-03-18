package org.eu.chiminori.GoH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.*;
import com.robinhood.ticker.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import meow.bottomnavigation.*;
import org.json.*;

public class ShopFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	
	private double buy1 = 0;
	
	private LinearLayout linear1;
	private LinearLayout linear_buy1;
	private TextView textview_buy1;
	private LinearLayout linear3;
	private Button button_buy1;
	private TextView price_buy1;
	
	private SharedPreferences shop;
	private TimerTask timer;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.shop_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear_buy1 = _view.findViewById(R.id.linear_buy1);
		textview_buy1 = _view.findViewById(R.id.textview_buy1);
		linear3 = _view.findViewById(R.id.linear3);
		button_buy1 = _view.findViewById(R.id.button_buy1);
		price_buy1 = _view.findViewById(R.id.price_buy1);
		shop = getContext().getSharedPreferences("file", Activity.MODE_PRIVATE);
		
		button_buy1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (shop.contains("buy1")) {
					if (Double.parseDouble(shop.getString("buy1", "")) > 9) {
						button_buy1.setText("Max");
						button_buy1.setEnabled(false);
					}
					else {
						if (Double.parseDouble(shop.getString("clicks", "")) > buy1) {
							shop.edit().putString("clicks", String.valueOf((long)(Double.parseDouble(shop.getString("clicks", "")) - buy1))).commit();
							shop.edit().putString("buy1", String.valueOf((long)(Double.parseDouble(shop.getString("buy1", "")) + 1))).commit();
							buy1 = Double.parseDouble(shop.getString("buy1", "")) + buy1;
							price_buy1.setText(String.valueOf((long)(buy1)));
							shop.edit().putString("p_buy1", String.valueOf((long)(buy1))).commit();
							textview_buy1.setText("TextView ".concat("(".concat(shop.getString("buy1", "").concat(") "))));
						}
						else {
							button_buy1.setText("Error");
							timer = new TimerTask() {
								@Override
								public void run() {
									getActivity().runOnUiThread(new Runnable() {
										@Override
										public void run() {
											button_buy1.setText("Buy");
										}
									});
								}
							};
							_timer.schedule(timer, (int)(1500));
						}
					}
				}
				else {
					if (Double.parseDouble(shop.getString("clicks", "")) > buy1) {
						shop.edit().putString("clicks", String.valueOf((long)(Double.parseDouble(shop.getString("clicks", "")) - buy1))).commit();
						shop.edit().putString("buy1", "1").commit();
						buy1 = Double.parseDouble(shop.getString("buy1", "")) + buy1;
						price_buy1.setText(String.valueOf((long)(buy1)));
						shop.edit().putString("p_buy1", String.valueOf((long)(buy1))).commit();
						textview_buy1.setText("TextView ".concat("(".concat(shop.getString("buy1", "").concat(") "))));
					}
					else {
						button_buy1.setText("Error");
						timer = new TimerTask() {
							@Override
							public void run() {
								getActivity().runOnUiThread(new Runnable() {
									@Override
									public void run() {
										button_buy1.setText("Buy");
									}
								});
							}
						};
						_timer.schedule(timer, (int)(1500));
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		if (shop.contains("buy1")) {
			buy1 = Double.parseDouble(shop.getString("p_buy1", ""));
		}
		else {
			buy1 = 10;
		}
	}
	
}