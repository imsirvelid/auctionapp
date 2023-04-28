import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faGavel} from "@fortawesome/free-solid-svg-icons";
import "./ProfileSellerTab.css";
import Button from "components/button/Button";
import {Link} from "react-router-dom";
import moment from "moment";
import {getDateDiffernece} from "utils/DateHelper";
import { getActiveUserProducts, getSoldUserProducts } from "api/Product";

function ProfileSellerTab() {
  const [currentTab, setCurrentTab] = useState(0);
  const [products, setProducts] = useState([]);
  const messages = ["active", "sold"]

  useEffect(() => {
    const getProducts = async () => {
      let res = []
      if (currentTab === 0) res = await getActiveUserProducts();
      else res = await getSoldUserProducts();
      setProducts(res);
    };
    getProducts();
  }, [currentTab]);
  const tabs = [
    {
      name: "Active",
      component: "",
    },
    {
      name: "Sold",
      component: "",
    },
  ];
  return (
    <>
      <div>
        {tabs.map((tab, index) => (
          <button
            className={`seller-tab-button ${
              index === currentTab && "selected-profile-button"
            }`}
            key={index}
            onClick={(e) => setCurrentTab(index)}
          >
            {tab.name}
          </button>
        ))}
        {tabs[currentTab].component}
        <div className="profile-bids-table">
          <div className="gray-heading">
            <div className="bids-table-item">Item</div>
            <div className="bids-table-name">Name</div>
            <div className="bids-table-left">Time Left</div>
            <div className="bids-table-price">Your Price</div>
            <div className="bids-table-bids">No. Bids</div>
            <div className="bids-table-highest">Highest Bid</div>
            <div className="bids-table-empty"></div>
          </div>
          {products.length !== 0 ? (
            products.map((bid, index) => (
              <div className="bids-table-row" key={index}>
                <div className="bids-table-item">
                  <img
                    className="bids-table-img"
                    alt="Item"
                    src={bid.productThumbnail}
                  />
                </div>
                <div className="bids-table-name">
                  <p className="bold">{bid.productName}</p>
                  <p className="purple-id">#{bid.productId}</p>
                </div>
                <div className="bids-table-left">
                  {getDateDiffernece(moment(), bid.endDate)}
                </div>
                <div
                  className={`bids-table-price ${
                    bid.myPrice === bid.highestPrice && "green-bold"
                  }`}
                >
                  ${Number(bid.myPrice)}
                </div>
                <div className="bids-table-bids">{Number(bid.numberOfBids)}</div>
                <div
                  className={`bids-table-price`}
                >
                  ${Number(bid.highestPrice)}
                </div>
                <div className="bids-table-empty">
                  <Button type="white">
                    <Link to={`/products/${bid.productId}`}>VIEW</Link>
                  </Button>
                </div>
              </div>
            ))
          ) : (
            <div className="empty-table-bids">
              <FontAwesomeIcon
                className="purple-icon"
                icon={faGavel}
                flip="horizontal"
              />
              <p>
                You don't have any {messages[currentTab]} products.
              </p>
              <Button>
                <Link to="/search">START SELLING</Link>
              </Button>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default ProfileSellerTab;
