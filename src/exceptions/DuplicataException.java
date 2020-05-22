package exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response.Status;

@ApplicationException(rollback = true)
public class DuplicataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4416890465759851578L;
	private Status status;
	
	public DuplicataException(String message, Status status) {
		super(message);
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
	
}
