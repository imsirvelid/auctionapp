import React, {useEffect, useState} from "react";
import TabView from "components/tab-view/TabView";
import "./ProductOverview.css";
import "react-image-gallery/styles/css/image-gallery.css";
import ImageGallery from "components/image-gallery/ImageGallery";
import {getProductById} from "api/Product";
import {useParams} from "react-router-dom";
import ReactHtmlParser from "react-html-parser";
import { getDateDiffFromToday } from "utils/DateHelper";

function ProductOverview() {
  const params = useParams();
  const [product, setProduct] = useState();
  useEffect(() => {
    const getProduct = async (id) => {
      const res = await getProductById(id);
      setProduct(res);
    };
    if (!product) getProduct(params.id);
    console.log(product);
  });
  var tabs = [
    {
      id: 1,
      title: "Details",
      selected: true,
      component: <p key={1}>{product && ReactHtmlParser(product.details)}</p>,
    },
    {
      id: 2,
      title: "Seller information",
      selected: false,
      component: <p key={2}>Seller informations</p>,
    },
    {
      id: 3,
      title: "Customer reviews",
      selected: false,
      component: (
        <p key={3}>
          Customer reviews, lorem ipsum dolor sit amet condesectur enit
        </p>
      ),
    },
  ];
  return (
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
            <div className="product-overview-bid-info">
              <p>
                Highest bid: <span className="purple-span">$55</span>
              </p>
              <p>
                Number of bids: <span className="purple-span">1</span>
              </p>
              <p>
                Time left: <span className="purple-span">{getDateDiffFromToday(product.endDate)}</span>
              </p>
            </div>
            <div className="tab-container">
              <TabView tabs={tabs} />
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ProductOverview;
