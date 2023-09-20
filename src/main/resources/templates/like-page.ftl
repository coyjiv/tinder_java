<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="./css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <form action="/users" method="POST">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img src="${user.profilePhoto}" alt="" class="user-image mx-auto img-fluid">
                        <h3 class="mb-0 text-truncated username">${user.username}</h3>
                        <br>
                        <input type="hidden" name="targetUserId" value="${user.userId}">
                    </div>
                    <div class="col-12 col-lg-6">
                        <button class="btn btn-outline-danger btn-block" type="submit" name="action" value="dislike"><span class="fa fa-times"></span> Dislike</button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button class="btn btn-outline-success btn-block" type="submit" name="action" value="like"><span class="fa fa-heart"></span> Like</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>