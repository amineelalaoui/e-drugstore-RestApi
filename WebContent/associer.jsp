<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,beans.Personne,beans.Address"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Associer</title>
</head>
<body>

<form action="./MainServlet?op=linker" method="POST" >

<% ArrayList<Personne> personnes = (ArrayList<Personne>) request.getAttribute("personnes") ;  %>
List des personnes :
</br>
<ul>
<% for (int i=0 ; i < personnes.size() ; i++ ) { %>
<input type="radio" value="<%= personnes.get(i).getId()%>" name="personne" >
<li> <%= "Nom : " + personnes.get(i).getNom() + " - Prenom : " + personnes.get(i).getPrenom()   %> </li>

<% } %>
</ul>

</br>
</br>
<% ArrayList<Address> adresses = (ArrayList<Address>) request.getAttribute("adresses") ;  %>
List des adresses :
</br>
<ul>
<% for (int i=0 ; i < adresses.size() ; i++ ) { %>
<input type="radio" value="<%= adresses.get(i).getId() %>" name="adresse" >
<li> <%= "Rue : " + adresses.get(i).getRue() + " - Ville : " + adresses.get(i).getVille()   %> </li>

<% } %>
</ul>

<input type="submit" value="Link">


</form>
</body>
</html>