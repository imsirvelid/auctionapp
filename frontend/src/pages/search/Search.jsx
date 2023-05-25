import {getParentCategories} from "api/Category";
import {searchProducts} from "api/Product";
import Button from "components/button/Button";
import ProductGridCard from "components/product-grid-card/ProductGridCard";
import React, {useState, useEffect} from "react";
import {Link, useLocation} from "react-router-dom";
import "./Search.css";
import Loader from "components/Loader/Loader";

function Search() {
  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([]);
  const [nextPage, setNextPage] = useState(1);
  const [hasMore, setHasMore] = useState(false);
  const [name, setName] = useState("");
  const [categoryId, setCategory] = useState();
  const [didYouMean, setDidYouMean] = useState();
  const [loader, setLoader] = useState(true);
  const location = useLocation();
  useEffect(() => {
    setProducts([]);
    setLoader(true);
    const params = new URLSearchParams(location.search);
    setNextPage(1);
    setName(params.get("name"));
    setCategory(params.get("category"));
    const getCategories = async () => {
      const res = await getParentCategories();
      setCategories(res);
    };

    const getProducts = async () => {
      const res = await searchProducts(
        0,
        "created",
        "DESC",
        9,
        params.get("name"),
        params.get("category")
      );
      setProducts(res.page.content);
      setDidYouMean(res.didYouMeanSuggestion);
      setHasMore(!res.page.last);
      setLoader(false);
    };
    if (categories.length === 0) getCategories();
    getProducts();
  }, [location, categories.length]);

  const exploreMore = async () => {
    setLoader(true);
    const res = await searchProducts(
      nextPage,
      "created",
      "DESC",
      9,
      name,
      categoryId
    );
    setNextPage(nextPage + 1);
    setProducts([...products, ...res.page.content]);
    setDidYouMean(res.didYouMeanSuggestion);
    setHasMore(!res.page.last);
    setLoader(false);
  };

  return (
    <>
      {didYouMean && (
        <div className="did-you-mean-breadcrumb">
          <p>
            Did you mean?{" "}
            <Link
              className="did-you-mean-link"
              to={`/search?name=${didYouMean}`}
            >
              {didYouMean}
            </Link>
          </p>
        </div>
      )}
      <div className="container">
        <div className="main-content-search">
          <div className="categories-container">
            <div className="search-categories">
              <p className="categories-title">PRODUCT CATEGORIES</p>
              {categories.map((category) => (
                <Link
                  to={`/search?category=${category.id}${
                    name !== null && name !== "" ? `&name=${name}` : ``
                  }`}
                  key={category.id}
                >
                  <p
                    className={`search-category-box-p ${
                      Number(categoryId) === category.id ? "selected" : ""
                    }`}
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
              { products.length > 0 ? (
                products.map((product) => (
                  <div className="search-product-item" key={product.id}>
                    <ProductGridCard
                      linkTo={`/products/${product.id}`}
                      thumbnailUrl={product.images[0].url}
                      startsFrom={product.startingPrice}
                      productTitle={product.name}
                    />
                  </div>
                ))
              ) : !loader && (
                <p>No products found</p>
              )}
            </div>
            <div className="search-loader-div">
            <Loader
              visible={loader}
              height="121"
              width="121"
              ariaLabel="MagnifyingGlass-loading"
              wrapperStyle={{}}
              wrapperClass="MagnifyingGlass-wrapper"
              glassColor="#ECECEC"
              color="#8367D8"
            />
            </div>
            {!loader && hasMore && (
              <Button type="explore-more-btn purple" onClick={exploreMore}>
                EXPLORE MORE
              </Button>
            )}
          </div>
        </div>
      </div>
    </>
  );
}

export default Search;
