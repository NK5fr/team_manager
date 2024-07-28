<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/655e475bd8.js" crossorigin="anonymous"></script>
</head>
<body>
    <% 
        String nom = (String) session.getAttribute("nom");
        if(nom == null){
            response.sendRedirect("login.html");
        }else{
    %>
    <h1 class="text-center mt-4">Bienvenue <%=nom%></h1>
    <%
        }
    %>
    <div class="d-flex justify-content-around my-4">
        <div class="w-25 mx-3 text-center border border-secondary p-3 rounded-4 bg-primary-subtle">
            <a href="control?action=owned">joueurs possédés</a>
        </div>
        <div class="w-25 mx-3 text-center border border-secondary p-3 rounded-4 bg-primary-subtle">
            <a href="control?action=transferable">joueurs transferables</a>
        </div>
        <div class="w-25 mx-3 text-center border border-secondary p-3 rounded-4 bg-primary-subtle">
            <a href="control?action=wished">joueurs souhaités</a>
        </div>
        <div class="w-25 mx-3 text-center border border-secondary p-3 rounded-4 bg-primary-subtle">
            <a href="control?action=other">autre joueurs</a>
        </div>
    </div>
    <div class="w-25 m-auto my-4 p-2 text-center border border-secondary rounded-4 bg-dark-subtle">
        <p class="my-0">Tactique</p>
        <a href="http://localhost:8000"><i class="fa-solid fa-chess-pawn fs-1"></i></a>
    </div>
    <form action="control" method="get" class="w-50 m-auto border border-secondary p-3 rounded-4 bg-secondary-subtle">
        <h1 class="text-center">Ajouter un joueur</h1>
        <input type="hidden" name="action" value="add" id="action">
        <input type="hidden" name="jno" value="0" id="id">
        <div>
            <label for="prenom" class="form-label mx-2">prenom</label>
            <input type="text" name="prenom" class="form-control" id="prenom">
        </div>
        <div>
            <label for="nom" class="form-label mx-2">nom</label>
            <input type="text" name="nom" class="form-control" id="nom">
        </div>
        <div>
            <label for="age" class="form-label mx-2">age</label>
            <input type="number" name="age" class="form-control" id="age">
        </div>
        <div>
            <label for="position" class="form-label mx-2">position</label>
            <select class="form-select" name="position" id="position">
                <option value="G" selected>G</option>
                <option value="DLD">DLD</option>
                <option value="DD">DD</option>
                <option value="DC">DC</option>
                <option value="DG">DG</option>
                <option value="DLG">DLG</option>
                <option value="MDC">MDC</option>
                <option value="MD">MD</option>
                <option value="MC">MC</option>
                <option value="MG">MG</option>
                <option value="MOC">MOC</option>
                <option value="AT">AT</option>
                <option value="AD">AD</option>
                <option value="BU">BU</option>
                <option value="AG">AG</option>
            </select>
        </div>
        <div>
            <label for="valeur" class="form-label mx-2">valeur</label>
            <input type="number" name="valeur" class="form-control" id="valeur">
        </div>
        <div>
            <label for="description" class="form-label mx-2">description</label>
            <textarea class="form-control" id="description" name="description" rows="5"></textarea>
        </div>
        <input type="hidden" name="situation" value="autre" id="action">
        <button type="submit" class="btn btn-primary mt-3">ajouter</button>
    </form>
    <div class="w-75 m-auto my-4 text-center border border-secondary p-3 rounded-4 bg-warning-subtle">
        <a href="deconnexion">déconnexion</a>
    </div>
</body>
</html>