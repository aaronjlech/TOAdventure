const Backbone = require('backbone')
const {UserModel, UserCollection} = require('./model-users.js')
const {ObstacleModel, ObstacleCollection} = require('./model-assets.js')
const {AvatarModel, AvatarCollection} = require("./model-avatars.js")
const {HighScoreModel, HighScoreCollection} = require('./highscore-models.js')
const {SignUpModel, SignUpCollection} = require('./signup-models.js')
const STORE = require("./store.js")

const ACTIONS = {

   fetchHighScores: function(){
      let scores = new HighScoreCollection()

       scores.fetch().then(function(){
         STORE.setStore('highscore', scores.models)


      })
   },

   fetchUserData: function(){
      let userCollInst = new UserModel()
      return userCollInst.fetch().then(function(){
         STORE.setStore('all_users', userCollInst)
      })

   },

   getAllAvatars: function(){
      let avtrs = new AvatarCollection()

       avtrs.fetch().then(function(){
         STORE.setStore('avatars', avtrs.models)


      })
   },

   handleUserLogin: function(usrInfo){
      let usrLogin = new UserModel()

      usrLogin.set(usrInfo)
      console.log( "hey" ,usrLogin)

      usrLogin.save().then(function(serverRes){


         console.log( "tickle me" ,serverRes)

         console.log(serverRes)
         STORE.setStore('currentUser', serverRes)

      })






   },
// EXECUTE TO GRAB RANDOM ASSETS
   getObstacles: function(){
      let obst = new ObstacleCollection()
      //
      // obst.fetch().then(function(){
      //
      //    STORE.setStore('obstacles', obst.models)
      //
      // })
   },

   createNewUser: function(modlVals){
      let newUser = new SignUpModel()

      newUser.set(modlVals)

      newUser.save().then(function(serverRes){
         STORE.setStore('currentUser', serverRes)

      })

   },

   fetchCharData: function(){
      let charCollInst = new CharCollection()

      return charCollInst.fetch().then(function(){
         STORE.setStore('characters', charCollInst)
      })


   },

   changeView: function(viewInput){

      STORE.setStore('currentView', viewInput)

   }

}




module.exports = ACTIONS
