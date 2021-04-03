import React, { useState } from "react";
import axios from "axios";
import Recipe from "../../components/Data/Recipe";

function ShowMoreResults(props) {
  function render() {
    let result = (
      <div className="col-12 text-center">
        <span className="spinner-border" />
      </div>
    );
    if (!props.loading) {
      if (props.hasMore) {
        result = (
          <div className="col-12 mt-4 mb-4 pl-0 pr-4 text-center">
            <button
              className="btn-link text-uppercase my-0 cursor-pointer"
              onClick={props.appendResults}
            >
              Show More Results
            </button>
          </div>
        );
      } else {
        result = null;
      }
    }
    return result;
  }

  return render();
}

export default function Home() {
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(false);
  const [nextOffset, setNextOffset] = useState(0);
  const [terms, setTerms] = useState("");
  const [recipes, setRecipes] = useState([]);

  function handleChange(event) {
    if (event.target.name === "terms") {
      setTerms(event.target.value);
    }
  }

  function params() {
    const string = JSON.stringify({ terms: terms, offset: nextOffset });
    return btoa(string);
  }

  function handleSubmit(event) {
    event.preventDefault();
    axios
      .get(`${process.env.REACT_APP_API}/recipes?q=${params()}`)
      .then((res) => {
        setRecipes(res.data.recipes);
        setHasMore(res.data.hasMore);
        setNextOffset(res.data.nextOffset);
        setLoading(false);
      });
  }

  function appendResults(e) {
    e.preventDefault();
    setLoading(true);
    axios
      .get(`${process.env.REACT_APP_API}/recipes?q=${params()}`)
      .then((res) => {
        setRecipes(recipes.concat(res.data.recipes));
        setHasMore(res.data.hasMore);
        setNextOffset(res.data.nextOffset);
        setLoading(false);
      });
  }

  const results = recipes.map((r) => <Recipe key={r.id} recipe={r} />);

  return (
    <main>
      <div id="results" className="container-xxl">
        <div className="row justify-content-center mt-4">
          <div className="col-12 col-md-8">
            <form onSubmit={handleSubmit}>
              <div class="input-group mb-3">
                <input
                  type="search"
                  className="form-control"
                  name="terms"
                  placeholder="Type ingredient names..."
                  value={terms}
                  onChange={handleChange}
                  autoComplete="chrome-is-buggy"
                />
                <button type="submit" className="btn btn-primary">
                  Search the Realm
                </button>
              </div>
            </form>
          </div>
        </div>
        <div className="row row-cols-auto justify-content-center">
          {results}
        </div>
        <ShowMoreResults
          loading={loading}
          hasMore={hasMore}
          appendResults={appendResults}
        />
      </div>
    </main>
  );
}
