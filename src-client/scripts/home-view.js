const React = require('react')
const ReactDOM = require('react-dom')
const Backbone = require('backbone')
const ModalView = require('./modal-view.js')
const ACTIONS = require('./actions.js')


let dummyUsers = [
   {
      userName: 'victor',
      score: '1000',
      level: '5'
   },
   {
      userName: 'beka',
      score: '14500',
      level: '4'
   },
   {
      userName: 'torbor',
      score: '109800',
      level: '2'
   },
   {
      userName: 'ictober',
      score: '1009860',
      level: '5'
   },
   {
      userName: 'lizzy',
      score: '54600',
      level: '8'
   },
   {
      userName: 'victor2',
      score: '8765',
      level: '5'
   }

]
const NavView = React.createClass({


   render:function(){

      return(
         <nav className="navbar navbar-default">
            <div className="container-fluid">
               <div className="navbar-header">
                  <a className="navbar-brand" href="#">TIY Adventure</a>
               </div>

               <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  <ul className="nav navbar-nav">
                     <li className="active"><a href="#">Home<span className="sr-only">(current)</span></a></li>
                     <li><a href="#game">Play</a></li>

                  </ul>
                  <form className="navbar-form navbar-left" role="search">
                     <div className="form-group">
                     </div>

                  </form>
                  <ul className="nav navbar-nav navbar-right">
                     <li><a href="login">Login</a></li>
                  </ul>
               </div>
            </div>
         </nav>
      )
   }


})


const HomeView = React.createClass({


   _loginEvt: function(){
      window.location.hash = "login"
   },

   _signinEvt: function(){
      window.location.hash = "signup"
   },

   componentWillMount: function(){

      ACTIONS.fetchHighScores()
   },




   render: function(){

      let scoreList = this.props.highscoreData
      console.log(scoreList)


      let leaderBoardData = scoreList.map((someData, i)=>{
         return (
            <li key={i}>
               <p>
                  <span>{someData.attributes.user.username}</span>
                  <span>{someData.attributes.score}</span>
               </p>
            </li>
         )
      })
      return (

         <div>
            
            <NavView/>
            <div>
               <div>
                  <img src="../ui/cooltext215660600474232.png" className="homeLogo"/>
               </div>
               <div className="col-sm-3 homeBoxes homeBoard">
                  <h2>LeaderBoard</h2>
                  <ol>
                     {leaderBoardData}
                  </ol>
               </div>
               <div className="col-sm-9 homeBoxes homeContent">
                  <div>
                     <h1>TIYO Adventure</h1>
                     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                     <button onClick={this._loginEvt} className="btn btn-primary">Login</button>
                     <button onClick={this._signinEvt} className="btn btn-primary">Sign-up</button>
                  </div>
               </div>
            </div>
            <footer className="navbar navbar-fixed-bottom">
               <div>
                  <p>Copyright © 2016 TIY Adventure Foundation. All Rights Reserved.</p>
               </div>
               <div className="gitHolder">
                  <a href="https://github.com/mikeplott" className="gitUsers">Mikeplott</a>
                  <a href="https://github.com/vicula" className="gitUsers">Vicula</a>
                  <a href="https://github.com/aaronjlech" className="gitUsers">Aaronjlech</a>
               </div>
            </footer>
         </div>
      )
   }

})


module.exports = {HomeView, dummyUsers, NavView}
