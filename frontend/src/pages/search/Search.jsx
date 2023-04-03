import {getParentCategories} from "api/Category";
import {searchProducts} from "api/Product";
import Button from "components/button/Button";
import ProductGridCard from "components/product-grid-card/ProductGridCard";
import React, {useState, useEffect} from "react";
import { Link } from "react-router-dom";
import "./Search.css";

function Search() {
  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([]);
  const [nextPage, setNextPage] = useState(1);

  useEffect(() => {
    const getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };

    const getProducts = async () => {
      const res = await searchProducts(0, "created", "DESC", 3);
      setProducts(res);
    };
    getCategories();
    getProducts();
  }, []);

  const exploreMore = async () => {
    const res = await searchProducts(nextPage, "created", "DESC", 3);
    setNextPage(nextPage + 1);
    setProducts([...products, ...res]);
  }
  return (
    <div className="container">
      <div className="main-content-search">
        <div className="landing-categories">
          <p className="categories-title">CATEGORIES</p>
          {categories.map((category) => (
            <p className="category-box-p" key={category.id}>
              {category.name}
            </p>
          ))}
        </div>
        <div className="right-search-content">
          <div className="search-products-grid">
            {products.map((product) => (
              <div className="search-product-item" key = {product.id}>
              <Link to={`/products/${product.id}`}>
              <ProductGridCard
                thumbnailUrl={product.images[0].imageUrl}
                startsFrom={product.startingPrice}
                productTitle={product.name}
              />
              </Link>
              </div>
            ))}
          </div>
          <Button type="explore-more-btn purple" onClick={exploreMore}>EXPLORE MORE</Button>
        </div>
      </div>
    </div>
  );
}

export default Search;
