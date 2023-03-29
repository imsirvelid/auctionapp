import React, {useEffect, useState} from "react";
import TabView from "components/tab-view/TabView";
import "./ProductOverview.css";
import "react-image-gallery/styles/css/image-gallery.css";
import ImageGallery from "components/image-gallery/ImageGallery";
import {getProductById} from "api/Product";
import {useParams} from "react-router-dom";
import ReactHtmlParser from "react-html-parser";
import {getDateDiffFromToday} from "utils/DateHelper";
import {getProductBidInfo} from "api/Bid";
import NavigationCard from "components/navigation-card/NavigationCard";

function ProductOverview() {
  const params = useParams();
  const [product, setProduct] = useState();
  const [productBidInfo, setProductBidInfo] = useState();
  useEffect(() => {
    const getProduct = async (id) => {
      const res = await getProductById(id);
      setProduct(res);
    };
    const getProductBid = async (id) => {
      const res = await getProductBidInfo(id);
      setProductBidInfo(res);
    };
    if (!product) getProduct(params.id);
    if (!productBidInfo) getProductBid(params.id);
    console.log(product);
  });
  var tabs = [
    {
      id: 1,
      title: "Details",
      selected: true,
      component: <p key={1} className="product-info-details">{product && ReactHtmlParser(product.details)}</p>,
    }
  ];
  return (
    <>
      <NavigationCard
        name={product && product.name}
        link="SHOP"
        subLink="Single product"
      />
      <div className="container">
        {product && (
          <div className="product-overview">
            <div className="product-overview-images">
              <ImageGallery images={product.images} />
            </div>
            <div className="product-overview-info">
              <h2 className="product-overview-title">{product.name}</h2>
              <p className="product-overview-starts-from">
                Starts from{" "}
                <span className="purple-span">${product.startingPrice}</span>
              </p>
              {productBidInfo && (
                <div className="product-overview-bid-info">
                  <p>
                    Highest bid:{" "}
                    <span className="purple-span">
                      ${productBidInfo.highestBid}
                    </span>
                  </p>
                  <p>
                    Number of bids: {productBidInfo.numberOfBids}
                  </p>
                  <p>
                    Time left: {getDateDiffFromToday(product.endDate)}
                  </p>
                </div>
              )}
              <div className="tab-container">
                <TabView tabs={tabs} />
              </div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}

export default ProductOverview;
