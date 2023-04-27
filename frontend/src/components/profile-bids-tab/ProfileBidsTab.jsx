import React from "react";
import "./ProfileBidsTab.css";
import Button from "components/button/Button";

function ProfileBidsTab() {
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
      <div className="bids-table-row">
        <div className="bids-table-item">
          <img
            className="bids-table-img"
            alt="Item"
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Patek-Philippe-Nautilus-5711-1A-010-1.jpg/479px-Patek-Philippe-Nautilus-5711-1A-010-1.jpg?20211118111020"
          />
        </div>
        <div className="bids-table-name"><p className="bold">Philipe Patek Nautilus 5711</p><p className="purple-id">#2145</p></div>
        <div className="bids-table-left">12h</div>
        <div className="bids-table-price blue-bold">$1255.20</div>
        <div className="bids-table-bids">4</div>
        <div className="bids-table-highest green-bold">$1255.20</div>
        <div className="bids-table-empty"><Button type="white">VIEW</Button></div>
      </div>
    </div>
  );
}

export default ProfileBidsTab;
