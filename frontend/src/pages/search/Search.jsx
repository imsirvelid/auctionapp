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
  const [last, setLast] = useState(false);
  const [name, setName] = useState("");
  const [category, setCategory] = useState();
  const location = useLocation();

  useEffect(() => {
    console.log("Prvi put");
    const params = new URLSearchParams(location.search);
    setName(params.get("name"));
    setCategory(params.get("category"));
    const getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };

    const getProducts = async () => {
      const res = await searchProducts(0, "created", "DESC", 3, params.get("name"), params.get("category"));
      setProducts(res.content);
      setLast(res.last);
    };
    getCategories();
    getProducts();
  }, [location]);

  const exploreMore = async () => {
    const res = await searchProducts(nextPage, "created", "DESC", 3, name, category);
    setNextPage(nextPage + 1);
    setProducts([...products, ...res.content]);
    setLast(res.last);
  };

  return (
    <div className="container">
      <div className="main-content-search">
      <div className="categories-container">
        <div className="search-categories">
          <p className="categories-title">PRODUCT CATEGORIES</p>
          {categories.map((category) => (
            <Link
              to={`/search?category=${category.id}${
                (name !== null && name !== '') ? `&name=${name}` : ``
              }`}
              key={category.id}
            >
              <p
                className="search-category-box-p"
              >
                {category.name}
              </p>
            </Link>
          ))}
        </div>
        <div className="empty-div"></div>
        </div>
        <div className="right-search-content">
          <div className="search-products-grid">
            {products.length > 0 ? products.map((product) => (
              <div className="search-product-item" key={product.id}>
                <ProductGridCard
                  linkTo = {`/products/${product.id}`}
                  thumbnailUrl={product.images[0].url}
                  startsFrom={product.startingPrice}
                  productTitle={product.name}
                />
              </div>
            )) : <p>No products found</p>}
          </div>
          {!last && <Button type="explore-more-btn purple" onClick={exploreMore}>
            EXPLORE MORE
          </Button>}
        </div>
      </div>
    </div>
  );
}

export default Search;
