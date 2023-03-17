import ProductGridCard from "components/product-grid-card/ProductGridCard";
import React from "react";

import "./Landing.css";

function Landing() {
  return (
    <div className="container">
      <div className="tab-container">
      <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://shop.imel.ba/wp-content/uploads/2022/10/Z14V0023L.jpg"
          productTitle="Macbook Pro 16"
          startsFrom="1695.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
          productTitle="Nike Air force 1"
          startsFrom="149.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://static.wikia.nocookie.net/marveldatabase/images/8/8b/Time_Gem_from_Doctor_Strange_Vol_5_3_001.jpg"
          productTitle="Time stone"
          startsFrom="1494520.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://images.unsplash.com/photo-1663564305613-c40450f29903?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
          productTitle="Patek Phillipe Nautilus"
          startsFrom="25512.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://shop.imel.ba/wp-content/uploads/2022/10/Z14V0023L.jpg"
          productTitle="Macbook Pro 16"
          startsFrom="1695.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
          productTitle="Nike Air force 1"
          startsFrom="149.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://static.wikia.nocookie.net/marveldatabase/images/8/8b/Time_Gem_from_Doctor_Strange_Vol_5_3_001.jpg"
          productTitle="Time stone"
          startsFrom="1494520.25"
        />
        </div>
        <div className="product-item">
        <ProductGridCard
          thumbnailUrl="https://images.unsplash.com/photo-1663564305613-c40450f29903?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
          productTitle="Patek Phillipe Nautilus"
          startsFrom="25512.25"
        />
        </div>
      </div>
    </div>
  );
}

export default Landing;
