package cl.automind.android.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import cl.automind.android.application.AppContext;
import cl.automind.android.utils.RutValidator;
import cl.automind.android.vote.R;

public class Startup extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(AppContext.InitialState);
	}
	@Override
	public void setContentView(int layoutResID){
		super.setContentView(layoutResID);
		AppContext.getInstance().setState(layoutResID);
	}
	public void actionButton(View view){
		if (AppContext.getInstance().getState() == AppContext.InitialState){
			EditText text_input;
			String keyCode = "";
			fixKeyCode();
			text_input = (EditText)findViewById(R.id.CodeInput00);
			keyCode = keyCode + text_input.getText();
			text_input = (EditText)findViewById(R.id.CodeInput01);
			keyCode = keyCode + text_input.getText();
			text_input = (EditText)findViewById(R.id.CodeInput02);
			keyCode = keyCode + text_input.getText();
			
			keyCode = keyCode + ".";
			
			text_input = (EditText)findViewById(R.id.CodeInput03);
			keyCode = keyCode + text_input.getText();
			text_input = (EditText)findViewById(R.id.CodeInput04);
			keyCode = keyCode + text_input.getText();
			text_input = (EditText)findViewById(R.id.CodeInput05);
			keyCode = keyCode + text_input.getText();
			text_input = (EditText)this.findViewById(R.id.RutInputTextField);
			
			AppContext.getInstance().setKeyCode(keyCode);
			AppContext.getInstance().setRut(text_input.getText().toString());
			
			if (RutValidator.validateRut(AppContext.getInstance().getRut())) {
                Intent myIntent = new Intent(view.getContext(), Vote.class);
                startActivity(myIntent);
                finish();
			} else {
				cleanTextField(text_input);
			}
		}
	}
	public void cleanTextField(View view){
		EditText text_input = (EditText)this.findViewById(R.id.RutInputTextField);
		text_input.setText("");
	}
	private void fixKeyCode(){
		EditText text_input;
		int k0,k1,k2,k3,k4,k5;
		text_input = (EditText)findViewById(R.id.CodeInput00);
		if (text_input.getText().length() == 0) text_input.setText("0");
		k0 = Integer.parseInt(text_input.getText().toString());
		text_input = (EditText)findViewById(R.id.CodeInput01);
		if (text_input.getText().length() == 0) text_input.setText("0");
		k1 = Integer.parseInt(text_input.getText().toString());
		text_input = (EditText)findViewById(R.id.CodeInput02);
		if (text_input.getText().length() == 0) text_input.setText("0");
		k2 = Integer.parseInt(text_input.getText().toString());
		text_input = (EditText)findViewById(R.id.CodeInput03);
		if (text_input.getText().length() == 0) text_input.setText("0");
		k3 = Integer.parseInt(text_input.getText().toString());
		text_input = (EditText)findViewById(R.id.CodeInput04);
		if (text_input.getText().length() == 0) text_input.setText("0");
		k4 = Integer.parseInt(text_input.getText().toString());
		text_input = (EditText)findViewById(R.id.CodeInput05);
		if (text_input.getText().length() == 0) text_input.setText("0");
		k5 = Integer.parseInt(text_input.getText().toString());
		
		if (k0 > 2){
			text_input = (EditText)findViewById(R.id.CodeInput00);
			text_input.setText("2"); k0 = 2;
		}
		if (k0 == 2){
			if (k1 > 5){
				text_input = (EditText)findViewById(R.id.CodeInput01);
				text_input.setText("5"); k1 = 5;
				if (k2 > 5){
					text_input = (EditText)findViewById(R.id.CodeInput02);
					text_input.setText("5"); k2 = 5;
				}
			}
		}
		
		if (k3 > 2){
			text_input = (EditText)findViewById(R.id.CodeInput03);
			text_input.setText("2"); k3 = 2;
		}
		if (k3 == 2){
			if (k4 > 5){
				text_input = (EditText)findViewById(R.id.CodeInput04);
				text_input.setText("5"); k4 = 5;
				if (k5 > 5){
					text_input = (EditText)findViewById(R.id.CodeInput05);
					text_input.setText("5"); k5 = 5;
				}
			}
		}
	}
}