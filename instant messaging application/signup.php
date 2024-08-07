<?php
session_start();

if(!isset($_SESSION['username'])){
?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Musklink - Signup</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="icon" href="img/MUSKODE.png">
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="d-flex 
justify-content-center
align-items-center vh-100 ">
    <div class="w-400 shadow p-5 rounded">
    <form method="POST" action="app/http/signup.php"
    enctype="multipart/form-data">
        <div class="d-flex justify-content-center align-items-center flex-column">
            <img src="img/6.png" class="w-25">
            <h3 class="display-4 fs-1 text-center ">
                Signup
            </h3>


        </div>

        <?php if(isset($_GET['error'])) {?>
                <div class="alert alert-secondary" role="alert">
                    <?php echo htmlspecialchars($_GET['error']); ?>
                </div>
            <?php }

            if(isset($_GET['name'])){
                $name=$_GET['name'];
            }else $name='';

            if(isset($_GET['username'])){
                $username=$_GET['username'];
            }else $username='';
            ?>

<div class="mb-3">
    <label class="form-label ">Full Name</label>
    <input type="text" class="form-control" name="name" class= "form-control"div value=<?=$name?>>
<div class="mb-3">
    <label class="form-label">User Name</label>
    <input type="text" class="form-control" name="username" class= "form-control" value="<?=$username?>">
</div>
<div class="mb-3">
    <label class="form-label">Password</label>
    <input type="password" class="form-control" class= "form-control" type ="password" name="password">
</div>
<div class="mb-3">
    <label class="form-label">Profile Picture</label>
    <input type="file" class="form-control" name="pp">
</div>
<button type="submit" class="btn btn-primary">Signup</button>
<a href="index.php">Login</a>
</form>

    </div>
</body>
</html>
<?php
}else{
    header("location: home.php");
    exit;
}

?>