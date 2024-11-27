package fr.Data.Acces.myExeptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataExeptions extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;

	public DataExeptions(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public DataExeptions(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}


}
