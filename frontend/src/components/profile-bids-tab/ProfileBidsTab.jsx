import React, {useEffect, useState} from "react";
import "./ProfileBidsTab.css";
import Button from "components/button/Button";
import {getBidsForUser} from "api/Bid";
import {getDateDiffernece} from "utils/DateHelper";
import moment from "moment";
import {Link} from "react-router-dom";
import {faGavel} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

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
        {bids.length !== 0 ? (
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
          {bids.map((bid) => (
            <tr key={bid.productId}>
            <td></td>
              <td>
                <img
                  className="bids-table-img"
                  alt="Item"
                  src={bid.productThumbnail}
                />
              </td>
              <td>
                <p className="bold">{bid.productName}</p>
                <p className="purple-id">#{bid.productId}</p>
              </td>
              <td>{getDateDiffernece(moment(), bid.endDate)}</td>
              <td className={`${bid.myPrice === bid.highestPrice && "green-bold"}`}>${bid.myPrice}</td>
              <td>{bid.numberOfBids}</td>
              <td className={`${bid.myPrice === bid.highestPrice ? "green-bold" : "blue-bold"}`}>${bid.highestPrice}</td>
              <td><Button type="white"><Link to={`/products/${bid.productId}`}>VIEW</Link></Button></td>
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
              You don't have any bids and there are so many cool products
              available for sale.
            </p>
            <Button>
              <Link to="/search">START BIDDING</Link>
            </Button>
          </div>
        )}
    </div>
  );
}

export default ProfileBidsTab;
