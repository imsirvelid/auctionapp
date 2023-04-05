import {getParentCategories} from "api/Category";
import {searchProducts} from "api/Product";
import Button from "components/button/Button";
import ProductGridCard from "components/product-grid-card/ProductGridCard";
import React, {useState, useEffect} from "react";
import {Link, useLocation} from "react-router-dom";
import "./Search.css";

function Search() {
  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([]);
  const [nextPage, setNextPage] = useState(1);
  const [category, setCategory] = useState(null);
  const [name, setName] = useState("");
  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    params.get("name") ? setName(params.get("name")) : setName("");
    setCategory(params.get("category"));
    const getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };

    const getProducts = async () => {
      const res = await searchProducts(0, "created", "DESC", 3, name, category);
      setProducts(res);
    };
    getCategories();
    getProducts();
  }, [location, category, name]);

  const exploreMore = async () => {
    const res = await searchProducts(nextPage, "created", "DESC", 3, name, category);
    setNextPage(nextPage + 1);
    setProducts([...products, ...res]);
  };

  return (
    <div className="container">
      <div className="main-content-search">
        <div className="landing-categories">
          <p className="categories-title">CATEGORIES</p>
          {categories.map((category) => (
            <Link
              to={`/search?category=${category.id}${
                name !== "" ? `&name=${name}` : ``
              }`}
              key={category.id}
            >
              <p
                className="category-box-p"
                key={category.id}
                onClick={() => setCategory(category.id)}
              >
                {category.name}
              </p>
            </Link>
          ))}
        </div>
        <div className="right-search-content">
          <div className="search-products-grid">
            {products.map((product) => (
              <div className="search-product-item" key={product.id}>
                <ProductGridCard
                  thumbnailUrl={product.images[0].url}
                  startsFrom={product.startingPrice}
                  productTitle={product.name}
                />
              </div>
            ))}
          </div>
          <Button type="explore-more-btn purple" onClick={exploreMore}>
            EXPLORE MORE
          </Button>
        </div>
      </div>
    </div>
  );
}

export default Search;
