package br.com.foursys.locadora.util;

import java.util.Date;

/**
 * Classe responsável por armazenar os métodos de validação de dados
 *
 * @author Diego Munhoz
 * @since 17/03/2021
 * @version 1.0
 */
public class Valida {
    
    public static boolean isEmptyOrNull(String args) {
        return (args.trim().equals(Constantes.STRING_VAZIO) || args == null);
    }
    
    public static boolean isDateNull(Date args) {
        return (args == null);
    }
    
    public static boolean isInteger(String args) {
        try {
            Integer.parseInt(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isDouble(String args) {
        try {
            Double.parseDouble(args);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean isDoubleZero(double num) {
    	if (num <= Constantes.INT_ZERO) {
			return true;
		}
    	return false;
    }
    
    public static boolean isIntZero(int num) {
    	if (num <= Constantes.INT_ZERO) {
			return true;
		}
    	return false;
    }
    
    public static boolean isListZero(int num) {
    	if (num <= Constantes.INT_ZERO) {
			return true;
		}
    	return false;
    }
    public static boolean isComboInvalida(int cidade) {
		// TODO Auto-generated method stub
		return false;
	}

}
