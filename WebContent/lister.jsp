<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,beans.Personne,beans.Address"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Annuaire</title>
</head>
<body>

<% ArrayList<Personne> personnes = (ArrayList<Personne>) request.getAttribute("personnes") ;
	if(personnes == null)
		personnes = new ArrayList<Personne>();
%>
Liste des personnes :
</br>
<ul>
<% for ( Personne personne : personnes ) { %>
<li> <%= "Nom : " + personne.getNom() + " - Prenom : " + personne.getPrenom()   %> 
	</br> Adresse : </br> <% if (personne.getAddresses() != null) { %>
	   <% for (Address adresse : personne.getAddresses()) {%>
			<%  %>
     		<%=adresse.getRue()  + " " + adresse.getVille() %>
        <%} %>
	 <% } %>
</li>

<% } %>
</ul>

</br>
</br>
<% Collection<Address> adresses = (Collection<Address>) request.getAttribute("adresses") ; 
if(adresses == null)
	adresses = new ArrayList<Address>();

%>
Liste des adresses :
</br>
<ul>
<% for (Address adresse: adresses) { %>
<li> <%= "Rue : " + adresse.getRue() + " - Ville : " + adresse.getVille()   %> </li>

<% } %>
</ul>


<a href="index.html">  <li> Accueil </li> </a>

</body>
</html>