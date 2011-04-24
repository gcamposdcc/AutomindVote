package cl.automind.android.utils;

public class RutValidator {
	public static boolean validateRut(String rut_string){
		boolean output = true;
		try {
			if (rut_string.length()<2) output = false;
			else output = verificarRut(Integer.parseInt(rut_string.substring(0,rut_string.length()-1)), rut_string.charAt(rut_string.length()-1));
		} catch (Exception e){
			output = false;
		}
		return output;
	}
	private static boolean verificarRut(int rut, char dv) {
		int dgt, cnt, mult, acc;
		String r_dig, dv_s;
		cnt = 2; acc = 0;
		while (rut != 0)
		{
			mult = (rut % 10) * cnt;
			acc += mult;
			rut /= 10;
			cnt++;
			if (cnt == 8) cnt = 2;		
		}
		dgt = 11 - (acc % 11);
		r_dig = ""+dgt;
		dv_s = ""+dv;
		if (dgt == 10) r_dig = "k";
		if (dgt == 11) r_dig = "0";
		return r_dig.equalsIgnoreCase(dv_s);
	}

}
