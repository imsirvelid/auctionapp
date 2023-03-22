import React from "react";
import LastChance from "components/tab-last-chance/LastChance";
import NewArrivals from "components/tab-new-arrivals/NewArrivals";
import TabView from "components/tab-view/TabView";
import "./ProductOverview.css";
function ProductOverview() {
  var tabs = [
    {
      id: 1,
      title: "Details",
      selected: true,
      component: <NewArrivals key={1} />,
    },
    {
      id: 2,
      title: "Seller information",
      selected: false,
      component: <LastChance key={2} />,
    },
    {
      id: 3,
      title: "Customer reviews",
      selected: false,
      component: <LastChance key={2} />,
    },
  ];
  return (
    <div className="container">
      <div className="product-overview">
        <div className="product-overview-images">test</div>
        <div className="product-overview-info">
          <p className="product-overview-title">
            BIYLACLESEN Womens 3-in-1 Snowboard Jacker Winter Coats
          </p>
          <p className="product-overview-starts-from">
            Starts from <span>$50</span>
          </p>
          <div className="product-overview-bid-info">
            <p>
              Highest bid: <span>$55</span>
            </p>
            <p>
              Number of bids: <span>1</span>
            </p>
            <p>
              Time left: <span>10 Weeks 6 Days</span>
            </p>
            <TabView tabs={tabs} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProductOverview;
