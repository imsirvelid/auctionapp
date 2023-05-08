import React from "react";
import "./Sell.css";
import AddItem from "components/add-item/AddItem";
import SetPrices from "components/set-prices/SetPrices";
import LocationAndShipping from "components/location-and-shipping/LocationAndShipping";
import {useState} from "react";
import { createProduct, uploadProductImages } from "api/Product";
import moment from "moment/moment";
import { updateOrCreateCreditCardInfo } from "api/CreditCard";
import { updateUserAddress } from "api/User";
import { useNavigate } from "react-router-dom";

function Sell() {
  const [currentStep, setCurrentStep] = useState(0);
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [subCategory, setSubCategory] = useState(0);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [description, setDescription] = useState("");
  const [address, setAddress] = useState("");
  const [country, setCountry] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [nameOnCard, setNameOnCard] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [expMonth, setExpMonth] = useState(0);
  const [expYear, setExpYear] = useState(0);
  const [startingPrice, setStartingPrice] = useState("");
  const [cvc, setCvc] = useState("");
  const [files, setFiles] = useState([]);
  const [creditCardInfo, setCreditCardInfo] = useState(false);
  const [featured, setFeatured] = useState(0);
  const [creditCardId, setCreditCardId] = useState(null);
  const [city, setCity] = useState("");
  const [zipCode, setZipCode] = useState("");
  const navigate = useNavigate();
  const handleNextAndTest = () => {
    setCurrentStep(1);
  };

  const submitForm = () => {
    uploadProductImages(files).then(response => {
      const productRequest = {
        productName: name,
        categoryId: subCategory,
        startDate: moment(startDate).toISOString(),
        description: description,
        endDate: moment(endDate).toISOString(),
        startingPrice: startingPrice,
        featuredImg: featured,
        images: response
      };
      createProduct(productRequest).then(response => {
        navigate("/products/" + response.id);
      });
    });

    const creditCardRequest = {
      id: creditCardId,
      nameOnCard: nameOnCard,
      cardNumber: cardNumber,
      expirationMonth: expMonth,
      expirationYear: expYear,
      cvc: cvc
    };

    updateOrCreateCreditCardInfo(creditCardRequest).then(response => {
      const shippingInfo = {
        phone: phoneNumber,
        city: city,
        zipCode: zipCode,
        address: address,
        country: country,
        phoneNumber: phoneNumber,
      };
      updateUserAddress(shippingInfo).then(response => {
      });
    });
  };

  const steps = [
    <AddItem
      productName={{value: name, set: setName}}
      description={{value: description, set: setDescription}}
      category={{value: category, set: setCategory}}
      subCategory={{value: subCategory, set: setSubCategory}}
      files={{value: files, set: setFiles}}
      featured={{value: featured, set: setFeatured}}
      onNext={() => handleNextAndTest()}
    />,
    <SetPrices
      startingPrice={{value: startingPrice, set: setStartingPrice}}
      startDate={{value: startDate, set: setStartDate}}
      endDate={{value: endDate, set: setEndDate}}
      onNext={() => setCurrentStep(2)}
      onBack={() => setCurrentStep(0)}
    />,
    <LocationAndShipping
      country={{value: country, set: setCountry}}
      address={{value: address, set: setAddress}}
      cardNumber={{value: cardNumber, set: setCardNumber}}
      nameOnCard={{value: nameOnCard, set: setNameOnCard}}
      expYear={{value: expYear, set: setExpYear}}
      expMonth={{value: expMonth, set: setExpMonth}}
      cvc={{value: cvc, set: setCvc}}
      phoneNumber={{value: phoneNumber, set: setPhoneNumber}}
      creditCardInfo={{value: creditCardInfo, set: setCreditCardInfo}}
      creditCardId = {{value: creditCardId, set: setCreditCardId}}
      city = {{value: city, set: setCity}}
      zipCode = {{value: zipCode, set: setZipCode}}
      onBack={() => setCurrentStep(1)}
      onDone={() => submitForm()}
    />,
  ];
  return <div>{steps[currentStep]}</div>;
}

export default Sell;
