import React, {useState} from 'react';
import axios from "axios";
import Recipe from "../../components/Data/Recipe";

export default function Home() {
  const [search, setSearch] = useState("");
  const [recipes, setRecipes] = useState([]);

  function handleChange(event) {
    if (event.target.name === "search") {
      setSearch(event.target.value);
    }
  }

  function handleSubmit(event) {
    event.preventDefault();
    axios.get(`${process.env.REACT_APP_API}/recipes/${search}`).then(res => {
      setRecipes(res.data.recipes);
    });
  }

  const results = recipes.map(r => (
    <Recipe key={r.id} recipe={r}/>
  ));

  return (
    <main>
      <form onSubmit={handleSubmit}>
        <input
          type="search"
          className="form-control"
          name="search"
          placeholder="Type ingredient names..."
          value={search}
          onChange={handleChange}
        />
        <button
          type="submit"
          className="btn btn-primary"
        >
          Search the Realm
        </button>
      </form>
      <div id="results" className="container">
        <div className="row row-cols-auto justify-content-center">
          {results}
        </div>
      </div>
    </main>
  );
}