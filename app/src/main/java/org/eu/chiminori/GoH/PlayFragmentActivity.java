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
import java.util.regex.*;
import meow.bottomnavigation.*;
import org.json.*;

public class PlayFragmentActivity extends Fragment {
	
	private double clicks = 0;
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private Button button1;
	private TextView textview2;
	private TextView textviewCLICKS;
	private TextView textview4;
	private TextView textviewLVL;
	
	private SharedPreferences game;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.play_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		button1 = _view.findViewById(R.id.button1);
		textview2 = _view.findViewById(R.id.textview2);
		textviewCLICKS = _view.findViewById(R.id.textviewCLICKS);
		textview4 = _view.findViewById(R.id.textview4);
		textviewLVL = _view.findViewById(R.id.textviewLVL);
		game = getContext().getSharedPreferences("file", Activity.MODE_PRIVATE);
		
		button1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				clicks++;
				Calendar c = Calendar.getInstance();
				int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
				if (timeOfDay >= 20 && timeOfDay < 24) {
					clicks++;
				}
				if (game.contains("buy1")) {
					clicks = clicks + Double.parseDouble(game.getString("buy1", ""));
				}
				game.edit().putString("clicks", String.valueOf((long)(clicks))).commit();
				textviewCLICKS.setText(String.valueOf((long)(clicks)));
			}
		});
	}
	
	private void initializeLogic() {
		if (game.contains("clicks")) {
			textviewCLICKS.setText(game.getString("clicks", ""));
			clicks = Double.parseDouble(game.getString("clicks", ""));
		}
		else {
			clicks = 0;
			textviewCLICKS.setText(String.valueOf((long)(clicks)));
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_click_animation_1(button1);
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor("#52cc99"));
		ab.setCornerRadius(22);
		button1.setElevation(0);
		button1.setBackground(ab);
	}
	public void _click_animation_1(final View _view) {
		_view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()){
					case MotionEvent.ACTION_DOWN:{
						ObjectAnimator scaleX = new ObjectAnimator();
						scaleX.setTarget(_view);
						scaleX.setPropertyName("scaleX");
						scaleX.setFloatValues(0.9f);
						scaleX.setDuration(100);
						scaleX.start();
						
						ObjectAnimator scaleY = new ObjectAnimator();
						scaleY.setTarget(_view);
						scaleY.setPropertyName("scaleY");
						scaleY.setFloatValues(0.9f);
						scaleY.setDuration(100);
						scaleY.start();
						break;
					}
					case MotionEvent.ACTION_UP:{
						
						ObjectAnimator scaleX = new ObjectAnimator();
						scaleX.setTarget(_view);
						scaleX.setPropertyName("scaleX");
						scaleX.setFloatValues((float)1);
						scaleX.setDuration(100);
						scaleX.start();
						
						ObjectAnimator scaleY = new ObjectAnimator();
						scaleY.setTarget(_view);
						scaleY.setPropertyName("scaleY");
						scaleY.setFloatValues((float)1);
						scaleY.setDuration(100);
						scaleY.start();
						
						break;
					}
				}
				return false;
			}
		});
	}
	
}