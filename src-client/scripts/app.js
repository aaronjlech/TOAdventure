const ReactDOM = require('react-dom');
const React = require('react')
const Backbone = require('backbone');
const ACTIONS = require('./actions.js')
const STORE = require('./store.js')
const {AppView} = require('./view-controller.js')

const AppRouter = Backbone.Router.extend({

   routes: {
      'game': "showGame",
      'login': "showLogin",
      'signup': "showSignup",
      '': "showHome"

   },

   showHome: function(){

console.log('hello')
      ReactDOM.render(<AppView currentView="home"/>, document.querySelector("#app-container"))
   },

   showLogin: function(){

      return renderDom(<AppView currentView="login"/>)
   },

   showSignup: function(){

      // return renderDom(<AppView currentView="signup"/>)
   },

   showGame: function(){
      console.log('game viieww')

      ReactDOM.render(<AppView currentView="game"/>, document.querySelector("#app-container"))
   },

   initialize: function(){

      Backbone.history.start()
   }

})

   new AppRouter()