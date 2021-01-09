import { Link } from 'react-router-dom';
export default function Footer(props) {
  return (
    <footer>
      <p>Your friendly vegan resources search engine, cruelty free and full of goodies. <Link to="/about">Learn More</Link>.</p>
      <p>Currently serving <span id="recipes-count">{props.recipesCount}</span> recipes.</p>
    </footer>
  );
}