<!DOCTYPE html>
<html lang="en">
<head>
  
  <title>DataEntry</title>
  <link rel="Aithent icon" type="x-icon" href="tabg.png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

  <style>


img {
      display: block;
      margin-left: auto;
      margin-right: auto;
    }

.container {
  width: 500rem;
  height: 15rem;
  box-shadow: 0 0 1rem 0 rgba(0, 0, 0, .2); 
  border-radius: 10px;
  position: relative;
  z-index: 1;
  background: inherit;
  overflow: hidden;
}

.container:before {
  content: "";
  position: absolute;
  background: inherit;
  z-index: -1;
  top: 30;
  left: 0;
  right: 0;
  bottom: 0;
  box-shadow: inset 0 0 2000px rgba(255, 255, 255, .5);
  filter: blur(30px);
  margin: -20px;
}
.container{
  color: beige;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
  font-style: italic;
}
    body {
    margin: auto;
    font-family: -apple-system, BlinkMacSystemFont, sans-serif;
    overflow: auto;
    background: linear-gradient(315deg, rgba(101,0,94,1) 3%, rgba(60,132,206,1) 38%, rgba(48,238,226,1) 68%, rgba(255,25,25,1) 98%);
    animation: gradient 15s ease infinite;
    background-size: 400% 400%;
    background-attachment: fixed;
}

@keyframes gradient {
    0% {
        background-position: 0% 0%;
    }
    50% {
        background-position: 100% 100%;
    }
    100% {
        background-position: 0% 0%;
    }
}

.wave {
    background: rgb(255 255 255 / 25%);
    border-radius: 1000% 1000% 0 0;
    position: fixed;
    width: 200%;
    height: 12em;
    animation: wave 10s -3s linear infinite;
    transform: translate3d(0, 0, 0);
    opacity: 0.8;
    bottom: 0;
    left: 0;
    z-index: -1;
}

.wave:nth-of-type(2) {
    bottom: -1.25em;
    animation: wave 18s linear reverse infinite;
    opacity: 0.8;
}

.wave:nth-of-type(3) {
    bottom: -2.5em;
    animation: wave 20s -1s reverse infinite;
    opacity: 0.9;
}

@keyframes wave {
    2% {
        transform: translateX(1);
    }

    25% {
        transform: translateX(-25%);
    }

    50% {
        transform: translateX(-50%);
    }

    75% {
        transform: translateX(-25%);
    }

    100% {
        transform: translateX(1);
    }
}
    div {
      font-size: large;
      align-content: center;
    }
    .contained {
      position: fixed;
      bottom: 70px;
      left: 50%;
      transform: translateX(-50%);
    }
    
button:hover {
  background-color: #a5d8ff;
}
  </style>
</head>
<body>
  <img src="Nobg.png" alt="Logo" class="centre" height="300px" width="690px">
  <br>
  <div class="container">Welcome to Aithent Tech, where you can easily view and 
    interact with your Excel sheet data in the form of dynamic and customizable data tables. No more shuffling through rows and columns of numbers, 
    with our platform, you can quickly and easily search, filter, and sort your data to find exactly what you're looking for. 
    Whether you're a business owner, data analyst, or just someone who works with large amounts of data, 
    we're confident that our tool will make your life easier and more efficient. Thank you for choosing us.

  </div>

 
  <br>
  <br>
  <br>
  <div>
    <div class="wave"></div>
    <div class="wave"></div>
    <div class="wave"></div>
 </div>
  <div class="contained">
<!--     <button type="button" class="btn btn-Dark">Browse File</button> -->
      <form action="DataCollect" method="post" name="form2">
       

      <div class="input-group input-group-sm mb-3">
      <div class="input-group-text" id="btnGroupAddon-sizing-sm">Sheet no.</div>
        <input type="number" name="sheetnumber" min="1" value="1" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
        <input type="submit" style="position:absolute;top: 45px;left: 125px;">
             
      </div>
       </form>
  </div>
  
  <br>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>