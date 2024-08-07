<?php
session_start();

if(!isset($_SESSION['username'])){
    
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" href="img/MUSKODE.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Musklink - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body class="d-flex 
justify-content-center
align-items-center vh-100 ">
    <div class="w-400 shadow p-5 rounded">
    <form method="POST" action="app/http/auth.php">
        <div class="d-flex justify-content-center align-items-center flex-column">
            <img src="img/6.png" class="w-25">
            <h3 class="display-4 fs-1 text-center ">
                Login
            </h3>


        </div>
        <?php if(isset($_GET['error'])) {?>
                <div class="alert alert-secondary" role="alert">
                    <?php echo htmlspecialchars($_GET['error']); ?>
                </div>
            <?php }?>

        <?php if(isset($_GET['success'])){?>
            <div class="alert alert-warning" role="alert">
                <?php echo htmlspecialchars($_GET['success'])?>
            </div>
            <?php } ?>
            

<div class="mb-3">
    <label class="form-label ">User name </label>
    <input type="text" class="form-control" name="username">
</div>
<div class="mb-3">
    <label class="form-label">Password</label>
    <input type="password" class="form-control" name="password">
</div>
<button type="submit" class="btn btn-primary">Login</button>
<a href="signup.php">Sign-up</a>
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