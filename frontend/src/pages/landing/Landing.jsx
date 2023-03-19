import ProductGridCard from "components/product-grid-card/ProductGridCard";
import RandomProductCard from "components/random-product-card/RandomProductCard";
import TabView from "components/tab-view/TabView";
import React, { useState, useEffect } from "react";

import { getRandomProduct } from "api/Product";
import "./Landing.css";
import NewArrivals from "components/tab-new-arrivals/NewArrivals";

function Landing() {
  var tabs = [
    {
      id: 1,
      title: "New Arrivals",
      selected: true,
      component: <NewArrivals></NewArrivals>
    },
    {
      id: 2,
      title: "Last Chance",
      selected: false,
      component: null
    },
  ];
  const [ randomProduct, setRandomProduct ] = useState();
  useEffect(() => {
    var random = async () => {
      const res = await getRandomProduct();
      setRandomProduct(res);
    }
    if (!randomProduct)
      random();
  });

  return (
    <div className="container">
      <div className="main-content-landing">
        <div className="landing-categories">
          <p className="categories-title">CATEGORIES</p>
          <p className="category-box-p">Fashion</p>
          <p className="category-box-p">Accessories</p>
          <p className="category-box-p">Computers</p>
          <p className="category-box-p">Cars</p>
          <p className="category-box-p">Hardware</p>
          <p className="category-box-p">Electronics</p>
          <p className="category-box-p">Jewlery</p>
          <p className="category-box-p">Sportware</p>
          <p className="category-box-p">Art</p>
          <p className="category-box-p-other">Other categories</p>
        </div>
        <div className="random-product-landing">
          {randomProduct && <RandomProductCard product={randomProduct}></RandomProductCard>}
        </div>
      </div>
      <div className="tab-container">
        <TabView tabs={tabs}></TabView>        
      </div>
    </div>
  );
}

export default Landing;
