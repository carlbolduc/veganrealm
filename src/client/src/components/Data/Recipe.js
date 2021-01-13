export default function Recipe(props) {
  return (
    <article className="recipe card col">
      <img src={props.recipe.imageLink} className="card-img-top" alt="bidu"/>
      <section className="card-body">
      <h5 className="card-title"><a href={props.recipe.link}>{props.recipe.title}</a></h5>
      <h6>Published by <a href="https://keepinitkind.com"><strong>{props.recipe.author}</strong></a> on {props.recipe.plublishedAt}</h6>
      <div className="ingredients">
        <ul className="small">
          <li>2 cups pitted Medjool Dates</li>
          <li>1 cup Almond Meal</li>
          <li>1 cup sweetened Coconut flakes</li>
          <li>1/2 cup Cacao Nibs</li>
          <li>1/2 cup ground Organic Chia Seeds</li>
          <li>1/4 cup Maca/Cacao/ Hemp Powder</li>
          <li>( I used Navitas brand Organic Superfoods blend)</li>
        </ul>
      </div>
      </section>
    </article>
  );
}