package exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicataExceptionMapper implements ExceptionMapper<DuplicataException>  {
	
	

    public String getExceptionString(String message) {
        return "{\n"
        		+ "\t \"error\": \"" + message + "\",\n"
        		+ "}";
    }

	@Override
	public Response toResponse(DuplicataException e) {
		return Response.status(e.getStatus())
	            .entity(getExceptionString(e.getMessage()))
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}

}
