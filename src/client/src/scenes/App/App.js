import React, {useState, useEffect} from 'react';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import axios from 'axios';
import Header from '../../components/Header/Header';
import Home from '../Home/Home';
import About from "../About/About";
import Footer from "../../components/Footer/Footer";

export default function App() {
  const [recipesCount, setRecipesCount] = useState(null);


  useEffect(() =>{
    axios.get(`${process.env.REACT_APP_API}/statistics/recipes-count`).then(res => {
      setRecipesCount(res.data);
    });
  }, [])

  return (
    <main className="App">
      <Router>
        <Header/>
        <Switch>
          <Route path="/about">
            <About/>
          </Route>
          <Route path="/">
            <Home/>
          </Route>
        </Switch>
        <Footer recipesCount={recipesCount}/>
      </Router>
    </main>
  );
}