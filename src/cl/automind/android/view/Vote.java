package cl.automind.android.view;

import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AsyncPlayer;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import cl.automind.android.application.AppContext;
import cl.automind.android.connectivity.Client;
import cl.automind.android.utils.ExportableObject;
import cl.automind.android.vote.R;

public class Vote extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
		setContentView(AppContext.VoteState);
	}
	@Override
	public void setContentView(int layoutResID){
		super.setContentView(layoutResID);
		AppContext.getInstance().setState(layoutResID);
		String ipLocal = "127.0.0.1";
		try{
			WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			ipLocal = Formatter.formatIpAddress(ipAddress);
		} catch (Exception e){ ipLocal = e.getMessage();}
		((TextView)findViewById(R.id.KeyCode)).setText(ipLocal+"::"+AppContext.getInstance().getKeyCode());
	}
	

	MediaPlayer mp;
	public void voteButton(View view){
		new CastVoteThread().execute((Button)view);
	}
	private void clearColors() {
		findViewById(R.id.click0).invalidateDrawable(findViewById(R.id.click0).getBackground());
		findViewById(R.id.click1).invalidateDrawable(findViewById(R.id.click1).getBackground());
		findViewById(R.id.click2).invalidateDrawable(findViewById(R.id.click2).getBackground());
		findViewById(R.id.click3).invalidateDrawable(findViewById(R.id.click3).getBackground());
		findViewById(R.id.click4).invalidateDrawable(findViewById(R.id.click4).getBackground());
		findViewById(R.id.click5).invalidateDrawable(findViewById(R.id.click5).getBackground());
		findViewById(R.id.click0).getBackground().clearColorFilter();   
		findViewById(R.id.click1).getBackground().clearColorFilter();   
		findViewById(R.id.click2).getBackground().clearColorFilter();   
		findViewById(R.id.click3).getBackground().clearColorFilter();   
		findViewById(R.id.click4).getBackground().clearColorFilter();   
		findViewById(R.id.click5).getBackground().clearColorFilter();
	}
	private void enableButtons(boolean enable){
		findViewById(R.id.click0).setEnabled(enable);
		findViewById(R.id.click1).setEnabled(enable);
		findViewById(R.id.click2).setEnabled(enable);
		findViewById(R.id.click3).setEnabled(enable);
		findViewById(R.id.click4).setEnabled(enable);
		findViewById(R.id.click5).setEnabled(enable);
	}
	class SoundPlayer extends AsyncPlayer{

		public SoundPlayer(String tag) {
			super(tag);
		}
		
	}
	class CastVoteThread extends AsyncTask<Button, Object, Button>{
		@Override protected void onPreExecute(){
			clearColors();
			enableButtons(false);
		}
		@Override protected Button doInBackground(Button... params) {
			Button btn = params[0];			
			try {
				String ipLocal = "127.0.0.1";
				try{
					WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
					WifiInfo wifiInfo = wifiManager.getConnectionInfo();
					int ipAddress = wifiInfo.getIpAddress();
					ipLocal = Formatter.formatIpAddress(ipAddress);
				} catch (Exception e){ }
				ExportableObject vote = new ExportableObject();
				vote.set(ExportableObject.VOTE, ""+btn.getText().charAt(0));
				vote.set(ExportableObject.RUT, AppContext.getInstance().getRut());
				String resp = Client.getClient(ipLocal).exportObject(vote);
				boolean success = resp.equals(Client.SUCCESS);
				publishProgress(new Object[]{btn, new Boolean(success)});
				MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.button);
                mp.start();
                mp.setOnCompletionListener(new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
			} catch (Exception e) {
				Logger.global.warning("Catastrophical error!");
			}
			return btn;
		}
		@Override protected void onProgressUpdate(Object... params){
			Button btn = (Button) params[0];
			Boolean success = (Boolean) params[1];
			enableButtons(true);
			Drawable d = btn.getBackground();   
			PorterDuffColorFilter filter = new PorterDuffColorFilter(success.booleanValue() ? Color.GREEN : Color.RED, PorterDuff.Mode.SRC_ATOP);   
			d.setColorFilter(filter);
		}
		@Override protected void onPostExecute(Button in){
			new ClearColorThread().execute(in);
		}
	}
	class ClearColorThread extends AsyncTask<Button, Object, Button>{
		@Override protected void onPreExecute(){
			
		}
		@Override protected Button doInBackground(Button... params) {
			Button btn = params[0];			
			return btn;
		}
		@Override protected void onProgressUpdate(Object... params){

		}
		@Override protected void onPostExecute(Button in){
			SystemClock.sleep(5000);
			clearColors();
			enableButtons(true);
		}
	}
}
