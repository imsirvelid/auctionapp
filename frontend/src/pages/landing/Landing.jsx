import React, {useState, useEffect} from "react";

import RandomProductCard from "components/random-product-card/RandomProductCard";
import TabView from "components/tab-view/TabView";
import {getRandomProduct} from "api/Product";
import "./Landing.css";
import NewArrivals from "components/tab-new-arrivals/NewArrivals";
import LastChance from "components/tab-last-chance/LastChance";
import {getParentCategories} from "api/Category";

function Landing() {
  var tabs = [
    {
      id: 1,
      title: "New Arrivals",
      selected: true,
      component: <NewArrivals key={1} />,
    },
    {
      id: 2,
      key: 2,
      title: "Last Chance",
      selected: false,
      component: <LastChance key={2} />,
    },
  ];
  const [randomProduct, setRandomProduct] = useState();
  const [categories, setCategories] = useState();
  useEffect(() => {
    var random = async () => {
      const res = await getRandomProduct();
      setRandomProduct(res);
    };

    var getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };
    if (!randomProduct) random();
    if (!categories) getCategories();
  });

  return (
    <div className="container">
      <div className="main-content-landing">
        <div className="landing-categories">
          <p className="categories-title">CATEGORIES</p>
          {categories &&
            categories.map((category) => (
              <p className="category-box-p" key={category.id}>
                {category.name}
              </p>
            ))}
          <p className="category-box-p-other">Other categories</p>
        </div>
        <div className="random-product-landing">
          {randomProduct && (
            <RandomProductCard product={randomProduct} />
          )}
        </div>
      </div>
      <div className="tab-container">
        <TabView tabs={tabs} />
      </div>
    </div>
  );
}

export default Landing;
