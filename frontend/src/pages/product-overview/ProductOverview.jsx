import React, {useContext, useEffect, useState} from "react";
import TabView from "components/tab-view/TabView";
import "./ProductOverview.css";
import moment from "moment";
import "react-image-gallery/styles/css/image-gallery.css";
import ImageGallery from "components/image-gallery/ImageGallery";
import {getProductById} from "api/Product";
import {useParams} from "react-router-dom";
import {getDateDiffernece} from "utils/DateHelper";
import {bid, getProductBidInfo} from "api/Bid";
import NavigationCard from "components/navigation-card/NavigationCard";
import Button from "components/button/Button";
import Input from "components/text-input/Input";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faAngleRight, faMoneyBill1Wave} from "@fortawesome/free-solid-svg-icons";
import {UserContext} from "context/UserContext";
import { getErrorMessage } from "utils/ErrorHelper";
import { getPaymentIntent } from "api/Payment";
import { counter } from "@fortawesome/fontawesome-svg-core";

function ProductOverview() {
  const params = useParams();
  const [product, setProduct] = useState();
  const [productBidInfo, setProductBidInfo] = useState();
  const [enteredPrice, setPrice] = useState("");
  const [message, setMessage] = useState("");
  const [messageStyle, setMessageStyle] = useState("");
  const {user} = useContext(UserContext);

  const handlePayment = async() => {
    const getPaymentRoute = async (request) => {
      const res = await getPaymentIntent(request);
      window.location.replace(res);
    }

    getPaymentRoute(params.id);
  }

  useEffect(() => {
    const getProduct = async (id) => {
      const res = await getProductById(id);
      setProduct(res);
    };
    const getProductBid = async (id) => {
      const res = await getProductBidInfo(id);
      setProductBidInfo(res);
    };
    getProduct(params.id);
    getProductBid(params.id);
  }, [params.id]);
  const tabs = [
    {
      id: 1,
      title: "Details",
      selected: true,
      component: (
        <p key={1} className="product-info-details">
          {product && product.details}
        </p>
      ),
    },
  ];

  const placeBid = async () => {
    try {
      const bidResponse = await bid({
        productId: product.id,
        price: enteredPrice,
      });
      setMessage(bidResponse.message);
      if (bidResponse.successful) setMessageStyle("success");
      else setMessageStyle("error");
    } catch (exception) {
      setMessageStyle("error");
      setMessage(getErrorMessage(exception));
    }
  };

  return (
    <>
      <NavigationCard
        name={product && product.name}
        link="SHOP"
        subLink="Single product"
        linkTo="/search"
      />
      {message && (
        <div className={`bid-message ${messageStyle}`}>
          <div className="current-location-flexdiv">{message}</div>
        </div>
      )}
      <div className="container container-top">
        {product && (
          <div className="product-overview">
            <div className="product-overview-images">
              <ImageGallery images={product.images} />
            </div>
            <div className="product-overview-info">
              <h2 className="product-overview-title">{product.name}</h2>
              <p className="product-overview-starts-from">
                Starts from&nbsp;
                <span className="purple-span">${product.startingPrice}</span>
              </p>
              {user && product.user.id !== user.id && moment(product.endDate).format("YYYY-MM-DD") > moment().format("YYYY-MM-DD") && (
                <div className="enter-bid-container">
                  <Input
                    value={enteredPrice}
                    onChange={(e) => setPrice(e.target.value)}
                    width="111px"
                    type="gray"
                  />
                  <Button onClick={placeBid}>
                    PLACE BID <FontAwesomeIcon icon={faAngleRight} />{" "}
                  </Button>
                  <p>
                    Enter{" "}
                    {productBidInfo
                      ? productBidInfo.highestBid
                        ? " price greater than $" + productBidInfo.highestBid
                        : "$" + product.startingPrice + " or more "
                      : ""}
                  </p>
                </div>
              )}
              {productBidInfo && (
                <div className="product-overview-bid-info">
                  <p>
                    Highest bid:
                    <span className="purple-span">
                      {productBidInfo.highestBid
                        ? " $" + productBidInfo.highestBid
                        : " /"}
                    </span>
                  </p>
                  <p>Number of bids: {productBidInfo.numberOfBids}</p>
                  <p>
                    Time left: {getDateDiffernece(moment(), product.endDate)}
                  </p>
                </div>
              )}
              {productBidInfo && getDateDiffernece(moment(), product.endDate) === 0 && user.id === productBidInfo.userId && (<div className="pay-button-div">
                <Button type="white" onClick={handlePayment}>Pay <FontAwesomeIcon icon={faMoneyBill1Wave} /></Button>
              </div>)}

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