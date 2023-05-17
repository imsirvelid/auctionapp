import {getProductBidInfo} from "api/Bid";
import {getProductById, setPurchasedProduct} from "api/Product";
import React, {useState} from "react";
import {useEffect} from "react";
import {useParams} from "react-router-dom";

function PaySuccess() {
  const params = useParams();
  const [product, setProduct] = useState();
  const [productBidInfo, setProductBidInfo] = useState();

  useEffect(() => {
    const getProduct = async (id) => {
      const res = await getProductById(id);
      setProduct(res);
      if (res && res.purchased !== true) {
        setPurchasedProduct(params.id).then(response => {
          console.log(response);
        }).catch(error => {
          console.log(error);
        });
      }
    };
    const getProductBid = async (id) => {
      const res = await getProductBidInfo(id);
      setProductBidInfo(res);
    };
    getProduct(params.id);
    getProductBid(params.id);
  }, []);

  return (
    <div className="container">
      {product && productBidInfo && product.purchased !== true && ( 
        <>
          <h1 className="successfull-pay-text">
            Congratulations! You successfully purchased product{" "}
            <span className="purple-text">{product.name}</span> for $
            {productBidInfo.highestBid}
          </h1>
          <img className="successfull-pay-img" src={product.images[0].url}></img>
        </>
      )}
    </div>
  );
}

export default PaySuccess;
