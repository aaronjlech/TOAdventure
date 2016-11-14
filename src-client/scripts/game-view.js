const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')
const ModalView = require('./modal-view.js')
const {HomeView, dummyUsers, NavView} = require("./home-view.js")
const {ObstacleModel, ObstacleCollection} = require('./model-assets.js')
const ACTIONS = require('./actions.js')

let leaderBoardData = dummyUsers.map((someData, i)=>{
   return (
      <li key={i}>
         <p>
            <span>{someData.userName}</span>
            <span>{someData.score}</span>
         </p>
      </li>
   )
})


const GameView = React.createClass({


   componentDidMount: function(){




   },




   render: function(){
      console.log(this.refs)
      console.log(this.props.crntUser)


      return(
         <div>
            <NavView/>
            <div className="container">
               <div className="row">
                  <div className="col-xs-4 leader-board">
                     <h2>LeaderBoard</h2>
                     <ol>
                        {leaderBoardData}
                     </ol>
                  </div>
                  <div>
                     <div className="col-xs-8">
                        <GameCanvas thePlayer={this.props.crntUser}/>
                     </div>
                  </div>
               </div>
               <footer>
                  <div>
                     <p>sources and contributions</p>
                  </div>
                  <div>
                     <p>github profiles</p>
                  </div>
               </footer>
            </div>
         </div>

            )
      }
   })




   const GameCanvas = React.createClass({

      componentDidMount: function(){
         console.log( "look here bitch" ,this.props)
         setTimeout(function(){
            const canvas = this.refs.gameCanvas
   var game = canvas.getContext("2d");
   var x = 0;
   var y = 0;
   var xspeed = 0;
   var yspeed = 0;
   var gravity = 1;
   var height = 50;
   var ground = canvas.height - height;
   var jumpCount = 0;
   var level = 0;
   var itemCount = 15;
   var gameState = true;
   var startTime = Date.now() + 3000;
   var score = 0;
   var health = 3;


   var obstacleArray = []
   var displayedObjects = []



   // var mageBoss = new Image();
   // mageBoss.src = "mage-standing.png"



   var background = new Image();
   background.src = "ui/CpX4uAU.jpg";
   background.onload = function(){
       game.drawImage(background,0,0,400,150);
   }

   var gameEnd = new Image();
   gameEnd.src = "ui/gameover.png"

   var playerRight = new Image();
   playerRight.src = this.props.thePlayer.filename;
   var imageRatio = 1;
   playerRight.onload = function() {
     imageRatio = playerRight.width / playerRight.height;
   }
   var playerLeft = new Image();
   playerLeft.src = this.props.thePlayer.filename;

   var playerJump = new Image();
   playerJump.src = this.props.thePlayer.filename2;

   var playerDeath = new Image();
   playerDeath.src = "avatars/elf-death.png"

   var playerImage = playerRight;

   var fullHealth = new Image();
   fullHealth.src = "ui/full-heart.png"


   var emptyHealth = new Image();
   emptyHealth.src = "ui/empty-heart.png"


   var backgroundMusic = new Audio();
   backgroundMusic.src = "sounds/game-music.wav"
   backgroundMusic.loop = true;

   var playerJumpSound = new Audio();
   playerJumpSound.src = "sounds/jump.wav"

   var playerHurtSound = new Audio();
   playerHurtSound.src = "sounds/player-hurt.wav"

   var playerSplatSound = new Audio();
   playerSplatSound.src = "sounds/player-splat.wav"

   var enemySound = new Audio();
   enemySound.src = "sounds/skull.wav"

   var moneySound = new Audio();
   moneySound.src = "sounds/coin.wav"

   var itemSound = new Audio();
   itemSound.src = "sounds/pickup-item.wav"

   var gameOverSound = new Audio();
   gameOverSound.src = "sounds/gameover.wav"


   backgroundMusic.play()

   function draw() {


     game.drawImage(background,0,0,canvas.width,canvas.height);
     game.font = "10px Arial";
     game.fillStyle = 'white';
     game.fillText("Level: " + level, 10, 10);
     game.fillText("Score: " + score, 10, 20);
     x = x + xspeed;
     y = y + yspeed;
     yspeed = yspeed + gravity;

     if (y > ground) {
       y = ground;
       yspeed = 0;
       jumpCount = 0;
       playerImage = playerRight
     }
     if (x < 0){
       x = 0;
     }

     if (y < ground){
        playerImage = playerJump
    }

     if (x > canvas.width - 30){
       x = canvas.width - 30;
     }

    if(health === 3){
      game.drawImage(fullHealth, 250, 2, 15, 15)
      game.drawImage(fullHealth, 265, 2, 15, 15)
      game.drawImage(fullHealth, 280, 2, 15, 15)
    } else if (health === 2){
      game.drawImage(fullHealth, 250, 2, 15, 15)
      game.drawImage(fullHealth, 265, 2, 15, 15)
      game.drawImage(emptyHealth, 280, 2, 15, 15)
    } else if (health === 1){
      game.drawImage(fullHealth, 250, 2, 15, 15)
      game.drawImage(emptyHealth, 265, 2, 15, 15)
      game.drawImage(emptyHealth, 280, 2, 15, 15)
    } else {
      game.drawImage(emptyHealth, 250, 2, 15, 15)
      game.drawImage(emptyHealth, 265, 2, 15, 15)
      game.drawImage(emptyHealth, 280, 2, 15, 15)


      endGame();
    }




     if (obstacleArray.length === 0){

        var obst = new ObstacleCollection()

        obst.fetch().then(function(){
           console.log(obst)
          obstacleArray = obst.models
        })



     }

     if(score <= 0){
       score = 0;
     }

     displayedObjects.forEach(function(item) {
       item.draw();
     });
     displayedObjects.forEach(function(item) {
       item.update();
     });
     displayedObjects = displayedObjects.filter(function(item) {
       return item.active;
     });

     handleCollisions();


     game.drawImage(playerImage, x, y, height * imageRatio, height);
   }

   var gameUpdate = setInterval(draw, 20);

   var endGame = function(){
      backgroundMusic.pause()
     game.drawImage(gameEnd, 5,30,100,50)
     clearInterval(objectCycle);
     clearInterval(gameUpdate);
     playerHurtSound.play()
     setTimeout(function(){
        playerHurtSound.play()
        gameOverSound.play()
       y += -15;
       var fallingDeath = setInterval(function(){

       playerImage = playerDeath;  game.drawImage(background,0,0,canvas.width,canvas.height);
     game.font = "10px Arial";
     game.fillStyle = 'white';
     game.fillText("Level: " + level, 10, 10);
     game.fillText("Score: " + score, 10, 20);
       x = x;
       y = y + yspeed;
       yspeed = yspeed + gravity;
       game.drawImage(playerImage, x, y, height * imageRatio, height)
       game.drawImage(gameEnd, 5,30,100,50)
       if(y > ground){
         y = ground;
         yspeed = 0;
         playerSplatSound.play()
         clearInterval(fallingDeath)
       }
   },45)



     }, 800)
   }

   function startMove(event) {
     // pressed left
     if (event.keyCode == 37) {
       xspeed = -5;
       playerImage = playerLeft;
     }
     // pressed right
     if (event.keyCode == 39) {
       xspeed = 5;
       playerImage = playerRight;
     }
     // pressed up
     if (event.keyCode == 38) {
       if (jumpCount < 2){
         playerJumpSound.load()
         playerJumpSound.play()
           yspeed = -10;
           jumpCount += 1;
           playerImage = playerJump;
           }
     }
   }



   var obstacle = function (O) {

      O.active = true;
      O.item = new Image()
      O.item.src = O.fileName
     O.xVelocity = 0;
     O.yVelocity = 0;
     O.width = 20;
     O.height = 20;
     O.color = "#FFF";

     O.inBounds = function() {
       if(O.y >= 0){
         return false
       }
     }

     O.draw = function() {
       game.drawImage(this.item,this.x, this.y, this.width, this.height)
       // game.fillStyle = this.color;
       // game.fillRect(this.x, this.y, this.width, this.height);
     };

     O.update = function() {
       O.x += -3;
       O.y += 0;

       if(O.x === 120){
         if(O.name === "ENEMY"){
            enemySound.play()
         }
      }

       if(O.x <= 0){
         score += 1;
         O.active = false

       } else {
         O.active = O.active
       }


     };

     return O;

   }



   var objectThrowing = function(){
      var itemTracks = [25,50,75,100,125]
      var xTracks = [900,920,950]
     if(Date.now() > startTime){

      if(displayedObjects.length === 0){
       level += 1;
     }

     var objAmount = (Math.floor(Math.random() * 3) + 1)
     if (objAmount > obstacleArray.length){
        objAmount = obstacleArray.length
    }

      for(var b = 0; b < objAmount; b++ ){
         var crntXIndex = (Math.floor(Math.random() * xTracks.length))
         var crntIndex = (Math.floor(Math.random() * itemTracks.length))
         var crntY = itemTracks[crntIndex]
         var crntX = xTracks[crntXIndex]
         var crntItem = obstacleArray[0]
         displayedObjects.push(obstacle({
           speed: -10,
            fileName: crntItem.attributes.filename,
           name: crntItem.attributes.category,
           x: crntX,
           y: crntY
         }));
         itemTracks.splice(crntIndex, 1)
        obstacleArray.splice(0,1)
      }

     if(obstacleArray.length === 0){

       startTime = Date.now() + 10000
     }
     console.log(obstacleArray.length)
   }


   }

   var objectCycle = setInterval(objectThrowing, 900)

   var collision = function (item, me){

     if(item.x < x + height * imageRatio &&
        item.x + item.width > x &&
        item.y < y + height &&
        item.y + item.height > y){
       return true
     }

   }

   var handleCollisions = function (){
     displayedObjects.forEach(function(item){
       if(collision(item)){
         if(item.name === "ENEMY"){
            item.active = false
            score += -5;
            if(health > 0){
              health += -1;
              playerHurtSound.load()
              playerHurtSound.play()

            }
         } else if (item.name === "ITEM"){
            itemSound.play()
            item.active = false
            score += 5;
         } else if (item.name === "MONEY"){
            moneySound.play()
            item.active = false
            score += 10;
         } else if (item.name === "HEALTH"){
            item.active = false
            itemSound.play()
            health += 1;
            if(health > 3){
               health = 3;
            }
         }

       }
     })
   }


   document.onkeydown = startMove;

   function stopMove(event) {
     if (event.keyCode == 37 || event.keyCode == 39) {
       xspeed = 0;
     }
   }

   document.onkeyup = stopMove;

}.bind(this),1000)

      },



      render: function(){



         return(

            <canvas className="game-canvas" ref="gameCanvas"></canvas>
         )
      }



   })





module.exports = GameView
