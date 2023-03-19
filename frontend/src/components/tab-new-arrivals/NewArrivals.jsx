import React, {useState, useEffect} from "react";

import ProductGridCard from "components/product-grid-card/ProductGridCard";
import {getLatestProducts} from "api/Product";
import InfiniteScroll from "react-infinite-scroll-component";

function NewArrivals() {
  const [productList, setProductList] = useState([]);
  const [page, setPage] = useState(1);
  const [end, setEnd] = useState(false);
  const [initial, setInitial] = useState(false);

  useEffect(() => {
    console.log("useEffect runned");
    const getLatest = async () => {
      const res = await getLatestProducts(0);
      setProductList(res);
    };
    getLatest();
    setInitial(true);
  }, []);

  var fetchData = async () => {
    if (end || !initial)
      return;
    const res = await getLatestProducts(page);
    if (res.length === 0)
      setEnd(true);
    setProductList([...productList, ...res]);
    setPage(page + 1);
  }
  return (
    <InfiniteScroll
      className="infinite-scroll"
      dataLength={productList.length}
      next={fetchData}
      hasMore={!end}
      loader={<h4>Loading...</h4>}
    >
      {productList
        ? productList.map((product) => (
            <div className="product-item" key={product.id}>
              <ProductGridCard
                thumbnailUrl={product.thumbnailUrl}
                productTitle={product.name}
                startsFrom={product.startingPrice}
                key={product.id}
              />
            </div>
          ))
        : false}
    </InfiniteScroll>
  );
}

export default NewArrivals;
