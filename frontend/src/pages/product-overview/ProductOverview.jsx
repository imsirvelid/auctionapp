import React, { useEffect } from "react";
import TabView from "components/tab-view/TabView";
import "./ProductOverview.css";
import "react-image-gallery/styles/css/image-gallery.css";
import ImageGallery from "components/image-gallery/ImageGallery";
import { getProductById } from "api/Product";
import { useParams } from "react-router-dom";

function ProductOverview() {
  const params = useParams();
  console.log(params);
  /*useEffect(() => {
    const getProduct = async() => {
      const res = await getProductById();
    }
  });*/
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

  var images = [
    {
      key: 1,
      original: 'https://picsum.photos/id/1018/1000/600/',
      thumbnail: 'https://picsum.photos/id/1018/250/150/',
    },
    {
      key: 2,
      original: 'https://picsum.photos/id/1015/1000/600/',
      thumbnail: 'https://picsum.photos/id/1015/250/150/',
    },
    {
      key: 3,
      original: 'https://picsum.photos/id/1019/1000/600/',
      thumbnail: 'https://picsum.photos/id/1019/250/150/',
    },
  ];
  return (
    <div className="container">
      <div className="product-overview">
        <div className="product-overview-images">
          <ImageGallery images={images}/>
        </div>
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
