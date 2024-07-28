<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="dto.*" %>
<%@ page import="util.*" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>transferable</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/655e475bd8.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="w-75 mx-auto mt-5">
        <h1>Joueurs transferables :</h1>
    </div>
    <div class="w-75 mx-auto my-3">
        <a href="control?action=owned" class="mx-3">joueurs possédés</a>
        <a href="control?action=wished" class="mx-3">joueurs souhaités</a>
        <a href="control?action=other" class="mx-3">autres joueurs</a>
        <a href="index.jsp" class="mx-3">accueil</a>
    </div>
    <table class="table w-75 mx-auto mt-2 table-striped table-bordered border-secondary text-center">
        <tr>
            <th></th>
            <th><a href="control?action=transferable&colonne=prenom" class="link-dark link-offset-2 link-underline-opacity-0">prenom</a></th>
            <th><a href="control?action=transferable&colonne=nom" class="link-dark link-offset-2 link-underline-opacity-0">nom</a></th>
            <th><a href="control?action=transferable&colonne=age" class="link-dark link-offset-2 link-underline-opacity-0">age</a></th>
            <th><a href="control?action=transferable&colonne=position" class="link-dark link-offset-2 link-underline-opacity-0">position</a></th>
            <th><a href="control?action=transferable&colonne=valeur" class="link-dark link-offset-2 link-underline-opacity-0">valeur</a></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <% 
            List<Joueur> joueurs = (List<Joueur>) request.getAttribute("joueurs");
            int sum = 0;
            for (Joueur j : joueurs) { 
                if(j.getSituation().equals("transferable")){
                    sum += j.getValeur();
        %>
        <tr>
        <td><a href="control?action=one&jno=<%=j.getJno()%>"><i class="fa-solid fa-pen"></i></a></td>
        <td><%=j.getPrenom()%></td>
        <td><%=j.getNom()%></td>
        <td><%=j.getAge()%></td>
        <td><%=j.getPosition()%></td>
        <td><%=j.getValeur()%>M€</td>
        <td><a href="control?action=updateSituation&jno=<%=j.getJno()%>&situation=possédé&from=transferable"><i class="fa-solid fa-file-signature"></i></a></td>
        <td><a href="control?action=updateSituation&jno=<%=j.getJno()%>&situation=autre&from=transferable"><i class="fa-solid fa-earth-europe"></i></a></td>
        <td><a href="control?action=delete&jno=<%=j.getJno()%>&situation=<%=j.getSituation()%>"><i class="fa-solid fa-trash"></i></a></td>
        <% }} %>
    </table>
    <div class="w-75 mx-auto mt-3">
        <h2>total : <%=sum%>M€</h2>
    </div>
</body>
</html>