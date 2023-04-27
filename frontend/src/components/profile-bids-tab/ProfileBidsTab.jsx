import React, {useEffect, useState} from "react";
import "./ProfileBidsTab.css";
import Button from "components/button/Button";
import {getBidsForUser} from "api/Bid";
import { getDateDiffernece } from "utils/DateHelper";
import moment from "moment";
import { Link } from "react-router-dom";

function ProfileBidsTab() {
  const [bids, setBids] = useState([]);

  useEffect(() => {
    const getBids = async () => {
      const res = await getBidsForUser();
      setBids(res);
    };
    getBids();
  }, []);

  return (
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
      {bids.map((bid, index) => (
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
          <div className="bids-table-left">{getDateDiffernece(moment(), bid.endDate)}</div>
          <div className={`bids-table-price ${bid.myPrice === bid.highestPrice && "green-bold"}`}>${bid.myPrice}</div>
          <div className="bids-table-bids">{bid.numberOfBids}</div>
          <div className={`bids-table-price ${bid.myPrice === bid.highestPrice ? "green-bold" : "blue-bold"}`}>${bid.highestPrice}</div>
          <div className="bids-table-empty">
            <Button type="white"><Link to={`/products/${bid.productId}`}>VIEW</Link></Button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProfileBidsTab;
