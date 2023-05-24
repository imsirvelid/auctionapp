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
      if (!currentTab) res = await getActiveUserProducts();
      else res = await getSoldUserProducts();
      setProducts(res);
    };
    getProducts();
  }, [currentTab]);
  const tabs = ["Acitve", "Sold"];
  return (
    <>
      <div>
        {tabs.map((tab, index) => (
          <button
            className={`seller-tab-button ${
              index === currentTab && "selected-profile-button"
            }`}
            key={index}
            onClick={() => setCurrentTab(index)}
          >
            {tab}
          </button>
        ))}
        {tabs[currentTab].component}
        <div className="profile-bids-table">
        {products.length !== 0 ? (
          <table>
          <tbody>
            <tr className="gray-heading">
            <th></th>
              <th>Item</th>
              <th>Name</th>
              <th>Time left</th>
              <th>Your price</th>
              <th>No. Bids</th>
              <th>Highest Bid</th>
            </tr>
          {products.map((product) => (
            <tr key = {product.productId}>
            <td></td>
              <td>
                <img
                  className="bids-table-img"
                  alt="Item"
                  src={product.productThumbnail}
                />
              </td>
              <td>
                <p className="bold">{product.productName}</p>
                <p className="purple-id">#{product.productId}</p>
              </td>
              <td>{getDateDiffernece(moment(), product.endDate)}</td>
              <td>${product.myPrice}</td>
              <td>{product.numberOfBids}</td>
              <td>{product.numberOfBids !== 0 ? "$" + product.highestPrice : "/"}</td>
              <td><Button type="white"><Link to={`/products/${product.productId}`}>VIEW</Link></Button></td>
            </tr>
          ))}
          </tbody>
          </table>
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
