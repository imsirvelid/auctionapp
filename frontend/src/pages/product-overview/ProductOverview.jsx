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
      component: <p>Details about random product</p>,
    },
    {
      id: 2,
      title: "Seller information",
      selected: false,
      component: <p>Seller informations</p>,
    },
    {
      id: 3,
      title: "Customer reviews",
      selected: false,
      component: (
        <p>Customer reviews, lorem ipsum dolor sit amet condesectur enit</p>
      ),
    },
  ];
  return (
    <div className="container">
      <div className="product-overview">
        <div className="product-overview-images">test</div>
        <div className="product-overview-info">
          <h2 className="product-overview-title">
            BIYLACLESEN Womens 3-in-1 Snowboard Jacker Winter Coats
          </h2>
          <p className="product-overview-starts-from">
            Starts from <span className="purple-span">$50</span>
          </p>
          <div className="product-overview-bid-info">
            <p>
              Highest bid: <span className="purple-span">$55</span>
            </p>
            <p>
              Number of bids: <span className="purple-span">1</span>
            </p>
            <p>
              Time left: <span className="purple-span">10 Weeks 6 Days</span>
            </p>
          </div>
          <div className="tab-container">
            <TabView tabs={tabs} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProductOverview;
