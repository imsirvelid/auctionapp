import React, {useState, useEffect, useContext} from "react";

import RandomProductCard from "components/random-product-card/RandomProductCard";
import TabView from "components/tab-view/TabView";
import {getRandomProduct, getRecommendedProducts} from "api/Product";
import "./Landing.css";
import NewArrivals from "components/tab-new-arrivals/NewArrivals";
import LastChance from "components/tab-last-chance/LastChance";
import {getParentCategories} from "api/Category";
import {Link} from "react-router-dom";
import {UserContext} from "context/UserContext";
import ProductGridCard from "components/product-grid-card/ProductGridCard";

function Landing() {
  const {user} = useContext(UserContext);
  const tabs = [
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
  const [categories, setCategories] = useState([]);
  const [recommendedProducts, setRecommendedProducts] = useState([]);
  useEffect(() => {
    const random = async () => {
      const res = await getRandomProduct();
      setRandomProduct(res);
    };

    const getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };
    random();
    if (categories.length === 0) getCategories();
    if (user)
      getRecommendedProducts().then((response) => {
        setRecommendedProducts(response);
      });
  }, [categories.length, user]);

  return (
    <div className="container">
      <div className="main-content-landing">
        <div className="categories-container">
          <div className="landing-categories">
            <p className="categories-title">CATEGORIES</p>
            {categories.map((category) => (
              <Link to={`/search?category=${category.id}`} key={category.id}>
                <p className="category-box-p">{category.name}</p>
              </Link>
            ))}
          </div>
          <div className="empty-div"></div>
        </div>
        <div className="random-product-landing">
          {randomProduct && (
            <RandomProductCard product={randomProduct} /> 
          )}
        </div>
      </div>
      <div className="tab-container">
        <div className="tab-view-header">
          <p className="featured-products-landing-p">Featured Products</p>
        </div>
        <div className="featured-products">
          {recommendedProducts.map((product, index) => (
            <div className="featured-product-item" key={index}>
              <ProductGridCard
                linkTo={`/products/${product.id}`}
                thumbnailUrl={product.productThumbnail}
                productTitle={product.productName}
                startsFrom={product.productPrice}
                key={product.id}
              />
            </div>
          ))}
        </div>
      </div>
      <div className="tab-container">
        <TabView tabs={tabs} optionSize="option-size-normal" />
      </div>
    </div>
  );
}

export default Landing;
