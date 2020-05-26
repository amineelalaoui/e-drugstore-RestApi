package service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Client;

@Stateless
public class ClientService {

	@PersistenceContext(unitName = "MaPU")
	private EntityManager em;
	
	private static final String SECRET_KEY = "1FErDa2JrkoggOkj9FoAi2tWcmNDi_W_dp-GZFlvxqg0U1-j304N2okJcrQsLNrKz10PEhdjblSHPOOtq5osPfyFM-t3QLjWKLUa7prtqTR9QWDY2eaZvpqBYGi9umnNTCE1UAVz8DvhkDM-ynr1lZ6iTlkIuEyLnD6lY6fgmus";
	
	static MessageDigest digest ;
	
	public Response addClient(Client c) {
		try {
			c.setPassword(hash(c.getPassword()));
			em.persist(c);
		}catch(Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"" + e.getMessage() +  "\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}
		return Response.status(Response.Status.OK)
	            .entity(c)
	            .type(MediaType.APPLICATION_JSON)
	            .build();
	}
	
	public Response authenticate(String email,String password) {
		try {
			Query qr = em.createQuery("select c from Client c where c.email=:email and c.password=:password");
			qr.setParameter("email", email);
			qr.setParameter("password", hash(password));
			System.out.println(password);
			Client c = ((Client) qr.getSingleResult());
			String token = createJWT(c.getId().toString(), c.getEmail(), c.getPassword(), 20);
			return Response.status(Response.Status.OK)
		            .entity(c.getJSON(token))
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE)
		            .entity("{\n"
		            		+ "\t \"error\": \"Login error. Check the email or the password\"\n"
		            		+ "}")
		            .type(MediaType.APPLICATION_JSON)
		            .build();
		}
		
		
	}
	
	private static String hash(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(text.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
	}
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	
	

	


	
	
}
