const React = require('react')
const {HomeView, dummyUsers} = require('./home-view.js')
const GameView = require("./game-view.js")
const ACTIONS = require("./actions.js")
const ModalView = require('./modal-view.js')
const STORE = require('./store.js')
const AppView = React.createClass({

   getInitialState: function(){


      return STORE.getStoreData()
   },

   componentWillMount: function(){
      let self = this

      STORE.onChange(function(){


         self.setState(STORE.getStoreData())
         console.log('app state changed')
      })
   },

   render: function(){

      switch (this.props.currentView) {
         case "home":
            return <HomeView highscoreData={this.state.highscore}/>
            break;
         case "login":
            return <ModalView crntView="login"/>
            break;
         case "signup":
            return <ModalView crntView="signup" avatarData ={this.state.avatars}/>
            break;
         case "game":
            return <GameView crntUser={this.state.currentUser}/>
            break;
         default:

            break;
      }

   }


})


module.exports = {AppView}
