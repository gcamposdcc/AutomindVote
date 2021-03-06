package cl.automind.android.application;

import cl.automind.android.vote.R;

public class AppContext {
	private static AppContext instance;
	public static AppContext getInstance(){
		if (instance == null) instance =  new AppContext();
		return instance;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRut() {
		return rut;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getState() {
		return state;
	}
	private String rut;
	private String keyCode;

    private int state;
    public final static int InitialState = R.layout.run_input_layout;
    public final static int VoteState = R.layout.main;
}
