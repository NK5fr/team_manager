<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.*" %>
<%@ page import="util.*" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>one</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <% Joueur j = (Joueur) request.getAttribute("joueur"); %>
    <form action="control" method="get" class="w-75 mx-auto mt-5 border border-secondary p-3 rounded-4 bg-secondary-subtle">
        <h1 class="text-center">Modifier</h1>
        <input type="hidden" name="action" value="update" id="update">
        <input type="hidden" name="jno" value="<%=j.getJno()%>" id="id">
        <div>
            <label for="prenom" class="form-label mx-2">prenom</label>
            <input type="text" name="prenom" class="form-control" id="prenom" value="<%=j.getPrenom()%>">
        </div>
        <div>
            <label for="nom" class="form-label mx-2">nom</label>
            <input type="text" name="nom" class="form-control" id="nom" value="<%=j.getNom()%>">
        </div>
        <div>
            <label for="age" class="form-label mx-2">age</label>
            <input type="number" name="age" class="form-control" id="age" value="<%=j.getAge()%>">
        </div>
        <div>
            <label for="position" class="form-label mx-2">position</label>
            <select class="form-select" name="position" id="position">
                <option value="G" <% if(j.getPosition() == Position.G) out.println("selected"); %>>G</option>
                <option value="DLD" <% if(j.getPosition() == Position.DLD) out.println("selected"); %>>DLD</option>
                <option value="DD" <% if(j.getPosition() == Position.DD) out.println("selected"); %>>DD</option>
                <option value="DC" <% if(j.getPosition() == Position.DC) out.println("selected"); %>>DC</option>
                <option value="DG" <% if(j.getPosition() == Position.DG) out.println("selected"); %>>DG</option>
                <option value="DLG" <% if(j.getPosition() == Position.DLG) out.println("selected"); %>>DLG</option>
                <option value="MDC" <% if(j.getPosition() == Position.MDC) out.println("selected"); %>>MDC</option>
                <option value="MD" <% if(j.getPosition() == Position.MD) out.println("selected"); %>>MD</option>
                <option value="MC" <% if(j.getPosition() == Position.MC) out.println("selected"); %>>MC</option>
                <option value="MG" <% if(j.getPosition() == Position.MG) out.println("selected"); %>>MG</option>
                <option value="MOC" <% if(j.getPosition() == Position.MOC) out.println("selected"); %>>MOC</option>
                <option value="AT" <% if(j.getPosition() == Position.AT) out.println("selected"); %>>AT</option>
                <option value="AD" <% if(j.getPosition() == Position.AD) out.println("selected"); %>>AD</option>
                <option value="BU" <% if(j.getPosition() == Position.BU) out.println("selected"); %>>BU</option>
                <option value="AG" <% if(j.getPosition() == Position.AG) out.println("selected"); %>>AG</option>
            </select>
        </div>
        <div>
            <label for="valeur" class="form-label mx-2">valeur</label>
            <input type="number" name="valeur" class="form-control" id="valeur" value="<%=j.getValeur()%>">
        </div>
        <div>
            <label for="description" class="form-label mx-2">description</label>
            <textarea class="form-control" id="description" name="description" rows="5"><%=j.getDescription()%></textarea>
        </div>
        <input type="hidden" name="situation" value="<%=j.getSituation()%>" id="action">
        <button type="submit" class="btn btn-primary mt-3">modifier</button>
        <a href="index.jsp" class="my-5 mx-3">accueil</a>
    </form>
</body>