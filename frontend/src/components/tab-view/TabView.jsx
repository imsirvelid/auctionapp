import React, {useState, useEffect} from "react";

import {getLatestProducts, getRandomProduct} from "api/Product";
import "./TabView.css";
import ProductGridCard from "components/product-grid-card/ProductGridCard";
import InfiniteScroll from "react-infinite-scroll-component";

function TabView(props) {
  const [productList, setProductList] = useState([]);
  const tabs = props.tabs;
  const [currentTab, setCurrentTab] = useState(tabs[0].id);
  const [page, setPage] = useState(1);
  const [end, setEnd] = useState(false);
  useEffect(() => {
    const getLatest = async () => {
      const res = await getLatestProducts(0);
      setProductList(res);
    };
    getLatest();
  }, []);

  var fetchData = async () => {
    if (end)
      return;
    const res = await getLatestProducts(page);
    if (res.length == 0)
      setEnd(true);
    setProductList([...productList, ...res]);
    setPage(page + 1);
  }

  return (
    <>
      <div className="tab-view-header">
        {true ? (
          tabs.map((tab) => (
            <p
              className={`tab-view-option ${
                tab.id === currentTab ? "tvo-selected" : ""
              }`}
              key={tab.id}
              onClick={() => setCurrentTab(tab.id)}
            >
              {tab.title}
            </p>
          ))
        ) : (
          <></>
        )}
      </div>
      <InfiniteScroll className="infinite-scroll"
        dataLength={productList.length}
        next={fetchData}
        hasMore={!end}
        loader={<h4>Loading...</h4>}
        endMessage={
          <p style={{textAlign: "center"}}>
            <b>Yay! You have seen it all</b>
          </p>
        }
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
    </>
  );
}

export default TabView;
